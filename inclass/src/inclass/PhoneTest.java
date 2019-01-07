package inclass;

import java.util.Random; 

public class PhoneTest {

	public static void main(String[] args) {
		Phone.callSimulator(); 
		
		Phone[] phones = { new Landline(), 
				new Mobile(), 
				new Smart(), 
				new Mobile(), 
				new Smart()
		}; 
	}
}

