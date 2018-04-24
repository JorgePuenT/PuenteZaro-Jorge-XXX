package domain;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class VolumeNewspaper extends DomainEntity{
	
	private Volume volume;
	private Newspaper newspaper;
	

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Volume getVolume() {
		return volume;
	}
	public void setVolume(Volume volume) {
		this.volume = volume;
	}
	

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Newspaper getNewspaper() {
		return newspaper;
	}
	public void setNewspaper(Newspaper newspaper) {
		this.newspaper = newspaper;
	}
	
	
}
