import java.net.MalformedURLException;
import java.util.HashMap; 
import java.util.HashSet; 
import java.util.Set; 
import java.util.regex.Pattern; 

public class Spider {
	protected HashMap<String, Boolean> urls; 
	protected Set<String> emails; 
	protected Pattern email; 
	protected Pattern link; 
	protected String baseUrl; 
	
	Spider() {
		baseUrl = "https://www.whitworth.edu"; 
		email = Pattern.compile("\"mailto:(.*?)\""); 
		link = Pattern.compile("<a\\s*?href=\"(.*?)\""); 
	
		// instantiates storage
		urls = new HashMap<String, Boolean>();
		emails = new HashSet<String>(); 
		
		urls.put("https://www.whitworth.edu/cms/academics/undergraduate-majors-and-programs/", false); 
		SpiderCrawl s = new SpiderCrawl(this);  
		try {
			s.run();
		} catch (MalformedURLException e) {
			e.printStackTrace(); 
		}
		
		// prints out every entry in emails 
		System.out.println("\nEMAILS");
		for (String tmp : emails) {
			System.out.println(tmp); 
		}
	}
}
