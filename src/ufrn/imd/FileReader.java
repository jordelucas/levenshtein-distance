package ufrn.imd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

public class FileReader {
	public LinkedHashSet<String> getUsers(String filePath) throws IOException {
		LinkedHashSet<String> lhs = new LinkedHashSet<String>();

		InputStream res = Main.class.getResourceAsStream(filePath);
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(res))) {
			String line;
			while((line = bufferedReader.readLine()) != null) {
				lhs.add(line.split(",", 2)[0]);
			}
		}
		
		return lhs;
	}
}
