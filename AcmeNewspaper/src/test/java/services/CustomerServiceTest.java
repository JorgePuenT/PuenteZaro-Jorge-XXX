
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.UserAccount;
import security.UserAccountService;
import utilities.AbstractTest;
import domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CustomerServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private CustomerService		customerService;

	//Supporting services -----------------------------------------------------
	@Autowired
	private UserAccountService	userAccountService;
	@Autowired
	private NewspaperService	nwService;
	@Autowired
	private VolumeService		vService;


	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE CUSTOMER==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("customer1"), null, "Búsqueda del find One correcto"
			},

			//Find one newspaper using another role id
			{
				getEntityId("newspaper1"), IllegalArgumentException.class, "Intentando buscar un newspaper"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int customerid, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			customerService.findOne(customerid);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Customerid: " + customerid);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverFindByUserAccount() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FINDBYUSERACCOUNT CUSTOMER==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("userAccount11"), null, "FindByUserAccount correcto"
			},
			//UserAccount param is null
			{
				null, NullPointerException.class, "Se intenta buscar un userAccount nulo"
			},
			//UserAccount param belongs to a different role than Customer
			{
				getEntityId("userAccount2"), IllegalArgumentException.class, "Se intenta buscar un userAccount que no es de Customer"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindByUserAccount((Integer) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindByUserAccount(Integer entityId, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			UserAccount userAccount = null;

			userAccount = userAccountService.findOne(entityId);
			Customer res = customerService.findByUserAccount(userAccount);
			Assert.notNull(res);

		} catch (Throwable oops) {

			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("UserAccount: " + entityId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverFindByPrincipal() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FINDBYPRINCIPAL CUSTOMER==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {

			//Positive test
			{
				"customer1", null, "FindByPrincipal correcto"
			},

			//FindByPrincipal with a different role than Customer
			{
				"user1", null, "Se intenta logear con un user"
			},
			//FindByPrincipal being anonymous
			{
				null, IllegalArgumentException.class, "Se intenta logear con un null"
			}

		};

		for (int i = 0; i < testingData.length; i++)
			templateFindByPrincipal((String) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindByPrincipal(String username, Class<?> expected, String explanation) {

		Class<?> caught = null;
		try {

			this.authenticate(username);
			customerService.findByPrincipal();
			this.unauthenticate();

		} catch (Throwable oops) {

			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Username: " + username);

		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

	@Test
	public void driverFindAll() {

		Object testingData[][] = {
			//Positive test
			{
				null, "FindAll correcto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateAll((Class<?>) testingData[i][0], (String) testingData[i][1]);
	}

	protected void templateAll(Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			customerService.findAll();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverCreate() {

		Object testingData[][] = {
			//Positive test

			{
				null, "Creacion correcta del Customer"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((Class<?>) testingData[i][0], (String) testingData[i][1]);
	}

	protected void templateCreate(Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			customerService.create();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("ExplicaciÛn: " + explanation);
		System.out.println("\røCorrecto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverSave() {

		Object testingData[][] = {
			//Positive test

			{
				0, "name", ConstraintViolationException.class, "Creacion correcta del customer"
			}, {
				getEntityId("customer1"), "name", IllegalArgumentException.class, "No se puede modificar"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateSave((Integer) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3]);
	}

	protected void templateSave(Integer aId, String name, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			if (aId > 0) {
				domain.Customer c = customerService.findOne(aId);
				c.setName(name);
				customerService.save(c);
			} else {
				Customer c = customerService.create();
				customerService.save(c);
			}
		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("\røCorrecto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverisSubscribed() {

		Object testingData[][] = {
			//Positive test

			{
				null, "Creacion correcta", getEntityId("newspaper1"), "customer1"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateisSubscribed((Class<?>) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2], (String) testingData[i][3]);
	}

	protected void templateisSubscribed(Class<?> expected, String explanation, Integer nwId, String rol) {

		Class<?> caught = null;

		try {
			this.authenticate(rol);
			customerService.isSubscribed(nwService.findOne(nwId));
			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("ExplicaciÛn: " + explanation);
		System.out.println("\røCorrecto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverisSubscribedVolume() {

		Object testingData[][] = {
			//Positive test

			{
				null, "Creacion correcta", getEntityId("volume1"), "customer1"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateisSubscribedVolume((Class<?>) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2], (String) testingData[i][3]);
	}

	protected void templateisSubscribedVolume(Class<?> expected, String explanation, Integer nwId, String rol) {

		Class<?> caught = null;

		try {
			this.authenticate(rol);
			customerService.isSubscribedVolume(vService.findOne(nwId));
			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("ExplicaciÛn: " + explanation);
		System.out.println("\røCorrecto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

}
