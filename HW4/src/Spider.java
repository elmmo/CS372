import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap; 
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern; 

public class Spider {
	protected HashMap<String, Boolean> urls; 
	protected Set<String> emails; 
	protected Pattern email; 
	protected Pattern link; 
	protected String baseUrl; 
	protected Stack<Boolean> finished; 
	boolean verbose; 
	SpiderCrawl[] crawlers; 
	
	Spider() {
		baseUrl = "https://www.whitworth.edu"; 
		email = Pattern.compile("\"mailto:(.*?)\""); 
		link = Pattern.compile("<a\\s*?href=\"(.*?)\""); 
	
		// instantiates storage
		urls = new HashMap<String, Boolean>();
		emails = new HashSet<String>(); 
		finished = new Stack<Boolean>(); 
		
		// prompts user for system options
		boolean reasonable = false; 
		Scanner console = new Scanner(System.in); 
		String start = "https://www.whitworth.edu/cms/academics/undergraduate-majors-and-programs/"; 
		// resets starting url if the user wants to specify 
		System.out.print("Would you like to specify starting URL (y/n) ");
		if (console.next().equals("y")) {
			do {
				System.out.print("What URL should the spider start at? ");
				start = console.next(); 
				try {
					URL trialUrl = new URL(start); 
					System.out.println("try" + reasonable);
					reasonable = true; 
				} catch (MalformedURLException e) {
					System.out.println("catch" + reasonable);
					System.out.println("That's not a valid URL! Try again.");
				}
			} while (!reasonable); 
		}
		// one crawler for every 100 urls, with a max of 1000 urls
		int max;
		do {
			System.out.print("How many URLs should the spider crawl? ");
			max = console.nextInt(); 
			reasonable = (max < 1000) ? true : false; 
			if (!reasonable) System.out.println("That number's too high! Try for something lower than 1000.");
		} while (!reasonable); 
		int crawlerCount = max/100; 
		System.out.print("Would you like verbose output? (y/n) ");
		verbose = (console.next().equals("y")) ? true : false; 
		console.close(); 
		
		crawlers = new SpiderCrawl[crawlerCount]; 
		urls.put(start, false); 
		
		for (int i = 0; i < crawlers.length; i++) {
			crawlers[i] = new SpiderCrawl(this, max, verbose); 
		}
		launch(); 
	}
	
	private void launch() {
		for (int i = 0; i < crawlers.length; i++) {
			if (verbose) System.out.println("New crawler entering..."); 
			finished.push(false);
			Thread t = new Thread(crawlers[i]); 
			t.start(); 
			try {
				// ensures that there's at least one url for the incoming crawler to crawl 
				while (urls.size() < 2+i) {
					Thread.sleep(300);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
