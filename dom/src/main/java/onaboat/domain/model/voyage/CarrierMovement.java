package onaboat.domain.model.voyage;

import java.util.Date;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.util.TitleBuffer;

import onaboat.domain.model.location.Location;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@ObjectType("MOVE")
public final class CarrierMovement {

	// Null Object pattern
	public static final CarrierMovement NONE = new CarrierMovement(
			Location.UNKNOWN, Location.UNKNOWN,
			new Date(0), new Date(0)
	);

	// {{ Identification
	public String title() {
		final TitleBuffer buf = new TitleBuffer();
		buf.append(departureLocation.getUnLocode());
		buf.append("-");
		buf.append(arrivalLocation.getUnLocode());
		return buf.toString();
	}
	// }}


	public CarrierMovement(){}

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

	// {{ DepartureLocation (property)
	private Location departureLocation;

	@MemberOrder(sequence = "1")
	public Location getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(final Location departureLocation) {
		this.departureLocation = departureLocation;
	}
	// }}


	// {{ DepartureTime (property)
	private Date departureTime;

	@MemberOrder(sequence = "2")
	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(final Date departureTime) {
		this.departureTime = departureTime;
	}
	// }}

	// {{ ArrivalLocation (property)
	private Location arrivalLocation;

	@MemberOrder(sequence = "3")
	public Location getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(final Location arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}
	// }}

	// {{ ArrivalTime (property)
	private Date arrivalTime;

	@MemberOrder(sequence = "4")
	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(final Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	// }}


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
