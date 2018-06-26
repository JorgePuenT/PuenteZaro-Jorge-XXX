package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Advertisement extends DomainEntity{
	
	private String title;
	private String bannerUrl;
	private String targetUrl;
	private CreditCard creditCard;
	private Boolean inappropriate;
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	@URL
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getBannerUrl() {
		return bannerUrl;
	}
	
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	
	@NotBlank
	@URL
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getTargetUrl() {
		return targetUrl;
	}
	
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	
	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	@NotNull
	public Boolean getInappropriate() {
		return inappropriate;
	}

	public void setInappropriate(Boolean inappropriate) {
		this.inappropriate = inappropriate;
	}

	private Agent agent;
	private Newspaper newspaper;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Newspaper getNewspaper() {
		return newspaper;
	}

	public void setNewspaper(Newspaper newspaper) {
		this.newspaper = newspaper;
	}
}
