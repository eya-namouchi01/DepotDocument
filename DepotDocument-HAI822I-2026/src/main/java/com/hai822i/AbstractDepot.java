package com.hai822i;

public abstract class AbstractDepot implements DepotDocument{

    protected int taille;

    protected AbstractDepot() { this.taille = 0; }

    public int getTaille(){
        return taille;
    }

    protected void verifierNonNull(Document doc) {
        if (doc == null)
            throw new IllegalArgumentException("Document null");
        if (doc.getReference() == null || doc.getReference().isBlank())
            throw new IllegalArgumentException("Référence vide");
    }
}
