package ufrn.imd.dim0124;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
	
	public static final int DISTANCE_DEFAULT = 100;

	public static void main(String[] args) throws IOException {
		int count = 0;

		int position_best_distance = 0;
		int value_best_distance = DISTANCE_DEFAULT;
		String username_best_distance = "";

		String username_to_search = "helio";

		InputStream res = Main.class.getResourceAsStream("/animelists.csv");
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(res))) {
			String line;
			while((line = bufferedReader.readLine()) != null) {
				count++;
				String current_username = line.split(",", 2)[0];
				int current_distance = LevenshteinDistance.calculate(username_to_search, current_username);
				if (current_distance < value_best_distance) {
					value_best_distance = current_distance;
					username_best_distance = current_username;
					position_best_distance = count;
					
					if(current_distance == 0) {
						break;
					}
				}
			}
		}
		System.out.println(position_best_distance + ": " + username_best_distance + " | " + value_best_distance);
	}
}
