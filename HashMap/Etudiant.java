package etudiantMaps;

import java.time.LocalDate;
import java.time.Period;

public class Etudiant {
	
	private final String numEtu;
	
	private final String nom; 
	
	private final int anneeNaiss;
	
	private CodePays codePays;
	
	private double note1, note2, note3;

	
	public Etudiant() 
		{this.numEtu = "inconnu"; this.nom = "inconnu"; this.anneeNaiss=0;}
	
	public Etudiant(String numEtu, String nom, int anneeNaiss, CodePays codePays,
			        double note1, double note2, double note3) {
		this.numEtu = numEtu;
		this.nom = nom;
		this.anneeNaiss = anneeNaiss; 
		this.setCodePays(codePays);
		this.setNote1(note1);
		this.setNote2(note2);
		this.setNote3(note3);
	}
	
	public String getNumEtu() {
		return this.numEtu;
	}
	
	public String getNom() {
		return this.nom;
	}
		
	public int getAge() {
		LocalDate today =  LocalDate.now();
		return today.getYear() - this.anneeNaiss;
	}
	
	public int getAnneeNaiss() {
		return this.anneeNaiss;
	}
	

	
	public double getNote1() {
		return this.note1;
	}
	
	public void setNote1(double nouvelleNote) {
		// on suppose des notes entre 0 et 20
		if (nouvelleNote >=0 && nouvelleNote <=20)
			this.note1 = nouvelleNote;
		else
			System.out.println("pb note hors de l'intervalle [0,20]");
	}
	
	public double getNote2() {
		return this.note2;
	}
	
	public void setNote2(double nouvelleNote) {
		// on suppose des notes entre 0 et 20
		if (nouvelleNote >=0 && nouvelleNote <=20)
			this.note2 = nouvelleNote;
		else
			System.out.println("pb note hors de l'intervalle [0,20]");
	}
	
	public double getNote3() {
		return this.note3;
	}
	
	public void setNote3(double nouvelleNote) {
		// on suppose des notes entre 0 et 20
		if (nouvelleNote >=0 && nouvelleNote <=20)
			this.note3 = nouvelleNote;
		else
			System.out.println("pb note hors de l'intervalle [0,20]");
	}
	

	
	public CodePays getCodePays() {
		return this.codePays;
	}
	
	public void setCodePays(CodePays nouveauCode) {
		// rien à vérifier car l'énumération limite
		// naturellement les valeurs à un 
		// ensemble de valeurs cohérentes
		this.codePays=nouveauCode;
	}
	
	
	public double moyenne() {
		return (this.note1+this.note2+this.note3)/3;
	}
	
	/*
	 * 
	Rattrapage : moyenne entre 0 et <10
	Passable : de 10 à <12
	Assez bien : de 12 à <14
	Bien : de 14 à <16
	Tresbien : de 16 à <18
	Excellent : de 18 à 20
	 */
	public Mention mention() {
		// Hyp = moyenne retourne une valeur entre 0 et 20
		double moy = this.moyenne();
		Mention mention = null;
		if (moy >= 0 && moy <10)
			mention = Mention.rattrapage;
		else // moy < 0 impossible ou moy >=10
			if (moy < 12)
				mention = Mention.passable;
			else //moy >=12
				if (moy < 14)
					mention = Mention.assezBien;
				else // moy >=14
					if (moy < 16)
						mention = Mention.bien;
					else // moy >=16
						if (moy < 18)
							mention = Mention.tresBien;
						else // moy >=18
							mention = Mention.excellent;
		return mention;
	}
	
	public String ligneResultats() {
		/*
		 * retourne nom, moyenne, mention 
		 * et si rattrapage, les modules obtenus 
		 * (module1, module2 ou module3)
		 */
		double moy = this.moyenne();
		String res = "Resultats de "+this.getNom()+
				" moyenne = "+moy+
				" mention = "+this.mention();
		if (moy < 10 ) // rattrapage
		{
			res = res + "\n modules obtenus : [ ";
			if (note1 >= 10)
				res = res + " module 1 ";
			if (note2 >= 10)
				res = res + " module 2 ";
			if (note3 >= 10)
				res = res + " module 3 ";
			res = res + " ]";
		}		
		return res;
	}
	
	public String toString() {
		return "Etudiant "+this.getNumEtu()+
				"\n nom"+this.getNom()+
				"\n age "+this.getAge()+
				"\n code pays "+this.getCodePays()+
				"\n "+this.ligneResultats()+"\n";
	}
}
