
public class Main {

	public static void main(String[] args) {
		Point [] points = FileParser.parserData("demo.txt");
		
		for (Point p : points) System.out.println(p);
		
		int k = 4;

		Point [][] result = KMeans.getInstance().process(points, k);
		
		for (Point [] group : result) {
			System.out.println("Group =====");
			for (Point p : group) {
				System.out.println(p);
			}
			System.out.println("===========");
		}
		
	}

}
