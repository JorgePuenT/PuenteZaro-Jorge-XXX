package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VolumeNewspaperRepository;
import domain.Newspaper;
import domain.Volume;
import domain.VolumeNewspaper;


@Service
@Transactional
public class VolumeNewspaperService {

	private VolumeNewspaperRepository volumeNewspaperRepository;
	@Autowired
	private UserService userService;
	
	public VolumeNewspaper findOne(int volumeNewspaperId) {
		return volumeNewspaperRepository.findOne(volumeNewspaperId);
	}
	
	public VolumeNewspaper createAndSave(Newspaper newspaper, Volume volume) {
		
		VolumeNewspaper res = new VolumeNewspaper();
		
		Assert.isTrue(newspaper.getUser()==userService.findByPrincipal());
		
		res.setNewspaper(newspaper);
		res.setVolume(volume);
		return volumeNewspaperRepository.save(res);
	}
	
	public void createAndSaveBatch(Collection<Newspaper> newspapers, Volume volume) {
		for(Newspaper n: newspapers)
			createAndSave(n,volume);
	}
	
	public void delete(int volumeNewspaperId) {
		Assert.isTrue(findOne(volumeNewspaperId).getNewspaper().getUser()==userService.findByPrincipal());
		volumeNewspaperRepository.delete(volumeNewspaperId);
	}
	
}
