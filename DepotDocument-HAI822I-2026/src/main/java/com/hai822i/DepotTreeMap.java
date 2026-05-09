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
        if (listeDocument.containsValue(doc))
            throw new DocumentDejaExistantException("Référence déjà existante : " + doc.getReference());
        listeDocument.put(doc.getReference(), doc);
        taille++;
    }


    public Document rechercherParReference(String ref) throws DocumentIntrouvaleException{
        if(listeDocument.containsKey(ref)) return listeDocument.get(ref);
        else throw new DocumentIntrouvaleException("Document introuvable : " + ref);
    }

    public void retirerDocument(String reference) throws DocumentIntrouvaleException {
        if (!listeDocument.containsKey(reference))
            throw new DocumentIntrouvaleException("Document introuvable : " + reference);
        listeDocument.remove(reference);
        taille--;
    }

    public void retirerDocument(Document doc){
        listeDocument.remove(doc.getReference());
    }

    public List<Document> listerTous(){
        List<Document> liste= new ArrayList<>();
        for(Document doc: listeDocument.values()){
            liste.add(doc);
        }
        return liste;
    }

    public List<Document> listerTousV2() {
        return new ArrayList<>(listeDocument.values());
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
