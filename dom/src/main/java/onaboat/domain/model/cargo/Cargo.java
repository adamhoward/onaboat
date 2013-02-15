package onaboat.domain.model.cargo;

import onaboat.domain.model.location.Location;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Title;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@ObjectType("CARGO")
public class Cargo {

	// {{ TrackingId (property)
	private TrackingId trackingId;

	@MemberOrder(sequence = "1")
	@Title
	public TrackingId getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(final TrackingId trackingId) {
		this.trackingId = trackingId;
	}
	// }}

	// {{ Origin (property)
	private Location origin;

	@MemberOrder(sequence = "3")
	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(final Location origin) {
		this.origin = origin;
	}
	// }}

	// {{ RouteSpecification (property)
	private RouteSpecification routeSpecification;

	@MemberOrder(sequence = "2")
	public RouteSpecification getRouteSpecification() {
		return routeSpecification;
	}

	public void setRouteSpecification(final RouteSpecification routeSpecification) {
		this.routeSpecification = routeSpecification;
	}
	// }}

	// {{ Itinerary (property)
	private Itinerary itinerary;

	@MemberOrder(sequence = "5")
	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(final Itinerary itinerary) {
		this.itinerary = itinerary;
	}
	// }}

	// {{ Delivery (property)
	private Delivery delivery;

	@MemberOrder(sequence = "4")
	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(final Delivery delivery) {
		this.delivery = delivery;
	}
	// }}

	public String validate() {
		if (trackingId == null) return "Tracking ID is required";
		if (routeSpecification == null) return "Route Specification is required";
		return null;
	}

	// {{ Lifecycle methods
	public void persisting() {
		this.origin = routeSpecification.getOrigin();
	}
	// }}




}
