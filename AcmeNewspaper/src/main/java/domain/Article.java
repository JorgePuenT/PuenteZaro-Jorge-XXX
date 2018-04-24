package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import cz.jirutka.validator.collection.constraints.EachURL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "newspaper_id, publicationMoment, inappropriate, summary, body, title")})
public class Article extends DomainEntity {


	//Attributes----------------
	private String title;
	private String summary;
	private String body;
	private Collection<String> picturess;
	private Boolean finalMode;
	private Boolean inappropriate;
	private Date publicationMoment;

	@NotBlank
	@NotNull
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Date getPublicationMoment() {
		return this.publicationMoment;
	}

	public void setPublicationMoment(final Date publicationMoment) {
		this.publicationMoment = publicationMoment;
	}

	@NotBlank
	@NotNull
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@NotBlank
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@NotNull
	@ElementCollection
	@EachURL
	public Collection<String> getPicturess() {
		return this.picturess;
	}

	public void setPicturess(final Collection<String> picturess) {
		this.picturess = picturess;
	}

	@NotNull
	public Boolean getFinalMode() {
		return this.finalMode;
	}

	public void setFinalMode(final Boolean finalMode) {
		this.finalMode = finalMode;
	}


	public Boolean getInappropriate() {
		return this.inappropriate;
	}

	public void setInappropriate(final Boolean inappropriate) {
		this.inappropriate = inappropriate;
	}


	//Relationships----------------
	private Newspaper newspaper;
	private Collection<FollowUp> followUps;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Newspaper getNewspaper() {
		return this.newspaper;
	}

	public void setNewspaper(final Newspaper newspaper) {
		this.newspaper = newspaper;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "article")
	public Collection<FollowUp> getFollowUps() {
		return this.followUps;
	}


	public void setFollowUps(final Collection<FollowUp> followUps) {
		this.followUps = followUps;
	}

}