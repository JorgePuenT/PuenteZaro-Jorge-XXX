package controllers.Agent;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdvertisementService;
import services.AgentService;
import services.NewspaperService;
import domain.Advertisement;
import domain.Newspaper;

@Controller
@RequestMapping("/agent/advertisement")
public class AgentAdvertisementController {
	
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private NewspaperService newspaperService;
	
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=true) Boolean advertised) {
		ModelAndView result;
		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers",advertised ? newspaperService.findMyAdvertisedNewspapers() : newspaperService.findMyNotAdvertisedNewspapers());
		result.addObject("requestUri", "agent/advertisement/list.do");
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Advertisement advertisement = advertisementService.create();
			result = this.newEditModelAndView(advertisement);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Advertisement advertisement, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.newEditModelAndView(advertisement);
		else
			try {
				advertisementService.save(advertisement);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = this.newEditModelAndView(advertisement, "advertisement.commitError");
				oops.printStackTrace();
			}
		return result;
	}
	
	
	protected ModelAndView newEditModelAndView(final Advertisement advertisement) {
		ModelAndView result;
		result = this.newEditModelAndView(advertisement, null);
		return result;
	}

	protected ModelAndView newEditModelAndView(final Advertisement advertisement, final String message) {
		ModelAndView result;
		Collection<Newspaper> newspapers = newspaperService.findAllNotInappropriate();
		result = new ModelAndView("advertisement/edit");
		result.addObject("newspapers", newspapers);
		result.addObject("advertisement", advertisement);
		result.addObject("actionUri", "agent/advertisement/edit.do");
		result.addObject("message", message);
		return result;
	}

}
