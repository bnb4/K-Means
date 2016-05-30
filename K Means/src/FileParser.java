import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileParser {

	/**
	 * 取得檔案中的資料
	 * @param path 檔案路徑
	 * @return Point 物件
	 */
	public static Point [] parserData(String path) {
		
		if (path.equals("")) return null;
		
		try (Scanner scn = new Scanner(new File(path))) {
			Set<Point> data = new HashSet<>();
			
			while ( scn.hasNextLine() ) {
				
				String inStr;
				if ((inStr = scn.nextLine()).equals("")) continue;
				
				String [] spl = inStr.split(" ", 2);
				if (spl.length != 2) continue;
				
				data.add(new Point(Double.parseDouble(spl[0]), Double.parseDouble(spl[1])));
			}
			return data.toArray(new Point[data.size()]);
			
		} catch (IOException e) { 
			e.printStackTrace(); 
			return null;
		} catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}
	}
}
