package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Purlet;

@Repository
public interface PurletRepository extends JpaRepository<Purlet, Integer>{

	@Query("select count(e) from Purlet e where e.ticker like ?1")
	int availableTicker(String ticker);

	@Query("select e from Purlet e where e.newspaper.id = ?1 and e.displayMoment <= CURRENT_TIMESTAMP")
	Collection<Purlet> findAllDisplayableForNewspaper(int newspaperId);
}
