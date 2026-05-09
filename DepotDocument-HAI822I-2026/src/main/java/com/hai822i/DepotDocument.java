package com.hai822i;

import java.util.List;
import java.util.Map;

public interface DepotDocument {
    void ajouterDocument(Document doc) throws DocumentDejaExistantException;
    Document rechercherParReference(String ref) throws DocumentIntrouvaleException;
    void retirerDocument(String ref)throws DocumentIntrouvaleException;
    List<Document> listerTous();
    Map<String, Long> compterParTheme();
    int getTaille();




}
