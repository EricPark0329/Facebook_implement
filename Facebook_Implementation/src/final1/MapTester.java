package final1;

import java.util.Map;

public class MapTester {
	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(1,1);
		map.put(2, 2);
		map.put(3, 3);
		System.out.println(map.size());
		for(TableEntry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		map.put(1, 9);
		for(TableEntry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		System.out.println(map.containsKey(1));
		System.out.println(map.containsValue(2));
		System.out.println(map.containsKey(4));
		
	}
}
