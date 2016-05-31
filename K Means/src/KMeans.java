import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
	public Point [] getInitialGroupCenter(Point [] points, int k) {
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
		
		// 重堆成一維陣列
		int size = 0;
		for (Point [] g : points) size += g.length;
		
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

		// 初始化儲存結構
		List<List<Point>> group = new ArrayList<List<Point>>();
		for (int i = 0; i < center.length; i++)
			group.add(i, new ArrayList<Point>());
		
		for (Point p : points) {
			int min_i = 0;
			double min_distance = Double.MAX_VALUE;
			
			for (int i = 0; i < center.length; i++) {
				double check_dis = Math.pow( Math.pow(p.getX() - center[i].getX(), 2) + 
						Math.pow(p.getY() - center[i].getY(), 2), 0.5);
				if ( check_dis < min_distance) {
					min_i = i;
					min_distance = check_dis;
				}
			}
			group.get(min_i).add(p);
		}
		
		// 轉存為陣列結構
		Point [][] newGroup = new Point [center.length][];
		for (int i = 0; i < center.length; i++) {
			List<Point> eachList = group.get(i);
			newGroup[i] = eachList.toArray(new Point[eachList.size()]);
		}
			
		return newGroup;
	}
	
	/**
	 * 比較是否兩個群體一樣
	 * @param a 群體A
	 * @param b 群體B
	 * @return 是否一樣
	 */
	private boolean compare(Point [][] a, Point [][] b) {
		
		// 先將兩個全部子群集排列成一致
		for (Point [] g : a) Arrays.sort(g);
		for (Point [] g : b) Arrays.sort(g);
		
		Arrays.sort(a, GroupComparator.getInstance());
		Arrays.sort(b, GroupComparator.getInstance());
		
		boolean same = true;
		
		for (int i = 0; i < a.length; i++) {
			Point [] as = a[i], bs = b[i];
			
			if (as.length != bs.length) {
				same = false;
				break;
			}
			
			for (int j = 0; j < as.length; j++) {
				if (!as[j].equals(bs[j])) {
					same = false;
					break;
				}
			}
		}
			
		return same;
	}
}

class GroupComparator implements Comparator<Point []> {

	private static GroupComparator instance;
	public static GroupComparator getInstance() {return instance == null ? instance = new GroupComparator() : instance;}
	
	@Override
	public int compare(Point[] a, Point[] b) {
		double axs = 0.0, ays = 0.0, bxs = 0.0, bys = 0.0;
		
		for (Point p : a) {
			axs += p.getX();
			ays += p.getY();
		}
		
		for (Point p : b) {
			bxs += p.getX();
			bys += p.getY();
		}
		
		return (int) (((axs * 1000 + ays) - (bxs * 1000 + bys)) * 100);
	}
	
}
