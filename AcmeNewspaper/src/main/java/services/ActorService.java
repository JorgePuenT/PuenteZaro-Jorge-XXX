
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccountService;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository	actorRepository;


	//Supporting Services -------------------
	@Autowired
	private UserAccountService userAccountService;
	
	//CRUD Methods -------------------------

	public Actor findOne(final int actorId) {
		Assert.isTrue(actorId != 0);
		Actor res = this.actorRepository.findOne(actorId);
		Assert.notNull(res);
		return res;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);

		if (actor.getId() == 0) {
			Md5PasswordEncoder password = new Md5PasswordEncoder();
			String encodedPassword = password.encodePassword(actor.getUserAccount().getPassword(), null);
			actor.getUserAccount().setPassword(encodedPassword);
			actor.setUserAccount(this.userAccountService.save(actor.getUserAccount()));
		}
		return this.actorRepository.save(actor);
	}


	//Other Business Methods --------------------------------

	public Boolean isLogged(){
		Boolean res = true;
		try{
			LoginService.getPrincipal();
		} catch(Throwable oops){
			res = false;
		}
		return res;
	}

}
