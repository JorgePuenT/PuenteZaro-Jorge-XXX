
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {

	@Query("select f from Actor a join a.folders f where a.id=?1 and f.name='Inbox'")
	Folder findInboxFrom(int actorId);

	@Query("select f from Actor a join a.folders f where a.id=?1 and f.name='Outbox'")
	Folder findOutboxFrom(int actorId);

	@Query("select f from Actor a join a.folders f where a.id=?1 and f.name='Trashbox'")
	Folder findTrashboxFrom(int actorId);

	@Query("select f from Actor a join a.folders f where a.id=?1 and f.name='Notificationbox'")
	Folder findNotificationboxFrom(int actorId);

	@Query("select f from Actor a join a.folders f where a.id=?1 and f.name='Spambox'")
	Folder findSpamboxFrom(int actorId);

	@Query("select f from Actor a join a.folders f where a = ?1 and f.system='1'")
	Collection<Folder> getSystemFoldersFrom(Actor a);

	@Query("select f from Actor a join a.folders f where a = ?1 and f.system='0'")
	Collection<Folder> getNonSystemFoldersFrom(Actor a);

	@Query("select f from Actor a join a.folders f where a = ?1 and f.system='0' and f.parent is null")
	Collection<Folder> getLvl1FoldersFrom(Actor a);

	@Query("select case when (count(*)>0) then true else false end from Folder f where (f.id=?1) and (f.name like %?2%)")
	Boolean isSpam(int id, String name);

}
