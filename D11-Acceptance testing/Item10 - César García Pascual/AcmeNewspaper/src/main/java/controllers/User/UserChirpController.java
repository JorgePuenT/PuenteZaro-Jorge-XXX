
package controllers.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChirpService;
import controllers.AbstractController;
import domain.Chirp;

@Controller
@RequestMapping("user/chirp")
public class UserChirpController extends AbstractController {

	@Autowired
	private ChirpService	chirpService;


	//Constructor
	public UserChirpController() {
		super();
	}

	@RequestMapping("/timeline")
	public ModelAndView timeline() {
		ModelAndView result;
		try{
			final List<Chirp> chirps = (List<Chirp>) chirpService.getTimeline();
			result = new ModelAndView("chirp/list");
			if(chirps != null)
				result.addObject("chirps", chirps);
			result.addObject("requestUri", "user/chirp/timeline.do");
		} catch(Throwable oops) {
			oops.printStackTrace();
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Chirp chirp = chirpService.create();
			result = newEditModelAndView(chirp);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Chirp chirp, final BindingResult binding) {
		ModelAndView result;
		Chirp validated = chirpService.reconstruct(chirp, binding);
		if (binding.hasErrors())
			result = newEditModelAndView(chirp);
		else
			try {
				chirpService.save(validated);
				result = new ModelAndView("redirect:../../");
			} catch (Throwable oops) {
				result = newEditModelAndView(chirp);
				result.addObject("message", "chirp.commitError");
			}
		return result;
	}

	protected ModelAndView newEditModelAndView(final Chirp chirp) {
		ModelAndView result;
		result = new ModelAndView("chirp/edit");
		result.addObject("chirp", chirp);
		result.addObject("actionUri", "user/chirp/save.do");
		return result;
	}
}