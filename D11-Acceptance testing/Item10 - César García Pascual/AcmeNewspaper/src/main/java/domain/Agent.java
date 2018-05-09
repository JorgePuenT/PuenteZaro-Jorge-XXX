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
public class Agent extends Actor{

	
	private Collection<Advertisement> advertisements;

	@NotNull
	@Valid
	@OneToMany(mappedBy="agent")
	public Collection<Advertisement> getAdvertisements() {
		return advertisements;
	}

	public void setAdvertisements(Collection<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}
	
	
}
