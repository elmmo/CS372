package inclass;

public class Smart extends Phone implements Textable {
	public enum OS { Android, iOS }; 
	OS os; 
	
	public Smart() {
		this.os = OS.Android; 
	}
	
	public Smart(OS os) {
		this.os = os; 
	}
	
	public boolean sendText() {
		return true; 
	}
}
