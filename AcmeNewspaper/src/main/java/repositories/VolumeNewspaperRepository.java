package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.VolumeNewspaper;

@Repository
public interface VolumeNewspaperRepository extends JpaRepository<VolumeNewspaper, Integer>{

}
