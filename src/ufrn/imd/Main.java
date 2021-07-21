package ufrn.imd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Main {
	
	public static int THREAD_SIZE = 32000;
	
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader();
		LinkedHashSet<String> lhs = fr.getUsers("/animelists.csv");

		List<String> list = new ArrayList<String>(lhs);
		String result = new SearchWord().betterDistance(list, "Sthephanny", THREAD_SIZE);
		
		System.out.println(result);
	}

}
