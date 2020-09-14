package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		// TODO: Implement this method
		// Throw an exception if element is null
		if (element == null) {
			throw new NullPointerException("Null element cannot be added");
		} else {
			// Create a new node
			LLNode<E> newNode = new LLNode<E>(element);
			
			// Insert the node at the end of the linked list 
			newNode.prev = tail.prev;
			newNode.next = newNode.prev.next;
			newNode.prev.next = newNode;
			newNode.next.prev = newNode;
			
			// Change the list size
			size++;
			
			return true;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method
		LLNode<E> currNode = head;
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Out of bounds");
		} else {
			for (int i = 0; i < index+1; i++) {
				currNode = currNode.next;
			}
		}
		return currNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Out of Bounds");
		} else {
			if (element == null) {
				throw new NullPointerException("Null element cannot be added");
			} else {
				// Create a new node
				LLNode<E> newNode = new LLNode<E>(element);
				
				// Insert the node at the specified index 
				LLNode<E> prevNode = head;
				for (int i = 0; i < index; i++) {
					prevNode = prevNode.next;
				}
				
				LLNode<E> nextNode = head;
				for (int i = 0; i < index+1; i++) {
					nextNode = nextNode.next;
				}
				
				newNode.next = nextNode;
				newNode.prev = prevNode;
				prevNode.next = newNode;
				nextNode.prev = newNode;
				
				// Change the list size
				size++;
			}
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Out of bounds");
		} else {
			E element = get(index);
			
			// Find the prevNode and nextNode relative to the index
			LLNode<E> prevNode = head;
			for (int i = 0; i < index; i++) {
				prevNode = prevNode.next;
			}
			
			LLNode<E> nextNode = head;
			for (int i = 0; i < index+2; i++) {
				nextNode = nextNode.next;
			}
			
			// Remove the node at the index and rearrange the linked list
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
			
			// Change the size of the list
			size--;
			
			return element;
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Out of bounds");
		} else {
			if (element == null) {
				throw new NullPointerException("Cannot set the element to null");
			} else {
				E previousElement = get(index);
				
				// Find the node at the specified index
				LLNode<E> currNode = head;
				for (int i = 0; i < index+1; i++) {
					currNode = currNode.next;
				}
				
				// Replace the data field with element
				currNode.data = element;
				
				return previousElement;
			}
		}
		
		
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
}
