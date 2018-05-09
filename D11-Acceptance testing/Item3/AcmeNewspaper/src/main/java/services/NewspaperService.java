package services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.NewspaperRepository;
import domain.Agent;
import domain.Article;
import domain.Newspaper;
import domain.Subscription;
import domain.Volume;

@Service
@Transactional
public class NewspaperService {

	@Autowired
	private NewspaperRepository		newspaperRepository;
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private Validator validator;
	@Autowired
	private AgentService agentService;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public Newspaper create() {
		Newspaper res = new Newspaper();

		res.setArticless(new ArrayList<Article>());
		res.setSubscriptionss(new ArrayList<Subscription>());
		res.setVolumes(new ArrayList<Volume>());

		res.setInappropriate(false);
		Assert.isTrue(userService.findByPrincipal() instanceof domain.User);
		res.setUser(this.userService.findByPrincipal());
		return res;
	}

	public Newspaper findOne(final int newspaperId) {
		Assert.isTrue(newspaperId != 0);
		Newspaper res = this.newspaperRepository.findOne(newspaperId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Newspaper> findAll() {
		return this.newspaperRepository.findAll();
	}
	
	public Collection<Newspaper> findAllNotInappropriate() {
		return this.newspaperRepository.findAllNotInappropriate();
	}

	public Newspaper save(final Newspaper newspaper) {
		Assert.notNull(newspaper);
		Assert.isTrue(newspaper.getUser().equals(userService.findByPrincipal()));
		Assert.notNull(this.userService.findByPrincipal());
		Assert.isTrue(newspaper.getPublicationDate().after(new Date()));
		if(newspaper.getIsPrivate()) Assert.notNull(newspaper.getPrice());
		else newspaper.setPrice(null);
		return this.newspaperRepository.save(newspaper);
	}

	public Collection<Newspaper> findByKeyword(final String keyword){
		return this.newspaperRepository.findByKeyword(keyword);
	}
	public void markAsInappropriate(final int newspaperId) {
		Assert.notNull(this.adminService.findByPrincipal());
		Newspaper n = this.findOne(newspaperId);
		n.setInappropriate(true);
		this.newspaperRepository.save(n);
		this.articleService.markInappropriateArticlesOfNewspaper(n);
	}

	public Collection<Newspaper> findMyNonPublished() {
		Assert.notNull(this.userService.findByPrincipal());
		return this.newspaperRepository.findMyNonPublished(this.userService.findByPrincipal());
	}

	public Collection<Newspaper> findAllTaboo() {
		return newspaperRepository.findAllTaboo();
	}
	
	public void flush(){
		newspaperRepository.flush();
	}
	
	public Newspaper reconstruct(Newspaper newspaper, BindingResult binding) {
		if(newspaper.getId() == 0){
			newspaper.setId(0);
			newspaper.setUser(userService.findByPrincipal());
			validator.validate(newspaper, binding);
		}else{
			Newspaper db = findOne(newspaper.getId());
			newspaper.setVersion(db.getVersion());
			newspaper.setArticless(db.getArticless());
			newspaper.setDescription(db.getDescription());
			newspaper.setInappropriate(db.getInappropriate());
			newspaper.setPicture(db.getPicture());
			newspaper.setIsPrivate(db.getIsPrivate());
			newspaper.setPrice(db.getPrice());
			newspaper.setSubscriptionss(db.getSubscriptionss());
			newspaper.setTitle(db.getTitle());
			newspaper.setUser(db.getUser());
			newspaper.setVolumes(db.getVolumes());
			newspaper.setAdvertisements(db.getAdvertisements());
			validator.validate(newspaper, binding);			
		}
		return newspaper;
	}

	public Double[] getStatsOfArticlesPerNewspaper(){
		return newspaperRepository.getStatsOfArticlesPerNewspaper();
	}

	public Collection<Newspaper> getNewspapersOverAvg(){
		return newspaperRepository.getNewspapersOverAvg();
	}

	public Collection<Newspaper> getNewspapersUnderAvg(){
		return newspaperRepository.getNewspapersUnderAvg();
	}

	public Double getRatioOfPublicOverPrivateNewspapers(){
		return newspaperRepository.getRatioOfPublicOverPrivateNewspapers();
	}

	public Double getArticleAvgForPrivateNewspapers(){
		return newspaperRepository.getArticleAvgForPrivateNewspapers();
	}

	public Double getArticleAvgForPublicNewspapers(){
		return newspaperRepository.getArticleAvgForPublicNewspapers();
	}

	public Double getRatioOfSubscribersVersusCustomersTotal(){
		return newspaperRepository.getRatioOfSubscribersVersusCustomersTotal();
	}

	public Collection<Newspaper> findMyAdvertisedNewspapers() {
		Agent a = agentService.findByPrincipal();
		Assert.notNull(a);
		return newspaperRepository.findMyAdvertisedNewspapers(a);
	}

	public Collection<Newspaper> findMyNotAdvertisedNewspapers() {
		Agent a = agentService.findByPrincipal();
		Assert.notNull(a);
		Collection<Newspaper> advertised = newspaperRepository.findMyAdvertisedNewspapers(a);
		Collection<Newspaper> result = findAllNotInappropriate();
		result.removeAll(advertised);
		return result;
	}	
	
	public Double getRatioAdvertisedNewspapers(){
		Double res = newspaperRepository.getRatioAdvertisedNewspapers();
		return res == null ? 0 : res;
	}
}
