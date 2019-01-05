
public class MyDateTest {

	public static void main(String[] args) {
		MyDate stringDate = new MyDate("January", 15, 2019); 
		System.out.printf("stringDate: %s\n", stringDate.toString());
		
		MyDate numDate = new MyDate(2, 11, 2019); 
		System.out.printf("numDate: %s\n", numDate.toString());
		
		MyDate current = MyDate.getCurrentDate(); 
		System.out.printf("Today's date is %s\n", current.toString());
		
		stringDate.setMonthByNum(12);
		System.out.printf("stringDate: %s\n", stringDate.toString());
		
		numDate.setMonthByName("March");
		System.out.printf("numDate: %s\n", numDate.toString());
		
		numDate.setDay(14);
		System.out.printf("numDate: %s\n", numDate.toString());
		
		stringDate.setYear(2019);
		System.out.printf("stringDate: %s\n", stringDate.toString());
		
		MyDate testOne = new MyDate(4, 3, 2019); 
		MyDate testTwo = new MyDate(11, 11, 2019); 
		MyDate.difference(testOne, testTwo); 
		
		testOne.setMonthByName("May");
		testTwo.setMonthByName("October");
		testOne.setDay(27); 
		testTwo.setDay(5);
		MyDate.difference(testOne, testTwo); 
		
		testOne.setMonthByName("December");
		testTwo.setMonthByName("January");
		testOne.setDay(31); 
		testTwo.setDay(1);
		testTwo.setYear(2020);
		MyDate.difference(testOne, testTwo); 
		
		testOne.setMonthByName("December");
		testTwo.setMonthByName("January");
		testOne.setDay(25); 
		testTwo.setDay(30);
		MyDate.difference(testOne, testTwo); 
		
		MyDate future = current.future(450); 
		System.out.println("450 days from now, " + current.toString() + " it will be " + future.toString());
		
		future = current.future(900); 
		System.out.println("900 days from now, " + current.toString() + ", it will be " + future.toString()); 
	}

}
