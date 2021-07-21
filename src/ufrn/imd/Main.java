package ufrn.imd;

import java.io.IOException;
import java.util.LinkedHashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader();
		LinkedHashSet<String> lhs = fr.getUsers("/animelists.csv");

		String result = new SearchWord().betterDistance(lhs, "Sthephanny");
		
		System.out.println(result);
	}

}
