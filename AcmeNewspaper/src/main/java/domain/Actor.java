package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import security.UserAccount;
import cz.jirutka.validator.collection.constraints.EachEmail;
import cz.jirutka.validator.collection.constraints.EachNotBlank;
import cz.jirutka.validator.collection.constraints.EachPattern;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(indexes = {@Index(columnList = "userAccount_id")})
public abstract class Actor extends DomainEntity {

	//Attributes----------------
	private String name;
	private String surnames;
	private Collection<String> addressess;
	private Collection<String> phoness;
	private Collection<String> emailss;

	@NotBlank
	@NotNull
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotNull
	@NotBlank
	public String getSurnames() {
		return this.surnames;
	}

	public void setSurnames(final String surnames) {
		this.surnames = surnames;
	}

	@NotNull
	@ElementCollection
	@EachNotBlank
	public Collection<String> getAddressess() {
		return this.addressess;
	}

	public void setAddressess(final Collection<String> addressess) {
		this.addressess = addressess;
	}

	@NotNull
	@ElementCollection
	@EachPattern(regexp = "^\\+?\\d{1,20}")
	public Collection<String> getPhoness() {
		return this.phoness;
	}

	public void setPhoness(final Collection<String> phoness) {
		this.phoness = phoness;
	}

	@NotEmpty
	@ElementCollection
	@EachEmail
	public Collection<String> getEmailss() {
		return this.emailss;
	}

	public void setEmailss(final Collection<String> emailss) {
		this.emailss = emailss;
	}


	//Relationships----------------
	private UserAccount userAccount;

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}


}