package tp4_2019;

public class MainPoste {

	public static void main(String[] args) {
		ObjetPostal o1 = new ObjetPostal();
		ObjetPostal o2 = new ObjetPostal("banquise sud",
								"banquise nord", "222876",
								9,0.01,1);
		System.out.println(o1+"\n"+o2);
		
		/* une lettre ordinaire a destination de la famille Kouk, 
		 * 	igloo 2, banquise nord.
		 */
		
		Lettre l1 = new Lettre("banquise sud",
				"famille kouk, igloo 2, banquise nord", "222876",
				9,0.01,1,false);		
		
		/* une lettre urgente a destination du chaman Sbouk, 
		 * igloo 8, banquise nord.
		*/
		
		Lettre l2 = new Lettre("banquise sud",
				"chaman Sbouk, igloo 8, banquise nord", "222876",
				9,0.01,1,true);
		
		// affichage des lettres
		System.out.println(l1+"\n"+l2);
		
		/*
		 * un colis a destination de la famille Miko, igloo 2, 
		 * banquise ouest
		 */
		
		Colis c1 = new Colis("banquise sud",
				"famille Miko, igloo 2, banquise ouest", "222876",
				9,0.01,1,"peau d'ours",123);
	
		
		/*
		 * un colis a destination du sculpteur Frodok, hangar 200, 
		 * terres ouest
		 *  
		 */
		
		Colis c2 = new Colis("banquise sud",
				"sculpteur Frodok, hangar 200, terres ouest", "222876",
				9,1,1,"traineau",667);

		System.out.println(c1+"\n"+c2);

		System.out.println(l1.tarifAffranchissement());
		System.out.println(l2.tarifAffranchissement());
		System.out.println(c1.tarifAffranchissement());
		System.out.println(c2.tarifAffranchissement());
	}

	
}
