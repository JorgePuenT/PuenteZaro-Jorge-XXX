
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

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class NewspaperServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private NewspaperService	newspaperService;


	//Supporting services -----------------------------------------------------

	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE NEWSPAPER==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("newspaper1"), null, "Búsqueda del find One correcto"
			},

			//Find one newspaper using another role id
			{
				getEntityId("user1"), IllegalArgumentException.class, "Intentando buscar un user"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int newpspaperId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			newspaperService.findOne(newpspaperId);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("NewpspaperId: " + newpspaperId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}
	
	@Test
	public void driverCreate(){
		
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST CREATE NEWSPAPER==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
				//Test positivo
				{
					"user1", null, "Creación correcta de un newspaper"
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
			newspaperService.create();
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
	
	@Test
	public void driverSave(){
		
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST SAVE NEWSPAPER==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
				//Test positivo
				{
					"user1", getEntityId("newspaper1"), "title", new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), "description", "http://www.imagen.es", true, false, null, "Guardado correcto de un newspaper siendo periódico privado"
				},
				{
					"user1", getEntityId("newspaper1"), "title", new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), "description", "http://www.imagen.es", false, false, null, "Guardado correcto de newspaper no privado"
				},
				{
					"user2", getEntityId("newspaper1"), "title", new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), "description", "http://www.imagen.es", false, false, IllegalArgumentException.class, "Intento de guardar un newspaper que no es mio"
				},
				{
					"user1", getEntityId("newspaper1"), "", new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), "description", "http://www.imagen.es", false, false, ConstraintViolationException.class, "Intento de guardar un newspaper con el título en blanco"
				},
				{
					"user1", getEntityId("newspaper1"), "title", new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), "", "http://www.imagen.es", false, false, ConstraintViolationException.class, "Intento de guardar un newspaper con la descripción en blanco"
				},
				{
					"user1", getEntityId("newspaper1"), "title", new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), "description", "www.imagen.es", false, false, ConstraintViolationException.class, "Intento de guardar un newspaper con una imagen erronea"
				},
				{
					"admin", getEntityId("newspaper1"), "title", new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), "description", "http://www.imagen.es", true, false, IllegalArgumentException.class, "Intento de guardar un newspaper con un rol incorrecto"
				},
				{
					null, getEntityId("newspaper1"), "title", new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), "description", "http://www.imagen.es", true, false, IllegalArgumentException.class, "Intento de guardar un newspaper sin estar logeado"
				},
				{
					"user1", getEntityId("newspaper1"), "title", new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), "description", "ftp://www.imagen.es", true, false, IllegalArgumentException.class, "Intento de guardar un newspaper con otro formato de imagen"
				},
				{
					"user1", getEntityId("newspaper1"), "title", new Date(System.currentTimeMillis()- TimeUnit.DAYS.toMillis(100000)), "description", "ftp://www.imagen.es", true, false, IllegalArgumentException.class, "Intento de guardar un newspaper con una fecha pasada"
				}
		};
		
		for(int i =0; i<testingData.length;i++)
			templateSave((String) testingData[i][0], (Integer) testingData[i][1],(String)testingData[i][2], (Date) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Boolean) testingData[i][6], (Boolean)testingData[i][7], (Class<?>)testingData[i][8], (String) testingData[i][9]);
	}
	protected void templateSave(String rol, Integer newspaperId, String title, Date publish, String description, String picture, Boolean isPrivate, Boolean inappropriate, Class<?> expected, String explanation){
		Class<?> caught = null;

		try {
			authenticate(rol);
			
			Newspaper news = null;
			news= newspaperService.findOne(newspaperId);
			news.setTitle(title);
			news.setDescription(description);
			news.setPublicationDate(publish);
			news.setPicture(picture);
			news.setInappropriate(inappropriate);
			news.setIsPrivate(isPrivate);
			this.newspaperService.save(news);

			authenticate(null);
			this.newspaperService.flush();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Newspaper: " + newspaperId);
		System.out.println("Title: " + title);
		System.out.println("Rol: " + rol);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

}
