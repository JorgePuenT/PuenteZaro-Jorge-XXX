package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VolumeRepository;
import domain.Newspaper;
import domain.Subscription;
import domain.Volume;

@Service
@Transactional
public class VolumeService {

	@Autowired
	private VolumeRepository volumeRepository;

	@Autowired
	private UserService userService;


	public Volume findOne(int volumeId) {
		return volumeRepository.findOne(volumeId);
	}

	public Collection<Volume> findAll() {
		return volumeRepository.findAll();
	}

	public Volume create() {
		Volume res = new Volume();

		res.setUser(userService.findByPrincipal());
		res.setNewspapers(new ArrayList<Newspaper>());
		res.setSubscriptions(new ArrayList<Subscription>());

		return res;
	}

	public Volume save(Volume volume) {
		if(volume.getId()==0){
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int year = cal.get(Calendar.YEAR);
			volume.setYear(year);
		}
		Volume res = volumeRepository.save(volume);
		cleanVolumeNewspaperRelationship(res.getId());
		for(Newspaper n : res.getNewspapers()){
			n.getVolumes().add(res);
		}
		return res;
	}

	public Double getAvgOfNewspapersPerVolume() {
		return volumeRepository.getAvgOfNewspapersPerVolume();
	}
	
	public Double getRatioOfSubscriptionsVolumesVersusNewspapers() {
		Double res = volumeRepository.getRatioOfSubscriptionsVolumesVersusNewspapers();
		return res == null ? 0 : res;
	}
	
	public void cleanVolumeNewspaperRelationship(int volumeId){
		volumeRepository.cleanVolumeNewspaperRelationship(volumeId);
	}

}
