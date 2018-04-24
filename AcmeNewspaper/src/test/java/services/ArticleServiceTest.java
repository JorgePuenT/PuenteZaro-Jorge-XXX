
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

import domain.Article;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ArticleServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ArticleService	articleService;
	


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
	public void driverCreate(){
		
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
		
		for(int i =0; i<testingData.length;i++)
			templateCreate((String) testingData[i][0], (Class<?>)testingData[i][1], (String) testingData[i][2]);
	}
	protected void templateCreate(String rol, Class<?> expected, String explanation){
		Class<?> caught = null;
		
		try{
			super.authenticate(rol);
			articleService.create();
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
	@SuppressWarnings("unchecked")
	@Test
	public void driverSave(){
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST SAVE ARTICLE==================================================");
		System.out.println("===============================================================================================================\r");
		
		Collection<String> pictures = this.addPictures();
		Collection<String> wrongUrlPictures = this.addWrongUrlPictures();

		
		Object testingData[][]= {
				//Test positivo
				{
					"user1", getEntityId("article1"), "title", "summary","body", pictures,  false, false, new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), null, "Guardado correcto de un article"
				},
				{
					"user2", getEntityId("article1"), "title", "summary","body", pictures,  false, false, new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), IllegalArgumentException.class, "Intento de guardar un article que no soy su propietario"
				},
				{
					"user1", getEntityId("article1"), "", "summary","body", pictures,  false, false, new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), ConstraintViolationException.class, "Intento de guardar un articulo con el titulo vacio"
				},
				{
					"user1", getEntityId("article1"), "title", "","body", pictures,  false, false, new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), ConstraintViolationException.class, "Intento de guardar un articulo con el resumen vacio"
				},
				{
					"user1", getEntityId("article1"), "title", "summary","", pictures,  false, false, new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), ConstraintViolationException.class, "Intento de guardar un articulo con el cuerpo vacio"
				},
				{
					"user1", getEntityId("article1"), "title", "summary","body", wrongUrlPictures,  false, false, new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), ConstraintViolationException.class, "Intento de guardar un articulo con un conjunto de imagenes erroneas"
				},
				{
					"user1", getEntityId("article1"), "title", "summary","body", null,  false, false, new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), ConstraintViolationException.class, "Intento de guardar un articulo con las imagenes a null"
				},
				{
					"user1", getEntityId("article1"), "", "summary","body", pictures,  false, false, new Date(System.currentTimeMillis()- TimeUnit.DAYS.toMillis(100000)), ConstraintViolationException.class, "Intento de guardar un articulo con la fecha ya pasada"
				},
				{
					null, getEntityId("article1"), "", "summary","body", pictures,  false, false, new Date(System.currentTimeMillis()- TimeUnit.DAYS.toMillis(100000)), IllegalArgumentException.class, "Intento de guardar un articulo sin estar logeado"
				},
				{
					"user1", getEntityId("article1"), "", "summary","body", pictures,  false, true, new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(10)), ConstraintViolationException.class, "Intento de guardar un articulo inapropiado"
				}
				
				
		};
		for(int i =0; i<testingData.length;i++)
			templateSave((String) testingData[i][0], (Integer) testingData[i][1],(String)testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Collection<String>) testingData[i][5], (Boolean) testingData[i][6], (Boolean) testingData[i][7], (Date)testingData[i][8], (Class<?>)testingData[i][9], (String) testingData[i][10]);
	}
	//Creados estos dos métodos porque me daba error al hacer el casteo de String a Collection
	private Collection<String> addPictures(){
		Collection<String> pictures = new HashSet<String>();
		pictures.add("http://www.1.com");
		pictures.add("http://www.2.com");
		return pictures;
	}
	private Collection<String> addWrongUrlPictures(){
		Collection<String> pictures = new HashSet<String>();
		pictures.add("www.1.es");
		pictures.add("www.2.com");
		return pictures;
	}


	protected void templateSave(String rol, Integer articleId, String title, String summary, String body, Collection<String> pictures, Boolean finalMode,
			Boolean inappropriate, Date publicationMoment, Class<?> expected, String explanation) {
		Class<?> caught = null;
		try {
			authenticate(rol);
			
			Article art = null;
			art= articleService.findOne(articleId);
			art.setTitle(title);
			art.setSummary(summary);
			art.setBody(body);
			art.setPicturess(pictures);
			art.setFinalMode(finalMode);
			art.setInappropriate(inappropriate);
			art.setPublicationMoment(publicationMoment);
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


}
