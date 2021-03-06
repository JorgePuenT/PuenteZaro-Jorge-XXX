
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Folder extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	name;
	private Boolean	system;


	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotNull
	public Boolean getSystem() {
		return this.system;
	}

	public void setSystem(final Boolean system) {
		this.system = system;
	}


	// Relationships ----------------------------------------------------------

	private Collection<Folder>	folders;
	private Folder				parent;
	private Collection<Message>	messages;


	@NotNull
	@OneToMany(mappedBy = "parent")
	public Collection<Folder> getFolders() {
		return this.folders;
	}

	public void setFolders(final Collection<Folder> folders) {
		this.folders = folders;
	}

	@Valid
	@ManyToOne(optional = true)
	public Folder getParent() {
		return this.parent;
	}

	public void setParent(final Folder parent) {
		this.parent = parent;
	}

	@NotNull
	@OneToMany(mappedBy = "folder")
	public Collection<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(final Collection<Message> messages) {
		this.messages = messages;
	}

}
