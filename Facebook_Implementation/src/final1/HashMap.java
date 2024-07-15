package final1;

public class HashMap<K, V> implements MapInterface<K, V> {

	private int numberOfEntries;
	private static final int DEFAULT_CAPACITY = 5; // Must be prime
	private static final int MAX_CAPACITY = 10000;

	private TableEntry<K, V>[] hashTable;
	private int tableSize; // Must be prime
	private static final int MAX_SIZE = 2 * MAX_CAPACITY;
	private boolean initialized = false;
	private static final double MAX_LOAD_FACTOR = 0.75;

	public HashMap() {
		this(DEFAULT_CAPACITY);
	}

	public HashMap(int initialCapacity) {
		checkCapacity(initialCapacity);
		numberOfEntries = 0; // Dictionary is empty

		// Set up hash table:
		// Initial size of hash table is same as initialCapacity if it is prime;
		// otherwise increase it until it is prime size

		int tableSize = getNextPrime(initialCapacity);
		checkSize(tableSize);

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		TableEntry<K, V>[] temp = (TableEntry<K, V>[]) new TableEntry[tableSize];
		hashTable = temp;
		initialized = true;
	} // end constructor

	public V put(K key, V value) {
		checkInitialization();
		V oldValue;

		if (isHashTableTooFull()) {
			enlargeHashTable();
		}

		int i = getHashIndex(key);
		i = probe(i, key); // check collision

		if (hashTable[i] == null) {
			hashTable[i] = new TableEntry<K, V>(key, value);
			numberOfEntries++;
			oldValue = null;
		} else {
			oldValue = hashTable[i].getValue();
			hashTable[i].setValue(value);
		}

		return oldValue;
	}

	public V remove(K key) {
		checkInitialization();
		V removedValue = null;
		int i = getHashIndex(key);
		i = locate(i, key);

		if (i != -1) {

			removedValue = hashTable[i].getValue();
			hashTable[i] = null;
			numberOfEntries--;
		}
		return removedValue;
	}

	public V get(K key) {
		V result = null;
		int i = getHashIndex(key);
		i = locate(i, key);

		if (i != -1) {
			result = hashTable[i].getValue();
		}
		return result;
	}

	public boolean containsKey(K key) {
		return get(key) != null;
	}

	public boolean containsValue(V value) {
		checkInitialization();
		for (TableEntry<K, V> entry : hashTable) {
			if (entry != null && entry.getValue().equals(value)) {
				return true; // Value found
			}
		}
		return false; // Value not found
	}

	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	public int size() {
		return numberOfEntries;
	}

	public void clear() {
		checkInitialization();
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = null;
		}
		numberOfEntries = 0;
	}

	private int getHashIndex(K key) {
		int hashIndex = key.hashCode() % hashTable.length;
		if (hashIndex < 0) {
			hashIndex = hashIndex + hashTable.length;
		} // end if

		return hashIndex;
	}

	private int getNextPrime(int integer) {

		// if even, add 1 to make odd
		if (integer % 2 == 0) {
			integer++;
		} // end if

		// test odd integers
		while (!isPrime(integer)) {
			integer = integer + 2;
		} // end while
		return integer;
	} // end getNextPrime

	// Returns true if the given integer is prime.
	private boolean isPrime(int integer) {
		boolean result;
		boolean done = false;

		// 1 and even numbers are not prime
		if ((integer == 1) || (integer % 2 == 0)) {
			result = false;
		}

		// 2 and 3 are prime
		else if ((integer == 2) || (integer == 3)) {
			result = true;
		} else // integer is odd and >= 5
		{
			assert (integer % 2 != 0) && (integer >= 5);
			// a prime is odd and not divisible by every odd integer up to its square root
			result = true; // assume prime
			for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2) {
				if (integer % divisor == 0) {
					result = false; // divisible; not prime
					done = true;
				} // end if
			} // end for
		} // end if
		return result;
	} // end isPrime

	private int probe(int index, K key) {
		boolean found = false;

		while (!found && (hashTable[index] != null)) {
			if (key.equals(hashTable[index].getKey()))
				found = true; // Key found
			else // Follow probe sequence
				index = (index + 1) % hashTable.length; // Linear probing

		} // end while
		return index;
	} // end probe

	// Precondition: checkInitialization has been called.
	private int locate(int index, K key) {
		checkInitialization();
		boolean found = false;

		while (!found && (hashTable[index] != null)) {
			if (key.equals(hashTable[index].getKey()))
				found = true; // Key found
			else // Follow probe sequence
				index = (index + 1) % hashTable.length; // Linear probing
		} // end while
			// Assertion: Either key or null is found at hashTable[index]
		int result = -1;
		if (found)
			result = index;
		return result;
	} // end locate

	private void checkInitialization() {
		if (!initialized)
			throw new SecurityException("HashedDictionary object is not initialized properly.");
	} // end checkInitialization

	private void checkCapacity(int capacity) {
		if (capacity < DEFAULT_CAPACITY)
			capacity = DEFAULT_CAPACITY;
		else if (capacity > MAX_CAPACITY)
			throw new IllegalStateException(
					"Attempt to create a dictionary " + "whose capacity is larger than " + MAX_CAPACITY);
	} // end checkCapacity

	// Throws an exception if the hash table becomes too large.
	private void checkSize(int size) {
		if (tableSize > MAX_SIZE)
			throw new IllegalStateException("Dictionary has become too large.");
	} // end checkSize

	private void enlargeHashTable() {

		TableEntry<K, V>[] oldTable = hashTable;
		int oldSize = hashTable.length;
		int newSize = getNextPrime(oldSize + oldSize);

		checkSize(newSize);

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		TableEntry<K, V>[] tempTable = (TableEntry<K, V>[]) new TableEntry[newSize]; // Increase size of array
		hashTable = tempTable;
		numberOfEntries = 0; // Reset number of dictionary entries, since

		for (int index = 0; index < oldSize; index++) {
			if ((oldTable[index] != null))
				put(oldTable[index].getKey(), oldTable[index].getValue());
		} // end for
	} // end enlargeHashTable

	private boolean isHashTableTooFull() {
		return numberOfEntries > MAX_LOAD_FACTOR * hashTable.length;
	}


	@SuppressWarnings("unchecked")
	@Override
	public CustomHashSet<K> keySet() {
		CustomHashSet<K> keySet = new CustomHashSet<>();
		for (TableEntry<K,V> t : hashTable) {
			if (t != null)
				keySet.add((K) t.getKey());
		}
		return keySet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomHashSet<V> valueSet() {
		CustomHashSet<V> valueSet = new CustomHashSet<>();
		for (TableEntry<K,V> t : hashTable) {
			if (t != null)
				valueSet.add((V) t.getValue());
		}
		return valueSet;
	}

	@Override
	public CustomHashSet<TableEntry<K, V>> entrySet() {
		CustomHashSet<TableEntry<K, V>> entrySet = new CustomHashSet<>();
		for (TableEntry<K,V> entry : hashTable) {
			if (entry != null)
				entrySet.add(entry);
		}
		return entrySet;
	}


}
