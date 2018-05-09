package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Advertisement;
import domain.Newspaper;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer>{
	
	@Query(
			value = "select advertisement.* from advertisement join (select taboowordss as word from systemconfig join systemconfig_taboowordss on systemconfig.id = systemconfig_taboowordss.systemconfig_id) as taboo_words where title like concat('%',word,'%') group by advertisement.id",
			nativeQuery = true)
	Collection<Advertisement> findAllTaboo();
	
	@Query(value = "select coalesce(count(*)/(select count(*)*1.0 from advertisement as a2),0) from advertisement join (select taboowordss as word from systemconfig join systemconfig_taboowordss on systemconfig.id = systemconfig_taboowordss.systemconfig_id) as taboo_words where title like concat('%',word,'%') group by advertisement.id",
			nativeQuery = true)
	Double getRatioWithTaboo();
	
	@Query("select a from Advertisement a where a.inappropriate = false and a.newspaper = ?1")
	Collection<Advertisement> getNonInappropriateForNewspaper(Newspaper newspaper);

}
