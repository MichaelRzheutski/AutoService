package com.solvd.autoservice.customlinkedlist;

import java.util.*;
import java.util.function.Consumer;

public class CustomLinkedList<E> extends AbstractSequentialList<E> implements List<E> {
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    public CustomLinkedList() {
    }

    // Links last element
    void linkLast(E element) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    // Inserts element before non-null Node
    void linkBefore(E element, Node<E> successor) {
        final Node<E> predecessor = successor.prev;
        final Node<E> newNode = new Node<>(predecessor, element, successor);
        successor.prev = newNode;
        if (predecessor == null)
            first = newNode;
        else
            predecessor.next = newNode;
        size++;
        modCount++;
    }

    // Unlinks non-null node
    E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }

    // Returns the number of elements in this list
    public int size() {
        return size;
    }

    // Appends the specified element to the end of this list
    public boolean add(E element) {
        linkLast(element);
        return true;
    }

    // Removes the first occurrence of the specified element from this list,
    // if it is present. If this list does not contain the element, it is
    // unchanged
    public boolean remove(Object object) {
        if (object == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (object.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    // Removes all of the elements from this list
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }

    // Returns the element at the specified position in this list
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    // Replaces the element at the specified position in this list with the
    // specified element
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    // Inserts the specified element at the specified position in this list
    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
    }

    // Removes the element at the specified position in this list
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    // Tells if the argument is the index of an existing element
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    // Tells if the argument is the index of a valid position for an
    // iterator or an add operation
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    // Constructs an IndexOutOfBoundsException detail message
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    // Returns the (non-null) Node at the specified element index
    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    // Returns the index of the first occurrence of the specified element
    // in this list, or -1 if this list does not contain the element
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    // Returns the index of the last occurrence of the specified element
    // in this list, or -1 if this list does not contain the element
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
    }

    // Returns a list-iterator of the elements in this list (in proper
    // sequence), starting at the specified position in the list
    public ListIterator<E> listIterator(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }

    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public E next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public E previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }

        public void set(E element) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.item = element;
        }

        public void add(E element) {
            checkForComodification();
            lastReturned = null;
            if (next == null)
                linkLast(element);
            else
                linkBefore(element, next);
            nextIndex++;
            expectedModCount++;
        }

        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (modCount == expectedModCount && nextIndex < size) {
                action.accept(next.item);
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    // Returns an array containing all of the elements in this list
    // in proper sequence (from first to last element)
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    // Method checks if the LinkedList empty
    public boolean isEmpty() {
        return size() == 0;
    }

}