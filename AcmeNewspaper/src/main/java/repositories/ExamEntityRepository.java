package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ExamEntity;

@Repository
public interface ExamEntityRepository extends JpaRepository<ExamEntity, Integer>{

}
