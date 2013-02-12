package onaboat.domain.model.cargo;

import onaboat.domain.model.location.Location;

import org.apache.isis.applib.annotation.MemberOrder;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class Cargo {

	// {{ TrackingId (property)
	private TrackingId trackingId;

	@MemberOrder(sequence = "1")
	public TrackingId getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(final TrackingId trackingId) {
		this.trackingId = trackingId;
	}
	// }}

	// {{ Origin (property)
	private Location origin;

	@MemberOrder(sequence = "1")
	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(final Location origin) {
		this.origin = origin;
	}
	// }}

	// {{ RouteSpecification (property)
	private RouteSpecification routeSpecification;

	@MemberOrder(sequence = "1")
	public RouteSpecification getRouteSpecification() {
		return routeSpecification;
	}

	public void setRouteSpecification(final RouteSpecification routeSpecification) {
		this.routeSpecification = routeSpecification;
	}
	// }}

	// {{ Itinerary (property)
	private Itinerary itinerary;

	@MemberOrder(sequence = "1")
	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(final Itinerary itinerary) {
		this.itinerary = itinerary;
	}
	// }}

	// {{ Delivery (property)
	private Delivery delivery;

	@MemberOrder(sequence = "1")
	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(final Delivery delivery) {
		this.delivery = delivery;
	}
	// }}



}
