
package controllers.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.NewspaperService;
import services.SubscriptionService;
import controllers.AbstractController;
import domain.Subscription;

@Controller
@RequestMapping("/customer/subscription")
public class CustomerSubscriptionController extends AbstractController {

	@Autowired
	private SubscriptionService	subscriptionService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private NewspaperService	newspaperService;


	//Constructor
	public CustomerSubscriptionController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(required = true) final int newspaperId) {
		ModelAndView result;
		try {
			if (customerService.isSubscribed(newspaperService.findOne(newspaperId)))
				throw new Exception("Already subscribed");
			Subscription subscription = subscriptionService.create(newspaperId,null);
			result = newEditModelAndView(subscription);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Subscription subscription, final BindingResult binding) {
		ModelAndView result;
		Subscription validated = subscriptionService.reconstruct(subscription, binding);
		if (binding.hasErrors())
			result = newEditModelAndView(validated);
		else
			try {
				subscriptionService.save(validated);
				result = new ModelAndView("redirect:/newspaper/display.do?newspaperId=" + validated.getNewspaper().getId());
			} catch (Throwable oops) {
				result = newEditModelAndView(validated);
				result.addObject("message", "subscription.commitError");
			}
		return result;
	}

	protected ModelAndView newEditModelAndView(final Subscription subscription) {
		ModelAndView result;
		result = new ModelAndView("subscription/edit");
		result.addObject("subscription", subscription);
		result.addObject("actionUri", "customer/subscription/save.do");
		return result;
	}
}
