package onaboat.domain.model.cargo;

import java.util.Date;

import onaboat.domain.model.handling.HandlingEvent;
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
	public Voyage getcurrentVoyage() {
		return currentVoyage;
	}

	public void setcurrentVoyage(final Voyage currentVoyage) {
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
