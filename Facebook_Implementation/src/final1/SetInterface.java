package final1;

public interface SetInterface<T> {

	/**
	 * Method used for getting the size of the Set.
	 * 
	 * @return the number of elements in this set
	 */
	public int getCurrentSize();

	/**
	 * Returns {@code true} if this set contains no elements, {@code false}
	 * otherwise.
	 * 
	 * @return {@code true} if this set is empty, {@code false} otherwise
	 */
	public boolean isEmpty();

	/**
	 * Adds the specified element to this set if it is not already present.
	 * 
	 * @param newEntry the element to be added to this set
	 * @return {@code true} if the newEntry is successfully added, {@code false}
	 *         otherwise
	 */
	public boolean add(T newEntry);

	/**
	 * Removes the specified element from this set if it is present.
	 * 
	 * @param anEntry the element to be removed from this set
	 * @return {@code true} if an element is successfully removed, {@code false}
	 *         otherwise
	 */
	public boolean remove(T anEntry);

	/**
	 * Removes all elements from this set
	 */
	public void clear();

	/**
	 * Returns {@code true} if this set contains the specified element,
	 * {@code false} otherwise.
	 *
	 * @param anEntry the element to be checked if the set contains it.
	 * @return {@code true} if this set contains the specified element,
	 *         {@code false} otherwise
	 */
	public boolean contains(T anEntry);

	/**
	 * Returns an Array-converted version of the set.
	 * 
	 * @return an Array-converted version of the set.
	 */
	public T[] toArray();

	/**
	 * Returns {@code -1} if the specified element is not present, otherwise returns
	 * the designated index.
	 * 
	 * @param anEntry the specified element to be checked
	 * @return {@code -1} if the specified element is not present, otherwise the
	 *         designated index.
	 */
	public int indexOf(T anEntry);

	/**
	 * Returns the String version of the set. It implements and uses
	 * {@code java.util.Arrays.toString(T[] array)} method.
	 * 
	 * @return the String version of the set.
	 */
	public String toString();
}
