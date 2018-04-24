
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.Newspaper;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.userAccount.id = ?1")
	Customer findByUserAccount(int id);

	@Query("select count(s) from Subscription s where s.newspaper = ?1 and s.customer = ?2")
	int isSubscribed(Newspaper newspaper,Customer principal);

}
