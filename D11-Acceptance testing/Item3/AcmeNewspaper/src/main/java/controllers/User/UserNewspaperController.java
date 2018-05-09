
package controllers.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NewspaperService;
import services.UserService;
import controllers.AbstractController;
import domain.Newspaper;

@Controller
@RequestMapping("user/newspaper")
public class UserNewspaperController extends AbstractController {

	@Autowired
	private NewspaperService	newspaperService;
	@Autowired
	private UserService	userService;


	//Constructor
	public UserNewspaperController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Newspaper newspaper = newspaperService.create();
			result = newEditModelAndView(newspaper);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/newspaper/list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = true) final int newspaperId) {
		Newspaper newspaper;
		try{
			newspaper = newspaperService.findOne(newspaperId);
		}catch(Throwable oops){
			return new ModelAndView("redirect:/newspaper/list.do");
		}
		if (newspaper.getUser().equals(userService.findByPrincipal()))
			return newEditModelAndView(newspaper);
		else
			return new ModelAndView("redirect:/newspaper/list.do");
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Newspaper newspaper, final BindingResult binding) {
		ModelAndView result;
		Newspaper validated = newspaperService.reconstruct(newspaper, binding);
		if (binding.hasErrors())
			result = newEditModelAndView(newspaper);
		else
			try {
				newspaperService.save(validated);
				result = new ModelAndView("redirect:/newspaper/list.do");
			} catch (Throwable oops) {
				result = newEditModelAndView(newspaper);
				result.addObject("message", "newspaper.commitError");
			}
		return result;
	}

	protected ModelAndView newEditModelAndView(final Newspaper newspaper) {
		ModelAndView result;
		result = new ModelAndView("newspaper/edit");
		result.addObject("newspaper", newspaper);
		result.addObject("actionUri", "user/newspaper/save.do");
		return result;
	}
}