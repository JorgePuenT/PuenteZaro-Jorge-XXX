
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.UserService;
import domain.Customer;
import domain.User;

@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractController {

	@Autowired
	private UserService		userService;
	@Autowired
	private CustomerService	customerService;


	//USER

	//Create Edit GET
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public ModelAndView createUser() {
		ModelAndView result;
		try {
			result = this.newEditModelAndViewUser(userService.create());
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	//Save Delete POST
	@RequestMapping(value = "user", method = RequestMethod.POST, params = "save")
	public ModelAndView saveUser(@Valid final User user, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.newEditModelAndViewUser(user);
		else
			try {
				User saved = userService.save(user);
				result = new ModelAndView("redirect:../user-display.do?userId=" + saved.getId());
			} catch (Throwable oops) {
				oops.printStackTrace();
				result = this.newEditModelAndViewUser(user);
				result.addObject("message", "user.commitError");
			}
		return result;
	}

	//CUSTOMER

	//Create Edit GET
	@RequestMapping(value = "customer", method = RequestMethod.GET)
	public ModelAndView createCustomer() {
		ModelAndView result;
		try {
			result = this.newEditModelAndViewCustomer(customerService.create());
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	//Save Delete POST
	@RequestMapping(value = "customer", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCustomer(@Valid final Customer customer, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.newEditModelAndViewCustomer(customer);
		else
			try {
				customerService.save(customer);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				oops.printStackTrace();
				result = this.newEditModelAndViewCustomer(customer);
				result.addObject("message", "customer.commitError");
			}
		return result;
	}

	//AUXILIARES

	protected ModelAndView newEditModelAndViewUser(final User user) {
		ModelAndView result;
		result = this.newEditModelAndViewUser(user, null);
		return result;
	}

	protected ModelAndView newEditModelAndViewUser(final User user, final String message) {
		ModelAndView result;
		result = new ModelAndView("register/user");
		result.addObject("user", user);
		result.addObject("message", message);
		result.addObject("actionUri", "user/save.do");
		return result;
	}

	protected ModelAndView newEditModelAndViewCustomer(final Customer customer) {
		ModelAndView result;
		result = this.newEditModelAndViewCustomer(customer, null);
		return result;
	}

	protected ModelAndView newEditModelAndViewCustomer(final Customer customer, final String message) {
		ModelAndView result;
		result = new ModelAndView("register/customer");
		result.addObject("customer", customer);
		result.addObject("message", message);
		result.addObject("actionUri", "customer/save.do");
		return result;
	}

}
