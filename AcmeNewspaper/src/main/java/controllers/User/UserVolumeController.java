
package controllers.User;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NewspaperService;
import services.UserService;
import services.VolumeService;
import controllers.AbstractController;
import domain.Volume;

@Controller
@RequestMapping("/user/volume")
public class UserVolumeController extends AbstractController {

	@Autowired
	private VolumeService		volumeService;
	@Autowired
	private NewspaperService	newspaperService;
	@Autowired
	private UserService			userService;


	//Constructor
	public UserVolumeController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Volume volume = volumeService.create();
			result = this.newEditModelAndView(volume);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = true) final int volumeId) {
		ModelAndView result;

		try {
			Volume volume = volumeService.findOne(volumeId);
			result = this.newEditModelAndView(volume);

		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Volume volume, final BindingResult binding) {
		ModelAndView result;
		System.out.println(binding);
		if (binding.hasErrors())
			result = this.newEditModelAndView(volume);
		else
			try {
				volumeService.save(volume);
				result = new ModelAndView("redirect:/volume/list.do");
			} catch (Throwable oops) {
				result = this.newEditModelAndView(volume, "article.commitError");
				oops.printStackTrace();
			}
		return result;
	}

	protected ModelAndView newEditModelAndView(final Volume volume) {
		ModelAndView result;
		result = this.newEditModelAndView(volume, null);
		return result;
	}

	protected ModelAndView newEditModelAndView(final Volume volume, final String message) {
		ModelAndView result;
		result = new ModelAndView("volume/edit");
		result.addObject("volume", volume);
		result.addObject("actionUri", "user/volume/edit.do");
		result.addObject("message", message);
		return result;
	}
}
