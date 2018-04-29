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

import services.AdvertisementService;
import services.ArticleService;
import services.CustomerService;
import domain.Advertisement;
import domain.Article;

@Controller
@RequestMapping("/article")
public class ArticleController extends AbstractController {

	@Autowired
	private ArticleService	articleService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AdvertisementService advertisementService;


	//Constructor
	public ArticleController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required=false,defaultValue="")String keyword) {
		ModelAndView result;
		try{
			List<Article> articles = new ArrayList<Article>(articleService.findAllPublishedKeyword(keyword));
			Map<Article,Boolean> articlesMap = new HashMap<Article,Boolean>();
			try{
				for (Article a: articles)
					articlesMap.put(a, customerService.isSubscribed(a.getNewspaper()));
			}catch(Throwable oops){
				//Volvemos a coger los artículos ya que al petar el customerService se pierden (BUG de hibernate)

				articles = new ArrayList<Article>(articleService.findAllPublishedKeyword(keyword));
				for (Article a: articles)
					articlesMap.put(a, false);
			}
			result = new ModelAndView("article/list");
			result.addObject("articlesMap", articlesMap);
			result.addObject("articles",articles);
			result.addObject("keyword",keyword);
			result.addObject("requestUri", "article/list.do");
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	@RequestMapping("/display")
	public ModelAndView display(@RequestParam(required=true)int articleId){
		ModelAndView result;
		try{
			Article a = articleService.findOne(articleId);
			Advertisement ad = advertisementService.getRandomForNewspaper(a.getNewspaper());
			if(!articleService.isPublished(a)) throw new Exception();
			if(a.getNewspaper().getIsPrivate())
				if(!customerService.isSubscribed(a.getNewspaper())) throw new Exception();
			result = new ModelAndView("article/display");
			result.addObject("article",a);
			result.addObject("advertisement",ad);
			result.addObject("followUps",a.getFollowUps());
			result.addObject("requestUri", "article/display.do");
		}catch(Throwable oops){
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}


}