
package controllers.User;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.FollowUpService;
import services.UserService;
import controllers.AbstractController;
import domain.Article;
import domain.FollowUp;

@Controller
@RequestMapping("user/followUp")
public class UserFollowUpController extends AbstractController {

	@Autowired
	private FollowUpService	followUpService;
	@Autowired
	private UserService	userService;
	@Autowired
	private ArticleService articleService;


	//Constructor
	public UserFollowUpController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(required = true)int articleId) {
		ModelAndView result;
		try {
			Article a = articleService.findOne(articleId);
			Assert.isTrue(articleService.isPublished(a));
			Assert.isTrue(a.getNewspaper().getUser()==userService.findByPrincipal());
			FollowUp followUp = followUpService.create(articleId);
			result = newEditModelAndView(followUp);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final FollowUp followUp, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = newEditModelAndView(followUp);
		else
			try {
				followUpService.save(followUp);
				result = new ModelAndView("redirect:../../newspaper/display.do?newspaperId="+followUp.getArticle().getNewspaper().getId());
			} catch (Throwable oops) {
				result = newEditModelAndView(followUp);
				result.addObject("message", "followUp.commitError");
			}
		return result;
	}

	protected ModelAndView newEditModelAndView(final FollowUp followUp) {
		ModelAndView result;
		result = new ModelAndView("followUp/edit");
		result.addObject("followUp", followUp);
		result.addObject("actionUri", "user/followUp/save.do");
		return result;
	}
}