package onaboat.domain.model.cargo;

import java.util.Date;

import javax.jdo.annotations.EmbeddedOnly;

import onaboat.domain.model.location.Location;

import com.google.common.base.Preconditions;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class RouteSpecification {

	public RouteSpecification(final Location origin, final Location destination, final Date arrivalDeadline) {
		Preconditions.checkArgument(!origin.getUnLocode().equals(destination.getUnLocode()), "Origin and destination cannot be the same:" + origin);

		this.origin = origin;
		this.destination = destination;
		this.arrivalDeadline = arrivalDeadline;
	}

	// {{ Origin (property)
	private Location origin;

	public Location getOrigin() {
		return origin;
	}
	public void setOrigin(Location origin) {
		this.origin = origin;
	}
	// }}

	// {{ Destination (property)
	private Location destination;

	public Location getDestination() {
		return destination;
	}
	public void setDestination(Location destination) {
		this.destination = destination;
	}
	// }}

	// {{ ArrivalDeadline (property)
	private Date arrivalDeadline;

	public Date getArrivalDeadline() {
		return arrivalDeadline;
	}
	public void setArrivalDeadline(Date arrivalDeadline) {
		this.arrivalDeadline = arrivalDeadline;
	}
	// }}

	public boolean isSatisfiedBy(final Itinerary itinerary) {
		return itinerary != null
				&& getOrigin().getUnLocode().equals(itinerary.getInitialDepartureLocation().getUnLocode())
				&& getDestination().getUnLocode().equals(itinerary.getFinalArrivalLocation().getUnLocode())
				&& getArrivalDeadline().after(itinerary.getFinalArrivalDate());
	}
}
