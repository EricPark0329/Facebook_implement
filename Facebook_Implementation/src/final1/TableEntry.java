package final1;

public class TableEntry<K, V> {

	private K key;
	private V value;

	public TableEntry(K searchKey, V dataValue) {
		key = searchKey;
		value = dataValue;
	} // end constructor

	public K getKey() {
		return key;
	} // end getKey

	public V getValue() {
		return value;
	} // end getValue

	public void setValue(V newValue) {
		value = newValue;
	} // end setValue
}
