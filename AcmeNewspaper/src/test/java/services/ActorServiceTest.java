
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	private ActorService	actorService;


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
		
		if(expected == null)
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
		
		if(expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Username: " + username);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

}
