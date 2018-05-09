
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.SubscriptionService;
import domain.Subscription;

@Component
@Transactional
public class StringToSubscriptionConverter implements Converter<String, Subscription> {

	@Autowired
	SubscriptionService	subscriptionService;


	@Override
	public Subscription convert(final String text) {
		Subscription result;
		int id;
		if (text == "") {
			result = null;
		} else {
			try {
				id = Integer.valueOf(text);
				result = subscriptionService.findOne(id);
			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		}
		return result;
	}

}
