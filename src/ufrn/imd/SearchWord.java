package ufrn.imd;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class SearchWord {

	private String username_best_distance = "";
	private int value_best_distance = 100;

	public String betterDistance(LinkedHashSet<String> list, String username_to_search) {
		Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
    		String current_username = itr.next();
    		int current_distance = LevenshteinDistance.calculate(username_to_search, current_username);
    		if (current_distance < value_best_distance) {
    			this.value_best_distance = current_distance;
    			this.username_best_distance = current_username;
    			
    			if(current_distance == 0) {
    				break;
    			}
    		}
        }

		return this.username_best_distance;
	}
}
