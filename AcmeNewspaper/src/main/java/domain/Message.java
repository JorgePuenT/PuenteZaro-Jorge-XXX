
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	// Attributes -------------------------------------------------------------
	@Enumerated(EnumType.STRING)
	private Priority	priority;

	private String		body;
	private String		subject;
	private Date		sentTime;


	@NotNull
	@Valid
	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(final Priority priority) {
		this.priority = priority;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	public Date getSentTime() {
		return this.sentTime;
	}

	public void setSentTime(final Date sentTime) {
		this.sentTime = sentTime;
	}


	// Relationships ----------------------------------------------------------
	private Folder	folder;
	private Actor	actorFrom;
	private Actor	actorTo;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(final Folder folder) {
		this.folder = folder;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getActorFrom() {
		return this.actorFrom;
	}
	public void setActorFrom(final Actor actorFrom) {
		this.actorFrom = actorFrom;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getActorTo() {
		return this.actorTo;
	}
	public void setActorTo(final Actor actorTo) {
		this.actorTo = actorTo;
	}

}
