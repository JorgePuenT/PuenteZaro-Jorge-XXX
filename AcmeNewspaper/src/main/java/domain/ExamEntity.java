package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class ExamEntity extends DomainEntity{

	private String ticker;
	private String title;
	private String description;
	private int gauge;
	private Date displayMoment;
	private boolean draft;


	@NotNull
	@Column(unique=true)
	//@Pattern()
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	@NotNull
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	@NotNull
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Range(min=1,max=3)
	public int getGauge() {
		return gauge;
	}
	public void setGauge(int gauge) {
		this.gauge = gauge;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDisplayMoment() {
		return displayMoment;
	}
	public void setDisplayMoment(Date displayMoment) {
		this.displayMoment = displayMoment;
	}


	public boolean getDraft() {
		return draft;
	}
	public void setDraft(boolean draft) {
		this.draft = draft;
	}


	//Relationships

	private Newspaper newspaper;
	private Admin admin;

	@Valid
	@ManyToOne(optional = true)
	public Newspaper getNewspaper() {
		return newspaper;
	}
	public void setNewspaper(Newspaper newspaper) {
		this.newspaper = newspaper;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}



}
