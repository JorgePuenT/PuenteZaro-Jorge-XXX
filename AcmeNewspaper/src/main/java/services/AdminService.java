
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Admin;
import domain.Message;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository	adminRepository;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private FolderService folderService;


	public Admin findOne(int adminId) {
		Assert.isTrue(adminId != 0);
		Admin res = adminRepository.findOne(adminId);
		Assert.notNull(res);
		return res;
	}

	public void flush() {
		adminRepository.flush();
	}

	//Other Business Methods --------------------------------

	public Admin findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Admin res;
		res = adminRepository.findByUserAccount(userAccount.getId());
		return res;
	}

	public Admin findByPrincipal() {
		Admin res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = findByUserAccount(userAccount);
		return res;
	}
	
	//Broadcast Message -------------------------
	public void broadcastMessage(String sbj, String msg) {
		Assert.notNull(msg);
		Assert.notNull(sbj);
		Admin ad = findByPrincipal();
		Assert.notNull(ad);
		Collection<Actor> actors = actorService.findAll();
		for (Actor a : actors) {
			Message m = messageService.create();
			m.setActorTo(a);
			m.setActorFrom(ad);
			m.setBody(msg);
			m.setFolder(folderService.findNotificationboxFrom(a.getId()));
			m.setSubject(sbj);
			messageService.saveBroadcast(m);
		}
	}

}
