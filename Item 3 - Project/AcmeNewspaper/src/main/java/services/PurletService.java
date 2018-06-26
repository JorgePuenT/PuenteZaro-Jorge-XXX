package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PurletRepository;
import domain.Purlet;
import domain.Newspaper;

@Service
@Transactional
public class PurletService {

	@Autowired
	private PurletRepository purletRepository;
	@Autowired
	private AdminService adminService;
	@Autowired
	private Validator validator;

	public Purlet create() {
		Purlet res = new Purlet();
		res.setAdmin(adminService.findByPrincipal());
		return res;
	}

	public Purlet findOne(int purletId){
		return purletRepository.findOne(purletId);
	}

	public Collection<Purlet> findAll() {
		return purletRepository.findAll();
	}

	public Purlet save(Purlet purlet) {
		if(purlet.getId() == 0)
			Assert.isTrue(purlet.getDisplayMoment() == null || purlet.getDisplayMoment().after(new Date(System.currentTimeMillis()-1000)));
		else if(purlet.getDisplayMoment() != null)
			Assert.isTrue(purlet.getDisplayMoment().after(new Date(System.currentTimeMillis()-1000))
				|| purlet.getDisplayMoment().equals(findOne(purlet.getId()).getDisplayMoment()));
		Assert.isTrue(adminService.findByPrincipal() == purlet.getAdmin());
		return purletRepository.save(purlet);
	}

	public Purlet reconstruct(Purlet purlet, BindingResult binding) {
		if(purlet.getId()==0) {
			purlet.setId(0);
			purlet.setAdmin(adminService.findByPrincipal());
			purlet.setTicker(createTicker());
			purlet.setNewspaper(null);
		} else{
			Purlet db = findOne(purlet.getId());
			if(db.getDraft()){
				purlet.setVersion(db.getVersion());
				purlet.setAdmin(db.getAdmin());
				purlet.setTicker(db.getTicker());
				purlet.setNewspaper(null);
			} else {
				purlet.setVersion(db.getVersion());
				purlet.setAdmin(db.getAdmin());
				purlet.setTicker(db.getTicker());
				purlet.setTitle(db.getTitle());
				purlet.setDescription(db.getDescription());
				purlet.setGauge(db.getGauge());
				purlet.setDisplayMoment(db.getDisplayMoment());
				purlet.setDraft(db.getDraft());
			}
		}

		validator.validate(purlet, binding);
		return purlet;
	}


	public void delete(int purletId) {
		Purlet purlet = findOne(purletId);
		Assert.isTrue(purlet.getDraft());
		Assert.isTrue(adminService.findByPrincipal()==purlet.getAdmin());
		purletRepository.delete(purlet);
	}

	public Boolean availableTicker(String ticker) {
		return purletRepository.availableTicker(ticker)==0;
	}

	public String createTicker(){
		String res = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year = cal.get(Calendar.YEAR) % 100;
		String yy = String.valueOf(year);
		int month = cal.get(Calendar.MONTH) + 1;
		String mm="";
		if(month<10)
			mm = 0+String.valueOf(month);
		else
			mm = String.valueOf(month);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String dd = "";
		if(day<10)
			dd = 0+String.valueOf(day);
		else
			dd = String.valueOf(day);
		res = yy+":"+cadenaAlfabetica(2)+":"+mm+":"+cadenaNumerica(5)+":"+dd;

		if(availableTicker(res))
			return res;
		else
			return createTicker();
	}

	private String cadenaAlfabetica(int size){
		String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String cadena = "";
		for (int i = 0; i < size; i++) {
			int num = (int) (Math.random() * 62);
			cadena = cadena.concat(caracteres.substring(num, num + 1));
		}
		return cadena;
	}

	private String cadenaNumerica(int size){
		String numeros = "0123456789";
		String cadena = "";
		for (int i = 0; i < size; i++) {
			int num = (int) (Math.random() * 10);
			cadena = cadena.concat(numeros.substring(num, num + 1));
		}
		return cadena;
	}

	public Collection<Purlet> findAllDisplayableForNewspaper(Newspaper newspaper) {
		return purletRepository.findAllDisplayableForNewspaper(newspaper.getId());
	}

}
