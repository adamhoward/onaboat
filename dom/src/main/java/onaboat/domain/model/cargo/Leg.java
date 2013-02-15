package onaboat.domain.model.cargo;

import java.util.Date;

import onaboat.domain.model.location.Location;
import onaboat.domain.model.voyage.Voyage;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@ObjectType("LEG")
public class Leg {

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

	// {{ LoadLocation (property)
	private Location loadLocation;

	@MemberOrder(sequence = "2")
	public Location getLoadLocation() {
		return loadLocation;
	}

	public void setLoadLocation(final Location loadLocation) {
		this.loadLocation = loadLocation;
	}
	// }}

	// {{ UnloadLocation (property)
	private Location unloadLocation;

	@MemberOrder(sequence = "3")
	public Location getUnloadLocation() {
		return unloadLocation;
	}

	public void setUnloadLocation(final Location unloadLocation) {
		this.unloadLocation = unloadLocation;
	}
	// }}

	// {{ LoadTime (property)
	private Date loadTime;

	@MemberOrder(sequence = "4")
	public Date getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(final Date loadTime) {
		this.loadTime = loadTime;
	}
	// }}

	// {{ UnloadTime (property)
	private Date unloadTime;

	@MemberOrder(sequence = "5")
	public Date getUnloadTime() {
		return unloadTime;
	}

	public void setUnloadTime(final Date unloadTime) {
		this.unloadTime = unloadTime;
	}
	// }}



}
