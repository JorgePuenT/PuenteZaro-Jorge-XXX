
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Newspaper;
import domain.User;

@Repository
public interface NewspaperRepository extends JpaRepository<Newspaper, Integer> {

	@Query("select n from Newspaper n where (n.title like %?1% or n.description like %?1%) and  n.inappropriate = false")
	Collection<Newspaper> findByKeyword(String keyword);

	@Query("select n from Newspaper n where n.user = ?1 AND (n.publicationDate > CURRENT_TIMESTAMP)")
	Collection<Newspaper> findMyNonPublished(User user);

	@Query(
		value = "select newspaper.* from newspaper join (select taboowordss as word from systemconfig join systemconfig_taboowordss on systemconfig.id = systemconfig_taboowordss.systemconfig_id) as taboo_words where description like concat('%',word,'%') or title like concat('%',word,'%') group by newspaper.id",
		nativeQuery = true)
	Collection<Newspaper> findAllTaboo();

	//The average and the standard deviation of articles per newspaper.
	@Query("select coalesce(avg(n.articless.size),0), coalesce(stddev(n.articless.size),0) from Newspaper n)")
	Double[] getStatsOfArticlesPerNewspaper();

	//The newspapers that have at least 10% more articles than the average
	@Query("select n from Newspaper n where n.articless.size > (select avg(n2.articless.size) from Newspaper n2)")
	Collection<Newspaper> getNewspapersOverAvg();

	//The newspapers that have at least 10% fewer articles than the average.
	@Query("select n from Newspaper n where n.articless.size < (select avg(n2.articless.size) from Newspaper n2)")
	Collection<Newspaper> getNewspapersUnderAvg();

	//The ratio of public versus private newspapers.
	@Query("select coalesce(count(n)*1.0/(select count(n2)*1.0 from Newspaper n2 where n2.isPrivate=true ),0) from Newspaper n where n.isPrivate=false")
	Double getRatioOfPublicOverPrivateNewspapers();

	//The average number of articles per private newspapers.
	@Query("select avg(n.articless.size) from Newspaper n where n.isPrivate=true")
	Double getArticleAvgForPrivateNewspapers();

	//The average number of articles per public newspapers.
	@Query("select avg(n.articless.size) from Newspaper n where n.isPrivate=false")
	Double getArticleAvgForPublicNewspapers();

	//The ratio of subscribers per private newspaper versus the total number of customers.
	@Query("select coalesce(count(n)*1.0/(select count(n2)*1.0 from Newspaper n2 where n2.isPrivate=true ),0) from Newspaper n where n.isPrivate=false")
	Double getRatioOfSubscribersVersusCustomersTotal();
}
