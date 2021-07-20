package ufrn.imd.dim0124;

import java.util.List;

public class SearchWord implements Runnable {
	private int range;
	private List<String> list;
	private String username_to_search;
	
	private String username_best_distance = "oto";
	private int value_best_distance = 100;
	
	public SearchWord(List<String> list, String username_to_search, int range) {
		this.list = list;
		this.username_to_search = username_to_search;
		this.range = range;
	}
	
	public SearchWord() {}
	
	public String getUsername_best_distance() {
		return this.username_best_distance;
	}

	public String search(List<String> list, String username_to_search, int total_threads) {
		this.list = list;
		this.username_to_search = username_to_search;
		this.range = (int)Math.ceil(list.size() / total_threads);
		
		SearchWord sw = new SearchWord(list, username_to_search, range);
		
		for(int i = 0; i <= total_threads; i++) {
			Thread t = new Thread(sw);
			t.setName(""+i);
			t.start();
		}
		
		try {
			Thread.currentThread().join(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sw.getUsername_best_distance();
	}
	
	public void run() {
		runThread();
	}

	private void runThread() {
		LevenshteinDistance ld = new LevenshteinDistance();
		int current_thread = Integer.parseInt(Thread.currentThread().getName());
		for(int j = current_thread * range; j <= ((current_thread + 1) * range) - 1; j++) {
			if(j >= list.size()) {
				break;
			}

			String current_username = list.get(j);
    		int current_distance = ld.calculate(username_to_search, current_username);
    		
    		checkAndSetNewBestDistance(current_distance, current_username, current_thread, j);
		}
	}
	
	private synchronized void checkAndSetNewBestDistance(int current_distance, String current_username, int current_thread, int order) {
		if (current_distance < value_best_distance) {
			this.value_best_distance = current_distance;
			this.username_best_distance = current_username;
		}
	}
}
