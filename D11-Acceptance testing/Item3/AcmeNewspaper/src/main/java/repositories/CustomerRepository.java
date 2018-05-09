
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.Newspaper;
import domain.Volume;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.userAccount.id = ?1")
	Customer findByUserAccount(int id);

	@Query(value="select count(subscription.id) from subscription left join volume on subscription.volume_id = volume.id left join volume_newspaper on volume.id = volume_newspaper.volumes_id where (subscription.newspaper_id = ?1 or volume_newspaper.newspapers_id = ?1) and subscription.customer_id = ?2",
			nativeQuery=true)
	int isSubscribed(Newspaper newspaper,Customer principal);
	
	@Query("select count(s) from Subscription s where s.newspaper = ?1 and s.customer = ?2")
	int isSubscribedNewspaper(Newspaper newspaper,Customer principal);

	@Query("select count(s) from Subscription s where s.volume = ?1 and s.customer = ?2")
	int isSubscribedVolume(Volume volume, Customer findByPrincipal);

}
