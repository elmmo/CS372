import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher; 

/** 
 * Web crawlers that are launched by a parent Spider 
 * @author Elizabeth Min
 */
public class SpiderCrawl implements Runnable {
	final int BREAK_AT; 
	final String[] DONT_ACCEPT = {"form", "#", "?", "mailto", "tel"}; 
	Spider master; 
	BufferedReader bReader; 
	URL url;
	Matcher emailMatcher; 
	Matcher linkMatcher; 
	boolean verbose; 
	
	/** 
	 * Stores user input preferences and a way to access the parent spider 
	 * @param master	the parent Spider that the crawler is running from 
	 * @param max		the number of urls that the crawlers will traverse total before quitting 
	 * @param verbose	whether or not the system should produce output as it's running 
	 */
	SpiderCrawl(Spider master, int max, boolean verbose) {
		this.master = master; 
		this.BREAK_AT = max; 
		this.verbose = verbose; 
	}
	
	/** 
	 * Reads one webpage looking for URLs, then attempts to move to the next unexplored URL
	 */
	public void run() {
		System.setProperty("http.agent", "Chrome");
		try {
			if (getNextUrl() != null) {
				bReader = new BufferedReader(new InputStreamReader(url.openStream())); 
				String line; 
				while ((line = bReader.readLine()) != null) {
					// looking for eamisl 
					emailMatcher = master.email.matcher(line); 
					if (emailMatcher.find()) {
						addEmail(emailMatcher.group(1)); 
					}
					// looks for urls 
					handleUrls(line); 
				}
				bReader.close(); 
				}
			} catch (FileNotFoundException e) {
				// if the url file isn't found, then its status in the list of URLs is changed with no further action
				putUrl(url.toString(), true); 
			} catch (MalformedURLException e) {
				// if the url is unvisitable, it prints the error 
				e.printStackTrace();
			} catch (IOException e) {
				// if there's another problem with the url, then its status in the list of URLs is changed with no further action
				putUrl(url.toString(), true); 
			}
			setUpRestart(); 
		}
	
	/** 
	 * Input validation for the URLs found on the webpage 
	 * @param line	the line on the webpage to examine
	 */
	private void handleUrls(String line) {
		linkMatcher = master.link.matcher(line); 
		// if there's a link tag found
		if (linkMatcher.find()) {
			String match = linkMatcher.group(1); 
			
			// make sure that it's not redirecting to the same page
			if (match.length() > 0 && master.urls.size() < BREAK_AT) {
				String query = ""; 
				// if the link is a partial path 
				if (match.charAt(0) == '/') query += master.baseUrl; 
				query += match; 
				if (verifyUrl(query)) {
					if (verbose) System.out.println("Scraping " + query);
					putUrl(query, false); 
				}
			}
		}
	}
	
	/** 
	 * Helper function for handleUrls() that verifies that the URL is valid 
	 * @param query	the link to examine
	 * @return		whether the link is valid 
	 */
	private boolean verifyUrl(String query) {
		for (int i = 0; i < DONT_ACCEPT.length; i++) {
			// if the url contains any invalid characters 
			if (query.contains(DONT_ACCEPT[i])) return false; 
			// if the url has already been entered 
			if (master.urls.containsKey(query)) return false; 
		}
		// ensures that the query is a whole url after adding the base path
		return (query.contains("http")); 
	}
	
	/** 
	 * Iterates over every entry in the hashmap and sets url to the url to use next 
	 * @return	the next URL to crawl through, or null if there are no remaining entries to be crawled
	 */
	private URL getNextUrl() throws MalformedURLException {
		for (Map.Entry<String, Boolean> entry : master.urls.entrySet()) {
			if (entry.getValue() == false) {
				putUrl(entry.getKey(), true); 
				String key = entry.getKey(); 
				url = new URL(entry.getKey()); 
				return url; 
			}
		}
		return null; 
	}
	
	/** 
	 * Helper function for adding to the url hashmap in the Spider parent 
	 * @param key	the key to the hashmap 
	 * @param value	the value for the hashmap 
	 */
	private void putUrl(String key, Boolean value) {
		master.urls.put(key, value); 
	}
	
	/** 
	 * Helper function for adding to the email set in the Spider parent 
	 * @param entry	the email that has been detected by the scraper 
	 */
	private void addEmail(String entry) {
		int dot = entry.lastIndexOf("."); 
		String toplevel = entry.substring(dot, dot+4); 
		master.emails.add(entry.substring(0, dot) + toplevel); 
	}
	
	/** 
	 * Controls the callback functions that detect when the spider needs a new url or is done altogether 
	 */
	private void setUpRestart() {
		SpiderCallbacks sc = new SpiderCallbacks() {
			/** 
			 * Prints out a list of emails when all the crawlers have finished running 
			 */
			public void report() {
				System.out.println("\nEMAILS");
				if (master.emails.size() == 0) {
					System.out.println("No emails to show :(");
				} else {
					for (String e : master.emails) {
						System.out.println(e); 
					}
				}
			}
			
			/** 
			 * Determines what to do when the crawler has finished scraping its current webpage 
			 */
			public void onComplete() {
				// Restarts the crawler if not all urls that are stored have been visited yet
				try {
					if (master.urls.values().contains(false)) {
						run(); 
					} else {
						// If all urls have been visited, it shuts down the crawler
						master.finished.pop(); 
						if (master.finished.isEmpty()) {
							report(); 
						}
					}
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}
		}; 
		
		sc.onComplete(); 
	}
}
