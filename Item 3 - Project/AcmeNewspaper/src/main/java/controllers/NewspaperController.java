
package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.CustomerService;
import services.ExamEntityService;
import services.NewspaperService;
import domain.Newspaper;

@Controller
@RequestMapping("/newspaper")
public class NewspaperController extends AbstractController {

	@Autowired
	private NewspaperService	newspaperService;
	@Autowired
	private ArticleService		articleService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private ExamEntityService	examEntityService;


	//Constructor
	public NewspaperController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false, defaultValue = "") final String keyword) {
		ModelAndView result;
		try{
			final List<Newspaper> newspapers = new ArrayList<Newspaper>(newspaperService.findByKeyword(keyword));
			result = new ModelAndView("newspaper/list");
			result.addObject("newspapers", newspapers);
			result.addObject("requestUri", "newspaper/list.do");
			result.addObject("keyword", keyword);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:../");
		}
		return result;
	}

	@RequestMapping("/display")
	public ModelAndView display(@RequestParam(required = true) final int newspaperId) {
		ModelAndView res = new ModelAndView("newspaper/display");
		Newspaper newspaper = null;
		try {
			newspaper = newspaperService.findOne(newspaperId);
		} catch (Throwable oops) {
			return new ModelAndView("redirect:list.do");
		}
		try {
			res.addObject("isSubscribed", customerService.isSubscribed(newspaper));
			res.addObject("isSubscribedNewspaper", customerService.isSubscribedNewspaper(newspaper));
		} catch (Throwable oops) {
		}
		if(newspaper.getInappropriate()==false){
			res.addObject("newspaper", newspaper);
			res.addObject("articles",articleService.findAllPublishedForNewspaper(newspaper));
			res.addObject("examEntities",examEntityService.findAllDisplayableForNewspaper(newspaper));
			res.addObject("requestUri", "newspaper/display.do");
			return res;
		} else
			return new ModelAndView("redirect:list.do");
	}
}
