import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher; 

public class SpiderCrawl implements Runnable {
	final int BREAK_AT = 500; 
	final String[] DONT_ACCEPT = {"form", "#", "?", "mailto", "tel"}; 
	Spider master; 
	BufferedReader bReader; 
	URL url;
	Matcher emailMatcher; 
	Matcher linkMatcher; 
	int index; 
	
	SpiderCrawl(Spider master, int index) {
		this.master = master; 
		this.index = index; 
	}
	
	public void run() {
		System.setProperty("http.agent", "Chrome");
		try {
			if (getNextUrl() != null) {
				bReader = new BufferedReader(new InputStreamReader(url.openStream())); 
				String line; 
				while ((line = bReader.readLine()) != null) {
					emailMatcher = master.email.matcher(line); 
					if (emailMatcher.find()) {
						addEmail(emailMatcher.group(1)); 
					}
					handleUrls(line); 
				}
				bReader.close(); 
				}
			} catch (FileNotFoundException e) {
				putUrl(url.toString(), true); 
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				putUrl(url.toString(), true); 
			}
			setUpRestart(); 
		}
	
	private void handleUrls(String line) {
		linkMatcher = master.link.matcher(line); 
		// if there's a link tag found
		if (linkMatcher.find()) {
			String match = linkMatcher.group(1); 
			
			// make sure that it's not redirecting to the same page
			if (match.length() > 0 && master.urls.size() < BREAK_AT) {
				String query = ""; 
				if (match.charAt(0) == '/') query += master.baseUrl; 
				query += match; 
				if (verifyUrl(query)) {
					//System.out.println(query);
					putUrl(query, false); 
				}
			}
		}
	}
	
	private boolean verifyUrl(String query) {
		for (int i = 0; i < DONT_ACCEPT.length; i++) {
			if (query.contains(DONT_ACCEPT[i])) return false; 
			if (master.urls.containsKey(query)) return false; 
		}
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
	
	private void putUrl(String key, Boolean value) {
		master.urls.put(key, value); 
	}
	
	private void addEmail(String entry) {
		int dot = entry.lastIndexOf("."); 
		String toplevel = entry.substring(dot, dot+4); 
		master.emails.add(entry.substring(0, dot) + toplevel); 
	}
	
	private void setUpRestart() {
		SpiderCallbacks sc = new SpiderCallbacks() {
			public void report() {
				System.out.println("\nEMAILS");
				for (String e : master.emails) {
					System.out.println(e); 
				}
			}
			
			public void verify() {
				try {
					if (master.urls.size() < BREAK_AT) {
						run(); 
					} else {
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
		
		sc.verify(); 
	}
}
