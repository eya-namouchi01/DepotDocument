package com.hai822i;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepotArrayList extends AbstractDepot{
    private ArrayList<Document> listeDocument;

    public DepotArrayList(){
        listeDocument= new ArrayList<>();
    }

    public void ajouterDocument(Document doc) throws DocumentDejaExistantException{
        verifierNonNull(doc);
        int tailleCourrante= taille;
        if (listeDocument.contains(doc))
            throw new DocumentDejaExistantException("Référence déjà existante : " + doc.getReference());
        listeDocument.add(doc);
        assert getTaille()== tailleCourrante++;
    }

    public Document rechercherParReference(String ref) throws DocumentIntrouvaleException{
        return
        listeDocument.stream().filter(d->d.getReference().equals(ref))
                .findFirst().orElseThrow(()->new DocumentIntrouvaleException("Document introuvable : " + ref));
    }

    public void retirerDocument(String ref) throws DocumentIntrouvaleException{
        int tailleCourrante= taille;
        Document doc = rechercherParReference(ref);
        listeDocument.remove(doc);
        assert getTaille()== tailleCourrante--;

    }
    public void retirerDocument(Document doc){
        int tailleCourrante= taille;
        listeDocument.remove(doc);
        assert getTaille()== tailleCourrante--;

    }

    public List<Document> listerTous(){
        List<Document> liste= new ArrayList<>();
        for(Document doc: listeDocument){
            liste.add(doc);
        }
        return liste;
    }

    public List<Document> listerTousV2() {
        return new ArrayList<>(listeDocument);
    }

    public Map<String, Long> compterParTheme(){
        return listeDocument.stream()
                .collect(Collectors.groupingBy(
                        Document::getTheme,Collectors.counting()));
    }

}

