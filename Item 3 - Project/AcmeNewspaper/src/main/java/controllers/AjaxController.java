package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

@RestController
@RequestMapping("/ajax")
public class AjaxController {
	@Autowired
	private UserAccountService	userAccountService;

	@RequestMapping(value = "/usernameExists", method = RequestMethod.GET)
	public String usernameExists(@RequestParam(required = true) String username) {
		UserAccount ua = LoginService.getPrincipal();
		if (!userAccountService.usernameAvailable(username) && !username.equals(ua.getUsername()))
			return "true";
		else
			return "false";
	}
}
