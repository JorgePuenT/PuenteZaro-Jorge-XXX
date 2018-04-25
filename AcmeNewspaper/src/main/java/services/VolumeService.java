package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
		res.setVolumeNewspapers(new ArrayList<VolumeNewspaper>());
		res.setSubscriptions(new ArrayList<Subscription>());

		return res;
	}

	public Volume save(Volume volume) {
		Assert.isTrue(volume.getId()==0);
		volume.setYear(new Date().getYear());
		return volumeRepository.save(volume);
	}


}
