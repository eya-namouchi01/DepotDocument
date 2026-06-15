package com.hai822i;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ExtracteurDocument {

    public Document extraire(File file) throws ExtractionEchoueeException {
        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        ContentHandler handler = new BodyContentHandler();

        try (InputStream stream = new FileInputStream(file)) {
            parser.parse(stream, handler, metadata, new ParseContext());
        } catch (Exception e) {
            throw new ExtractionEchoueeException("Échec : " + file.getName());
        }

        String titre  = metadata.get(TikaCoreProperties.TITLE);
        String auteur = metadata.get(TikaCoreProperties.CREATOR);
        String format = metadata.get(Metadata.CONTENT_TYPE);
        String contenu = handler.toString();

        // Thème = nom du dossier parent
        String theme = file.getParentFile().getName();

        // Auteur depuis le contenu si null
        if (auteur == null || auteur.isBlank()) {
            auteur = Arrays.stream(contenu.split("\n"))
                    .filter(l -> l.startsWith("Auteur:"))
                    .map(l -> l.replace("Auteur:", "").trim())
                    .findFirst()
                    .orElse("Inconnu");
        }

        return new Document(
                UUID.randomUUID().toString(),
                titre  != null ? titre  : file.getName(),
                file.length(),
                auteur,
                theme,
                format  != null ? format  : "Inconnu",
                LocalDate.now(),
                contenu
        );
    }

    // Navigation récursive dans les sous-dossiers
    public List<Document> extraireTous(List<File> fichiers) throws ExtractionEchoueeException {
        List<Document> documents = new ArrayList<>();
        for (File f : fichiers) {
            if (f.isDirectory()) {
                // Naviguer dans le sous-dossier récursivement
                documents.addAll(extraireTous(Arrays.asList(f.listFiles())));
            } else {
                documents.add(extraire(f));
            }
        }
        return documents;
    }
}