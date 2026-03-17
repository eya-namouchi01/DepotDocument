package linkedList;

/* Inspired from 
 * http://www.docjar.com/html/api/java/util/LinkedList.java.html
 */

public class MyNode<E> {
	
	// exceptionnellement les attributs sont laissés visibles dans le package
	// cela permettra de simplifier le code
	
	E item;
	MyNode<E> next;
	MyNode<E> prev;

	MyNode(MyNode<E> prev, E element, MyNode<E> next) {
		this.item = element;
		this.next = next;
		this.prev = prev;
	}
}