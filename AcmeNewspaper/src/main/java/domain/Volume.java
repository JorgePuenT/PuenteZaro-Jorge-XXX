package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Volume extends DomainEntity{

	private String title;
	private String description;
	private int year;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	private Collection<VolumeNewspaper> volumeNewspapers;
	private Collection<Subscription> subscriptions;

	@Valid
	@NotNull
	@OneToMany(mappedBy="volume")
	public Collection<VolumeNewspaper> getVolumeNewspapers() {
		return volumeNewspapers;
	}
	public void setVolumeNewspaper(Collection<VolumeNewspaper> volumeNewspapers) {
		this.volumeNewspapers = volumeNewspapers;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="volume")
	public Collection<Subscription> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(Collection<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
	
	
}
