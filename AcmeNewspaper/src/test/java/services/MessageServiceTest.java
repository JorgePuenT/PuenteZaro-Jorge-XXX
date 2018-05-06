
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private MessageService	messageService;

	//Supporting services -----------------------------------------------------
	@Autowired
	private FolderService	folderService;


	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE MESSAGE==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("message1"), null, "Búsqueda del find One correcto"
			}, {
				0, IllegalArgumentException.class, "Búsqueda del find One erronea"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int messageId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			messageService.findOne(messageId);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("MessageId: " + messageId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverCreate() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST CREATE Message==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"user1", null, "Creación correcta de un message"
			},
			//Intento de crear con un rol que no debe
			{
				null, IllegalArgumentException.class, "Se ha intentado guardar un message con un actor sin loguear"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}
	protected void templateCreate(String rol, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			super.authenticate(rol);
			messageService.create();
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
		System.out.println("=====================================TEST SAVE MESSAGE==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Test positivo
			{
				getEntityId("message1"), "user1", "Body", "Subject", null, "Guardado correcto de un message"
			}, {
				getEntityId("message1"), null, "summary", "body", IllegalArgumentException.class, "Intento de guardar un message sin un actor loguedo"
			}
		};
		for (int i = 0; i < testingData.length; i++)
			templateSave((Integer) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4], (String) testingData[i][5]);
	}

	protected void templateSave(Integer messageId, String rol, String title, String body, Class<?> expected, String explanation) {
		Class<?> caught = null;
		try {

			authenticate(rol);
			Message m = messageService.findOne(messageId);
			m.setBody(body);
			m.setSubject(title);
			this.messageService.save(m);
			authenticate(null);
			this.messageService.flush();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Message: " + messageId);
		System.out.println("Title: " + title);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void driverSaveBroadcast() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST SAVE BROADCAST==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Test positivo
			{
				"admin", "Body", "Subject", null, "Guardado correcto de un message"
			}, {
				"user2", "summary", "body", IllegalArgumentException.class, "Intento de guardar un message sin un actor loguedo"
			}
		};
		for (int i = 0; i < testingData.length; i++)
			templateBroadcast((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3], (String) testingData[i][4]);
	}

	protected void templateBroadcast(String rol, String title, String body, Class<?> expected, String explanation) {
		Class<?> caught = null;
		try {

			authenticate(rol);
			Message m = messageService.create();
			m.setBody(body);
			m.setSubject(title);
			this.messageService.saveBroadcast(m);
			authenticate(null);
			this.messageService.flush();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Title: " + title);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void driverNotification() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST SAVE NOTIFICATION==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Test positivo
			{
				"admin", "Body", "Subject", null, "Guardado correcto de un message"
			}, {
				"user2", "summary", "body", IllegalArgumentException.class, "Intento de guardar un message nulo"
			}
		};
		for (int i = 0; i < testingData.length; i++)
			templateNotification((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3], (String) testingData[i][4]);
	}

	protected void templateNotification(String rol, String title, String body, Class<?> expected, String explanation) {
		Class<?> caught = null;
		try {

			authenticate(rol);
			Message m = messageService.create();
			m.setBody(body);
			m.setSubject(title);
			if (rol == "user2") {
				this.messageService.saveNotification(null);
			} else {
				this.messageService.saveNotification(m);
			}

			authenticate(null);
			this.messageService.flush();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Title: " + title);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

	@Test
	public void driverDelete() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST DELETE==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Test positivo
			{
				"user1", getEntityId("message1"), null, "Borrado correcto de un message"
			}, {
				null, getEntityId("message1"), IllegalArgumentException.class, "Intento de borrar un message sin loguearse"
			}
		};
		for (int i = 0; i < testingData.length; i++)
			templateDelete((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3]);
	}

	protected void templateDelete(String rol, Integer mId, Class<?> expected, String explanation) {
		Class<?> caught = null;
		try {

			authenticate(rol);
			messageService.delete(messageService.findOne(mId));
			authenticate(null);
			this.messageService.flush();

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
	public void driverIsSpam() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST SPAM==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Test positivo
			{
				getEntityId("message1"), null, "Message sin spam"
			}, {
				getEntityId("message3"), IllegalArgumentException.class, "Message con spam"
			}
		};
		for (int i = 0; i < testingData.length; i++)
			templateIsSpam((Integer) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateIsSpam(Integer mId, Class<?> expected, String explanation) {
		Class<?> caught = null;
		try {

			if (!messageService.isSpam(mId)) {
				caught = null;
			} else {
				caught = IllegalArgumentException.class;
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
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

	@Test
	public void driverMove() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST MOVE==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Test positivo
			{
				"admin", getEntityId("message6"), getEntityId("folder71"), null, "Message movido correctamente"
			}, {
				null, getEntityId("message3"), getEntityId("folder8"), IllegalArgumentException.class, "Message movido sin loguearse"
			}
		};
		for (int i = 0; i < testingData.length; i++)
			templateMove((String) testingData[i][0], (Integer) testingData[i][1], (Integer) testingData[i][2], (Class<?>) testingData[i][3], (String) testingData[i][4]);
	}

	protected void templateMove(String rol, Integer mId, Integer fId, Class<?> expected, String explanation) {
		Class<?> caught = null;
		try {

			this.authenticate(rol);
			messageService.move(messageService.findOne(mId), folderService.findOne(fId));

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
