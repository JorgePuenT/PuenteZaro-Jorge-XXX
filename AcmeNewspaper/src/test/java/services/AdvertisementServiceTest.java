
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
	public void driverCreate(){
		
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
		
		for(int i =0; i<testingData.length;i++)
			templateCreate((String) testingData[i][0], (Class<?>)testingData[i][1], (String) testingData[i][2]);
	}
	protected void templateCreate(String rol, Class<?> expected, String explanation){
		Class<?> caught = null;
		
		try{
			super.authenticate(rol);
			advertisementService.create();
			super.unauthenticate();
		}catch (Throwable oops){
			caught = oops.getClass();
		}
		
		checkExceptions(expected, caught);
		
		if(expected==null)
			System.out.println("-----------------POSITIVO------------");
		else
			System.out.println("-----------------NEGATIVO------------");
		System.out.println("Explicacion: " + explanation);
		System.out.println("Rol: " + rol);
		System.out.println("\r¿Correcto? " +(expected==caught));
	}
}