
package controllers.Cesar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LuceneService;
import controllers.AbstractController;
import domain.Article;

@Controller
@RequestMapping("/lucene")
public class LuceneController extends AbstractController {

	@Autowired
	private LuceneService	luceneService;

	//Constructor
	public LuceneController() {
		super();
	}

	@RequestMapping("/search")
	public ModelAndView search(){
		ModelAndView res;
		try{
			res = new ModelAndView("lucene/search");
			res.addObject("requestUri", "lucene/search.do");
		} catch(Throwable oops) {
			res = new ModelAndView("redirect:/");
		}
		return res;
	}

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required=true)String keyword){
		ModelAndView res;
		try{
			List<Article> articles = luceneService.search(keyword);
			res = new ModelAndView("lucene/list");
			res.addObject("articles", articles);
			res.addObject("requestUri", "lucene/list.do");
		} catch(Throwable oops) {
			res = new ModelAndView("redirect:search.do");
		}
		return res;
	}

}