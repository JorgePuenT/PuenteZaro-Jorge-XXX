
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.VolumeNewspaper;

@Component
@Transactional
public class VolumeNewspaperToStringConverter implements Converter<VolumeNewspaper, String> {

	@Override
	public String convert(final VolumeNewspaper volumeNewspaper) {
		String result;

		if (volumeNewspaper == null) {
			result = null;
		} else {
			result = String.valueOf(volumeNewspaper.getId());
		}

		return result;
	}

}
