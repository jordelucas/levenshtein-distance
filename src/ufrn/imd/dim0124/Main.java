package ufrn.imd.dim0124;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Main {
	public static int THREAD_SIZE = 32000;
	
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();

		LinkedHashSet<String> lhs = new LinkedHashSet<String>();

		InputStream res = Main.class.getResourceAsStream("/animelists.csv");
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(res))) {
			String line;
			while((line = bufferedReader.readLine()) != null) {
				lhs.add(line.split(",", 2)[0]);
			}
		}
		
		List<String> list = new ArrayList<String>(lhs);
		
		String result = new SearchWord().search(list, "Sthephanny", THREAD_SIZE);
		
		System.out.println(result);
		
		long end = System.currentTimeMillis();
		long delay = end - start;
		
		System.out.println("Time: " + delay);
	}
}
