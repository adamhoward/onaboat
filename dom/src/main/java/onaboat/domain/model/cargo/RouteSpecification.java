package onaboat.domain.model.cargo;

import java.util.Date;

import org.apache.isis.applib.annotation.Aggregated;
import org.apache.isis.applib.util.TitleBuffer;

import onaboat.domain.model.location.Location;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@Aggregated
public class RouteSpecification {

	// {{ Identification
	public String title() {
		final TitleBuffer buf = new TitleBuffer();
		buf.append(origin.getUnLocode())
				.append("to")
				.append(destination.getUnLocode())
				.append("by")
				.append(arrivalDeadline);
		return buf.toString();
	}
	// }}

	public String validate() {
		if (origin.getUnLocode().equals(destination.getUnLocode())) {
			return "Origin and destination cannot be the same:" + origin;
		}
		return null;
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
