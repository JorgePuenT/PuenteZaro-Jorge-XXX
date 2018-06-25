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

import repositories.ExamEntityRepository;
import domain.ExamEntity;

@Service
@Transactional
public class ExamEntityService {

	@Autowired
	private ExamEntityRepository examEntityRepository;
	@Autowired
	private AdminService adminService;
	@Autowired
	private Validator validator;
	
	public ExamEntity create() {
		ExamEntity res = new ExamEntity();
		
		res.setTicker(createTicker());
		res.setAdmin(adminService.findByPrincipal());

		return res;
	}
	
	public ExamEntity findOne(int examEntityId){
		return examEntityRepository.findOne(examEntityId);
	}
	
	public Collection<ExamEntity> findAll() {
		return examEntityRepository.findAll();
	}
	
	public ExamEntity save(ExamEntity examEntity) {
		Assert.isTrue(examEntity.getDisplayMoment().after(new Date(System.currentTimeMillis()-1000)) 
				|| examEntity.getDisplayMoment()==null);
		Assert.isTrue(examEntity.getDraft());
		Assert.notNull(adminService.findByPrincipal());
		return examEntityRepository.save(examEntity);
	}
	
	public ExamEntity reconstruct(ExamEntity examEntity, BindingResult binding) {
		if(examEntity.getId()==0) {
			examEntity.setId(0);
			examEntity.setAdmin(adminService.findByPrincipal());
			validator.validate(examEntity, binding);
		} else if(examEntity.getDraft()){
			ExamEntity db = findOne(examEntity.getId());
			examEntity.setVersion(db.getVersion());
			examEntity.setAdmin(db.getAdmin());
			examEntity.setNewspaper(null);
			examEntity.setTicker(db.getTicker());
			examEntity.setTitle(examEntity.getTitle());
			examEntity.setDescription(examEntity.getDescription());
			examEntity.setGauge(examEntity.getGauge());
			examEntity.setDisplayMoment(examEntity.getDisplayMoment());
		} else {
			ExamEntity db = findOne(examEntity.getId());
			examEntity.setVersion(db.getVersion());
			examEntity.setAdmin(db.getAdmin());
			examEntity.setNewspaper(examEntity.getNewspaper());
			examEntity.setTicker(db.getTicker());
			examEntity.setTitle(db.getTitle());
			examEntity.setDescription(db.getDescription());
			examEntity.setGauge(db.getGauge());
			examEntity.setDisplayMoment(db.getDisplayMoment());
		}
		return examEntity;
	}
	
	public void delete(ExamEntity examEntity) {
		Assert.isTrue(examEntity.getDraft());
		Assert.isTrue(adminService.findByPrincipal()==examEntity.getAdmin());
		examEntityRepository.delete(examEntity);
	}
	
	public Boolean availableTicker(String ticker) {
		return examEntityRepository.availableTicker(ticker)==0;
	}
	
	public String createTicker(){
		String res = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year = cal.get(Calendar.YEAR) % 100;
		String yy = String.valueOf(year);
		int month = cal.get(Calendar.MONTH) + 1;
		String mm="";
		if(month<10) { 
			mm = 0+String.valueOf(month);	
		}else{
			mm = String.valueOf(month); 
		}
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String dd = "";
		if(day<10) { 
			dd = 0+String.valueOf(day);	
		}else{
			dd = String.valueOf(day); 
		}
		
		//*************************
		
//		res = 
		
		//*************************
		if(availableTicker(res)){
			return res;
		} else {
			return createTicker();
		}
	}

	private String cadenaAlfabética(int size){
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String cadena = "";
		for (int i = 0; i < size; i++) {
			int num = (int) (Math.random() * 26);
		    cadena = cadena.concat(caracteres.substring(num, num + 1));
		}
		return cadena;
	}

	private String cadenaNumérica(int size){
		String numeros = "0123456789";
		String cadena = "";
		for (int i = 0; i < size; i++) {
			int num = (int) (Math.random() * 10);
		    cadena = cadena.concat(numeros.substring(num, num + 1));
		}
		return cadena;
	}
}
