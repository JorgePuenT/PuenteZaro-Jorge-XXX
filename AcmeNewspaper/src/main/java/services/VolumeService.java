package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VolumeRepository;
import domain.Subscription;
import domain.Volume;
import domain.VolumeNewspaper;

@Service
@Transactional
public class VolumeService {

	private VolumeRepository volumeRepository;
	
	public Volume findOne(int volumeId) {
		return volumeRepository.findOne(volumeId);
	}
	
	public Collection<Volume> findAll() {
		return volumeRepository.findAll();
	}
	
	public Volume create() {
		Volume res = new Volume();
		
		res.setVolumeNewspaper(new ArrayList<VolumeNewspaper>());
		res.setSubscriptions(new ArrayList<Subscription>());
		
		return res;
	}
	
	public Volume save(Volume volume) {
		Assert.isTrue(volume.getId()==0);
		return volumeRepository.save(volume);
	}
	
	
}
