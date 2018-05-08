
package services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SubscriptionRepository;
import utilities.internal.SchemaPrinter;
import domain.CreditCard;
import domain.Newspaper;
import domain.Subscription;
import domain.Volume;

@Service
@Transactional
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository	subscriptionRepository;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private Validator				validator;
	@Autowired
	private NewspaperService		newspaperService;
	@Autowired
	private VolumeService 			volumeService;


	//Supporting Services -------------------

	//CRUD Methods -------------------------

	public Subscription create(Integer newspaperId, Integer volumeId) {
		Assert.isTrue(newspaperId!=null || volumeId!=null);
		Subscription res = new Subscription();
		if(volumeId==null)
			res.setNewspaper(newspaperService.findOne(newspaperId));
		else{
			res.setVolume(volumeService.findOne(volumeId));
		}
		return res;
	}

	public Subscription findOne(int subscriptionId) {
		Assert.isTrue(subscriptionId != 0);
		Subscription res = subscriptionRepository.findOne(subscriptionId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Subscription> findAll() {
		return subscriptionRepository.findAll();
	}

	public Subscription save(final Subscription subscription) 
	{
		Assert.notNull(subscription);
		if(subscription.getNewspaper()!=null){
			Assert.isTrue(!customerService.isSubscribedNewspaper(subscription.getNewspaper()));
		}
		else
			Assert.isTrue(!customerService.isSubscribedVolume(subscription.getVolume()));
					
		//Comprobación fecha
		Date cardDate = new Date();
		String date =subscription.getCreditCard().getExpirationYear() + "/"+ subscription.getCreditCard().getExpirationMonth() + "/00";
		try {
			cardDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
		} catch (ParseException e) {
		}
		Assert.isTrue(cardDate.after(new Date()));
		return subscriptionRepository.save(subscription);
	}

	public Subscription reconstruct(Subscription subscription, BindingResult binding) {
		subscription.setId(0);
		subscription.setCustomer(customerService.findByPrincipal());
		validator.validate(subscription, binding);
		return subscription;
	}

}
