
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.FollowUp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class FollowUpServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private FollowUpService	followUpService;


	//Supporting services -----------------------------------------------------
	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE FOLLOW UP==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("followUp1"), null, "Búsqueda del find One correcto"
			},

			//Find one newspaper using another role id
			{
				getEntityId("user1"), IllegalArgumentException.class, "Intentando buscar un user"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int followUpId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			followUpService.findOne(followUpId);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("FollowUpId: " + followUpId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverCreate() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST CREATE FOLLOW UP==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"user1", "article8", null, "Creación correcta de un followUp"
			},
			//Intento de crear con un rol que no debe
			{
				"customer1", "article1", IllegalArgumentException.class, "Se ha intentado crear un followUp con un customer1"
			},
			//Create sin logearse
			{
				null, "article1", IllegalArgumentException.class, "Intento de crear un followUp sin estar logeado"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3]);
	}
	protected void templateCreate(String rol, String article, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			super.authenticate(rol);
			followUpService.create(getEntityId(article));
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

	@SuppressWarnings("unchecked")
	@Test
	public void driverSave() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST SAVE FOLLOW UP==================================================");
		System.out.println("===============================================================================================================\r");

		Collection<String> pictures = this.addPictures();
		Collection<String> wrongUrlPictures = this.addWrongUrlPictures();

		Object testingData[][] = {
			//Test positivo
			{
				getEntityId("followUp1"), "title", "summary", "body", pictures, new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1000000)), null, "Guardado correcto de un followUp"
			}, {
				getEntityId("followUp1"), "", "summary", "body", pictures, new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1000000)), ConstraintViolationException.class, "Intento de guardar un followUp con un titulo en blanco"
			}, {
				getEntityId("followUp1"), "title", "", "body", pictures, new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1000000)), ConstraintViolationException.class, "Intento de guardar un followUp con un resumen en blanco"
			}, {
				getEntityId("followUp1"), "title", "body", "", pictures, new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1000000)), ConstraintViolationException.class, "Intento de guardar un followUp con un cuerpo en blanco"
			}, {
				getEntityId("followUp1"), "title", "summary", "body", wrongUrlPictures, new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1000000)), ConstraintViolationException.class, "Intento de guardar un followUp con imagenes erroneas"
			}, {
				getEntityId("followUp1"), "title", "summary", "body", pictures, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)), ConstraintViolationException.class, "Intento de guardar un followUp con una fecha futura"
			}, {
				getEntityId("newspaper1"), "title", "summary", "body", pictures, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)), IllegalArgumentException.class, "Intento de guardar una entidad que no es un followUp"
			}
		};
		for (int i = 0; i < testingData.length; i++)
			templateSave((Integer) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Collection<String>) testingData[i][4], (Date) testingData[i][5], (Class<?>) testingData[i][6],
				(String) testingData[i][7]);
	}
	//Creados estos dos métodos porque me daba error al hacer el casteo de String a Collection
	private Collection<String> addPictures() {
		Collection<String> pictures = new HashSet<String>();
		pictures.add("http://www.1.com");
		pictures.add("http://www.2.com");
		return pictures;
	}
	private Collection<String> addWrongUrlPictures() {
		Collection<String> pictures = new HashSet<String>();
		pictures.add("www.1.es");
		pictures.add("www.2.com");
		return pictures;
	}
	protected void templateSave(Integer followUpId, String title, String summary, String body, Collection<String> pictures, Date publicationMoment, Class<?> expected, String explanation) {
		Class<?> caught = null;
		try {

			authenticate("user1");
			FollowUp follup = followUpService.findOne(followUpId);
			follup.setTitle(title);
			follup.setSummary(summary);
			follup.setBody(body);
			follup.setPicturess(pictures);
			follup.setPublicationMoment(publicationMoment);

			this.followUpService.save(follup);

			this.followUpService.flush();
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
		System.out.println("FollowUp: " + followUpId);
		System.out.println("Title: " + title);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

	@Test
	public void driverFindAll() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ALL FOLLOW UP=======================================================");
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
			followUpService.findAll();

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
	public void driverDelete() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST Delete FOLLOW UP=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				getEntityId("followUp1"), null, "borrado correcto correcto"
			}, {
				0, IllegalArgumentException.class, "borrado con id 0"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateDelete((Integer) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateDelete(Integer id, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			followUpService.delete(id);

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
