package controllers.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import controllers.AbstractController;

@Controller
@RequestMapping("/user")
public class UserUserController extends AbstractController{

	@Autowired
	private UserService	userService;

	public UserUserController(){
		super();
	}

	@RequestMapping("/follow")
	public ModelAndView follow(@RequestParam(required=true)int userId){
		try{
			userService.follow(userId);
		}catch(Throwable oops){}
		return new ModelAndView("redirect:/user-display.do?userId="+userId);
	}

	@RequestMapping("/un-follow")
	public ModelAndView unFollow(@RequestParam(required=true)int userId){
		try{
			userService.unFollow(userId);
		}catch(Throwable oops){}
		return new ModelAndView("redirect:/user-display.do?userId="+userId);
	}

}
