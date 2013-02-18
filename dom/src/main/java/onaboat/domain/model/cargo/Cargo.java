package onaboat.domain.model.cargo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import onaboat.domain.model.handling.HandlingEvent;
import onaboat.domain.model.handling.HandlingHistory;
import onaboat.domain.model.location.Location;
import onaboat.domain.model.voyage.Voyage;

import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Immutable;
import org.apache.isis.applib.annotation.Mask;
import org.apache.isis.applib.annotation.MemberGroups;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Resolve;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.When;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@ObjectType("CARGO")
@MemberGroups({"General", "Route Specification", "Delivery"})
@Immutable(When.ONCE_PERSISTED)
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

	@MemberOrder(sequence = "2")
	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(final Location origin) {
		this.origin = origin;
	}
	// }}

	// {{ RouteSpecification (property)
	private RouteSpecification routeSpecification;

	@Hidden
	public RouteSpecification getRouteSpecification() {
		return routeSpecification;
	}

	public void setRouteSpecification(final RouteSpecification routeSpecification) {
		this.routeSpecification = routeSpecification;
	}
	// }}

	// {{ RouteSpecificationOrigin (property)
	@MemberOrder(sequence = "3.1", name="Route Specification")
	@Named("Origin")
	public Location getRouteSpecificationOrigin() {
		return routeSpecification.getOrigin();
	}
	// }}

	// {{ routeSpecificationDestination (property)
	@MemberOrder(sequence = "3.2", name="Route Specification")
	@Named("Destination")
	public Location getRouteSpecificationDestination() {
		return routeSpecification.getDestination();
	}
	// }}

	// {{ RouteSpecificationArrivalDeadline (property)
	@MemberOrder(sequence = "3.3", name="Route Specification")
	@Named("Arrival Deadline")
	public Date getRouteSpecificationArrivalDeadline() {
		return routeSpecification.getArrivalDeadline();
	}
	// }}


	// {{ Itinerary (property)
	private Itinerary itinerary;

	@Hidden
	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(final Itinerary itinerary) {
		this.itinerary = itinerary;
	}
	// }}

	// {{ ItineraryLegs (property)
	@Named("Itinerary")
	@Resolve
	public List<Leg> getItineraryLegs() {
		return itinerary != null ? itinerary.getLegs() : new ArrayList<Leg>();
	}
	// }}

	// {{ Delivery (property)
	private Delivery delivery;

	@Hidden
	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(final Delivery delivery) {
		this.delivery = delivery;
	}
	// }}


	// {{ DeliveryTransportStatus (property)
	@MemberOrder(sequence = "4.1", name="Delivery")
	@Named("Transport Status")
	public TransportStatus getDeliveryTransportStatus() {
		return delivery != null ? delivery.getTransportStatus() : null;
	}
	// }}

	// {{ DeliveryLastKnownLocation (property)
	@MemberOrder(sequence = "4.2", name="Delivery")
	@Named("Last Known Location")
	public Location getDeliveryLastKnownLocation() {
		return delivery != null ? delivery.getLastKnownLocation() : null;
	}
	// }}

	// {{ DeliveryCurrentVoyage (property)
	@MemberOrder(sequence = "4.3", name="Delivery")
	@Named("Current Voyage")
	public Voyage getDeliveryCurrentVoyage() {
		return delivery != null ? delivery.getCurrentVoyage() : null;
	}
	// }}

	// {{ DeliveryMisdirected (property)
	@MemberOrder(sequence = "4.4", name="Delivery")
	@Named("Misdirected?")
	public boolean getDeliveryMisdirected() {
		return delivery != null ? delivery.getMisdirected() : false;
	}
	// }}

	// {{ DeliveryEta (property)
	@MemberOrder(sequence = "4.5", name="Delivery")
	@Named("ETA")
	public Date getDeliveryEta() {
		return delivery != null ? delivery.getEta() : null;
	}
	// }}

	// {{ DeliveryNextExpectedActivity (property)
	@MemberOrder(sequence = "4.6", name="Delivery")
	@Named("Next Expected Activity")
	public HandlingActivity getDeliveryNextExpectedActivity() {
		return delivery != null ? delivery.getNextExpectedActivity() : null;
	}
	// }}

	// {{ DeliveryIsUnloadedAtDestination (property)
	@MemberOrder(sequence = "4.7", name="Delivery")
	@Named("Unloaded At Destination?")
	public boolean getDeliveryIsUnloadedAtDestination() {
		return delivery != null ? delivery.getIsUnloadedAtDestination() : false;
	}
	// }}

	// {{ DeliveryRoutingStatus (property)
	@MemberOrder(sequence = "4.8", name="Delivery")
	@Named("Routing Status")
	public RoutingStatus getDeliveryRoutingStatus() {
		return delivery != null ? delivery.getRoutingStatus() : null;
	}
	// }}

	// {{ DeliveryCalculatedAt (property)
	@MemberOrder(sequence = "4.9", name="Delivery")
	@Named("Calculated At")
	@Mask("yyyy-MM-dd HH:mm:ss")
	public Date getDeliveryCalculatedAt() {
		return delivery != null ? delivery.getCalculatedAt() : null;
	}
	// }}

	// {{ DeliveryLastEvent (property)
	@MemberOrder(sequence = "4.10", name="Delivery")
	@Named("Last Event")
	public HandlingEvent getDeliveryLastEvent() {
		return delivery != null ? delivery.getLastEvent() : null;
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
		this.delivery = Delivery.derivedFrom(routeSpecification, itinerary, HandlingHistory.EMPTY);
	}
	// }}

	// {{ injected: CargoRepository
	private CargoRepository cargoRepository;

	public void setCargoRepository(final CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	// }}

	// {{ actions
	public Cargo specifyNewRoute(
			@Named("Origin") final Location origin,
			@Named("Destination") final Location destination,
			@Named("Arrival Deadline") final Date arrivalDeadline) {
		return cargoRepository.specifyNewRouteFor(this, origin, destination, arrivalDeadline);
	}
	// }}

}
