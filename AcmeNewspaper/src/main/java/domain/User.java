package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	//Attributes----------------

	//Relationships

	//Relationships----------------
	private Collection<Newspaper> newspapers;
	private Collection<User> follows;
	private Collection<User> followedBy;
	private Collection<Chirp> chirps;

	@NotNull
	@Valid
	@OneToMany(mappedBy="user")
	public Collection<Newspaper> getNewspapers() {
		return newspapers;
	}

	public void setNewspapers(Collection<Newspaper> newspapers) {
		this.newspapers = newspapers;
	}

	@NotNull
	@Valid
	@ManyToMany(mappedBy="followedBy")
	public Collection<User> getFollows() {
		return follows;
	}

	public void setFollows(Collection<User> follows) {
		this.follows = follows;
	}

	@NotNull
	@Valid
	@ManyToMany()
	public Collection<User> getFollowedBy() {
		return followedBy;
	}

	public void setFollowedBy(Collection<User> followedBy) {
		this.followedBy = followedBy;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy="user")
	public Collection<Chirp> getChirps() {
		return chirps;
	}

	public void setChirps(Collection<Chirp> chirps) {
		this.chirps = chirps;
	}


}