
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.VolumeNewspaperService;
import domain.VolumeNewspaper;

@Component
@Transactional
public class StringToVolumeNewspaperConverter implements Converter<String, VolumeNewspaper> {

	@Autowired
	VolumeNewspaperService	volumeNewspaperService;


	@Override
	public VolumeNewspaper convert(final String text) {
		VolumeNewspaper result;
		int id;
		if (text == "") {
			result = null;
		} else {
			try {
				id = Integer.valueOf(text);
				result = volumeNewspaperService.findOne(id);
			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		}
		return result;
	}

}
