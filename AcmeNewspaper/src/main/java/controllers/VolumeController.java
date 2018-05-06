package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.NewspaperService;
import services.VolumeService;
import utilities.internal.SchemaPrinter;
import domain.Volume;

@Controller
@RequestMapping("/volume")
public class VolumeController {
	
	@Autowired
	private VolumeService volumeService;
	@Autowired
	private NewspaperService newspaperService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView res;
		try{
			Collection<Volume> volumes = new ArrayList<Volume>(volumeService.findAll());
			res = new ModelAndView("volume/list");
			res.addObject("volumes",volumes);
			res.addObject("requestUri", "volume/list.do");
		}catch(Throwable oops) {
			res = new ModelAndView("redirect:../");
		}
		return res;
	}
	
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam(required=true) int volumeId) {
		ModelAndView res = new ModelAndView("volume/display");
		Volume volume = null;
		try {
			res.addObject("isSubscribed", customerService.isSubscribedVolume(volume));
		} catch (Throwable oops) {
		}
		try {
			volume = volumeService.findOne(volumeId);
		} catch (Throwable oops) {
			return new ModelAndView("redirect:list.do");
		}
		res.addObject("volume", volume);
		res.addObject("newspapers",volume.getNewspapers());
		res.addObject("requestUri", "volume/display.do");
		return res;
	}
}
