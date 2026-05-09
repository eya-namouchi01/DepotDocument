package com.hai822i;
import com.drew.metadata.Metadata;
import org.apache.tika.Tika;

import java.io.File;
import java.time.LocalDate;


public class ExtracteurDocument {
    //private final Tika tika= new Tika();

    public static Document depuisFichier(File fichier) throws Exception {
        Tika tika = new Tika();
        Metadata metadata = new Metadata();

        // Extrait automatiquement :
        String auteur    = metadata.get("Author");
        String titre     = metadata.get("title");
        String theme     = metadata.get("subject");
        long taille      = fichier.length();          // octets réels
        String reference    = fichier.getAbsolutePath();
        String contenu   = tika.parseToString(fichier); // texte extrait

        return new Document(reference, titre, auteur, taille,theme,date, contenu);

    }
}
