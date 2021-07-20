package ufrn.imd.dim0124;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Main {
	
	public static final int DISTANCE_DEFAULT = 100;

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();

		int value_best_distance = DISTANCE_DEFAULT;
		String username_best_distance = "";

		String username_to_search = "Sthephanny";
		
		LinkedHashSet<String> lhs = new LinkedHashSet<String>();

		InputStream res = Main.class.getResourceAsStream("/animelists.csv");
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(res))) {
			String line;
			while((line = bufferedReader.readLine()) != null) {
				lhs.add(line.split(",", 2)[0]);
			}
		}
		
		Iterator<String> itr = lhs.iterator();
        while (itr.hasNext()) {
    		String current_username = itr.next();
    		int current_distance = LevenshteinDistance.calculate(username_to_search, current_username);
    		if (current_distance < value_best_distance) {
    			value_best_distance = current_distance;
    			username_best_distance = current_username;
    			
    			if(current_distance == 0) {
    				break;
    			}
    		}
        }

		System.out.println(username_best_distance);
		
        long end = System.currentTimeMillis();
		long delay = end - start;
		
		System.out.println("Time: " + delay);
	}
}
