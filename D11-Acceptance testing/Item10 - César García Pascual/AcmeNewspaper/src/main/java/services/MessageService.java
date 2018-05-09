
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Admin;
import domain.Folder;
import domain.Message;
import domain.Priority;

@Service
@Transactional
public class MessageService {

	// Managed repository -----------------------------------------------------	
	@Autowired
	private MessageRepository	messageRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private FolderService		folderService;
	@Autowired
	private SystemConfigService	systemConfigService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private AdminService		adminService;


	// Simple CRUD methods ----------------------------------------------------
	public Message create() {
		Actor a = actorService.findByPrincipal();
		Assert.notNull(a);
		Message res;
		res = new Message();
		res.setActorFrom(a);
		res.setPriority(Priority.NEUTRAL);
		res.setSentTime(new Date(System.currentTimeMillis() - 1000));
		res.setFolder(folderService.findOutboxFrom(a.getId()));
		return res;
	}

	public Message findOne(int messageId) {
		Assert.isTrue(messageId != 0);
		Message res;
		res = messageRepository.findOne(messageId);
		Assert.notNull(res);

		return res;
	}

	public Message save(Message message) {
		Assert.notNull(message);
		Actor a = actorService.findByPrincipal();
		Assert.notNull(a);
		message.setSentTime(new Date(System.currentTimeMillis() - 1000));

		Folder f = null;
		f = folderService.findInboxFrom(message.getActorTo().getId());
		Assert.notNull(f);
		message.setFolder(f);
		Message res = messageRepository.save(message);

		boolean spam = isSpam(res.getId());

		if (spam) {
			f = folderService.findSpamboxFrom(res.getActorTo().getId());
			Assert.notNull(f);
			res.setFolder(f);
		}

		Assert.notNull(res);
		Message copy = create();
		copy.setSubject(message.getSubject());
		copy.setBody(message.getBody());
		copy.setSentTime(message.getSentTime());
		copy.setPriority(message.getPriority());
		copy.setActorFrom(message.getActorFrom());
		copy.setActorTo(message.getActorTo());
		Folder f2 = folderService.findOutboxFrom(message.getActorFrom().getId());
		Assert.notNull(f2);
		copy.setFolder(f2);
		f.getMessages().add(message);
		f2.getMessages().add(copy);
		messageRepository.save(copy);

		return res;

	}

	public void saveBroadcast(Message message) {
		Assert.notNull(message);
		Admin a = adminService.findByPrincipal();
		Assert.notNull(a);
		message.setSentTime(new Date(System.currentTimeMillis() - 1000));

		Message res = messageRepository.save(message);
		Assert.notNull(res);

		res.getFolder().getMessages().add(message);
	}

	public Message saveNotification(Message message) {
		Assert.notNull(message);
		message.setSentTime(new Date(System.currentTimeMillis() - 1000));
		Message res = messageRepository.save(message);
		Assert.notNull(res);
		res.getFolder().getMessages().add(message);
		return res;
	}

	public void delete(Message message) {
		Assert.notNull(message);
		Actor a = actorService.findByPrincipal();
		Assert.notNull(a);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(messageRepository.exists(message.getId()));
		Assert.isTrue(a == message.getActorFrom() || a == message.getActorTo());

		if (message.getFolder().getName().equals("Trashbox")) {
			message.getFolder().getMessages().remove(message);

			messageRepository.delete(message);
		} else {//Al eliminarse, el mensaje se envía a la carpeta Trashbox
			Folder f = folderService.findTrashboxFrom(a.getId());
			message.getFolder().getMessages().remove(message);
			message.setFolder(f);
			message.getFolder().getMessages().add(message);
		}

	}

	//Other business method-----------------------------------------------

	public boolean isSpam(int messageId) {
		Assert.notNull(messageId);

		Collection<String> forbiddenWords = systemConfigService.get().getTabooWordss();
		Assert.notNull(forbiddenWords);

		Boolean res = false;
		for (String fw : forbiddenWords) {
			Boolean isSpam = messageRepository.isSpam(messageId, fw);
			System.out.println(messageId + " - " + isSpam);
			Assert.notNull(isSpam);
			if (isSpam) {
				res = true;
				break;
			}
		}

		return res;
	}

	public void move(Message m, Folder f) {
		Assert.notNull(m);
		Assert.notNull(f);
		Actor a = actorService.findByPrincipal();
		Assert.notNull(a);
		Assert.isTrue(!f.getSystem());

		Assert.isTrue(a == m.getActorFrom() || a == m.getActorTo());

		f.getMessages().add(m);
		m.getFolder().getMessages().remove(m);
		m.setFolder(f);
		messageRepository.save(m);

	}

	public void flush() {
		messageRepository.flush();

	}

}
