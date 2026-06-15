package com.hai822i;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ThemeDetecteurOllama {

    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";
    private static final String MODELE = "llama3.2:1b";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public String detecterTheme(String titre, String contenu) {
        String extrait = contenu.substring(0, Math.min(500, contenu.length()));
        String prompt = """
            Voici le titre et un extrait d'un document :
            Titre : %s
            Extrait : %s
            
            Réponds UNIQUEMENT par un seul mot ou courte expression représentant 
            le thème principal (ex: Informatique, Médecine, Droit, Histoire, Économie).
            Pas d'explication, juste le thème.
            """.formatted(titre, extrait);

        String body = """
            {
                "model": "%s",
                "prompt": "%s",
                "stream": false
            }
            """.formatted(MODELE, prompt.replace("\"", "\\\"").replace("\n", "\\n"));

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(OLLAMA_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            // ← AJOUTE CES LIGNES ICI
            System.out.println("=== Réponse brute Ollama ===");
            System.out.println(response.body());
            System.out.println("===========================");

            JsonNode json = mapper.readTree(response.body());
            JsonNode responseNode = json.get("response");
            if (responseNode == null) {
                JsonNode messageNode = json.get("message");
                if (messageNode != null) {
                    return messageNode.get("content").asText().trim();
                }
                return "Thème non détecté";
            }
            return responseNode.asText().trim();

        } catch (Exception e) {
            System.out.println("Ollama non disponible : " + e.getMessage());
            return "Thème non détecté";
        }
    }}