package com.hai822i;

import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.time.Instant;
import java.util.*;


public class Complexite {
	
	//Fonctions pour le chronometre
	static long chrono = 0 ;

	//Lancement du chrono
	static void Go_Chrono() {
	//chrono = java.lang.System.currentTimeMillis() ;
	chrono = System.nanoTime();
	}

	//Arret du chrono
	static long Stop_Chrono() {
	//long chrono2 = java.lang.System.currentTimeMillis() ;
	long chrono2 =System.nanoTime();
	long temps = chrono2 - chrono ;
	//System.out.println("Temps ecoule = " + temps + " ms") ;
	return temps;
	} 
	
	//http://rosettacode.org/wiki/Power_set
	public static <T> List<List<T>> powerset(Collection<T> list) {
		  List<List<T>> ps = new ArrayList<List<T>>();
		  ps.add(new ArrayList<T>());   // add the empty set
		  System.out.println(ps);		 
		  // for every item in the original list
		  for (T item : list) {
		    List<List<T>> newPs = new ArrayList<List<T>>();
		 
		    for (List<T> subset : ps) {
		      // copy all of the current powerset's subsets
		      newPs.add(subset);
		 
		      // plus the subsets appended with the current item
		      List<T> newSubset = new ArrayList<T>(subset);
		      newSubset.add(item);
		      newPs.add(newSubset);
		    }
		 
		    // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
		      ps = newPs;
			  System.out.println(ps);
		  }
		  return ps;
		}
	
	public static<T> boolean cherche(List<T> l, T t) {
		for (T elem:l)
			if (elem.equals(t)) return true;
		return false;
	}

public static void main(String[] args) throws InterruptedException {
	List<String> l = List.of("a","b","c");
	powerset(l);	
	/*
	List<String> l1,l2;
	l1=List.of("a","b","c","d","e","f");
	//System.out.println(l1);
	long moyLp0=0;
	for(int i=0; i<100000;i++)
	{	
		Go_Chrono();
		powerset(l1);
		moyLp0+=Stop_Chrono();
	}
	System.out.println(moyLp0/100000);
	//Go_Chrono();
	//System.out.println(powerset(l1));
	//powerset(l1);
	//System.out.println(Stop_Chrono());
	l2=List.of("a","b","c","d","e","f","g",
			"h","i","j","k","l","m","n","o","p");
	//System.out.println(l2);
	//Go_Chrono();
	//System.out.println(powerset(l2));
	//powerset(l2);
	//System.out.println(Stop_Chrono());
	long moyLp1=0;
	for(int i=0; i<100;i++)
	{	
		Go_Chrono();
		//powerset(l2);
		moyLp1+=Stop_Chrono();
	}
	System.out.println(moyLp1/100);

	long moyL2=0;
	for(int i=0; i<10000000;i++)
	{	
		Go_Chrono();
		cherche(l2,"f");
		Stop_Chrono();
		moyL2+=Stop_Chrono();
	}
	System.out.println("l2 "+moyL2/10000000);
	
	long moyL3=0;
	for(int i=0; i<10000000;i++)
	{	
		Go_Chrono();
		cherche(l2,"f");
		Stop_Chrono();
		moyL3+=Stop_Chrono();
	}
	System.out.println("l2 "+moyL3/10000000);
	
	long moyL1=0;
	for(int i=0; i<10000000;i++)
	{	
		Instant start = Instant.now();
		cherche(l1,"f");
		Instant end = Instant.now();
		moyL1+=Duration.between(start, end).getNano();
	}
	System.out.println("l1 "+moyL1/10000000);
	
	long moyL4=0;
	for(int i=0; i<10000000;i++)
	{	
		Instant start = Instant.now();
		cherche(l2,"f");
		Instant end = Instant.now();
		moyL4+=Duration.between(start, end).getNano();
	}
	System.out.println("l2 "+moyL4/10000000);
		
	long moyLpi=0;
	for(int i=0; i<100;i++)
	{	
		Instant start = Instant.now();
		//powerset(l2);
		Instant end = Instant.now();
		moyLpi+=Duration.between(start, end).getNano();
	}
	System.out.println(moyLpi/100);
	*/
}
}
