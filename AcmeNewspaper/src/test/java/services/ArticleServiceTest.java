
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
import domain.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ArticleServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ArticleService		articleService;
	@Autowired
	private UserService			userService;
	@Autowired
	private NewspaperService	nwService;


	//Supporting services -----------------------------------------------------

	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE ARTICLE==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("article1"), null, "Búsqueda del find One correcto"
			},

			//Find one newspaper using another role id
			{
				getEntityId("newspaper1"), IllegalArgumentException.class, "Intentando buscar un newspaper"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int articleId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			articleService.findOne(articleId);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("ArticleId: " + articleId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	@Test
	public void driverCreate() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST CREATE ARTICLE==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"user1", null, "Creación correcta de un article"
			},
			//Intento de crear con un rol que no debe
			{
				"customer1", IllegalArgumentException.class, "Se ha intentado crear un article con un customer1"
			},
			//Create sin logearse
			{
				null, IllegalArgumentException.class, "Intento de crear un article sin estar logeado"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}
	protected void templateCreate(String rol, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			super.authenticate(rol);
			articleService.create();
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
		System.out.println("=====================================TEST SAVE ARTICLE==================================================");
		System.out.println("===============================================================================================================\r");

		Collection<String> pictures = this.addPictures();
		Collection<String> wrongUrlPictures = this.addWrongUrlPictures();

		Object testingData[][] = {
			//Test positivo
			{
				"user5", getEntityId("article7"), "title", "summary", "body", pictures, false, false, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(100)), null, "Guardado correcto de un article"
			},
			{
				"user2", getEntityId("article1"), "title", "summary", "body", pictures, false, false, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(100)), IllegalArgumentException.class,
				"Intento de guardar un article que no soy su propietario"
			},
			{
				"user5", getEntityId("article7"), "", "summary", "body", pictures, false, false, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(100)), ConstraintViolationException.class, "Intento de guardar un articulo con el titulo vacio"
			},
			{
				"user5", getEntityId("article7"), "title", "", "body", pictures, false, false, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(100)), ConstraintViolationException.class, "Intento de guardar un articulo con el resumen vacio"
			},
			{
				"user5", getEntityId("article7"), "title", "summary", "", pictures, false, false, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(100)), ConstraintViolationException.class, "Intento de guardar un articulo con el cuerpo vacio"
			},
			{
				"user5", getEntityId("article7"), "title", "summary", "body", wrongUrlPictures, false, false, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(100)), ConstraintViolationException.class,
				"Intento de guardar un articulo con un conjunto de imagenes erroneas"
			},
			{
				"user5", getEntityId("article7"), "title", "summary", "body", null, false, false, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(100)), ConstraintViolationException.class,
				"Intento de guardar un articulo con las imagenes a null"
			},
			{
				"user5", getEntityId("article7"), "", "summary", "body", pictures, false, false, new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(100000)), ConstraintViolationException.class,
				"Intento de guardar un articulo con la fecha ya pasada"
			}, {
				null, getEntityId("article7"), "", "summary", "body", pictures, false, false, new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(100000)), IllegalArgumentException.class, "Intento de guardar un articulo sin estar logeado"
			}, {
				"user5", getEntityId("article7"), "", "summary", "body", pictures, false, true, new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1000)), ConstraintViolationException.class, "Intento de guardar un articulo inapropiado"
			}

		};
		for (int i = 0; i < testingData.length; i++)
			templateSave((String) testingData[i][0], (Integer) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Collection<String>) testingData[i][5], (Boolean) testingData[i][6],
				(Boolean) testingData[i][7], (Date) testingData[i][8], (Class<?>) testingData[i][9], (String) testingData[i][10]);
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

	protected void templateSave(String rol, Integer articleId, String title, String summary, String body, Collection<String> pictures, Boolean finalMode, Boolean inappropriate, Date publicationMoment, Class<?> expected, String explanation) {
		Class<?> caught = null;
		try {
			authenticate(rol);

			Article art = null;
			art = articleService.findOne(articleId);
			art.setTitle(title);
			art.setSummary(summary);
			art.setBody(body);
			art.setPicturess(pictures);
			this.articleService.save(art);

			authenticate(null);
			this.articleService.flush();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("Article: " + articleId);
		System.out.println("Title: " + title);
		System.out.println("Rol: " + rol);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");
	}

	@Test
	public void driverFindAll() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ALL ARTICLE=======================================================");
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
			articleService.findAll();

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
		System.out.println("=====================================TEST MARK INAPPROPRIATE ARTICLE==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"admin", getEntityId("article1"), null, "marcado con inapropiado de forma correcta"
			}, {
				"user1", getEntityId("article1"), IllegalArgumentException.class, "usuario intenta marcar como inapropiado"
			}, {
				"admin", getEntityId("advertisement1"), IllegalArgumentException.class, "Recibe un tipo distinto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateMarkAsInappropriate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3]);
	}
	protected void templateMarkAsInappropriate(String rol, Integer adId, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			authenticate(rol);

			articleService.markAsInappropriate(adId);

			authenticate(null);
			this.articleService.flush();
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
	public void drivermarkInappropriateArticlesOfNewspaper() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST MmarkInappropriateArticlesOfNewspaper==================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Test positivo
			{
				"admin", getEntityId("newspaper1"), null, "marcado con inapropiado de forma correcta"
			}, {
				"user1", getEntityId("newspaper1"), IllegalArgumentException.class, "usuario intenta marcar como inapropiado"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templatemarkInappropriateArticlesOfNewspaper((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3]);
	}
	protected void templatemarkInappropriateArticlesOfNewspaper(String rol, Integer adId, Class<?> expected, String explanation) {
		Class<?> caught = null;

		try {
			authenticate(rol);

			articleService.markInappropriateArticlesOfNewspaper(nwService.findOne(adId));

			authenticate(null);
			this.articleService.flush();
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
			articleService.findAllTaboo();

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
	public void driverfindAllPublishedForUser() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST findAllPublishedForUser=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				null, "FindAll correcto", getEntityId("user1")
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templatefindAllPublishedForUser((Class<?>) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2]);
	}

	protected void templatefindAllPublishedForUser(Class<?> expected, String explanation, Integer userId) {

		Class<?> caught = null;

		try {
			articleService.findAllPublishedForUser(userService.findOne(userId));

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
	public void driverfindAllPublishedForNewspaper() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST findAllPublishedForNewspaper=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				null, "FindAll correcto", getEntityId("newspaper1")
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templatefindAllPublishedForNewspaper((Class<?>) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2]);
	}

	protected void templatefindAllPublishedForNewspaper(Class<?> expected, String explanation, Integer nwId) {

		Class<?> caught = null;

		try {
			articleService.findAllPublishedForNewspaper(nwService.findOne(nwId));

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
	public void driverfindAllPublishedKeyword() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST findAllPublishedKeyword=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				null, "FindAll correcto", "pepe"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templatefindAllPublishedKeyword((Class<?>) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templatefindAllPublishedKeyword(Class<?> expected, String explanation, String str) {

		Class<?> caught = null;

		try {
			articleService.findAllPublishedKeyword(str);

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
	public void driverisPublished() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST isPublished=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				null, "FindAll correcto", getEntityId("article1")
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateisPublished((Class<?>) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2]);
	}

	protected void templateisPublished(Class<?> expected, String explanation, Integer aId) {

		Class<?> caught = null;

		try {
			articleService.isPublished(articleService.findOne(aId));

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
	public void driverDashboard() {
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST Dashboard=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
			//Positive test
			{
				null, "FindAll correcto"
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateDashboard((Class<?>) testingData[i][0], (String) testingData[i][1]);
	}

	protected void templateDashboard(Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			articleService.getFollowUpsPerArticleAvg();
			articleService.getFollowUpsPerArticleAvgAfterOneWeek();
			articleService.getFollowUpsPerArticleAvgAfterTwoWeeks();
			articleService.findMyArticles();

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
