import java.util.Arrays;

/**
 * 儲存 K Means 演算法
 */
public class KMeans {

	// Singleton pattern
	private static KMeans kmeans = null;
	public static KMeans getInstance() { return kmeans == null ? kmeans = new KMeans() : kmeans;}
	
	/**
	 * 處理 K-Means 演算
	 */
	public Point [][] process(Point [] points, int k) {
		
		// 取得初始化的分堆
		Point [][] group = getNewGroup(points, getInitialGroupCenter(points, k));
		
		// 重複分群直到不變
		while(true) {
			Point [][] newGroup = getNewGroup(group);
			if (compare(group, newGroup)) 
				return newGroup;
			
			group = newGroup;
		}
	}
	
	/**
	 * 取得初始點 (以前k個為主)
	 * @param points 所有可用的點
	 * @param k k 個點
	 * @return 初始點
	 */
	private Point [] getInitialGroupCenter(Point [] points, int k) {
		return Arrays.copyOfRange(points, 0, k);
	}
	
	/**
	 * 取得分群中心點
	 * @param points 上一輪的分群
	 * @return 新的中心點
	 */
	private Point [] getNewGroupCenter(Point [][] points) {
		
		if (points == null) return null;
		
		int k = points.length;
		int counter = 0;
		Point [] newCenter = new Point [k];
				
		for (Point [] pGroup : points) {
			double avg_x = 0.0, avg_y = 0.0;
			
			for (Point p : pGroup) {
				avg_x += p.getX();
				avg_y += p.getY();
			}
			
			newCenter[counter++] = new Point(avg_x / pGroup.length, avg_y / pGroup.length);
		}
		
		return newCenter;
	}
	
	/**
	 * 重新計算新的分群
	 * @param points 前一次的分群
	 * @return 新的分群
	 */
	private Point [][] getNewGroup(Point [][] points) {
		
		if (points == null) return null;
		
		// 重堆
		int size = points.length * points[0].length;
		Point [] newPoints = new Point[size];
		for (int i = 0, counter = 0; i < points.length; i++)
			for (int j = 0; j < points[i].length; j++) 
				newPoints[counter++] = points[i][j];
			
		return getNewGroup(newPoints, getNewGroupCenter(points));
	}
	
	/**
	 * 重新計算新的分群
	 * @param points 前一次的分群
	 * @param center 前一次分群中心點
	 * @return 新的分群
	 */
	private Point [][] getNewGroup(Point [] points, Point [] center) {
		
		if (points == null || center == null) return null;

		// TODO
		return null;
	}
	
	/**
	 * 比較是否兩個群體一樣
	 * @param a 群體A
	 * @param b 群體B
	 * @return 是否一樣
	 */
	private boolean compare(Point [][] a, Point [][] b) {
		
		// TODO
		return true;
	}
}
