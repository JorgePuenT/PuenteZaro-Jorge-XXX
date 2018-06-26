package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	//Attributes----------------
	
	
	//Relationships----------------
	private Collection<Subscription> subscriptionss;
	
	@NotNull
	@Valid
	@OneToMany(mappedBy = "customer")
	public Collection<Subscription> getSubscriptionss() {
		return subscriptionss;
	}
	
	public void setSubscriptionss(Collection<Subscription> subscriptionss) {
		this.subscriptionss = subscriptionss;
	}
	
	
}