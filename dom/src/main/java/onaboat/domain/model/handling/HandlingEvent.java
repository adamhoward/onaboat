package onaboat.domain.model.handling;

import java.util.Date;

import onaboat.domain.model.cargo.Cargo;
import onaboat.domain.model.location.Location;
import onaboat.domain.model.voyage.Voyage;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@ObjectType("EVENT")
public class HandlingEvent {

	public enum Type {
		LOAD(true),
		UNLOAD(true),
		RECEIVE(false),
		CLAIM(false),
		CUSTOMS(false);

		private final boolean voyageRequired;

		private Type(final boolean voyageRequired) {
			this.voyageRequired = voyageRequired;
		}

		public boolean requiresVoyage() {
			return voyageRequired;
		}

		public boolean prohibitsVoyage() {
			return !requiresVoyage();
		}
	}

	// {{ Type (property)
	private Type type;

	@MemberOrder(sequence = "1")
	public Type getType() {
		return type;
	}

	public void setType(final Type type) {
		this.type = type;
	}
	// }}

	// {{ Voyage (property)
	private Voyage voyage;

	@MemberOrder(sequence = "1")
	public Voyage getVoyage() {
		return voyage;
	}

	public void setVoyage(final Voyage voyage) {
		this.voyage = voyage;
	}
	// }}

	// {{ Location (property)
	private Location location;

	@MemberOrder(sequence = "1")
	public Location getLocation() {
		return location;
	}

	public void setLocation(final Location location) {
		this.location = location;
	}
	// }}

	// {{ CompletionTime (property)
	private Date completionTime;

	@MemberOrder(sequence = "1")
	public Date getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(final Date completionTime) {
		this.completionTime = completionTime;
	}
	// }}

	// {{ RegistrationTime (property)
	private Date registrationTime;

	@MemberOrder(sequence = "1")
	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(final Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	// }}

	// {{ Cargo (property)
	private Cargo cargo;

	@MemberOrder(sequence = "1")
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(final Cargo cargo) {
		this.cargo = cargo;
	}
	// }}

	public String validate() {
		if (cargo == null) return "Cargo is required";
		if (completionTime == null) return "Completion time is required";
		if (registrationTime == null) return "Registration time is required";
		if (type == null) return "Handling event type is required";
		if (location == null) return "Location is required";
		if (type.prohibitsVoyage() && voyage != null) {
			return "Voyage is not allowed with event type " + type;
		}
		if (type.requiresVoyage() && voyage == null) {
			return "Voyage is required for event type " + type;
		}
		return null;
	}

}
