
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class AdvertisementServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private AdvertisementService	advertisementService;
	@Autowired
	private NewspaperService		nwService;


	//Supporting services -----------------------------------------------------

	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE ADVERTISEMENT==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("advertisement1"), null, "Búsqueda del find One correcto"
			},

			//Find one newspaper using another role id
			{
				getEntityId("user1"), IllegalArgumentException.class, "Intentando buscar un user"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int advertisementId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			advertisementService.findOne(advertisementId);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("NewpspaperId: " + advertisementId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverCreate() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST CREATE ADVERTISEMENT==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"agent1", null, "Creación correcta de un newspaper"
			},
			//Intento de crear con un rol que no debe
			{
				"customer1", IllegalArgumentException.class, "Se ha intentado crear un newspaper con un customer1"
			},
			//Create sin logearse
			{
				null, IllegalArgumentException.class, "Intento de crear un newspaper sin estar logeado"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}
	protected void templateCreate(String rol, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			super.authenticate(rol);
			advertisementService.create();
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
		System.out.println("=====================================TEST FIND ALL ADVERTISEMENT=======================================================");
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
			advertisementService.findAll();

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

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Test
	 * public void driverSave() {
	 * System.out.println("===============================================================================================================");
	 * System.out.println("=====================================TEST SAVE ADVERTISEMENT==================================================");
	 * System.out.println("===============================================================================================================\r");
	 * 
	 * Object testingData[][] = {
	 * //Test positivo
	 * {
	 * 0, "agent1", null, "Guardado correcto de un advertisement"
	 * }
	 * 
	 * , {
	 * getEntityId("advertisement2"), "agent1", IllegalArgumentException.class, "Se intenta gusradar algo con id distinta de 0"
	 * }, {
	 * 0, null, IllegalArgumentException.class, "nadie logueado"
	 * }
	 * 
	 * 
	 * };
	 * for (int i = 0; i < testingData.length; i++)
	 * templateSave((Integer) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3]);
	 * }
	 * 
	 * protected void templateSave(Integer idAd, String rol, Class<?> expected, String explanation) {
	 * Class<?> caught = null;
	 * try {
	 * authenticate(rol);
	 * if (idAd == 0) {
	 * advertisementService.save(advertisementService.create());
	 * } else {
	 * advertisementService.save(advertisementService.findOne(idAd));
	 * }
	 * authenticate(null);
	 * this.advertisementService.flush();
	 * } catch (Throwable oops) {
	 * caught = oops.getClass();
	 * }
	 * 
	 * checkExceptions(expected, caught);
	 * if (expected == null)
	 * System.out.println("---------------------------- POSITIVO ---------------------------");
	 * else
	 * System.out.println("---------------------------- NEGATIVO ---------------------------");
	 * System.out.println("Explicación: " + explanation);
	 * System.out.println("id: " + idAd);
	 * System.out.println("Rol: " + rol);
	 * System.out.println("\r¿Correcto? " + (expected == caught));
	 * System.out.println("-----------------------------------------------------------------\r");
	 * }
	 */
	@Test
	public void driverMarkAsInappropriate() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST MARK INAPPROPRIATE CHIRP==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"admin", getEntityId("advertisement1"), null, "marcado con inapropiado de forma correcta"
			}, {
				"user1", getEntityId("advertisement1"), IllegalArgumentException.class, "usuario intenta marcar como inapropiado"
			}, {
				"admin", getEntityId("article1"), IllegalArgumentException.class, "Recibe un tipo distinto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateMarkAsInappropriate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3]);
	}
	protected void templateMarkAsInappropriate(String rol, Integer adId, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			authenticate(rol);

			advertisementService.markAsInappropriate(adId);

			authenticate(null);
			this.advertisementService.flush();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("adId: " + adId);
		System.out.println("Rol: " + rol);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

	@Test
	public void driverFindAllTaboo() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ALL TABOO=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				null, "FindAll correcto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateAllTaboo((Class<?>) testingData[i][0], (String) testingData[i][1]);
	}

	protected void templateAllTaboo(Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			advertisementService.findAllTaboo();

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
	public void drivertemplateGetRatio() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST GET RATIO=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				null, "FindAll correcto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateGetRatio((Class<?>) testingData[i][0], (String) testingData[i][1]);
	}

	protected void templateGetRatio(Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			advertisementService.getRatioWithTaboo();

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
	public void getRandomNonInappropriateForNewspaper() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST getRandomNonInappropriateForNewspaper=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				null, getEntityId("newspaper1"), "FindAll correcto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			getRandomNonInappropriateForNewspaper((Class<?>) testingData[i][0], (Integer) testingData[i][1], (String) testingData[i][2]);
	}
	protected void getRandomNonInappropriateForNewspaper(Class<?> expected, Integer nw, String explanation) {

		Class<?> caught = null;

		try {
			advertisementService.getRandomNonInappropriateForNewspaper(nwService.findOne(nw));

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

}
