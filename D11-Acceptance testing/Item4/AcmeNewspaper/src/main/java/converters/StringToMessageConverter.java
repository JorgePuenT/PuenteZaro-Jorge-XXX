
package converters;

/*
 * StringToAuditorConverter.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.MessageService;
import domain.Message;

@Component
@Transactional
public class StringToMessageConverter implements Converter<String, Message> {

	@Autowired
	MessageService	messageService;;


	@Override
	public Message convert(final String text) {
		Message result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = messageService.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
