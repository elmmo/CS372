
import java.util.Date; 
import java.util.Calendar; 

enum Months {
	// enum keeps track of the months and the number of days inside them 
	JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30), JULY(31), AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);
	
	int days; 
	Months(int d) { days = d; }
	static int getNumDays(Months m) { return m.days; }
	/** 
	 * determines whether an item is present in the enum 
	 * @param value	the name of the item to find in the enum 
	 * @return		the index in the enum where the param is located
	 */
	static int indexOf(String value) {
		value = value.toUpperCase();
		for (Months m : Months.values()) {
			if (m.name().equals(value)) return m.ordinal(); 
		}
		return -1; 
	}	
}

public class MyDate {
	int monthInt; 
	String monthName;
	int day; 
	int year;

	// used for passing around values 
	int monthIntInput;
	String monthStrInput; 
	int dayInput; 
	int yearInput; 
	
	/** 
	 * private helper function to set monthName, given an user-inputted int 
	 * @param monthInput	the input to convert to a String 
	 */
	void intToStringMonthAssignment() {
		try {
			if (monthIntInput < 1 || monthIntInput > 12) throw new IllegalArgumentException(monthIntInput + " isn't a valid month"); 
			monthInt = monthIntInput-1; 
			Months month = Months.values()[monthInt]; 
			monthName = month.toString(); 
			// brings every letter except the first to lowercase 
			monthName = monthName.substring(0, 1) + monthName.substring(1, monthName.length()).toLowerCase(); 
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * private helper function to set monthName, given an user-inputed String 
	 */
	void stringToIntMonthAssignment() {
		try {
			// month assignment
			monthInt = Months.indexOf(monthStrInput); 
			if (monthInt == -1) throw new IllegalArgumentException(monthIntInput + " isn't a valid month."); 
			monthName = monthStrInput; 
			monthName = monthName.substring(0, 1) + monthName.substring(1, monthName.length()).toLowerCase(); 
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace(); 
		}
	}
	
	/**
	 * used to assign day and year values  
	 * @param day	the inputed day of the month 
	 * @param year	the inputed year 
	 */
	void dayAndYearAssignment() {
		try {
			// day assignment 
			// takes the max number of days from the enum 
			int maxDays = Months.getNumDays(Months.valueOf(monthName.toUpperCase())); 
			
			if (dayInput < 1 || dayInput > maxDays) throw new IllegalArgumentException(dayInput + " isn't a valid day in " + monthName); 
			day = dayInput; 
			
			// year assignment
			if (((Integer)yearInput).toString().length() != 4) throw new IllegalArgumentException(year + " isn't a valid year."); 
			year = yearInput; 
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * default constructor creates a myDate object for today's date 
	 */
	MyDate() {
		Date date = new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); 
		monthIntInput = cal.get(Calendar.MONTH)+1; 
		intToStringMonthAssignment(); 
		day = cal.get(Calendar.DAY_OF_MONTH); 
		year = cal.get(Calendar.YEAR); 
	}
	
	/** 
	 * Constructor with all int inputs 
	 * @param monthInput	the month to create a date for 
	 * @param day			the day to create a date for 
	 * @param year			the year to create a date for 
	 */
	MyDate(int monthInput, int dayInput, int yearInput) {
		this.monthIntInput = monthInput; 
		this.dayInput = dayInput; 
		this.yearInput = yearInput; 
		intToStringMonthAssignment(); 
		dayAndYearAssignment(); 
	}
	
	/** 
	 * Constructor with a String month input
	 * @param month	the month to create a date for 
	 * @param day	the day to create a date for 
	 * @param year	the year to create a date for 
	 */
	MyDate(String monthInput, int dayInput, int yearInput) {
		this.monthStrInput = monthInput; 
		this.dayInput = dayInput; 
		this.yearInput = yearInput; 
		stringToIntMonthAssignment(); 
		dayAndYearAssignment(); 
	}
	
	// getters 
	public static MyDate getCurrentDate() { return new MyDate(); }
	public int getMonthNum() { return monthInt; }
	public String getMonth() { return monthName; }
	public int getDay() { return day; }
	public int getYear() { return year; }
	
	// setters 
	public void setMonthByNum(int num) { 
		monthIntInput = num-1; 
		intToStringMonthAssignment(); 
	}
	public void setMonthByName(String name) { 
		monthStrInput = name; 
		stringToIntMonthAssignment(); 
	}
	public void setDay(int input) {
		dayInput = input; 
		dayAndYearAssignment(); 
	}
	public void setYear(int num) { 
		this.yearInput = num; 
		dayAndYearAssignment(); 
	}
	
	public String toString() {
		return monthName + " " + day + ", " + year;
	}
	
	/** 
	 * calculates the difference in days between one date and another 
	 * @param from	the date to start counting at 
	 * @param to	the date to end at 
	 * @return
	 */
	public static int difference(MyDate from, MyDate to) {
		int total = 0; 
		int fromDays = Months.getNumDays(Months.valueOf(from.getMonth().toUpperCase())); 
		int fromNum = from.getMonthNum(); 
		int toNum = to.getMonthNum(); 
		
		if (from.getMonth().equals(to.getMonth())) {
			total = to.getDay()-from.getDay();
		} else {
			total += (fromDays - from.getDay()) + to.getDay(); 
			// if the months aren't adjacent
			if (fromNum+1 != toNum) {
				int monthsBetween = toNum-fromNum-1; 
				for (int i = 0; i < monthsBetween; i++) {
					int increment = fromNum++; 
					if (increment == 12) {
						fromNum = 0; 
					}
					total += Months.getNumDays(Months.values()[fromNum]); 
				}
			}
		}
		
		if (total == 1) {
			System.out.printf("There is %d day between %s and %s\n", total, from.toString(), to.toString());
		} else {
			System.out.printf("There are %d days between %s and %s\n", total, from.toString(), to.toString());
		}
		return total; 
	}
	
	/** 
	 * Returns a date a set number of days in the future 
	 * @param n		the number of days in the future to project the date
	 * @return		the date in the future 
	 */
	public MyDate future(int n) {
		int current = 0; 
		int daysLeft = Months.getNumDays(Months.valueOf(monthName.toUpperCase())) - day; 
		int futureMonth = monthInt; 
		int futureDay = day; 
		int futureYear = year; 
		
		while (n > daysLeft) {
			if (n >= 365) {
				futureYear++; 
				n -= 365; 
			}
			if (n > daysLeft) {
				// add month wrap around 
				futureMonth++; 
				n -= daysLeft; 
				daysLeft = Months.getNumDays(Months.values()[futureMonth]); 
			}
			futureDay = 0; 
		}
		futureDay += n; 
		
		return new MyDate(futureMonth+1, futureDay-1, futureYear); 
	}
}
