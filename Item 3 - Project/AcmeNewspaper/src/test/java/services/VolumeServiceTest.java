
package services;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Newspaper;
import domain.Volume;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class VolumeServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private VolumeService		volumeService;
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
				getEntityId("volume1"), null, "Búsqueda del find One correcto"
			},

			//Find one newspaper using another role id
			{
				getEntityId("user1"), IllegalArgumentException.class, "Intentando buscar un user"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int volumeId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			volumeService.findOne(volumeId);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("VolumeId: " + volumeId);
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
				"user1", null, "Creación correcta de un volume"
			},
			//Create sin logearse
			{
				null, IllegalArgumentException.class, "Intento de crear un volume sin estar logeado"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}
	protected void templateCreate(String rol, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			super.authenticate(rol);
			volumeService.create();
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
			volumeService.findAll();

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
		System.out.println("=====================================TEST SAVE NEWSPAPER==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"user1", getEntityId("volume1"), "title", "description",2018, null, "Guardado correcto de un volume"
			},
			{
				"user1", getEntityId("volume1"), "", "description",2018, ConstraintViolationException.class, "Intento de guardar un volume con título vacio"
			},
			{
				"user1", getEntityId("volume1"), "Title", "", 2018, ConstraintViolationException.class, "Intento de guardar un volume con la descripción vacia"
			},
			{
				"user1", 0, "Title", "Description", 2018, IllegalArgumentException.class, "Intento de guardar un volume con id 0"
			},
			{
				"user1", getEntityId("newspaper1"), "Title", "Description", 2018, IllegalArgumentException.class, "Intento de guardar un newspaper"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateSave((String) testingData[i][0], (Integer) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (int) testingData[i][4],(Class<?>) testingData[i][5], (String) testingData[i][6]);
	}
	protected void templateSave(String rol, Integer volumeId, String title,
			String description, int year, Class<?> expected, String explanation) {
		
		Class<?> caught = null;
		try {
			authenticate(rol);

			Volume vol = null;
			vol = volumeService.findOne(volumeId);
			vol.setTitle(title);
			vol.setDescription(description);
			vol.setYear(year);
			authenticate(null);
			this.volumeService.flush();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Newspaper: " + volumeId);
		System.out.println("Title: " + title);
		System.out.println("Rol: " + rol);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}




}
