
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Subscription;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class SubscriptionServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private SubscriptionService	subscriptionService;


	//Supporting services -----------------------------------------------------

	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE SUBSCRIPTION==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("subscription1"), null, "Búsqueda del find One correcto."
			},
			//Find one subscription using another entity id
			{
				getEntityId("newspaper1"), IllegalArgumentException.class, "Intento de buscar una suscripción usando otra id de entidad."
			},
			//Find one subscription using a non persisted ID
			{
				0, IllegalArgumentException.class, "Intento de buscar una suscripción usando una id no persistida."
			},

		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int subscriptionId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			subscriptionService.findOne(subscriptionId);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Subscription: " + subscriptionId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverCreate() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST CREATE SUSCRIPTION==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"user1", getEntityId("newspaper1"), null, "Creación correcta de una suscripción"
			}, {
				"user1", getEntityId("article1"), IllegalArgumentException.class, "el id no es de un articulo"
			}, {
				"user1", -3, IllegalArgumentException.class, "id de un objeto que no existe"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3]);
	}
	protected void templateCreate(String rol, Integer idnwp, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			super.authenticate(rol);
			subscriptionService.create(idnwp);
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
		System.out.println("========================================TEST FIND ALL====================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				null, "Búsqueda correcta del Find All", subscriptionService.findAll().size()
			}, {
				IllegalArgumentException.class, "Distintos rangos", subscriptionService.findAll().size() + 1
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateAll((Class<?>) testingData[i][0], (String) testingData[i][1], (int) testingData[i][2]);
	}

	protected void templateAll(Class<?> expected, String explanation, int index) {

		Class<?> caught = null;

		try {
			Collection<Subscription> z = subscriptionService.findAll();
			Assert.isTrue(z.size() == index);

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
		System.out.println("=====================================TEST SAVE SUBSCRIPTION==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("subscription1"), null, "Guardado de suscripción correctamente"
			},

			//Try to save one Subscription using another entity
			{
				getEntityId("newspaper1"), IllegalArgumentException.class, "Intento de guardar un Actor usando otra id de entidad"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateSave((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateSave(int subscriptionId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			Subscription subscription = subscriptionService.findOne(subscriptionId);
			Subscription subscription2 = subscriptionService.findOne(getEntityId("subscription4"));

			subscription.setCreditCard(subscription2.getCreditCard());
			subscriptionService.save(subscription);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Subscription: " + subscriptionId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

}
