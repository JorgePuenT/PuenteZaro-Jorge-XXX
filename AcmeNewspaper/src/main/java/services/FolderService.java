
package services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class FolderService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FolderRepository	folderRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private MessageService		messageService;
	@Autowired
	private ActorService		actorService;


	// Simple CRUD methods ----------------------------------------------------
	public Folder create() {
		Folder res = new Folder();
		res.setMessages(new ArrayList<Message>());
		res.setFolders(new ArrayList<Folder>());
		res.setSystem(false);

		return res;
	}

	public Folder findOne(int folderId) {
		Assert.isTrue(folderId != 0);
		Folder res = folderRepository.findOne(folderId);
		Assert.notNull(res);
		return res;
	}

	public Folder save(Folder folder, Actor actor) {
		Assert.notNull(folder);
		Assert.notNull(actor);
		if (folder.getId() != 0)
			Assert.isTrue(!folder.getSystem());
		if (folder.getParent() != null)
			Assert.isTrue(folder.getParent().getParent() == null);
		Folder res = folderRepository.save(folder);
		if (folder.getId() == 0)
			actor.getFolders().add(res);

		return res;
	}

	public Folder editName(Integer folderId, String folderName) {
		Assert.isTrue(folderId != 0);
		Assert.notNull(folderName);
		Folder f = findOne(folderId);
		Assert.isTrue(actorService.findByPrincipal().getFolders().contains(f));
		f.setName(folderName);
		return folderRepository.save(f);
	}

	public void delete(Folder folder) {
		Assert.notNull(folder);
		Actor a = actorService.findByPrincipal();
		Assert.notNull(a);
		Assert.isTrue(a.getFolders().contains(folder));
		Assert.isTrue(folder.getId() != 0);
		Assert.isTrue(folderRepository.exists(folder.getId()));
		Assert.isTrue(!folder.getSystem());

		Iterator<Message> i = folder.getMessages().iterator();
		while (i.hasNext()) {
			Message m = i.next();
			i.remove();
			messageService.delete(m);
		}

		if (folder.getParent() != null)
			folder.getParent().getFolders().remove(folder);

		//We delete sub-folders using iterator to avoid ConcurrentModificationException
		Iterator<Folder> i2 = folder.getFolders().iterator();
		while (i2.hasNext()) {
			Folder f = i2.next();
			i2.remove();
			delete(f);
		}

		a.getFolders().remove(folder);

		folderRepository.delete(folder);
	}

	//Other business method-----------------------------------------------

	public Folder findInboxFrom(int actorId) {
		Assert.notNull(actorId);
		Assert.isTrue(actorId != 0);
		Folder res;
		res = folderRepository.findInboxFrom(actorId);
		Assert.notNull(res);

		return res;
	}

	public Folder findOutboxFrom(int actorId) {
		Assert.notNull(actorId);
		Assert.isTrue(actorId != 0);
		Folder res;
		res = folderRepository.findOutboxFrom(actorId);
		Assert.notNull(res);

		return res;
	}

	public Folder findTrashboxFrom(int actorId) {
		Assert.notNull(actorId);
		Assert.isTrue(actorId != 0);
		Folder res;
		res = folderRepository.findTrashboxFrom(actorId);
		Assert.notNull(res);

		return res;
	}

	public Folder findNotificationboxFrom(int actorId) {
		Assert.notNull(actorId);
		Assert.isTrue(actorId != 0);
		Folder res;
		res = folderRepository.findNotificationboxFrom(actorId);
		Assert.notNull(res);

		return res;
	}

	public Folder findSpamboxFrom(int actorId) {
		Assert.notNull(actorId);
		Assert.isTrue(actorId != 0);
		Folder res;
		res = folderRepository.findSpamboxFrom(actorId);
		Assert.notNull(res);

		return res;
	}

	public List<Folder> getSystemFoldersFrom(Actor a) {
		return new ArrayList<Folder>(folderRepository.getSystemFoldersFrom(a));
	}

	public List<Folder> getNonSytemFoldersFrom(Actor a) {
		return new ArrayList<Folder>(folderRepository.getNonSystemFoldersFrom(a));
	}

	public List<Folder> getLvl1FoldersFrom(Actor a) {
		return new ArrayList<Folder>(folderRepository.getLvl1FoldersFrom(a));
	}

	public boolean isMine(Folder folder) {
		Actor a = actorService.findByPrincipal();
		return a.getFolders().contains(folder);
	}

}
