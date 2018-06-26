
package controllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ExamEntityService;
import services.NewspaperService;
import services.AdminService;
import utilities.internal.SchemaPrinter;
import controllers.AbstractController;
import domain.ExamEntity;

@Controller
@RequestMapping("/admin/examEntity")
public class AdminExamEntityController extends AbstractController {

	@Autowired
	private ExamEntityService		examEntityService;
	@Autowired
	private NewspaperService	newspaperService;
	@Autowired
	private AdminService			adminService;


	//Constructor
	public AdminExamEntityController() {
		super();
	}

	@RequestMapping(value="/list",method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		result = new ModelAndView("examEntity/list");
		result.addObject("examEntities",adminService.findByPrincipal().getExamEntities());
		result.addObject("requestUri", "admin/examEntity/list.do");
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			ExamEntity examEntity = examEntityService.create();
			result = this.newEditModelAndView(examEntity);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = true) final int examEntityId) {
		ModelAndView result;

		try {
			ExamEntity examEntity = examEntityService.findOne(examEntityId);
			Assert.isTrue(examEntity.getAdmin() == adminService.findByPrincipal());
			result = this.newEditModelAndView(examEntity);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final ExamEntity examEntity, final BindingResult binding) {
		ModelAndView result;
		ExamEntity reconstructed = examEntityService.reconstruct(examEntity, binding);
		if (binding.hasErrors()){
			SchemaPrinter.print(binding.getAllErrors());
			result = this.newEditModelAndView(reconstructed);
		}else
			try {
				examEntityService.save(reconstructed);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				oops.printStackTrace();
				result = this.newEditModelAndView(reconstructed, "examEntity.commitError");
			}
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(required=true) final int examEntityId){
		try {
			examEntityService.delete(examEntityId);
		} catch (Throwable oops) {}
		return new ModelAndView("redirect:list.do");
	}

	protected ModelAndView newEditModelAndView(final ExamEntity examEntity) {
		ModelAndView result;
		result = this.newEditModelAndView(examEntity, null);
		return result;
	}

	protected ModelAndView newEditModelAndView(final ExamEntity examEntity, final String message) {
		ModelAndView result;
		result = new ModelAndView("examEntity/edit");
		result.addObject("newspapers", newspaperService.findAll());
		result.addObject("examEntity", examEntity);
		result.addObject("actionUri", "admin/examEntity/save.do");
		result.addObject("message", message);
		if(examEntity.getId() != 0){
			result.addObject("isDraft",examEntityService.findOne(examEntity.getId()).getDraft());
		}else{
			result.addObject("isDraft",true);
		}
		return result;
	}
}
