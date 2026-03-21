package tp4_2019;

public class Lettre extends ObjetPostal {
	/*
	 * Attribut
	 */
	
	private boolean urgente; 
	       // alternatives : une chaîne de caractères dont on contrôlera
		   // le contenu
	
	/*
	 * Constructeurs
	 */
	
	public Lettre() {}
	
	public Lettre(String origine, String destination, String codePostal, double poids, double volume,
			double tauxDeRecommandation, boolean urgente) {
		super(origine, destination, codePostal, poids, volume, tauxDeRecommandation);
		this.setUrgente(urgente);
	}

	/*
	 * Accesseurs
	 */

	public boolean isUrgente() {
		return urgente;
	}

	public void setUrgente(boolean urgente) {
		this.urgente = urgente;
	}
	
	/*
	 * Méthode toString
	 */
	
	@Override
	public String toString() {
		return "Lettre [urgente=" + urgente + ", toString()=" + super.toString() + "]";
	}

	/*
	 * Méthode tarifAffranchissement
	 */

	@Override
	public double tarifAffranchissement() {
		double tarifBase = 0.5;
		double tarifAff = super.tarifAffranchissement()+tarifBase;
		if (this.urgente) tarifAff += 0.3;
		return tarifAff;
	}

		
}
