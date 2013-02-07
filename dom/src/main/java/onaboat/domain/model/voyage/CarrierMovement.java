package onaboat.domain.model.voyage;

import java.util.Date;

import onaboat.domain.model.location.Location;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public final class CarrierMovement {

	private Location departureLocation;
	private Location arrivalLocation;
	private Date departureTime;
	private Date arrivalTime;

	// Null Object pattern
	public static final CarrierMovement NONE = new CarrierMovement(
			Location.UNKNOWN, Location.UNKNOWN,
			new Date(0), new Date(0)
	);

	public CarrierMovement(
			Location departureLocation,
			Location arrivalLocation,
			Date departureTime,
			Date arrivalTime) {
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	public Location getDepartureLocation() {
		return departureLocation;
	}
	public Location getArrivalLocation() {
		return arrivalLocation;
	}
	public Date getDepartureTime() {
		return departureTime;
	}
	public Date getArrivalTime() {
		return arrivalTime;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final CarrierMovement other = (CarrierMovement)o;

		return com.google.common.base.Objects.equal(this.departureLocation, other.departureLocation)
				&& com.google.common.base.Objects.equal(this.arrivalLocation, other.arrivalLocation)
				&& com.google.common.base.Objects.equal(this.departureTime, other.departureTime)
				&& com.google.common.base.Objects.equal(this.arrivalTime, other.arrivalTime);
	}

	@Override
	public int hashCode() {
		return com.google.common.base.Objects.hashCode(
				this.departureLocation, this.arrivalLocation, this.departureTime, this.arrivalTime);
	}

}
