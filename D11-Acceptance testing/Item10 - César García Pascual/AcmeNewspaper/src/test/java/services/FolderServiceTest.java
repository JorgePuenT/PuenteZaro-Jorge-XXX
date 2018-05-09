
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Actor;
import domain.Folder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class FolderServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private FolderService	folderService;
	@Autowired
	private ActorService	actorService;


	//Supporting services -----------------------------------------------------
	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE FOLDER==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("folder1"), null, "Búsqueda del find One correcto"
			},

			//Find one newspaper using another role id
			{
				0, IllegalArgumentException.class, "Folder con id 0"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int folderUpId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			folderService.findOne(folderUpId);

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
	public void driverCreate() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST CREATE FOLDER==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				null, "Creación correcta de un folder"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((Class<?>) testingData[i][0], (String) testingData[i][1]);
	}
	protected void templateCreate(Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {

			folderService.create();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("-----------------POSITIVO------------");
		else
			System.out.println("-----------------NEGATIVO------------");
		System.out.println("Explicacion: " + explanation);
		System.out.println("\r¿Correcto? " + (expected == caught));
	}


	@Test
	public void driverSave() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST SAVE FOLDER==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Test positivo
			{
				0, "Folder test", null, null, "Guardado correcto de un folder", getEntityId("admin")
			}, {
				getEntityId("folder1"), "Folder failure", null, IllegalArgumentException.class, "Intento de editar un folder del sistema", getEntityId("admin")
			}
		};
		for (int i = 0; i < testingData.length; i++)
			templateSave((Integer) testingData[i][0], (String) testingData[i][1], (Folder) testingData[i][2], (Class<?>) testingData[i][3], (String) testingData[i][4], (Integer) testingData[i][5]);
	}

	protected void templateSave(Integer folderId, String title, Folder parent, Class<?> expected, String explanation, Integer idActor) {
		Class<?> caught = null;
		try {
			Folder f;
			Actor a = actorService.findOne(idActor);
			if (folderId == 0) {
				f = folderService.create();
				f.setName(title);
				f.setParent(parent);
				folderService.save(f, a);
			} else {
				f = folderService.findOne(folderId);
				f.setName(title);
				folderService.save(f, a);
			}

			folderService.flush();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Folder: " + folderId);
		System.out.println("Title: " + title);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

	@Test
	public void driverEditName() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST EDIT NAME FOLDER=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				"admin", getEntityId("folder71"), "test name", null, "Cambio de nombre correcto"
			}, {
				"admin", getEntityId("folder66"), "test name", IllegalArgumentException.class, "Cambio de nombre correcto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateEditName((String) testingData[i][0], (Integer) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3], (String) testingData[i][4]);
	}

	protected void templateEditName(String rol, Integer fId, String name, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			authenticate(rol);
			folderService.editName(fId, name);
			unauthenticate();

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
		System.out.println("=====================================TEST DELETE FOLDER=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				"admin", getEntityId("folder71"), null, "Borrado correcto"
			}, {
				"admin", getEntityId("folder66"), IllegalArgumentException.class, "Borrado fallido"
			}, {
				"admin", getEntityId("folder1"), IllegalArgumentException.class, "Borrado fallido carpeta system"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateDelete((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3]);
	}

	protected void templateDelete(String rol, Integer fId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			authenticate(rol);
			folderService.delete(folderService.findOne(fId));
			unauthenticate();

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
	public void driverFindInboxFrom() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST findInboxFrom=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				getEntityId("admin"), null, "test correcto"
			}, {
				0, IllegalArgumentException.class, "test fallido"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindInboxFrom((Integer) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindInboxFrom(Integer idRol, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {

			folderService.findInboxFrom(idRol);

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
	public void driverFindOutboxFrom() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST findOutboxFrom=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				getEntityId("admin"), null, "test correcto"
			}, {
				0, IllegalArgumentException.class, "test fallido"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindInboxFrom((Integer) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOutboxFrom(Integer idRol, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {

			folderService.findOutboxFrom(idRol);

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
	public void driverFindTrashboxFrom() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST findTrashboxFrom=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				getEntityId("admin"), null, "test correcto"
			}, {
				0, IllegalArgumentException.class, "test fallido"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindTrashboxFrom((Integer) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindTrashboxFrom(Integer idRol, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {

			folderService.findInboxFrom(idRol);

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
	public void driverFindNotificationboxFrom() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST findNotificationboxFrom=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				getEntityId("admin"), null, "test correcto"
			}, {
				0, IllegalArgumentException.class, "test fallido"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindNotificationboxFrom((Integer) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindNotificationboxFrom(Integer idRol, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {

			folderService.findNotificationboxFrom(idRol);

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
	public void driverFindSpamboxFrom() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST findSpamboxFrom=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				getEntityId("admin"), null, "test correcto"
			}, {
				0, IllegalArgumentException.class, "test fallido"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindSpamboxFrom((Integer) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindSpamboxFrom(Integer idRol, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {

			folderService.findSpamboxFrom(idRol);

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
	public void driverOtros() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST OTROS METODOS FOLDER=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				getEntityId("admin"), "admin", null, "test correcto", getEntityId("folder2")
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateOtros((Integer) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3], (Integer) testingData[i][4]);
	}

	protected void templateOtros(Integer idRol, String auth, Class<?> expected, String explanation, Integer idF) {

		Class<?> caught = null;

		try {
			authenticate(auth);
			folderService.isMine(folderService.findOne(idF));
			unauthenticate();
			Actor a = actorService.findOne(idRol);
			folderService.getLvl1FoldersFrom(a);
			folderService.getNonSytemFoldersFrom(a);
			folderService.getSystemFoldersFrom(a);

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
