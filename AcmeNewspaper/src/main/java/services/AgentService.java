
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
import domain.Folder;

@Service
@Transactional
public class AgentService {

	@Autowired
	private AgentRepository	agentRepository;


	//Supporting Services -------------------
	@Autowired
	private UserAccountService	userAccountService;
	@Autowired
	private FolderService		folderService;
	//CRUD Methods -------------------------

	public Agent create() {
		Agent res = new Agent();

		//Collections
		res.setAdvertisements(new ArrayList<Advertisement>());
		res.setFolders(new ArrayList<Folder>());
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
		Assert.isTrue(agent.getId() == 0);
		
		Folder inbox = folderService.create();
		inbox.setName("Inbox");
		inbox.setSystem(true);

		Folder outbox = folderService.create();
		outbox.setName("Outbox");
		outbox.setSystem(true);

		Folder trashbox = folderService.create();
		trashbox.setName("Trashbox");
		trashbox.setSystem(true);

		Folder spambox = folderService.create();
		spambox.setName("Spambox");
		spambox.setSystem(true);

		Folder notificationbox = folderService.create();
		notificationbox.setName("Notificationbox");
		notificationbox.setSystem(true);

		Md5PasswordEncoder password = new Md5PasswordEncoder();
		String encodedPassword = password.encodePassword(agent.getUserAccount().getPassword(), null);
		agent.getUserAccount().setPassword(encodedPassword);
		agent.setUserAccount(this.userAccountService.save(agent.getUserAccount()));

		Agent res = this.agentRepository.saveAndFlush(agent);
		
		folderService.save(inbox, res);
		folderService.save(outbox, res);
		folderService.save(trashbox, res);
		folderService.save(spambox, res);
		folderService.save(notificationbox, res);
		
		return res;
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
