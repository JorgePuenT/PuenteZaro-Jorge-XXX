package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

import cz.jirutka.validator.collection.constraints.EachURL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "publicationMoment")})
public class FollowUp extends DomainEntity {

	//Attributes----------------
	private Date publicationMoment;
	private String title;
	private String summary;
	private String body;
	private Collection<String> picturess;

	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getPublicationMoment() {
		return this.publicationMoment;
	}

	public void setPublicationMoment(final Date publicationMoment) {
		this.publicationMoment = publicationMoment;
	}

	@NotBlank
	@NotNull
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@NotNull
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@NotBlank
	@NotNull
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@EachURL
	@ElementCollection
	@NotNull
	public Collection<String> getPicturess() {
		return this.picturess;
	}

	public void setPicturess(final Collection<String> picturess) {
		this.picturess = picturess;
	}


	//Relationships----------------

	private Article article;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Article getArticle() {
		return this.article;
	}


	public void setArticle(final Article article) {
		this.article = article;
	}



}