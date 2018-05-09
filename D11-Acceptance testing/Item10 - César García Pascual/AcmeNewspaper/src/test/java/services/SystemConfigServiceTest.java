
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Admin;
import domain.SystemConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class SystemConfigServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private SystemConfigService	systemConfigService;

	//Supporting services -----------------------------------------------------

	@Autowired
	private AdminService		adminService;


	// Tests ------------------------------------------------------------------

	// =====================================================================================================================
	// ===================================================== FIND GET ======================================================
	// =====================================================================================================================

	@Test
	public void testGetDriver() {

		System.out.println("===============================================================================================================");
		System.out.println("===============================================TEST GET===================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			{
				null, "Busqueda de system."
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testGetTemplate((Class<?>) testingData[i][0], (String) testingData[i][1]);
	}

	protected void testGetTemplate(final Class<?> expected, final String explanation) {

		Class<?> caught = null;

		try {
			super.authenticate(null);

			@SuppressWarnings("unused")
			SystemConfig sc = this.systemConfigService.get();

			super.unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

		// --------------------------------- CONSOLA ---------------------------------

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("¿Objeto encontrado? " + (caught == null));
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

	// =====================================================================================================================
	// =================================================== SAVE system config ==================================================
	// =====================================================================================================================

	@Test
	public void testSystemConfigDriver() {

		System.out.println("===============================================================================================================");
		System.out.println("========================================system config====================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			{
				"admin", "pene", null, "Correcta creacion system."
			}, {
				"user1", "pene", IllegalArgumentException.class, "SAVE con otro rol."
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSystemConfigTemplate(super.getEntityId((String) testingData[i][0]), (String) testingData[i][1], (Class<?>) testingData[i][2], (String) testingData[i][3]);
	}

	protected void testSystemConfigTemplate(final int adminAux, String taboo, final Class<?> expected, final String explanation) {

		Class<?> caught = null;

		try {
			Admin admin = adminService.findOne(adminAux);
			super.authenticate(admin.getUserAccount().getUsername());

			SystemConfig sc = systemConfigService.get();
			sc.getTabooWordss().add(taboo);

			this.systemConfigService.save(sc);

			super.unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

		// --------------------------------- CONSOLA ---------------------------------

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}

}
