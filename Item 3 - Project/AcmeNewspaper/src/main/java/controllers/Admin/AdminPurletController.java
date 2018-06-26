
package controllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PurletService;
import services.NewspaperService;
import services.AdminService;
import utilities.internal.SchemaPrinter;
import controllers.AbstractController;
import domain.Purlet;

@Controller
@RequestMapping("/admin/purlet")
public class AdminPurletController extends AbstractController {

	@Autowired
	private PurletService		purletService;
	@Autowired
	private NewspaperService	newspaperService;
	@Autowired
	private AdminService			adminService;


	//Constructor
	public AdminPurletController() {
		super();
	}

	@RequestMapping(value="/list",method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		result = new ModelAndView("purlet/list");
		result.addObject("purlets",adminService.findByPrincipal().getPurlets());
		result.addObject("requestUri", "admin/purlet/list.do");
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Purlet purlet = purletService.create();
			result = this.newEditModelAndView(purlet);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = true) final int purletId) {
		ModelAndView result;

		try {
			Purlet purlet = purletService.findOne(purletId);
			Assert.isTrue(purlet.getAdmin() == adminService.findByPrincipal());
			result = this.newEditModelAndView(purlet);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Purlet purlet, final BindingResult binding) {
		ModelAndView result;
		Purlet reconstructed = purletService.reconstruct(purlet, binding);
		if (binding.hasErrors()){
			SchemaPrinter.print(binding.getAllErrors());
			result = this.newEditModelAndView(reconstructed);
		}else
			try {
				purletService.save(reconstructed);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				oops.printStackTrace();
				result = this.newEditModelAndView(reconstructed, "purlet.commitError");
			}
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(required=true) final int purletId){
		try {
			purletService.delete(purletId);
		} catch (Throwable oops) {}
		return new ModelAndView("redirect:list.do");
	}

	protected ModelAndView newEditModelAndView(final Purlet purlet) {
		ModelAndView result;
		result = this.newEditModelAndView(purlet, null);
		return result;
	}

	protected ModelAndView newEditModelAndView(final Purlet purlet, final String message) {
		ModelAndView result;
		result = new ModelAndView("purlet/edit");
		result.addObject("newspapers", newspaperService.findAll());
		result.addObject("purlet", purlet);
		result.addObject("actionUri", "admin/purlet/save.do");
		result.addObject("message", message);
		if(purlet.getId() != 0){
			result.addObject("isDraft",purletService.findOne(purlet.getId()).getDraft());
		}else{
			result.addObject("isDraft",true);
		}
		return result;
	}
}
