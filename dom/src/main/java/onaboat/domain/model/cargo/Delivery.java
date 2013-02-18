package onaboat.domain.model.cargo;

import java.util.Date;
import java.util.Iterator;

import onaboat.domain.model.handling.HandlingEvent;
import onaboat.domain.model.handling.HandlingHistory;
import onaboat.domain.model.location.Location;
import onaboat.domain.model.voyage.Voyage;

import org.apache.isis.applib.annotation.MemberOrder;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class Delivery {

	private static final Date ETA_UNKOWN = null;
	private static final HandlingActivity NO_ACTIVITY = null;

	Delivery updateOnRouting(RouteSpecification routeSpecification, Itinerary itinerary) {
		return new Delivery(this.lastEvent, itinerary, routeSpecification);
	}

	static Delivery derivedFrom(RouteSpecification routeSpecification, Itinerary itinerary, HandlingHistory handlingHistory) {
		final HandlingEvent lastEvent = handlingHistory.getMostRecentlyCompletedEvent();
		return new Delivery(lastEvent, itinerary, routeSpecification);
	}

	private Delivery(HandlingEvent lastEvent, Itinerary itinerary, RouteSpecification routeSpecification) {
		this.calculatedAt = new Date();
		this.lastEvent = lastEvent;

		this.misdirected = calculateMisdirectionStatus(itinerary);
		this.routingStatus = calculateRoutingStatus(itinerary, routeSpecification);
		this.transportStatus = calculateTransportStatus();
		this.lastKnownLocation = calculateLastKnownLocation();
		this.currentVoyage = calculateCurrentVoyage();
		this.eta = calculateEta(itinerary);
		this.nextExpectedActivity = calculateNextExpectedActivity(routeSpecification, itinerary);
		this.isUnloadedAtDestination = calculateUnloadedAtDestination(routeSpecification);
	}

	private boolean calculateMisdirectionStatus(Itinerary itinerary) {
		if (lastEvent == null) {
			return false;
		} else {
			return !itinerary.isExpected(lastEvent);
		}
	}

	private RoutingStatus calculateRoutingStatus(Itinerary itinerary, RouteSpecification routeSpecification) {
		if (itinerary == null) {
			return RoutingStatus.NOT_ROUTED;
		} else {
			if (routeSpecification.isSatisfiedBy(itinerary)) {
				return RoutingStatus.ROUTED;
			} else {
				return RoutingStatus.MISROUTED;
			}
		}
	}

	private TransportStatus calculateTransportStatus() {
		if (lastEvent == null) {
			return TransportStatus.NOT_RECEIVED;
		}

		switch (lastEvent.getType()) {
			case LOAD:
				return TransportStatus.ONBOARD_CARRIER;
			case UNLOAD:
			case RECEIVE:
			case CUSTOMS:
				return TransportStatus.IN_PORT;
			case CLAIM:
				return TransportStatus.CLAIMED;
			default:
				return TransportStatus.UNKNOWN;
		}
	}

	private Location calculateLastKnownLocation() {
		if (lastEvent != null) {
			return lastEvent.getLocation();
		} else {
			return null;
		}
	}

	private Voyage calculateCurrentVoyage() {
		if (transportStatus.equals(TransportStatus.ONBOARD_CARRIER) && lastEvent != null) {
			return lastEvent.getVoyage();
		} else {
			return null;
		}
	}

	private Date calculateEta(Itinerary itinerary) {
		if (onTrack()) {
			return itinerary.getFinalArrivalDate();
		} else {
			return ETA_UNKOWN;
		}
	}

	private HandlingActivity calculateNextExpectedActivity(RouteSpecification routeSpecification, Itinerary itinerary) {
		if (!onTrack()) return NO_ACTIVITY;

		if (lastEvent == null) return new HandlingActivity(HandlingEvent.Type.RECEIVE, routeSpecification.getOrigin());

		switch (lastEvent.getType()) {
			case LOAD:
				for (Leg leg : itinerary.getLegs()) {
					if (leg.getLoadLocation().equals(lastEvent.getLocation())) {
						return new HandlingActivity(HandlingEvent.Type.UNLOAD, leg.getUnloadLocation(), leg.getVoyage());
					}
				}

				return NO_ACTIVITY;

			case UNLOAD:
				for (Iterator<Leg> it = itinerary.getLegs().iterator(); it.hasNext();) {
					final Leg leg = it.next();
					if (leg.getUnloadLocation().equals(lastEvent.getLocation())) {
						if (it.hasNext()) {
							final Leg nextLeg = it.next();
							return new HandlingActivity(HandlingEvent.Type.LOAD, nextLeg.getLoadLocation(), nextLeg.getVoyage());
						} else {
							return new HandlingActivity(HandlingEvent.Type.CLAIM, leg.getUnloadLocation());
						}
					}
				}

				return NO_ACTIVITY;

			case RECEIVE:
				final Leg firstLeg = itinerary.getLegs().iterator().next();
				return new HandlingActivity(HandlingEvent.Type.LOAD, firstLeg.getLoadLocation(), firstLeg.getVoyage());

			case CLAIM:
			default:
				return NO_ACTIVITY;
		}
	}

	private boolean calculateUnloadedAtDestination(RouteSpecification routeSpecification) {
		return lastEvent != null
				&& HandlingEvent.Type.UNLOAD.equals(lastEvent.getType())
				&& routeSpecification.getDestination().equals(lastEvent.getLocation());
	}

	private boolean onTrack() {
		return routingStatus.equals(RoutingStatus.ROUTED) && !misdirected;
	}

	// {{ TransportStatus (property)
	private TransportStatus transportStatus;

	@MemberOrder(sequence = "1")
	public TransportStatus getTransportStatus() {
		return transportStatus;
	}

	public void setTransportStatus(final TransportStatus transportStatus) {
		this.transportStatus = transportStatus;
	}
	// }}

	// {{ LastKnownLocation (property)
	private Location lastKnownLocation;

	@MemberOrder(sequence = "1")
	public Location getLastKnownLocation() {
		return lastKnownLocation;
	}

	public void setLastKnownLocation(final Location lastKnownLocation) {
		this.lastKnownLocation = lastKnownLocation;
	}
	// }}

	// {{ currentVoyage (property)
	private Voyage currentVoyage;

	@MemberOrder(sequence = "1")
	public Voyage getCurrentVoyage() {
		return currentVoyage;
	}

	public void setCurrentVoyage(final Voyage currentVoyage) {
		this.currentVoyage = currentVoyage;
	}
	// }}

	// {{ Misdirected (property)
	private boolean misdirected;

	@MemberOrder(sequence = "1")
	public boolean getMisdirected() {
		return misdirected;
	}

	public void setMisdirected(final boolean misdirected) {
		this.misdirected = misdirected;
	}
	// }}

	// {{ Eta (property)
	private Date eta;

	@MemberOrder(sequence = "1")
	public Date getEta() {
		return eta;
	}

	public void setEta(final Date eta) {
		this.eta = eta;
	}
	// }}

	// {{ NextExpectedActivity (property)
	private HandlingActivity nextExpectedActivity;

	@MemberOrder(sequence = "1")
	public HandlingActivity getNextExpectedActivity() {
		return nextExpectedActivity;
	}

	public void setNextExpectedActivity(final HandlingActivity nextExpectedActivity) {
		this.nextExpectedActivity = nextExpectedActivity;
	}
	// }}

	// {{ IsUnloadedAtDestination (property)
	private boolean isUnloadedAtDestination;

	@MemberOrder(sequence = "1")
	public boolean getIsUnloadedAtDestination() {
		return isUnloadedAtDestination;
	}

	public void setIsUnloadedAtDestination(final boolean isUnloadedAtDestination) {
		this.isUnloadedAtDestination = isUnloadedAtDestination;
	}
	// }}

	// {{ RoutingStatus (property)
	private RoutingStatus routingStatus;

	@MemberOrder(sequence = "1")
	public RoutingStatus getRoutingStatus() {
		return routingStatus;
	}

	public void setRoutingStatus(final RoutingStatus routingStatus) {
		this.routingStatus = routingStatus;
	}
	// }}

	// {{ CalculatedAt (property)
	private Date calculatedAt;

	@MemberOrder(sequence = "1")
	public Date getCalculatedAt() {
		return calculatedAt;
	}

	public void setCalculatedAt(final Date calculatedAt) {
		this.calculatedAt = calculatedAt;
	}
	// }}

	// {{ LastEvent (property)
	private HandlingEvent lastEvent;

	@MemberOrder(sequence = "1")
	public HandlingEvent getLastEvent() {
		return lastEvent;
	}

	public void setLastEvent(final HandlingEvent lastEvent) {
		this.lastEvent = lastEvent;
	}
	// }}

}
