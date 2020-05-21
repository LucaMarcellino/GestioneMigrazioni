package it.polito.tdp.borders.model;

public class Evento implements Comparable<Evento> {
	
	private int t;
	private Country stato;//stato in cui arrivano al tempo t
	private int n;//numero emigranti arrivano in stato al tempo t
	public Evento(int t, Country stato, int n) {
		super();
		this.t = t;
		this.stato = stato;
		this.n = n;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public Country getStato() {
		return stato;
	}
	public void setStato(Country stato) {
		this.stato = stato;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	@Override
	public int compareTo(Evento e1) {
		
		return this.t-e1.getT();
	}
	
	

}
