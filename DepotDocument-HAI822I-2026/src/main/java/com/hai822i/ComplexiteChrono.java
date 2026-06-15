package com.hai822i;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ComplexiteChrono {
	
	//http://rosettacode.org/wiki/Power_set
	public static <T> List<List<T>> powerset(Collection<T> list) {
		  List<List<T>> ps = new ArrayList<List<T>>();
		  ps.add(new ArrayList<T>());   // add the empty set
		  //System.out.println(ps);		 
		  // for every item in the original list
		  for (T item : list) {
		    List<List<T>> newPs = new ArrayList<List<T>>();
		 
		    for (List<T> subset : ps) {
		      // copy the current subset
		      newPs.add(subset);
		 
		      // plus the subset appended with the current item
		      List<T> newSubset = new ArrayList<T>(subset);
		      newSubset.add(item);
		      newPs.add(newSubset);
		    }
		 
		    // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
		      ps = newPs;
			  //System.out.println(ps);
		  }
		  return ps;
		}
	
	public static<T> boolean cherche(List<T> l, T t) {
		for (T elem:l)
			if (elem.equals(t)) return true;
		return false;
	}


	public static void main(String[] arg) {
		
		List<String> l1,l2;
		l1=List.of("a","b","c","d","e","f");
		l2=List.of("a","b","c","d","e","f","g",
				"h","i","j","k","l","m","n","o","p");
	
	Chrono chrono=new Chrono("mon chrono");

	for(int essai=0;essai<1500;essai++)

	{

	chrono.start("powerset l1");

	powerset(l1);

	chrono.stop("powerset l1");

	chrono.start("powerset l2");

	powerset(l2);

	chrono.stop("powerset l2");

	}

	System.out.println("powerset l1 ="+chrono.getResult("powerset l1")/1500.0+" ms");

	System.out.println("powerset l2 ="+chrono.getResult("powerset l2")/1500.0+" ms");

	System.out.println("total ="+chrono.getResult()/1500+" ms");


	ChronoCPU chronoCPU=new ChronoCPU("mon chrono");

	for(int essai=0;essai<1500;essai++)

	{

		chronoCPU.start("powerset l1");

	powerset(l1);

	chronoCPU.stop("powerset l1");

	chronoCPU.start("powerset l2");

	powerset(l2);

	chronoCPU.stop("powerset l2");

	}

	System.out.println("powerset l1 ="+chronoCPU.getResult("powerset l1")/1500.0+" ms");

	System.out.println("powerset l2 ="+chronoCPU.getResult("powerset l2")/1500.0+" ms");

	System.out.println("total ="+chronoCPU.getResult()/1500+" ms");

	ChronoCPU chronoCPUuser=new ChronoCPU("mon chrono");

	for(int essai=0;essai<1500;essai++)

	{

	chronoCPUuser.startUser("powerset l1");

	powerset(l1);

	chronoCPUuser.stopUser("powerset l1");

	chronoCPUuser.startUser("powerset l2");

	powerset(l2);

	chronoCPUuser.stopUser("powerset l2");

	}

	System.out.println("powerset l1 ="+chronoCPUuser.getResult("powerset l1")/1500.0+" ms");

	System.out.println("powerset l2 ="+chronoCPUuser.getResult("powerset l2")/1500.0+" ms");

	System.out.println("total ="+chronoCPUuser.getResult()/1500+" ms");

	
	ChronoInstant chronoInstant=new ChronoInstant("mon chrono");

	for(int essai=0;essai<1500;essai++)

	{

		chronoInstant.startInstant("powerset l1");

		powerset(l1);

		chronoInstant.stopInstant("powerset l1");

		chronoInstant.startInstant("powerset l2");

		powerset(l2);

		chronoInstant.stopInstant("powerset l2");

	}

	System.out.println("powerset l1 ="+chronoInstant.getResult("powerset l1")/1500.0+" ms");

	System.out.println("powerset l2 ="+chronoInstant.getResult("powerset l2")/1500.0+" ms");

	System.out.println("total ="+chronoInstant.getResult()/1500+" ms");

	}
}
