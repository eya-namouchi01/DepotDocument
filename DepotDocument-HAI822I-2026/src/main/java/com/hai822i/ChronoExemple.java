package com.hai822i;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChronoExemple {
	
	public static void afficheNombresAleatoires() {
		double i = Math.random();
	}

	public static void main(String[] arg) {
	
	Chrono chrono=new Chrono("mon chrono");

	for(int essai=0;essai<1000000;essai++)

	{

	chrono.start("affiche nombres aleatoires");

	afficheNombresAleatoires();

	chrono.stop("affiche nombres aleatoires");

	}

	System.out.println("chrono nanoTime() et currentTimeMillis()");
	
	System.out.println("affiche nombres aleatoires ="+chrono.getResult("affiche nombres aleatoires")/1000000.0+" ms");

	System.out.println("total ="+chrono.getResult()/1000000.0+" ms");



	ChronoCPU chronoCPU=new ChronoCPU("mon chrono");

	for(int essai=0;essai<1000000;essai++)

	{

		chronoCPU.start("affiche nombres aleatoires");

		afficheNombresAleatoires();

		chronoCPU.stop("affiche nombres aleatoires");

	}

	System.out.println("chrono CPU");

	System.out.println("affiche nombres aleatoires ="+chronoCPU.getResult("affiche nombres aleatoires")/1000000.0+" ms");


	System.out.println("total ="+chronoCPU.getResult()/1000000.0+" ms");

	ChronoCPU chronoCPUuser=new ChronoCPU("mon chrono");

	for(int essai=0;essai<1000000;essai++)

	{
	
		chronoCPUuser.startUser("affiche nombres aleatoires");

		afficheNombresAleatoires();

		chronoCPUuser.stopUser("affiche nombres aleatoires");
	
	}
	System.out.println("chrono user");

	System.out.println("affiche nombres aleatoires ="+chronoCPUuser.getResult("affiche nombres aleatoires")/1000000.0+" ms");

	System.out.println("total ="+chronoCPUuser.getResult()/1000000.0+" ms");
	
	ChronoInstant chronoInstant=new ChronoInstant("mon chrono");

	for(int essai=0;essai<1000000;essai++)

	{

		chronoInstant.startInstant("affiche nombres aleatoires");

		afficheNombresAleatoires();

		chronoInstant.stopInstant("affiche nombres aleatoires");

	}
	
	System.out.println("chrono Instant");

	System.out.println("affiche nombres aleatoires ="+chronoInstant.getResult("affiche nombres aleatoires")/1000000.0+" ms");
	
	System.out.println("total ="+chronoInstant.getResult()/1000000.0+" ms");

	}
}
