
package services;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Chirp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ChirpServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ChirpService	chirpService;


	//Supporting services -----------------------------------------------------

	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE CHIRP==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("chirp1"), null, "Búsqueda del find One correcto"
			},

			//Find one newspaper using another role id
			{
				getEntityId("newspaper1"), IllegalArgumentException.class, "Intentando buscar un newspaper"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int chirpId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			chirpService.findOne(chirpId);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("ChirpId: " + chirpId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverCreate() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST CREATE CHIRP=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"user1", null, "Creación correcta de un chirp"
			},
			//Intento de crear con un rol que no debe
			{
				"customer1", IllegalArgumentException.class, "Se ha intentado crear un chirp con un customer1"
			},
			//Create sin logearse
			{
				null, IllegalArgumentException.class, "Intento de crear un chirp sin estar logeado"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}
	protected void templateCreate(String rol, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			super.authenticate(rol);
			chirpService.create();
			super.unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("-----------------POSITIVO------------");
		else
			System.out.println("-----------------NEGATIVO------------");
		System.out.println("Explicacion: " + explanation);
		System.out.println("Rol: " + rol);
		System.out.println("\r¿Correcto? " + (expected == caught));
	}

	@Test
	public void driverFindAll() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ALL CHIRP=======================================================");
		System.out.println("===============================================================================================================\r");
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
			chirpService.findAll();

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
	public void driverSave() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST SAVE CHIRP==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"user1", 0, "titulo", new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(10)), "descripcion", false, null, "Guardado correcto de Chirp"
			}, {
				"user1", getEntityId("chirp1"), null, null, null, false, IllegalArgumentException.class, "Guardar un  chirp con id distinta de 0"
			}, {
				null, 0, "titulo", new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(10)), "descripcion", false, IllegalArgumentException.class, "No hay nadie logueado"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateSave((String) testingData[i][0], (Integer) testingData[i][1], (String) testingData[i][2], (Date) testingData[i][3], (String) testingData[i][4], (Boolean) testingData[i][5], (Class<?>) testingData[i][6], (String) testingData[i][7]);
	}
	protected void templateSave(String rol, Integer chirpId, String title, Date createMoment, String description, Boolean inappropriate, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			authenticate(rol);

			if (chirpId > 0) {
				Chirp c = chirpService.findOne(chirpId);
				c.setTitle(title);
				chirpService.save(c);
			} else {
				Chirp c = chirpService.create();
				c.setTitle(title);
				c.setDescription(description);
				c.setCreationMoment(createMoment);
				c.setInappropriate(inappropriate);

			}

			authenticate(null);
			this.chirpService.flush();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("chirpId: " + chirpId);
		System.out.println("Title: " + title);
		System.out.println("Rol: " + rol);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

	@Test
	public void driverGetTimeline() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST GET TIME LINE=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				"user1", null, "getTimeline correcto"
			}, {
				"admin", IllegalArgumentException.class, "no es un usuario"
			}, {
				null, IllegalArgumentException.class, "no es un usuario"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateGetTimeline((String) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateGetTimeline(String username, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			this.authenticate(username);
			chirpService.getTimeline();
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
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverFindAllTaboo() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ALL TABOO CHIRP=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				null, "FindAll taboo correcto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateAll((Class<?>) testingData[i][0], (String) testingData[i][1]);
	}

	protected void driverFindAllTaboo(Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			chirpService.findAllTaboo();

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
	public void driverMarkAsInappropriate() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST MARK INAPPROPRIATE CHIRP==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"admin", getEntityId("chirp1"), null, "marcado con inapropiado de forma correcta"
			}, {
				"user1", getEntityId("chirp1"), IllegalArgumentException.class, "usuario intenta marcar como inapropiado"
			}, {
				"admin", getEntityId("article1"), IllegalArgumentException.class, "Recibe un tipo distinto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateMarkAsInappropriate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3]);
	}
	protected void templateMarkAsInappropriate(String rol, Integer chirpId, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			authenticate(rol);

			chirpService.markAsInappropriate(chirpId);

			authenticate(null);
			this.chirpService.flush();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("chirpId: " + chirpId);
		System.out.println("Rol: " + rol);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

}
