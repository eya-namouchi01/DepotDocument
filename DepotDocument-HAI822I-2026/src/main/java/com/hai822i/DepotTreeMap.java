package com.hai822i;

import java.util.*;
import java.util.stream.Collectors;

public class DepotTreeMap extends AbstractDepot{
    private TreeMap<String, Document> listeDocument;

    public DepotTreeMap(){
        listeDocument= new TreeMap<>();
    }

    public void ajouterDocument(Document doc) throws DocumentDejaExistantException {
        verifierNonNull(doc);
        if (listeDocument.containsKey(doc.getReference()))
            throw new DocumentDejaExistantException("Référence déjà existante : " + doc.getReference());
        listeDocument.put(doc.getReference(), doc);
        taille++;
    }


    public Document rechercherParReference(String ref) throws DocumentIntrouvableException {
        if(listeDocument.containsKey(ref)) return listeDocument.get(ref);
        else throw new DocumentIntrouvableException("Document introuvable : " + ref);
    }

    public void retirerDocument(String reference) throws DocumentIntrouvableException {
        if (!listeDocument.containsKey(reference))
            throw new DocumentIntrouvableException("Document introuvable : " + reference);
        listeDocument.remove(reference);
        taille--;
    }

    public void retirerDocument(Document doc){
        listeDocument.remove(doc.getReference());
    }

    public List<Document> listerTous() {
        return new ArrayList<>(listeDocument.values());
    }

    public List<Document> listerTousV2(){
        List<Document> liste= new ArrayList<>();
        for(Document doc: listeDocument.values()){
            liste.add(doc);
        }
        return liste;
    }

    @Override
    public Map<String, Long> compterParTheme() {
        return listeDocument.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Document::getTheme,
                        Collectors.counting()));
    }


    public Map<String, Long> compterParThemeV2() {
        Map<String, Long> resultat = new HashMap<>();
        for (String k : listeDocument.keySet()) {
            String theme = listeDocument.get(k).getTheme();
            if (!resultat.containsKey(theme)) {
                resultat.put(theme, 1L);
            } else {
                resultat.put(theme, resultat.get(theme) + 1L);
            }
        }
        return resultat;
    }

}
