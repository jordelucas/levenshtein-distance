package ufrn.imd;

import java.util.List;

public class SearchWord implements Runnable {
	private int range;
	private List<String> list;
	private String usernameToSearch;
	
	private String usernameBestDistance = "";
	private int valueBestDistance = 100;
	
	public SearchWord(List<String> list, String usernameToSearch, int range) {
		this.list = list;
		this.usernameToSearch = usernameToSearch;
		this.range = range;
	}
	
	public SearchWord() {}
	
	public String getUsernameBestDistance() {
		return this.usernameBestDistance;
	}

	public String betterDistance(List<String> list, String usernameToSearch, int total_threads) {
		this.list = list;
		this.usernameToSearch = usernameToSearch;
		this.range = (int)Math.ceil(list.size() / total_threads);
		
		SearchWord sw = new SearchWord(list, usernameToSearch, range);
		
		for(int i = 0; i <= total_threads; i++) {
			Thread t = new Thread(sw);
			t.setName(""+i);
			t.start();
		}
		
		try {
			Thread.currentThread().join(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return sw.getUsernameBestDistance();
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

			String currentUsername = list.get(j);
    		int currentDistance = ld.calculate(usernameToSearch, currentUsername);
    		
    		checkAndSetNewBestDistance(currentDistance, currentUsername);
		}
	}
	
	private synchronized void checkAndSetNewBestDistance(int currentDistance, String currentUsername) {
		if (currentDistance < valueBestDistance) {
			this.valueBestDistance = currentDistance;
			this.usernameBestDistance = currentUsername;
		}
	}
}
