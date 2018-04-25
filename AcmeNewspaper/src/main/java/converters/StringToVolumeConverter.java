
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.VolumeService;
import domain.Volume;

@Component
@Transactional
public class StringToVolumeConverter implements Converter<String, Volume> {

	@Autowired
	VolumeService	volumeService;


	@Override
	public Volume convert(final String text) {
		Volume result;
		int id;
		if (text == "") {
			result = null;
		} else {
			try {
				id = Integer.valueOf(text);
				result = volumeService.findOne(id);
			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		}
		return result;
	}

}
