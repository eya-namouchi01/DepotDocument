package com.hai822i;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BenchmarkRunner {

    private static final int REPETITIONS_STATS = 1000;

    private static DepotArrayList arrayList = new DepotArrayList();
    private static DepotTreeMap   treeMap   = new DepotTreeMap();


    public static void chargerDatasetArray(List<Document> dataset) {
        arrayList = new DepotArrayList();
        for (Document doc : dataset) {
            try { arrayList.ajouterDocument(doc); }
            catch (DocumentDejaExistantException e) { System.err.println(e.getMessage()); }
        }
    }

    public static void chargerDatasetTreeMap(List<Document> dataset) {
        treeMap = new DepotTreeMap();
        for (Document doc : dataset) {
            try { treeMap.ajouterDocument(doc); }
            catch (DocumentDejaExistantException e) { System.err.println(e.getMessage());}
        }
    }

    public static long mesurerMemoire() {
        Runtime rt = Runtime.getRuntime();
        rt.gc();
        try { Thread.sleep(50); } catch (InterruptedException e) { System.err.println(e.getMessage()); }
        rt.gc();
        return rt.totalMemory() - rt.freeMemory();
    }

    public static void afficher(String operation, String structure,
                                int taille, long tempsNano, long memoireOctets) {
        System.out.printf(
                "%-15s | %-10s | taille=%-6d | %12d ns | %12d octets%n",
                operation, structure, taille, tempsNano, memoireOctets);
    }

    // ===================================================================
    // Benchmark Ajout
    // ===================================================================
    public static void benchmarkerAjout(List<Document> dataset) {

        // --- ArrayList ---
        arrayList = new DepotArrayList();
        Chrono chronoAL = new Chrono("ajout_arraylist", true); // nano = true
        for (Document doc : dataset) {
            chronoAL.start("ajout");
            try { arrayList.ajouterDocument(doc); }
            catch (DocumentDejaExistantException e) { System.err.println(e.getMessage()); }
            chronoAL.stop("ajout");
        }
        long memAL = mesurerMemoire();

        // --- TreeMap ---
        treeMap = new DepotTreeMap();
        Chrono chronoTM = new Chrono("ajout_treemap", true);
        for (Document doc : dataset) {
            chronoTM.start("ajout");
            try { treeMap.ajouterDocument(doc); }
            catch (DocumentDejaExistantException e) { System.err.println(e.getMessage()); }
            chronoTM.stop("ajout");
        }
        long memTM = mesurerMemoire();

        System.out.println("\n--- Benchmark Ajout ---");
        afficher("ajout", "ArrayList", dataset.size(),
                chronoAL.getResult("ajout") / dataset.size(), memAL);
        afficher("ajout", "TreeMap", dataset.size(),
                chronoTM.getResult("ajout") / dataset.size(), memTM);
    }

    // ===================================================================
    // Benchmark Retrait
    // ===================================================================
    public static void benchmarkerRetrait(List<Document> dataset) {

        List<String> refs = new ArrayList<>();
        for (Document d : dataset) refs.add(d.getReference());
        Collections.shuffle(refs);

        // --- ArrayList ---
        chargerDatasetArray(dataset);
        Chrono chronoAL = new Chrono("retrait_arraylist", true);
        for (String ref : refs) {
            chronoAL.start("retrait");
            try { arrayList.retirerDocument(ref); }
            catch (DocumentIntrouvableException e) { System.err.println(e.getMessage()); }
            chronoAL.stop("retrait");
        }
        long memAL = mesurerMemoire();

        // --- TreeMap ---
        chargerDatasetTreeMap(dataset);
        Chrono chronoTM = new Chrono("retrait_treemap", true);
        for (String ref : refs) {
            chronoTM.start("retrait");
            try { treeMap.retirerDocument(ref); }
            catch (DocumentIntrouvableException e) { System.err.println(e.getMessage());}
            chronoTM.stop("retrait");
        }
        long memTM = mesurerMemoire();

        System.out.println("\n--- Benchmark Retrait ---");
        afficher("retrait", "ArrayList", dataset.size(),
                chronoAL.getResult("retrait") / refs.size(), memAL);
        afficher("retrait", "TreeMap", dataset.size(),
                chronoTM.getResult("retrait") / refs.size(), memTM);
    }

    // ===================================================================
    // Benchmark Recherche
    // ===================================================================
    public static void benchmarkerRecherche(List<Document> dataset) {

        List<String> refs = new ArrayList<>();
        for (Document d : dataset) refs.add(d.getReference());
        Collections.shuffle(refs);

        // --- ArrayList ---
        chargerDatasetArray(dataset);
        Chrono chronoAL = new Chrono("recherche_arraylist", true);
        for (String ref : refs) {
            chronoAL.start("recherche");
            try { arrayList.rechercherParReference(ref); }
            catch (DocumentIntrouvableException e) { System.err.println(e.getMessage()); }
            chronoAL.stop("recherche");
        }
        long memAL = mesurerMemoire();

        // --- TreeMap ---
        chargerDatasetTreeMap(dataset);
        Chrono chronoTM = new Chrono("recherche_treemap", true);
        for (String ref : refs) {
            chronoTM.start("recherche");
            try { treeMap.rechercherParReference(ref); }
            catch (DocumentIntrouvableException e) {System.err.println(e.getMessage()); }
            chronoTM.stop("recherche");
        }
        long memTM = mesurerMemoire();

        System.out.println("\n--- Benchmark Recherche ---");
        afficher("recherche", "ArrayList", dataset.size(),
                chronoAL.getResult("recherche") / refs.size(), memAL);
        afficher("recherche", "TreeMap", dataset.size(),
                chronoTM.getResult("recherche") / refs.size(), memTM);
    }

    // ===================================================================
    // Benchmark Parcours (listerTous)
    // ===================================================================
    public static void benchmarkerParcours(List<Document> dataset) {

        // --- ArrayList ---
        chargerDatasetArray(dataset);
        Chrono chronoAL = new Chrono("parcours_arraylist", true);
        for (int i = 0; i < REPETITIONS_STATS; i++) {
            chronoAL.start("parcours");
            arrayList.listerTous();
            chronoAL.stop("parcours");
        }
        long memAL = mesurerMemoire();

        // --- TreeMap ---
        chargerDatasetTreeMap(dataset);
        Chrono chronoTM = new Chrono("parcours_treemap", true);
        for (int i = 0; i < REPETITIONS_STATS; i++) {
            chronoTM.start("parcours");
            treeMap.listerTous();
            chronoTM.stop("parcours");
        }
        long memTM = mesurerMemoire();

        System.out.println("\n--- Benchmark Parcours ---");
        afficher("parcours", "ArrayList", dataset.size(),
                chronoAL.getResult("parcours") / REPETITIONS_STATS, memAL);
        afficher("parcours", "TreeMap", dataset.size(),
                chronoTM.getResult("parcours") / REPETITIONS_STATS, memTM);
    }

    // ===================================================================
    // Benchmark Statistiques (compterParTheme)
    // ===================================================================
    public static void benchmarkerStatistiques(List<Document> dataset) {

        // --- ArrayList ---
        chargerDatasetArray(dataset);
        Chrono chronoAL = new Chrono("stats_arraylist", true);
        for (int i = 0; i < REPETITIONS_STATS; i++) {
            chronoAL.start("stats");
            arrayList.compterParTheme();
            chronoAL.stop("stats");
        }
        long memAL = mesurerMemoire();

        // --- TreeMap ---
        chargerDatasetTreeMap(dataset);
        Chrono chronoTM = new Chrono("stats_treemap", true);
        for (int i = 0; i < REPETITIONS_STATS; i++) {
            chronoTM.start("stats");
            treeMap.compterParTheme();
            chronoTM.stop("stats");
        }
        long memTM = mesurerMemoire();

        System.out.println("\n--- Benchmark Statistiques ---");
        afficher("statistiques", "ArrayList", dataset.size(),
                chronoAL.getResult("stats") / REPETITIONS_STATS, memAL);
        afficher("statistiques", "TreeMap", dataset.size(),
                chronoTM.getResult("stats") / REPETITIONS_STATS, memTM);
    }

    // ===================================================================
    // Benchmark Scénario Mixte
    // ===================================================================
    public static void benchmarkerScenario(int pAjout, int pRetrait, int pRecherche,
                                          int pParcours, int pStats,
                                          List<Document> dataset) {

        if (pAjout + pRetrait + pRecherche + pParcours + pStats != 100) {
            System.out.println("La somme des pourcentages doit être 100");
            return;
        }

        // On utilise tout le dataset comme "réservoir" d'ajouts
        int totalOps = dataset.size();
        int nbAjout     = totalOps * pAjout     / 100;
        int nbRetrait   = totalOps * pRetrait   / 100;
        int nbRecherche = totalOps * pRecherche / 100;
        int nbParcours  = totalOps * pParcours  / 100;
        int nbStats     = totalOps * pStats     / 100;
        int totalReel   = nbAjout + nbRetrait + nbRecherche + nbParcours + nbStats;

        if (totalReel == 0) {
            System.out.println("Aucune opération à effectuer.");
            return;
        }

        // Construction de la liste mélangée d'opérations
        List<String> operations = new ArrayList<>();
        for (int i = 0; i < nbAjout;     i++) operations.add("AJOUT");
        for (int i = 0; i < nbRetrait;   i++) operations.add("RETRAIT");
        for (int i = 0; i < nbRecherche; i++) operations.add("RECHERCHE");
        for (int i = 0; i < nbParcours;  i++) operations.add("PARCOURS");
        for (int i = 0; i < nbStats;     i++) operations.add("STATS");
        Collections.shuffle(operations);

        System.out.println("\n=== Scénario Mixte ===");
        System.out.printf("Ajout=%d%% (%d) | Retrait=%d%% (%d) | Recherche=%d%% (%d) | " +
                        "Parcours=%d%% (%d) | Stats=%d%% (%d) | Total=%d ops%n",
                pAjout, nbAjout, pRetrait, nbRetrait, pRecherche, nbRecherche,
                pParcours, nbParcours, pStats, nbStats, totalReel);

        // --- ArrayList ---
        // Si pas d'ajouts dans le scénario, on pré-charge le dataset complet
        // sinon on part vide et les ajouts construisent la structure.
        long memAvantAL = mesurerMemoire();
        if (nbAjout == 0) {
            chargerDatasetArray(dataset);
        } else {
            arrayList = new DepotArrayList();
        }
        Chrono chronoAL = new Chrono("scenario_arraylist", true);
        executerScenarioArrayList(chronoAL, operations, dataset, nbAjout == 0);
        long memApresAL = mesurerMemoire();
        long memAL = memApresAL - memAvantAL;

        // --- TreeMap ---
        long memAvantTM = mesurerMemoire();
        if (nbAjout == 0) {
            chargerDatasetTreeMap(dataset);
        } else {
            treeMap = new DepotTreeMap();
        }
        Chrono chronoTM = new Chrono("scenario_treemap", true);
        executerScenarioTreeMap(chronoTM, operations, dataset, nbAjout == 0);
        long memApresTM = mesurerMemoire();
        long memTM = memApresTM - memAvantTM;

        // Affichage détaillé par opération
        System.out.println("\n--- Détail par opération ---");
        System.out.printf("%-12s | %15s | %15s%n", "Opération", "ArrayList (ns)", "TreeMap (ns)");
        System.out.println("-".repeat(50));
        for (String op : new String[]{"ajout", "retrait", "recherche", "parcours", "stats"}) {
            long tAL = chronoAL.getResultArray(op) != null ? chronoAL.getResult(op) : 0;
            long tTM = chronoTM.getResultArray(op) != null ? chronoTM.getResult(op) : 0;
            System.out.printf("%-12s | %15d | %15d%n", op, tAL, tTM);
        }

        // Totaux
        long totalAL = chronoAL.getResult();
        long totalTM = chronoTM.getResult();
        System.out.println("\n--- Résultats globaux ---");
        System.out.printf("ArrayList : total=%d ns | moyenne=%d ns | mémoire=%d octets%n",
                totalAL, totalAL / totalReel, memAL);
        System.out.printf("TreeMap   : total=%d ns | moyenne=%d ns | mémoire=%d octets%n",
                totalTM, totalTM / totalReel, memTM);
        System.out.printf("Meilleur  : %s%n",
                totalAL < totalTM ? "ArrayList ✅" : "TreeMap ✅");
    }

    // -------- Exécution du scénario pour ArrayList --------
    private static void executerScenarioArrayList(Chrono chrono,
                                                  List<String> operations,
                                                  List<Document> dataset,
                                                  boolean preCharge) {
        int idxAjout = 0;
        // refsPresentes = références actuellement dans la structure (pour retrait/recherche)
        List<String> refsPresentes = new ArrayList<>();
        if (preCharge) {
            // La structure est déjà pleine du dataset, on initialise refsPresentes en conséquence
            for (Document d : dataset) refsPresentes.add(d.getReference());
            Collections.shuffle(refsPresentes);
        }

        for (String op : operations) {
            switch (op) {
                case "AJOUT" -> {
                    if (idxAjout >= dataset.size()) break;
                    Document doc = dataset.get(idxAjout++);
                    chrono.start("ajout");
                    try { arrayList.ajouterDocument(doc); refsPresentes.add(doc.getReference()); }
                    catch (DocumentDejaExistantException e) { /* ignoré */ }
                    chrono.stop("ajout");
                }
                case "RETRAIT" -> {
                    if (refsPresentes.isEmpty()) break;
                    String ref = refsPresentes.remove(refsPresentes.size() - 1);
                    chrono.start("retrait");
                    try { arrayList.retirerDocument(ref); }
                    catch (DocumentIntrouvableException e) { System.err.println(e.getMessage()); }
                    chrono.stop("retrait");
                }
                case "RECHERCHE" -> {
                    if (refsPresentes.isEmpty()) break;
                    String ref = refsPresentes.get((int)(Math.random() * refsPresentes.size()));
                    chrono.start("recherche");
                    try { arrayList.rechercherParReference(ref); }
                    catch (DocumentIntrouvableException e) { System.err.println(e.getMessage()); }
                    chrono.stop("recherche");
                }
                case "PARCOURS" -> {
                    chrono.start("parcours");
                    arrayList.listerTous();
                    chrono.stop("parcours");
                }
                case "STATS" -> {
                    chrono.start("stats");
                    arrayList.compterParTheme();
                    chrono.stop("stats");
                }
            }
        }
    }

    // -------- Exécution du scénario pour TreeMap --------
    private static void executerScenarioTreeMap(Chrono chrono,
                                                List<String> operations,
                                                List<Document> dataset,
                                                boolean preCharge) {
        int idxAjout = 0;
        List<String> refsPresentes = new ArrayList<>();
        if (preCharge) {
            for (Document d : dataset) refsPresentes.add(d.getReference());
            Collections.shuffle(refsPresentes);
        }

        for (String op : operations) {
            switch (op) {
                case "AJOUT" -> {
                    if (idxAjout >= dataset.size()) break;
                    Document doc = dataset.get(idxAjout++);
                    chrono.start("ajout");
                    try { treeMap.ajouterDocument(doc); refsPresentes.add(doc.getReference()); }
                    catch (DocumentDejaExistantException e) { /* ignoré */ }
                    chrono.stop("ajout");
                }
                case "RETRAIT" -> {
                    if (refsPresentes.isEmpty()) break;
                    String ref = refsPresentes.remove(refsPresentes.size() - 1);
                    chrono.start("retrait");
                    try { treeMap.retirerDocument(ref); }
                    catch (DocumentIntrouvableException e) { System.err.println(e.getMessage());}
                    chrono.stop("retrait");
                }
                case "RECHERCHE" -> {
                    if (refsPresentes.isEmpty()) break;
                    String ref = refsPresentes.get((int)(Math.random() * refsPresentes.size()));
                    chrono.start("recherche");
                    try { treeMap.rechercherParReference(ref); }
                    catch (DocumentIntrouvableException e) { System.err.println(e.getMessage()); }
                    chrono.stop("recherche");
                }
                case "PARCOURS" -> {
                    chrono.start("parcours");
                    treeMap.listerTous();
                    chrono.stop("parcours");
                }
                case "STATS" -> {
                    chrono.start("stats");
                    treeMap.compterParTheme();
                    chrono.stop("stats");
                }
            }
        }
    }
}
