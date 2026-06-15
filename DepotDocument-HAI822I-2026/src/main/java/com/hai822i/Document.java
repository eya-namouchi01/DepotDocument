package com.hai822i;

import lombok.Getter;

import java.time.LocalDate;

public class Document {
    @Getter
    private final String reference;
    @Getter
    private String titre;
    @Getter
    private String auteur;
    @Getter
    private long tailleFichierOctects;
    @Getter
    private String theme;
    @Getter
    private String format;
    @Getter
    private LocalDate dateAjout;
    @Getter
    private String contenu;

    public Document(String reference, String titre, long tailleFichierOctects, String auteur,
                    String theme, String format, LocalDate dateAjout, String contenu) {
        this.reference = reference;
        this.titre = titre;
        this.tailleFichierOctects = tailleFichierOctects;
        this.auteur = auteur;
        this.theme = theme;
        this.format= format;
        this.dateAjout = dateAjout;
        this.contenu = contenu;
    }


    @Override
    public String toString() {
        return "Document{" +
                "reference='" + reference + '\'' +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", tailleFichierOctects=" + tailleFichierOctects +
                ", theme='" + theme + '\'' +
                ", format='" + format + '\'' +
                ", dateAjout=" + dateAjout +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}
