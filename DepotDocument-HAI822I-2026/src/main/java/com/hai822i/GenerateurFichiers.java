package com.hai822i;

import java.io.File;

/*public class GenerateurFichiers {
    public static void generer(String dossier, int nombre) throws Exception {
        File dir = new File(dossier);
        dir.mkdirs();

        String[] themes  = {"science", "droit", "histoire", "informatique", "medecine"};
        String[] auteurs = {"Alice", "Bob", "Charlie", "Diana", "Eve"};

        for (int i = 0; i < nombre; i++) {
            File fichier = new File(dossier + "/doc_" + i + ".txt");
            try (java.io.PrintWriter pw = new java.io.PrintWriter(fichier)) {
                pw.println("Titre: Document " + i);
                pw.println("Auteur: " + auteurs[i % auteurs.length]);
                pw.println("Theme: " + themes[i % themes.length]);
                pw.println("Contenu: " + "Lorem ipsum dolor sit amet. ".repeat(20));
            }
        }
        System.out.println(nombre + " fichiers générés dans : " + dossier);
    }
}*/
public class GenerateurFichiers {

    private static final String[] THEMES  = {"science", "droit", "histoire", "informatique", "medecine"};
    private static final String[] AUTEURS = {"Alice", "Bob", "Charlie", "Diana", "Eve"};

    public static void generer(String dossierRacine, int nombre, int offset) throws Exception {
        File dir = new File(dossierRacine);
        dir.mkdirs();

        for (int i = 0; i < nombre; i++) {
            int index  = offset + i;
            String theme = THEMES[index % THEMES.length];  // ← distribution circulaire
            String auteur = AUTEURS[index % AUTEURS.length];

            // Créer le sous-dossier du thème si nécessaire
            File sousDir = new File(dossierRacine + "/" + theme);
            sousDir.mkdirs();

            File fichier = new File(sousDir, "doc_" + index + ".txt");
            try (java.io.PrintWriter pw = new java.io.PrintWriter(fichier)) {
                pw.println("Titre: Document " + index);
                pw.println("Auteur: " + auteur);
                pw.println("Contenu: " + "Lorem ipsum dolor sit amet. ".repeat(20));
            }
        }
        System.out.println(nombre + " fichiers générés dans : " + dossierRacine);
    }

    public static void generer(String dossierRacine, int nombre) throws Exception {
        generer(dossierRacine, nombre, 0);
    }
}