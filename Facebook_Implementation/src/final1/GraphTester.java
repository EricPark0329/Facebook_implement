package final1;

public class GraphTester {
	public static void main(String[] args) {
		Profile a = new Profile("A", 18, null, null, null);
		Profile b = new Profile("B", 17, null, null, null);
		UndirectedGraph<Profile> graph = new UndirectedGraph<>();
		int score = 0;
		System.out.println("Testing addVertex() for different Profiles\n");
		if (graph.addVertex(a) && graph.addVertex(b)) {
			System.out.println("Adding vertex: SUCCESS\n");
			score++;
		} else {
			System.out.println("Adding vertex: FAIL\n");
		}
		
		System.out.println("---------------\n\nTesting addVertex() duplicate\n");
		if (!graph.addVertex(a) && !graph.addVertex(b)) {
			System.out.println("Adding vertex: FAIL (The code runs successfully)");
			score++;
		} else {
			System.out.println("Adding vertex: SUCCESS (SHOULD BE FAIL)");
		}
		
		System.out.println("---------------\n\nTesting addEdge() from a -> b \n");
		if (graph.addEdge(a, b)) {
			System.out.println("Adding Edges (a->b): SUCCESS\n");
			score++;
		} else {
			System.out.println("Adding Edges (a->b): FAIL\n");
		}
		
		System.out.println("---------------\n\nTesting if a and b are friends (a->b and b->a) \n");
		if (graph.hasEdge(a, b) && graph.hasEdge(b, a)) {
			System.out.println("hasEdge(a,b) && hasEdge(b,a) : SUCCESS\n");
			score++;
		} else {
			System.out.println("hasEdge(a,b) && hasEdge(b,a) : FAIL\n");
		}
	}
}
