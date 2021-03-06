/**
 * 儲存點資料的結構
 */
public class Point implements Comparable<Point> {

	private double x = 0.0;
	private double y = 0.0;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 取得 X 座標
	 * @return X 座標
	 */
	public double getX() {return x;}
	
	/**
	 * 取得 Y 座標
	 * @return Y 座標
	 */
	public double getY() {return y;}
	
	/**
	 * 判斷是否一樣
	 */
	@Override
	public boolean equals(Object o) {
		
		if (o instanceof Point) 
			return equals((Point) o);
		
		return false;
	}
	
	/**
	 * 判斷是否一樣
	 * @param p 比較的點
	 * @return 是否一樣
	 */
	private boolean equals(Point p) {

		if (this.x == p.x && this.y == p.y) return true;
		return false;
	}
	
	/**
	 * 設定印出格式
	 */
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	/**
	 * 讓 HashSet 能夠避免相同的點座標重複被讀入
	 */
	@Override
	public int hashCode() {
		return (int) (x * 1000 + y);
	}

	/**
	 * 用來排序
	 */
	@Override
	public int compareTo(Point o) {
		return this.hashCode() - o.hashCode();
	}
}
