package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Volume;

@Repository
public interface VolumeRepository extends JpaRepository<Volume, Integer>{

	@Query("select avg(v.volumeNewspapers.size) from Volume v")
	Double getAvgOfNewspapersPerVolume();
	
	@Query("select coalesce(count(s)*1.0/(select count(s2)*1.0 from Subscription s2 where s2.newspaper is not null ),0) from Subscription s where s.volume is not null")
	Double getRatioOfSubscriptionsVolumesVersusNewspapers();
}
