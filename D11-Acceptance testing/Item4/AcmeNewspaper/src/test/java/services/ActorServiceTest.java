
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.UserAccount;
import security.UserAccountService;
import utilities.AbstractTest;
import domain.Actor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ActorServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ActorService		actorService;
	@Autowired
	private UserAccountService	userAccountService;


	//Supporting services -----------------------------------------------------

	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE ACTOR==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("admin"), null, "Búsqueda del find One correcto"
			},

			//Find one Actor using another role id
			{
				getEntityId("newspaper1"), IllegalArgumentException.class, "Intentando buscar un newspaper"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int actorId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			actorService.findOne(actorId);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("ActorId: " + actorId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverSave() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST SAVE ACTOR==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("user1"), null, "Guardado de Actor correctamente"
			},

			//Try to save one Actor using another entity
			{
				getEntityId("newspaper1"), IllegalArgumentException.class, "Intento de guardar un Actor usando otra id de entidad"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateSave((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateSave(int actorId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			Actor actor = actorService.findOne(actorId);
			actor.setName("TEST NAME");
			actor.setSurnames("TEST SURNAMES");
			actorService.save(actor);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("ActorId: " + actorId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverIsLogged() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST ISLOGGED ACTOR==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				"user1", null, "Comprobar si un Actor está logueado"
			},

			//Positive test
			{
				null, null, "Comprobar si un Actor no está logueado"
			},

			//Try to authenticate using a no existing username
			{
				"noExistingUserName", IllegalArgumentException.class, "Intento de comprobar si un Actor está logueado usando un usuario no existente."
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateIsLogged((String) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateIsLogged(String username, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			authenticate(username);
			actorService.isLogged();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		authenticate(null);
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
	public void driverFindByUserAccount() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FINDBYUSERACCOUNT ==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("userAccount1"), null, "FindByUserAccount correcto"
			},
			//UserAccount param is null
			{
				null, NullPointerException.class, "Se intenta buscar un userAccount nulo"
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
			Actor res = actorService.findByUserAccount(userAccount);
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
		System.out.println("=====================================TEST FINDBYPRINCIPAL ==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {

			//Positive test
			{
				"user1", null, "FindByPrincipal correcto"
			},

			//FindByPrincipal with a different role than User
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
			actorService.findByPrincipal();
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

}
