package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Volume;

public interface VolumeRepository extends JpaRepository<Volume, Integer>{

}
