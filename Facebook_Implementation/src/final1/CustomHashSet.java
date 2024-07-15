package final1;

import java.util.Arrays;
import java.util.Iterator;

/**
 * A simple implementation of a HashSet data structure. This ADT is an array
 * based HashSet<T>, where the initial capacity is set to 10 elements. To follow
 * the characteristics of HashSet<T>, duplicate values are not accepted.
 * 
 * @param <T> the generic type of elements in the set. In this example, it will
 *            be Profile.
 */
public class CustomHashSet<T> implements SetInterface<T>, Iterable<T> {

	private final static int DEFAULT_CAPACITY = 10;

	private T[] list;
	private int size;

	/**
	 * Constructs a new HashSet with the default initial capacity, which is 10.
	 */
	public CustomHashSet() {
		@SuppressWarnings("unchecked")
		T[] tempList = (T[]) new Object[DEFAULT_CAPACITY];
		size = 0;
		list = tempList;
	}

	@Override
	public int getCurrentSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean add(T newEntry) {
		ensureCapacity();
		if (size == 0) {
			list[size++] = newEntry;
			return true;
		}
		else if (contains(newEntry)) {
			return false;
		} else {
			list[size++] = newEntry;
			return true;
		}
	}

	@Override
	public boolean remove(T anEntry) {
		int index = indexOf(anEntry);
		if (index == -1) {
			return false;
		}

		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(list, index + 1, list, index, numMoved);
		}
		list[--size] = null;
		return true;
	}

	@Override
	public void clear() {
		Arrays.fill(list, null);
		size = 0;
	}

	@Override
	public boolean contains(T anEntry) {
		return indexOf(anEntry) != -1;
	}

	@Override
	public T[] toArray() {
		return Arrays.copyOf(list, size);
	}

	@Override
	public int indexOf(T anEntry) {
		for (int i = 0; i < size; i++) {
			if (anEntry.equals(list[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Ensures that the array has enough space to save more data. It automatically
	 * doubles the dimension of the array if reaches maximum.
	 *
	 */
	private void ensureCapacity() {
		int capacity = list.length - 1;
		if (size >= capacity) {
			int newCapacity = 2 * capacity;
			list = Arrays.copyOf(list, newCapacity + 1);
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}

	/**
	 * Iterator implementation for the HashSet.
	 */
	private class HashSetIterator implements Iterator<T> {
		private int currentIndex;

		/**
		 * Constructs a new HashSetIterator.
		 */
		public HashSetIterator() {
			currentIndex = 0;
		}

		/**
		 * Returns {@code true} if there are more elements to iterate, {@code false}
		 * otherwise.
		 *
		 * @return {@code true} if there are more elements, {@code false} otherwise
		 */
		public boolean hasNext() {
			return currentIndex < size;
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return the next element in the iteration
		 * @throws CustomNoSuchElementException if there are no more elements to iterate
		 */
		@SuppressWarnings("unchecked")
		public T next() {
			if (!hasNext()) {
				throw new CustomNoSuchElementException();
			}
			return (T) list[currentIndex++];
		}

		/**
		 * Removes the last element returned by the iterator. This function is not
		 * supported at current circumstances.
		 *
		 * @throws CustomUnsupportedOperationException as remove() is not supported.
		 */
		public void remove() {
			throw new CustomUnsupportedOperationException();
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}

}
