package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import cz.jirutka.validator.collection.constraints.EachNotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class SystemConfig extends DomainEntity {

	//Attributes----------------
	private Collection<String> tabooWordss;


	@ElementCollection
	@NotNull
	@EachNotBlank
	public Collection<String> getTabooWordss() {
		return this.tabooWordss;
	}

	public void setTabooWordss(final Collection<String> tabooWordss) {
		this.tabooWordss = tabooWordss;
	}


	//Relationships----------------

}