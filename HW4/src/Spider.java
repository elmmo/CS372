import java.net.MalformedURLException;
import java.util.HashMap; 
import java.util.HashSet;
import java.util.Queue;
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
	SpiderCrawl[] crawlers; 
	
	Spider() {
		baseUrl = "https://www.whitworth.edu"; 
		email = Pattern.compile("\"mailto:(.*?)\""); 
		link = Pattern.compile("<a\\s*?href=\"(.*?)\""); 
	
		// instantiates storage
		urls = new HashMap<String, Boolean>();
		emails = new HashSet<String>(); 
		crawlers = new SpiderCrawl[5]; 
		finished = new Stack<Boolean>(); 
		
		urls.put("https://www.whitworth.edu/cms/academics/undergraduate-majors-and-programs/", false); 
		
		for (int i = 0; i < crawlers.length; i++) {
			crawlers[i] = new SpiderCrawl(this, i); 
		}
		launch(); 
	}
	
	private void launch() {
		for (int i = 0; i < crawlers.length; i++) {
			System.out.println("Entering"); 
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
