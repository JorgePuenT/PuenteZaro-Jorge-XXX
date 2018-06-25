package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends Actor {

	//Attributes----------------
	
	private Collection<ExamEntity> examEntities;

	@NotNull
	@Valid
	@OneToMany(mappedBy = "newspaper")
	public Collection<ExamEntity> getExamEntities() {
		return examEntities;
	}

	public void setExamEntity(Collection<ExamEntity> examEntities) {
		this.examEntities = examEntities;
	}
	
}