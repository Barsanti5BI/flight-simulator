package Aereo;

import java.util.LinkedList; // import the linked list class

public class Stack<T> {
    private LinkedList<T> list; // the linked list to store the elements

    // constructor with no parameters
    public Stack() {
        list = new LinkedList<>(); // create an empty linked list
    }

    // push an element to the stack
    public void push(T element) {
        list.addFirst(element); // add the element to the head of the list
    }

    // pop an element from the stack
    public T pop() {
        if (isEmpty()) { // check if the stack is empty
            System.out.println("Stack is empty");
            return null;
        }
        return list.removeFirst(); // remove and return the element at the head of the list
    }

    // get the size of the stack
    public int size() {
        return list.size(); // return the size of the list
    }

    // check if the stack is empty
    public boolean isEmpty() {
        return list.isEmpty(); // return true if the list is empty
    }

    // display the stack contents
    public String toString() {
        return list.toString(); // return the string representation of the list
    }
}
