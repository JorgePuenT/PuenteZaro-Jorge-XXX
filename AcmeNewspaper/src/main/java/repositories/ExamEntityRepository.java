package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ExamEntity;

@Repository
public interface ExamEntityRepository extends JpaRepository<ExamEntity, Integer>{

	@Query("select count(e) from ExamEntity e where e.ticker like ?1")
	int availableTicker(String ticker);

	@Query("select e from ExamEntity e where e.newspaper.id = ?1 and e.displayMoment <= CURRENT_TIMESTAMP")
	Collection<ExamEntity> findAllDisplayableForNewspaper(int newspaperId);
}
