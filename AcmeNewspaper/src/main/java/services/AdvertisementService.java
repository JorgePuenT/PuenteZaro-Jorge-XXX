package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdvertisementRepository;
import domain.Advertisement;
import domain.Agent;
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
			Assert.notNull(advertisement);
			Assert.isTrue(advertisement.getId()==0);
			
			Agent agent = agentService.findByPrincipal();
			Assert.notNull(agent);
			advertisement.setAgent(agent);
			
			//Verificación de tarjeta de credito vigente
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int month = cal.get(Calendar.MONTH)+1; //Current month(Month starts in 0)
			int year = cal.get(Calendar.YEAR);
			Assert.isTrue(advertisement.getCreditCard().getExpirationYear() >= year);
			if(advertisement.getCreditCard().getExpirationYear() == year)
				Assert.isTrue(advertisement.getCreditCard().getExpirationMonth() > month);
			
			return this.advertisementRepository.save(advertisement);
		}
		
		public void markAsInappropriate(final int advertisementId) {
			Assert.notNull(this.adminService.findByPrincipal());
			Advertisement a = this.findOne(advertisementId);
			a.setInappropriate(true);
			this.advertisementRepository.save(a);
		}

		public Collection<Advertisement> findAllTaboo() {
			return advertisementRepository.findAllTaboo();
		}
		
		public void flush(){
			advertisementRepository.flush();
		}
		
		public Double getRatioWithTaboo(){
			Double res = advertisementRepository.getRatioWithTaboo();
			return res == null ? 0 : res;
		}
		
		public Advertisement getRandomForNewspaper(Newspaper newspaper){
			List<Advertisement> advertisements = new ArrayList<Advertisement>(newspaper.getAdvertisements());
			if(advertisements.isEmpty()) return null;
			Advertisement res = advertisements.get((int)Math.round(Math.random()*advertisements.size()-1));
			return res;
		}
		
		
}
