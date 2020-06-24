package it.polito.tdp.gestionale.model;

public class StampaCorsoStudenti implements Comparable<StampaCorsoStudenti>{
	
	private int numeroCorsiIscritti;
	private int numeroStudenti;
	/**
	 * @param numeroCorsiIscritti
	 * @param numeroStudenti
	 */
	public StampaCorsoStudenti(int numeroCorsiIscritti, int numeroStudenti) {
		super();
		this.numeroCorsiIscritti = numeroCorsiIscritti;
		this.numeroStudenti = numeroStudenti;
	}
	/**
	 * @return the numeroCorsiIscritti
	 */
	public int getNumeroCorsiIscritti() {
		return numeroCorsiIscritti;
	}
	/**
	 * @param numeroCorsiIscritti the numeroCorsiIscritti to set
	 */
	public void setNumeroCorsiIscritti(int numeroCorsiIscritti) {
		this.numeroCorsiIscritti = numeroCorsiIscritti;
	}
	/**
	 * @return the numeroStudenti
	 */
	public int getNumeroStudenti() {
		return numeroStudenti;
	}
	/**
	 * @param numeroStudenti the numeroStudenti to set
	 */
	public void setNumeroStudenti(int numeroStudenti) {
		this.numeroStudenti = numeroStudenti;
	}
	@Override
	public String toString() {
		return "numeroCorsiIscritti=" + numeroCorsiIscritti + ", numeroStudenti=" + numeroStudenti
				;
	}
	@Override
	public int compareTo(StampaCorsoStudenti o) {
		// TODO Auto-generated method stub
		return this.numeroCorsiIscritti-o.getNumeroCorsiIscritti();
	}
	
	

}
