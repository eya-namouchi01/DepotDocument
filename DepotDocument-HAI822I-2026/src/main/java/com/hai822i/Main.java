package com.hai822i;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Chemin vers le dossier de documents : ");
        String chemin = scanner.nextLine();

        ExtracteurDocument extracteur = new ExtracteurDocument();
        List<Document> dataset = extracteur.extraireTous(
                Arrays.asList(new File(chemin).listFiles()));
        System.out.println(dataset.size() + " documents chargés.");

        boolean continuer = true;
        while (continuer) {
            System.out.println("\n========================================");
            System.out.println("0. Générer un dataset de test");
            System.out.println("1. Benchmark Ajout");
            System.out.println("2. Benchmark Retrait");
            System.out.println("3. Benchmark Recherche");
            System.out.println("4. Benchmark Parcours (listerTous)");
            System.out.println("5. Benchmark Statistiques");
            System.out.println("6. Scénario Mixte");
            System.out.println("7. Afficher le dataset");
            System.out.println("8. Quitter");
            System.out.print("Choix : ");

            switch (scanner.nextLine()) {
                case "0" -> {
                    System.out.print("Dossier de destination : ");
                    String dest = scanner.nextLine();
                    System.out.print("Nombre de fichiers     : ");
                    int nb = Integer.parseInt(scanner.nextLine());

                    // Générer avec offset dans un sous-dossier unique horodaté
                    String sousDir = dest + "/genere_" + System.currentTimeMillis();
                    GenerateurFichiers.generer(sousDir, nb, dataset.size());

                    // Extraire uniquement les nouveaux fichiers
                    dataset.addAll(extracteur.extraireTous(
                            Arrays.asList(new File(sousDir).listFiles())));

                    System.out.println(dataset.size() + " documents au total.");
                }

                case "1" -> BenchmarkRunner.benchmarkerAjout(dataset);
                case "2" -> BenchmarkRunner.benchmarkerRetrait(dataset);
                case "3" -> BenchmarkRunner.benchmarkerRecherche(dataset);
                case "4" -> BenchmarkRunner.benchmarkerParcours(dataset);
                case "5" -> BenchmarkRunner.benchmarkerStatistiques(dataset);

                case "6" -> {
                    System.out.println("\n=== Définir le scénario (total = 100) ===");
                    System.out.print("% Ajout        : "); int a  = Integer.parseInt(scanner.nextLine());
                    System.out.print("% Retrait      : "); int r  = Integer.parseInt(scanner.nextLine());
                    System.out.print("% Recherche    : "); int re = Integer.parseInt(scanner.nextLine());
                    System.out.print("% Parcours     : "); int p  = Integer.parseInt(scanner.nextLine());
                    System.out.print("% Statistiques : "); int s  = Integer.parseInt(scanner.nextLine());
                    BenchmarkRunner.benchmarkerScenario(a, r, re, p, s, dataset);
                }

                case "7" -> dataset.forEach(d -> System.out.println(d + "\n" + "-".repeat(80)));
                case "8" -> continuer = false;
                default  -> System.out.println("Choix invalide.");
            }
        }

        scanner.close();
    }
}
