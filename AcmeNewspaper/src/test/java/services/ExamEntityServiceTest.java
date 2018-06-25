
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import utilities.AbstractTest;
import domain.ExamEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ExamEntityServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ExamEntityService		examEntityService;


	//Supporting services -----------------------------------------------------


	// Tests ------------------------------------------------------------------
	@Test
	public void driverExamTestCase() {

		System.out.println("===============================================================================================================");
		System.out.println("========================================== ExamTestCase =======================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Test positivo
			{"Increible periódico","Periódico fantástico","Un periódico sin igual", null, 1, "admin", "newspaper4", null, "Creación y edición correcta."},
			//Test negativo
			{"Increible periódico",null,"Un periódico sin igual", null, 1, "admin2", "newspaper4", IllegalArgumentException.class, "Creación correcta pero al modificar se introduce un título nulo."},
		};

		for (int i = 0; i < testingData.length; i++)
			templateExamTestCase((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Date) testingData[i][3], (Integer) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6], (Class<?>) testingData[i][7], (String) testingData[i][8]);
	}

	protected void templateExamTestCase(String title, String secondTitle, String description, Date displayMoment, int gauge, String userBean, String newspaperBean, Class<?> expected, String explanation) {
		Class<?> caught = null;

		if (expected == null)
			System.out.println("-----------------------------------POSITIVO-----------------------------------");
		else
			System.out.println("-----------------------------------NEGATIVO-----------------------------------");
		System.out.println("Explicacion: " + explanation);
		System.out.println("User: " + userBean);
		System.out.println("Newspaper: " + newspaperBean);
		System.out.println("First title: " + title);
		System.out.println("Second title: " + secondTitle);
		System.out.println("Description: " + description);
		System.out.println("Display Moment: " + displayMoment);
		System.out.println("Gauge: " + gauge);

		try {
			super.authenticate(userBean);

			/////// Object creation ///////
			ExamEntity object = examEntityService.create();
			object.setTitle(title);
			object.setDescription(description);
			object.setDisplayMoment(displayMoment);
			object.setGauge(gauge);
			object.setDraft(true);

			/////// Validation ///////
			Errors binding = new BindException(object, "ExamEntity");
			ExamEntity reconstructed = examEntityService.reconstruct(object, binding);
			Assert.isTrue(!binding.hasErrors());

			/////// Save ///////
			ExamEntity saved = examEntityService.save(reconstructed);

			/////// Object modification ///////
			saved.setTitle(secondTitle);
			saved.setDraft(false);

			/////// Second Validation ///////
			binding = new BindException(object, "ExamEntity");
			reconstructed = examEntityService.reconstruct(saved, binding);
			Assert.isTrue(!binding.hasErrors());

			/////// Final save ///////
			examEntityService.save(reconstructed);

			super.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		System.out.println("\r¿Correcto? " + (expected == caught));
	}


}
