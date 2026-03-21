package tp4_2019;

public class ObjetPostal {
	
	/*
	 * Attributs
	 */
	
	private String origine = "origine inconnue",
			destination = "destination inconnue",
			codePostal = "code postal" ;
	
	private double poids, // en grammes
				   volume, // en m3
                   tauxDeRecommandation; // egal a 0, 1 ou 2 
										 // pourrait etre un entier
	
	/*
	 * Constructeurs : 
	 * - le constructeur sans paramètres
	 * - un constructeur initialisant la totalité des attributs
	 * - d'autres variantes peuvent être ajoutées
	 */
	
	public ObjetPostal() {
		// il est vide car on a choisi d'initialiser les 
		// attributs lors de leur déclaration
	}
	
	public ObjetPostal(String origine, String destination, String codePostal, double poids, double volume,
			double tauxDeRecommandation) {
		this.setOrigine(origine);
		this.setDestination(destination);
		this.setCodePostal(codePostal);
		this.setPoids(poids);
		this.setVolume(volume);
		this.setTauxDeRecommandation(tauxDeRecommandation);
	}


	/*
	 * 	Accesseurs
	 */
	

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		// on n'accepte que des poids strictement positifs
		if (poids > 0)
			this.poids = poids;
		else
			System.out.println("Un poids doit être strictement positif");
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		// on n'accepte que des volumes strictement positifs
		if (volume > 0)
			this.volume = volume;
		else
			System.out.println("Un volume doit être strictement positif");
	}

	public double getTauxDeRecommandation() {
		return tauxDeRecommandation;
	}

	public void setTauxDeRecommandation(double tauxDeRecommandation) {
		// vérifier 0, 1 ou 2
		if (tauxDeRecommandation==0 || tauxDeRecommandation==1 ||
				tauxDeRecommandation==2)
			this.tauxDeRecommandation = tauxDeRecommandation;
		else
			System.out.println("le taux de recommandation est 0, 1 ou 2");		
	}
	
	/*
	 * méthode toString
	 */

	@Override
	public String toString() {
		return "ObjetPostal [origine=" + origine + ", destination=" + destination + ", codePostal=" + codePostal
				+ ", poids=" + poids + ", volume=" + volume + ", tauxDeRecommandation=" + tauxDeRecommandation + "]";
	}
	
	/*
	 * Méthode tarifAffranchissement
	 */

	public double tarifAffranchissement() {
		// partie valable pour les lettres et les colis
		// 0.5 euro si le taux de recommandation est 1
		//  1.5 euros si le taux de recommandation est 2
		if (this.tauxDeRecommandation==1)
			return 0.5;
		else if (this.tauxDeRecommandation==2)
			return 1.5;
		else return 0;			
	}
}
