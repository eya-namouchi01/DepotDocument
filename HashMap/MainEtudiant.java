package etudiantMaps;

public class MainEtudiant {

	public static void main(String[] args) {
		/*Etudiant(String num, String nom, int anneeNaiss, CodePays codePays,
			        double note1, double note2, double note3)*/
		Etudiant e1 = new Etudiant("2021_1","Marie", 1998, CodePays.etuFrançais,
			        18, 18, 18);
		System.out.println(e1);
		
		Etudiant e2 = new Etudiant("2021_2","Jeanne", 1998, CodePays.etuFrançais,
		        14, 9, 16);
		System.out.println(e2);
		
		Etudiant e3 = new Etudiant("2021_3","Sylvie", 1998, CodePays.etuFrançais,
		        7, 9, 10);
		System.out.println(e3);
		
		Etudiant e4 = new Etudiant("2021_4","Esther", 1998, CodePays.etuFrançais,
		        7, 9, 5);
		System.out.println(e4);
		
		Etudiant e5 = new Etudiant("2021_5","Astrid", 1998, CodePays.etuFrançais,
		        10, 10, 0);
		System.out.println(e5);	
		
		Etudiant e6 = new Etudiant("2021_6","Mohamed", 1998, CodePays.etuEtrangerNonFrancophone,
		        18, 18, 18);
		System.out.println(e5);	
		
		Etudiant e7 = new Etudiant("2021_7","Bjorg", 1998, CodePays.etuEtrangerNonFrancophone,
		        18, 18, 18);
		System.out.println(e5);	
		
		// ici il faudra remplacer Promotion par PromotionMap
		
		Promotion p = new Promotion("groupe B FDS",2021);
		
		System.out.println("\n---PROMO VIDE----\n"+p.moyenneGénérale());
		System.out.println("\n---PROMO VIDE----\n"+p.recherche("Astrid"));
		System.out.println("\n---PROMO VIDE----\n"+p.recherche("astrid"));
		System.out.println("\n---PROMO VIDE Admis----\n"+p.admis());
		System.out.println("\n---FIN PROMO VIDE----\n");
		
		// tester le cas général
		p.inscrire(e1);
		p.inscrire(e2);
		p.inscrire(e2);// ne sera pas réinscrit (pas de doublon)
		p.inscrire(e3);
		p.inscrire(e4);
		p.inscrire(e5);
		p.inscrire(e6);
		p.inscrire(e7);

		
		System.out.println("\n-------\n"+p.moyenneGénérale());	
		System.out.println("\n-------\n"+p.recherche("Astrid"));
		System.out.println("\n-------\n"+p.recherche("astrid"));
		System.out.println("\n---Admis----\n"+p.admis());	
		System.out.println("\n---Etu Et. non francophone ----\n"
							+p.etuEtrangerNonFranco());		
		System.out.println("\n---Majors ----\n"
				+p.majors());	
		
		System.out.println("\n---Majors v1 ----\n"
				+p.majorsv1());		
				
	}
	


}
