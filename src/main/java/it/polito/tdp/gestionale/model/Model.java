package it.polito.tdp.gestionale.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.gestionale.db.DidatticaDAO;

public class Model {
	
	private Graph<Nodo,DefaultEdge> graph;
	private DidatticaDAO dao;
	private Map<String,Corso> corsi;
	private Map<Integer,Studente> studenti;
	private List<Corso> best;
	private int bestPeso;
	private List<Studente> studentiIscrittiAlmenoUnCorso;
	
	public Model() {
		this.dao = new DidatticaDAO();
		this.corsi = new HashMap<>();
		this.studenti = new HashMap<>();
		this.studentiIscrittiAlmenoUnCorso = new ArrayList<>();
	}
	
	public void createGraph() {
		this.graph = new SimpleGraph<>(DefaultEdge.class);
		this.dao.loadAllCorsi(corsi);
		this.dao.loadAllStudenti(studenti);
		Graphs.addAllVertices(this.graph,this.corsi.values());
		Graphs.addAllVertices(this.graph, this.studenti.values());
		List<Arco> archi = this.dao.getArchi(corsi,studenti);
		for(Arco a : archi) {
			if(this.graph.getEdge(a.getS(), a.getC()) == null) {
				Graphs.addEdgeWithVertices(this.graph, a.getS(), a.getC());
			}
		}
		
	}

	public Integer nVertici() {
		// TODO Auto-generated method stub
		return this.graph.vertexSet().size();
	}

	public Integer nArchi() {
		// TODO Auto-generated method stub
		return this.graph.edgeSet().size();
	}
	
	public List<StampaCorsoStudenti> getStudentiCorsi(){
		List<StampaCorsoStudenti> lista = new ArrayList<>();
		int massimoCorsiIscritti = calcolaMassimoCorsiIscritti();
		for(int i = 1; i<=massimoCorsiIscritti;i++) {
			int count = 0;
			for(Nodo n : this.graph.vertexSet()) {
				if(n instanceof Studente) {
					if(this.graph.outDegreeOf(n)==i) {
						count++;
					}
				}
			}
			lista.add(new StampaCorsoStudenti(i,count));
		}
		return lista;
	}

	private int calcolaMassimoCorsiIscritti() {
		int num = 0;
		for(Nodo n : this.graph.vertexSet()) {
			if(n instanceof Studente) {
				if(this.graph.outDegreeOf(n)>num) {
					num = this.graph.outDegreeOf(n);
				}
			}
		}
		return num;
	}

	public void cercaPercorso() {
		List<Corso> parziale = new ArrayList<>();
		this.best = new ArrayList<>();
		this.bestPeso = Integer.MAX_VALUE;
		this.studentiIscrittiAlmenoUnCorso = this.dao.getStudentiIscrittiAlmenoUnCorso(studenti);
		cerca(parziale);
		
	}

	private void cerca(List<Corso> parziale) {
		if(parziale.size()<this.bestPeso && contieneTuttiStudenti(parziale)) {
			this.bestPeso = parziale.size();
			this.best = new ArrayList<>(parziale);			
			return;
		}
		for(Nodo n : this.graph.vertexSet()) {
			if(n instanceof Corso) {
				if(!parziale.contains(n)) {
					parziale.add((Corso) n);
					cerca(parziale);
					parziale.remove(parziale.size()-1);
				}
			}
		}
		return;
		
		
	}

	private boolean contieneTuttiStudenti(List<Corso> parziale) {
		List<Studente> presenti = new ArrayList<>();
		int count = 0;
		for(Corso c : parziale) {
			for(Nodo n : Graphs.neighborListOf(this.graph,c)) {
				if(n instanceof Studente) {
					if(!presenti.contains(n)) {
						count++;
					}
				}
			}
		}
		if(this.studentiIscrittiAlmenoUnCorso.size()==count) {
			return true;
		}
		return false;
	}

	public List<Corso> getCammino() {
		// TODO Auto-generated method stub
		return this.best;
	}
}
