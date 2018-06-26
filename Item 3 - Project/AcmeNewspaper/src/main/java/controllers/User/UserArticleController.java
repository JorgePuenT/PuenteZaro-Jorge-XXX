
package controllers.User;

import java.util.Collection;

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
import services.NewspaperService;
import services.UserService;
import controllers.AbstractController;
import domain.Article;
import domain.Newspaper;

@Controller
@RequestMapping("/user/article")
public class UserArticleController extends AbstractController {

	@Autowired
	private ArticleService		articleService;
	@Autowired
	private NewspaperService	newspaperService;
	@Autowired
	private UserService			userService;


	//Constructor
	public UserArticleController() {
		super();
	}

	@RequestMapping(value="/list",method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		result = new ModelAndView("article/list");
		result.addObject("articles",articleService.findMyArticles());
		result.addObject("requestUri", "user/article/list.do");
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Article article = articleService.create();
			result = this.newEditModelAndView(article);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = true) final int articleId) {
		ModelAndView result;

		try {
			Article article = articleService.findOne(articleId);
			Assert.isTrue(article.getNewspaper().getUser() == (userService.findByPrincipal()));
			Assert.isTrue(!article.getFinalMode());
			result = this.newEditModelAndView(article);

		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Article article, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.newEditModelAndView(article);
		else
			try {
				articleService.save(article);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = this.newEditModelAndView(article, "article.commitError");
				oops.printStackTrace();
			}
		return result;
	}

	protected ModelAndView newEditModelAndView(final Article article) {
		ModelAndView result;
		result = this.newEditModelAndView(article, null);
		return result;
	}

	protected ModelAndView newEditModelAndView(final Article article, final String message) {
		ModelAndView result;
		Collection<Newspaper> nonPublishedNewspapers = newspaperService.findMyNonPublished();
		result = new ModelAndView("article/edit");
		result.addObject("nonPublishedNewspapers", nonPublishedNewspapers);
		result.addObject("article", article);
		result.addObject("actionUri", "user/article/edit.do");
		result.addObject("message", message);
		return result;
	}
}
