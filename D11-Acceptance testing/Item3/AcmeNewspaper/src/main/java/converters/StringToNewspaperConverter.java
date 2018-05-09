
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.NewspaperService;
import domain.Newspaper;

@Component
@Transactional
public class StringToNewspaperConverter implements Converter<String, Newspaper> {

	@Autowired
	NewspaperService	newspaperService;


	@Override
	public Newspaper convert(final String text) {
		Newspaper result;
		int id;
		if (text == "") {
			result = null;
		} else {
			try {
				id = Integer.valueOf(text);
				result = newspaperService.findOne(id);
			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		}
		return result;
	}

}
