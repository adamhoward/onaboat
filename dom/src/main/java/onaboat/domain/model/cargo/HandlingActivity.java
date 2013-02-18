package onaboat.domain.model.cargo;

import onaboat.domain.model.handling.HandlingEvent;
import onaboat.domain.model.location.Location;
import onaboat.domain.model.voyage.Voyage;

import org.apache.isis.applib.annotation.MemberOrder;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class HandlingActivity {

	HandlingActivity(HandlingEvent.Type type, Location location) {
		this.type = type;
		this.location = location;
	}

	HandlingActivity(HandlingEvent.Type type, Location location, Voyage voyage) {
		this.type = type;
		this.location = location;
		this.voyage = voyage;
	}

	// {{ Type (property)
	private HandlingEvent.Type type;

	@MemberOrder(sequence = "1")
	public HandlingEvent.Type getType() {
		return type;
	}

	public void setType(final HandlingEvent.Type type) {
		this.type = type;
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


}
