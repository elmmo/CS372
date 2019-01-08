package inclass;

public class Mobile extends Phone implements Textable {
	public boolean sendText() { return true; }
	
	public void answer(Phone src) {}
	
	public void ignore(Phone src) {}
}
