
package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.ChirpService;
import services.CustomerService;
import services.UserService;
import domain.Article;
import domain.User;

@Controller
@RequestMapping("")
public class UserController extends AbstractController {

	@Autowired
	private UserService	userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ChirpService chirpService;


	//Constructor
	public UserController() {
		super();
	}

	@RequestMapping("/user-list")
	public ModelAndView list() {
		ModelAndView result = null;
		try{
			final List<User> users = new ArrayList<User>(this.userService.findAll());
			result = new ModelAndView("user/list");
			result.addObject("users", users);
			result.addObject("requestUri", "user-list.do");
		} catch(Throwable oops) {
			result = new ModelAndView("welcome/index");
		}
		return result;
	}



	@RequestMapping("/user-display")
	public ModelAndView display(@RequestParam(required=false) final Integer userId) {
		ModelAndView result= new ModelAndView("user/display");
		Boolean follows = false;
		User user;
		try{
			if(userId != null)
				user = this.userService.findOne(userId);
			else
				user = this.userService.findByPrincipal();

			try{
				User principal = this.userService.findByPrincipal();
				follows = user.getFollowedBy().contains(principal) ;
				result.addObject("logged", principal);
				result.addObject("follows",follows);
				result.addObject("chirps", chirpService.findForUserNotInappropiate(user));
			}catch(Throwable oops){
				if(userId != null)
					user = this.userService.findOne(userId);
				else
					user = this.userService.findByPrincipal();
			}

			List<Article> articles = new ArrayList<Article>(this.articleService.findAllPublishedForUser(user));
			Map<Article,Boolean> articlesMap = new HashMap<Article,Boolean>();
			try{

				for (Article a: articles)
					articlesMap.put(a, this.customerService.isSubscribed(a.getNewspaper()));
			}catch(Throwable oops){
				//Volvemos a coger los artículos y el usuario ya que al petar el customerService se pierden (BUG de hibernate)
				if(userId != null)
					user = this.userService.findOne(userId);
				else
					user = this.userService.findByPrincipal();
				articles = new ArrayList<Article>(this.articleService.findAllPublishedForUser(user));
				for (Article a: articles)
					articlesMap.put(a, false);
			}


			result.addObject("user", user);
			result.addObject("articles",articles);
			result.addObject("articlesMap",articlesMap);
			result.addObject("requestUri", "user/display.do");
		} catch(Throwable oops) {
			return new ModelAndView("redirect:user-list.do");
		}



		return result;
	}
}