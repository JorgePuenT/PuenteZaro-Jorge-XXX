
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AgentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Advertisement;
import domain.Agent;

@Service
@Transactional
public class AgentService {

	@Autowired
	private AgentRepository	agentRepository;


	//Supporting Services -------------------
	@Autowired
	private UserAccountService	userAccountService;
	//CRUD Methods -------------------------

	public Agent create() {
		Agent res = new Agent();

		//Collections
		res.setAdvertisements(new ArrayList<Advertisement>());
		//UserAccount
		UserAccount userAccount = new UserAccount();
		Collection<Authority> authorities = userAccount.getAuthorities();
		Authority authority = new Authority();

		authority.setAuthority(Authority.AGENT);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		res.setUserAccount(userAccount);

		return res;
	}

	public void flush() {
		this.agentRepository.flush();
	}

	public Agent findOne(final int agentId) {
		Assert.isTrue(agentId != 0);
		Agent res = this.agentRepository.findOne(agentId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Agent> findAll() {
		Collection<Agent> res = this.agentRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Agent save(final Agent agent) {
		Assert.notNull(agent);

		if (agent.getId() == 0) {
			Md5PasswordEncoder password = new Md5PasswordEncoder();
			String encodedPassword = password.encodePassword(agent.getUserAccount().getPassword(), null);
			agent.getUserAccount().setPassword(encodedPassword);
			agent.setUserAccount(this.userAccountService.save(agent.getUserAccount()));
		}

		return this.agentRepository.save(agent);
	}

	public Agent findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Agent res;
		res = this.agentRepository.findByUserAccount(userAccount.getId());
		return res;
	}

	public Agent findByPrincipal() {
		Agent res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		return res;
	}

}
