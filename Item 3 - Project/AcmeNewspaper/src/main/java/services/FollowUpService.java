package services;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FollowUpRepository;
import domain.Article;
import domain.FollowUp;

@Service
@Transactional
public class FollowUpService {

	@Autowired
	private FollowUpRepository		followUpRepository;

	//Supporting Services -------------------
	@Autowired
	private	UserService				userService;
	@Autowired
	private ArticleService articleService;

	//CRUD Methods -------------------------

	public FollowUp create(int articleId) {
		FollowUp res = new FollowUp();
		Assert.isTrue(userService.findByPrincipal() instanceof domain.User);	
		Article a = articleService.findOne(articleId);
		Assert.isTrue(a.getNewspaper().getUser()==userService.findByPrincipal());
		res.setArticle(a);
		res.setPublicationMoment(new Date(System.currentTimeMillis()-1000));
		return res;
	}
	
	public FollowUp findOne(int followUpId) {
		Assert.isTrue(followUpId != 0);
		FollowUp res = followUpRepository.findOne(followUpId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<FollowUp> findAll() {
		return followUpRepository.findAll();
	}
	
	public FollowUp save(final FollowUp followUp) {
		Assert.notNull(followUp);
		Assert.isTrue(articleService.isPublished(followUp.getArticle()));
		Assert.isTrue(followUp.getArticle().getNewspaper().getUser()==userService.findByPrincipal());
		followUp.setPublicationMoment(new Date(System.currentTimeMillis()-1000));
		return followUpRepository.save(followUp);
	}
	
	public void delete(final int followUpId) {
		Assert.isTrue(followUpId != 0);
		followUpRepository.delete(followUpId);
	}
	
	public void flush(){
		followUpRepository.flush();
	}
	
	
}