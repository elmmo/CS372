import org.junit.Before;
import org.junit.Test;
import org.junit.Rule; 
import org.junit.rules.ExpectedException;
import org.junit.Assert; 
import java.math.BigDecimal; 

/** 
 * Unit tests for the City simulation 
 * @author Elizabeth Min
 */
public class Hw2PeopleTests {
	Person jasmin; // a dummy used for testing functions 

	/** 
	 * Sets up the function with the test dummy 
	 * @throws Exception if the creation of the dummy fails 
	 */
	@Before
	public void setUp() throws Exception {
		jasmin = new Person("Jasmin", 19, "253-370-9999", 0.00); 
	}
	
	/** 
	 * Setting up functionality for testing that an exception is thrown 
	 */
	@Rule 
	public ExpectedException thrown = ExpectedException.none();
	
	/** 
	 * Making sure that people can be created and their values accessed 
	 */
	@Test
	public void testPersonConstructors() {
		Person defaultPerson = new Person(); 
		BigDecimal defaultValue = new BigDecimal(0.00); 
		
		// default 
		Assert.assertEquals("Default name failure", "John Doe", defaultPerson.getName());
		Assert.assertEquals("Default getMoneyInBank failure", defaultValue, defaultPerson.getMoneyInBank());
		
		// arg
		Assert.assertEquals("Arg name failure", "Jasmin", jasmin.getName());
		Assert.assertEquals("Arg age failure", 19, jasmin.getAge());
		Assert.assertEquals("Arg phoneNum failure", "253-370-9999", jasmin.getPhoneNum());
		Assert.assertEquals("Arg getMoneyInBank failure", defaultValue, jasmin.getMoneyInBank());
	}
	
	/** 
	 * Tests the police constructor to make sure that the random id assignments are working 
	 */
	@Test 
	public void testPoliceDefaultConstructor() {
		Person joe = new Police(); 
		Assert.assertTrue(((Police)joe).getId() >= 0 && ((Police)joe).getId() <= 1000);
	}
	
	/** 
	 * Tests the money functions in the Person class to make sure that the arithmetic and double-to-BigDecimal conversions are working 
	 */
	@Test 
	public void testMoneyHandling() {
		BigDecimal money = new BigDecimal(200.00); 
		jasmin.depositMoney(200.00); 
		Assert.assertEquals("Depositing money", money, jasmin.getMoneyInBank()); 
		
		money = new BigDecimal(100.00); 
		jasmin.withdrawMoney(100.00); 
		Assert.assertEquals("Withdrawing money", money, jasmin.getMoneyInBank());
		
		Assert.assertEquals("Insufficient funds: withdraw", money, jasmin.withdrawMoney(500.00));
	}
	
	/** 
	 * Tests to make sure that the withdraw money function will throw an error if an illegal sum is queried 
	 */
	@Test 
	public void testWithdrawExceptions() {
		thrown.expect(IllegalArgumentException.class);
		jasmin.withdrawMoney(-800.00);
	}
	
	/**
	 * Tests to make sure that the deposit money function will throw an error if an illegal sum is queried 
	 */
	@Test 
	public void testDepositExceptions() {
		thrown.expect(IllegalArgumentException.class);
		jasmin.depositMoney(-100.00); 
	}
	
	/** 
	 * Tests to make sure that the pay function is distributing money correctly 
	 */
	@Test 
	public void testPayEmployee() {
		Police boss = new Police("Joe", 42, "253-419-4129", 200.00, PoliceRole.CHIEF); 
		Police employee = new Police("Fred", 22, "438-452-5943", 50.00, PoliceRole.PATROL); 
		boss.payEmployee(employee, 78.00); 
		Assert.assertEquals("Police payEmployee", new BigDecimal(128.00), employee.getMoneyInBank()); 
	}
	
	/** 
	 * Tests to see if an exception will be thrown if a kid enters an age that's too high 
	 */
	@Test 
	public void testKidAgeException() {
		thrown.expect(IllegalArgumentException.class);
		Kid kiddo = new Kid("Kiddo", 19, "306-475-9722", 0.00, Candy.SOUR_APPLE); 
	}
}
