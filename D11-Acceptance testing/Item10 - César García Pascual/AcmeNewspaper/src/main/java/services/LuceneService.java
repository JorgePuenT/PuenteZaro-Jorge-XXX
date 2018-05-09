package services;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Article;

@Service
@Transactional
public class LuceneService {

	//Inits -----------------------------------------------------------------------------------------------------------------------------
	// Logger
	@SuppressWarnings("unused")
	private static Logger LOG = LoggerFactory.getLogger(LuceneService.class);

	//JPA Persistence Unit
	@PersistenceContext(type = PersistenceContextType.EXTENDED, name = "AcmeNewspaper")
	private EntityManager em;

	//Hibernate Full Text Entity Manager
	private FullTextEntityManager ftem;





	//Search Method -----------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Article> search(String searchString) {

		try {
			updateFullTextIndex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Create a Query Builder
		QueryBuilder qb = getFullTextEntityManager().getSearchFactory().buildQueryBuilder().forEntity(Article.class).get();


		// Create a Lucene Full Text Query
		org.apache.lucene.search.Query luceneQuery = qb.bool()
			.must(qb.keyword().onFields("title", "body", "summary").matching(searchString).createQuery()).createQuery();


		Query fullTextQuery = getFullTextEntityManager().createFullTextQuery(luceneQuery, Article.class);


		System.out.println(getFullTextEntityManager().createFullTextQuery(luceneQuery, Article.class).toString());
		// Run Query and print out results to console
		List<Article> result = fullTextQuery.getResultList();




		return result;
	}

	//Auxiliary Methods -----------------------------------------------------------------------------------------------------------------------------
	public void updateFullTextIndex() throws Exception {
		getFullTextEntityManager().createIndexer().startAndWait();
	}

	protected FullTextEntityManager getFullTextEntityManager() {
		if (ftem == null)
			ftem = Search.getFullTextEntityManager(em);
		return ftem;
	}


}