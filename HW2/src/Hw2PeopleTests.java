import org.junit.Before;
import org.junit.Test;
import org.junit.Rule; 
import org.junit.rules.ExpectedException;
import org.junit.Assert; 
import java.math.BigDecimal; 

public class Hw2PeopleTests {
	Person jasmin; 

	@Before
	public void setUp() throws Exception {
		jasmin = new Person("Jasmin", 19, "253-370-9999", 0.00); 
	}
	
	@Rule 
	public ExpectedException thrown = ExpectedException.none();
	
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
	
	@Test 
	public void testPoliceDefaultConstructor() {
		Person joe = new Police(); 
		Assert.assertTrue(((Police)joe).getId() >= 0 && ((Police)joe).getId() <= 1000);
	}
	
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
	
	@Test 
	public void testWithdrawExceptions() {
		thrown.expect(IllegalArgumentException.class);
		jasmin.withdrawMoney(-800.00);
	}
	
	@Test 
	public void testDepositExceptions() {
		thrown.expect(IllegalArgumentException.class);
		jasmin.depositMoney(-100.00); 
	}
	
	@Test 
	public void testPayEmployee() {
		// implement
	}
	
	@Test 
	public void testKidAgeException() {
		thrown.expect(IllegalArgumentException.class);
		Kid kiddo = new Kid("Kiddo", 19, "306-475-9722", 0.00, "Snickers"); 
	}
}
