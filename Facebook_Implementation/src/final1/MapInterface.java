package final1;
public interface MapInterface<K, V> {
	
	public V put(K key, V value);
	public V remove(K key);
	public V get(K key);
	public boolean containsKey(K key);
	public boolean containsValue(V value);
	public boolean isEmpty();
	public int size();
	public void clear();
	public CustomHashSet<K> keySet();
	public CustomHashSet<V> valueSet();
	public CustomHashSet<TableEntry<K,V>> entrySet();
}
