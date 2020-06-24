package it.polito.tdp.gestionale.model;

public class Arco {
	
	private Studente s;
	private Corso c;
	/**
	 * @param s
	 * @param c
	 */
	public Arco(Studente s, Corso c) {
		super();
		this.s = s;
		this.c = c;
	}
	/**
	 * @return the s
	 */
	public Studente getS() {
		return s;
	}
	/**
	 * @param s the s to set
	 */
	public void setS(Studente s) {
		this.s = s;
	}
	/**
	 * @return the c
	 */
	public Corso getC() {
		return c;
	}
	/**
	 * @param c the c to set
	 */
	public void setC(Corso c) {
		this.c = c;
	}
	
	

}
