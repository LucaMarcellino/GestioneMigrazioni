package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Simulatore {
	//modello -->stato del sitema da cui tenere traccia ad ogni passo
	private Graph<Country,DefaultEdge> grafo;
	
	//tipi di evento-->coda prioritaria
	private PriorityQueue<Evento> queque;
	
	//parametri simulazione
	private int N_MIGRANTI=1000;
	private Country partenza;
	
	//valori che fornisce al modello 
	private int T=-1;
	private Map<Country,Integer> stanziali;
	
	
	public void init(Country partenza,Graph<Country,DefaultEdge> grafo) {
		this.partenza=partenza;
		this.grafo=grafo;
		
		//impostazione dello stato iniziale
		this.T=1;
		this.stanziali= new HashMap<>();
		for(Country c: this.grafo.vertexSet()) {
			stanziali.put(c, 0);
		}
		this.queque=new PriorityQueue<>();
		//primo evento 
		this.queque.add(new Evento(T,partenza,N_MIGRANTI));
		
	}
	
	public void run() {
		//finchè è presente un evento nella coda proseguo
		Evento e;
		while((e=this.queque.poll()) != null) {
			//Estratto evento ed ora eseguo evento 
			this.T=e.getT();
			int nPersone = e.getN();
			Country stato=e.getStato();
			//cerco i vicini di stato
			List<Country> vicini= Graphs.neighborListOf(this.grafo,stato);
			
			
			int migranti= (nPersone/2) /vicini.size();
			
			if(migranti>0) {
				//persone si possono muovere
				for(Country c : vicini) {
					queque.add(new Evento(e.getT()+1,c,migranti));
				}
			}
			
			int stanziali = nPersone- migranti*vicini.size();
			this.stanziali.put(stato, this.stanziali.get(stato)+stanziali);
		}
	}
	
	public Map<Country,Integer> getStanziali(){
		return this.stanziali;
	}
	
	public int getT() {
		return this.T;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
