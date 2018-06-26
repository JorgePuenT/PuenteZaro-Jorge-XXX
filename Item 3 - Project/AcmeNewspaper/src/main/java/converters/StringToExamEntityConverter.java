
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

import services.ExamEntityService;
import domain.ExamEntity;

@Component
@Transactional
public class StringToExamEntityConverter implements Converter<String, ExamEntity> {

	@Autowired
	ExamEntityService	examEntityService;


	@Override
	public ExamEntity convert(final String text) {
		ExamEntity result;
		int id;

		if (text == "") {
			result = null;
		} else {
			try {
				id = Integer.valueOf(text);
				if (id == 0) {
					result = examEntityService.create();
				} else {
					result = examEntityService.findOne(id);
				}
			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		}

		return result;
	}

}
