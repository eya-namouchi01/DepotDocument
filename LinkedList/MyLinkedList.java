package linkedList;

import java.util.*;

public class MyLinkedList <E>
       extends AbstractSequentialList<E>
       implements List<E>
{
	private int size = 0; 
       /**
        * Pointer to first node.
        * Invariant: (first == null && last == null) ||
        *            (first.prev == null && first.item != null)
        */
	private MyNode<E> first=null;
       /**
        * Pointer to last node.
        * Invariant: (first == null && last == null) ||
        *            (last.next == null && last.item != null)
        */
	private MyNode<E> last=null;

	public MyLinkedList() {} 
	
	// une liste vide est telle que first == null && last == null
	@Override   
	public boolean isEmpty() {
        return size==0;
        //return (first==null) && (last== null);
	}
	
	// ajout à la fin de la chaîne
	@Override 
	public boolean add(E element) {
        MyNode newNode= new MyNode(null, element, null);
        if (isEmpty()){first=newNode;
        last= newNode;}
        else{
            last.next = newNode; // l'ancien dernier pointe vers le nouveau
            last = newNode;      // mise à jour du dernier

		/*last.next= newNode;
        newNode.prev= last.next;*/
        }
        size++;
		return true;
	}
	
	// cherche si l'élément est dans la liste
	@Override 
	public boolean contains(Object element) {
        MyNode<E> node= first;
        while(node!= null) {
            if(Objects.equals(node.item, element)) { // gère null
                return true;
            }
            node = node.next;
        }
        return false;
	}

    public int size_() {
        int compte= 0;
        if (! isEmpty()){
            MyNode node= first;
        while( node!= null){
            size++;
            node= node.next;
            }
        }
        return compte;
	}
    @Override
    public int size(){
        return size;
}
	@Override
	public E get(int i) {
        if (i >= size) {
            throw new IndexOutOfBoundsException();
        }
        else if(i==-1) return last.item;
        else
        {MyNode<E> node= first;
        for(int j=0; j< i; j++)
        {
            node= node.next;
        }
		return node.item;}
	}
	
	@Override
	public String toString() {
        StringBuilder machaine= new StringBuilder();
        MyNode<E> node= first;
        while(node!= null){
            machaine.append(node.item.toString());
            node= node.next;
        }
		return machaine.toString();
	}	
	
	public String toStringInverse() {
    String chaineInversee= "";
        MyNode<E> node= last;
        while(node!=null){
            chaineInversee+= node.item;
            node= node.prev;

        }
		return chaineInversee;
	}	
	
	// methode ecrite pour respecter l'interface mais que l'on ne va
	// pas implementer dans ce TP
	@Override
	public ListIterator<E> listIterator(int index) {

		throw new UnsupportedOperationException();
	}
	
	// méthodes plus difficiles pour les avancés :
	// ajout à une position i quelconque
	// retrait à une position i quelconque
}