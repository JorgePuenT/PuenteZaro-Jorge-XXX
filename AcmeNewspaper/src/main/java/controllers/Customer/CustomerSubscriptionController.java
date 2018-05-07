
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
import services.VolumeService;
import utilities.internal.SchemaPrinter;
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
	@Autowired
	private VolumeService		volumeService;


	//Constructor
	public CustomerSubscriptionController() {
		super();
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(required = false) final Integer newspaperId,
			@RequestParam(required = false) final Integer volumeId) {
		ModelAndView result;
		try {
			if (newspaperId!=null && customerService.isSubscribedNewspaper(newspaperService.findOne(newspaperId)))
				throw new Exception("Already subscribed");
			if (volumeId!=null && customerService.isSubscribedVolume(volumeService.findOne(volumeId)))
				throw new Exception("Already subscribed");
			if (newspaperId == null && volumeId == null)
				throw new Exception("Wrong direction");
			Subscription subscription;
			if(volumeId==null)
				subscription = subscriptionService.create(newspaperId,null);
			else
				subscription = subscriptionService.create(null, volumeId);
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
		SchemaPrinter.print(validated);
		if (binding.hasErrors())
			result = newEditModelAndView(validated);
		else
			try {
				subscriptionService.save(validated);
				if(validated.getNewspaper()!=null)
					result = new ModelAndView("redirect:/newspaper/display.do?newspaperId=" + validated.getNewspaper().getId());
				else {
					result = new ModelAndView("redirect:/volume/display.do?volumeId=" + validated.getVolume().getId());
				}		
			} catch (Throwable oops) {
				oops.printStackTrace();
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
