package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdvertisementRepository;
import domain.Advertisement;
import domain.Newspaper;

@Service
@Transactional
public class AdvertisementService {
	
	@Autowired
	private AdvertisementRepository advertisementRepository;
	@Autowired
	private AgentService agentService;
	@Autowired
	private AdminService adminService;

	//CRUD Methods -------------------------

		public Advertisement create() {
			Advertisement res = new Advertisement();
			res.setInappropriate(false);
			Assert.notNull(agentService.findByPrincipal());
			res.setAgent(this.agentService.findByPrincipal());
			return res;
		}

		public Advertisement findOne(final int advertisementId) {
			Assert.isTrue(advertisementId != 0);
			Advertisement res = this.advertisementRepository.findOne(advertisementId);
			Assert.notNull(res);
			return res;
		}

		public Collection<Advertisement> findAll() {
			return this.advertisementRepository.findAll();
		}

		public Advertisement save(final Advertisement advertisement) {
			return this.advertisementRepository.save(advertisement);
		}
		
		public void markAsInappropriate(final int advertisementId) {
			Assert.notNull(this.adminService.findByPrincipal());
			Advertisement a = this.findOne(advertisementId);
			a.setInappropriate(true);
			this.advertisementRepository.save(a);
		}

		public Collection<Newspaper> findAllTaboo() {
			return advertisementRepository.findAllTaboo();
		}
		
		public void flush(){
			advertisementRepository.flush();
		}
		
		
}
