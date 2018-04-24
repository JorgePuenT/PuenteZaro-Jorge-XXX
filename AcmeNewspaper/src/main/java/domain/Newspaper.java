package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "user_id, title, description, inappropriate, publicationDate, isPrivate")})
public class Newspaper extends DomainEntity {

	//Attributes----------------
	private String title;
	private Date publicationDate;
	private String description;
	private String picture;
	private Double price;
	private Boolean isPrivate;
	private Boolean inappropriate;


	@NotBlank
	@NotNull
	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@NotBlank
	@NotNull
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@Min(0)
	public Double getPrice() {
		return price;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	@NotNull
	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(final Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	@NotNull
	public Boolean getInappropriate() {
		return inappropriate;
	}

	public void setInappropriate(final Boolean inappropriate) {
		this.inappropriate = inappropriate;
	}


	//Relationships----------------
	private Collection<Subscription> subscriptionss;
	private Collection<Article> articless;
	private User user;

	@NotNull
	@Valid
	@OneToMany(mappedBy = "newspaper")
	public Collection<Subscription> getSubscriptionss() {
		return subscriptionss;
	}

	public void setSubscriptionss(final Collection<Subscription> subscriptionss) {
		this.subscriptionss = subscriptionss;
	}

	@NotNull
	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "newspaper")
	public Collection<Article> getArticless() {
		return articless;
	}

	public void setArticless(final Collection<Article> articless) {
		this.articless = articless;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}


}