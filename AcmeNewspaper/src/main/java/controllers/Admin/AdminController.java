
package controllers.Admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.ChirpService;
import services.NewspaperService;
import services.UserService;
import controllers.AbstractController;
import domain.Newspaper;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

	@Autowired
	private ArticleService	articleService;
	@Autowired
	private NewspaperService newspaperService;
	@Autowired
	private ChirpService chirpService;
	@Autowired
	private UserService userService;

	//Constructor
	public AdminController() {
		super();
	}

	@RequestMapping("/article/inappropriate")
	public ModelAndView setArticleInappropriate(@RequestParam(required=true) final int articleId){
		articleService.markAsInappropriate(articleId);
		return new ModelAndView("redirect: ../../../../article/list.do");
	}

	@RequestMapping("/article/taboo-list")
	public ModelAndView listInappropriateArticles(){
		ModelAndView res;
		try{

			res = new ModelAndView("article/list");
			res.addObject("articles",articleService.findAllTaboo());
			res.addObject("requestUri", "admin/article/taboo-list.do");
		} catch(Throwable oops) {
			res = new ModelAndView("redirect:/");
		}
		return res;
	}

	@RequestMapping("/newspaper/inappropriate")
	public ModelAndView setNewspaperInappropriate(@RequestParam(required=true) final int newspaperId){
		newspaperService.markAsInappropriate(newspaperId);
		return new ModelAndView("redirect: ../../../../newspaper/list.do");
	}

	@RequestMapping("/newspaper/taboo-list")
	public ModelAndView listInappropriateNewspaper(){
		ModelAndView res ;
		try{
			res = new ModelAndView("newspaper/list");
			res.addObject("newspapers",newspaperService.findAllTaboo());
			res.addObject("requestUri", "admin/newspaper/taboo-list.do");
		} catch(Throwable oops) {
			res = new ModelAndView("redirect:/");
		}
		return res;
	}

	@RequestMapping("/chirp/inappropriate")
	public ModelAndView setChirpInappropriate(@RequestParam(required=true) final int chirpId){
		chirpService.markAsInappropriate(chirpId);
		return new ModelAndView("redirect:taboo-list.do");
	}


	@RequestMapping("/chirp/taboo-list")
	public ModelAndView listInappropriateChirps(){
		ModelAndView res ;
		try{
			res = new ModelAndView("chirp/list");
			res.addObject("chirps",chirpService.findAllTaboo());
			res.addObject("requestUri", "admin/chirp/taboo-list.do");
		} catch(Throwable oops) {
			res = new ModelAndView("redirect:/");
		}
		return res;
	}

	@RequestMapping("/chirp/list")
	public ModelAndView listChirpsAdmin() {
		ModelAndView res;
		try{
			res = new ModelAndView("chirp/list");
			res.addObject("chirps",chirpService.findAll());
			res.addObject("requestUri", "admin/chirp/list.do");
		} catch(Throwable oops) {
			res = new ModelAndView("redirect:/");
		}
		return res;
	}

	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView result;

		try{
			result = new ModelAndView("admin/dashboard");

			List<Double> avgs = new ArrayList<Double>();
			avgs.add(userService.getStatsOfNewspapersPerUser()[0]);
			avgs.add(userService.getStatsOfArticlesPerUser()[0]);
			avgs.add(newspaperService.getStatsOfArticlesPerNewspaper()[0]);
			avgs.add(articleService.getFollowUpsPerArticleAvg());
			avgs.add(articleService.getFollowUpsPerArticleAvgAfterOneWeek());
			avgs.add(articleService.getFollowUpsPerArticleAvgAfterTwoWeeks());
			avgs.add(userService.getStatsOfChirpsPerUser()[0]);
			avgs.add(newspaperService.getArticleAvgForPrivateNewspapers());
			avgs.add(newspaperService.getArticleAvgForPublicNewspapers());
			result.addObject("avgs",avgs);

			List<Double> stddevs = new ArrayList<Double>();
			stddevs.add(userService.getStatsOfNewspapersPerUser()[1]);
			stddevs.add(userService.getStatsOfArticlesPerUser()[1]);
			stddevs.add(newspaperService.getStatsOfArticlesPerNewspaper()[1]);
			stddevs.add(userService.getStatsOfChirpsPerUser()[1]);
			result.addObject("stddevs",stddevs);

			List<Double> ratios = new ArrayList<Double>();
			ratios.add(userService.getRatioOfUsersWhoHaveCreatedNewspapers());
			ratios.add(userService.getRatioOfUsersWhoHavePostedMOreChirpsThan75Avg());
			ratios.add(newspaperService.getRatioOfPublicOverPrivateNewspapers());
			ratios.add(newspaperService.getRatioOfSubscribersVersusCustomersTotal());
			ratios.add(userService.getAvgRatioOfNewspapersPerPublisher());
			result.addObject("ratios",ratios);

			List<Newspaper> newspapersOverAvg = new ArrayList<Newspaper>(newspaperService.getNewspapersOverAvg());
			result.addObject("newspapersOverAvg",newspapersOverAvg);
			List<Newspaper> newspapersUnderAvg = new ArrayList<Newspaper>(newspaperService.getNewspapersUnderAvg());
			result.addObject("newspapersUnderAvg",newspapersUnderAvg);
		} catch(Throwable oops) {
			result = new ModelAndView("redirect:/");
		}

		return result;
	}
}