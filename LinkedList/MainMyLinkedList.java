package linkedList;

public class MainMyLinkedList {

	public static void main(String[] arg) {
		
		MyLinkedList<String> maListe = new MyLinkedList<>();
		System.out.println("1- taille = "+maListe.size());
		System.out.println("2- vide ? "+maListe.isEmpty());
		maListe.add("n");
		System.out.println("3- taille = "+maListe.size());
		System.out.println("4- vide ? "+maListe.isEmpty());
		System.out.println("5-"+maListe);
		System.out.println("6-"+maListe.toStringInverse());
		maListe.add("e");
		System.out.println("7- taildddle = "+maListe.size());
		System.out.println("8- vide ? "+maListe.isEmpty());
		System.out.println("9-"+maListe);
		System.out.println("10-"+maListe.toStringInverse());
		maListe.add("z");
		System.out.println("11-"+maListe);
		System.out.println("12-"+maListe.toStringInverse());
		System.out.println("13 vide ? "+maListe.isEmpty());
		System.out.println("14 taille = "+maListe.size());
		System.out.println("15 element -1 "+maListe.get(-1));
		System.out.println("element 0 "+maListe.get(0));
		System.out.println("element 1 "+maListe.get(1));
		System.out.println("element 2 "+maListe.get(2));
		System.out.println("element 3 "+maListe.get(3));
		System.out.println("contient n ? "+maListe.contains("n"));	
		System.out.println("contient e ? "+maListe.contains("e"));	
		System.out.println("contient z ? "+maListe.contains("z"));	
		System.out.println("contient o ? "+maListe.contains("o"));	
				
	}
}
