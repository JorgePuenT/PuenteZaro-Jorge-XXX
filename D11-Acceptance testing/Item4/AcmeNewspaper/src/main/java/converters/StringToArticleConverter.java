
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.ArticleService;
import domain.Article;

@Component
@Transactional
public class StringToArticleConverter implements Converter<String, Article> {

	@Autowired
	ArticleService	articleService;


	@Override
	public Article convert(final String text) {
		Article result;
		int id;
		if (text == "") {
			result = null;
		} else {
			try {
				id = Integer.valueOf(text);
				result = articleService.findOne(id);
			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		}
		return result;
	}

}
