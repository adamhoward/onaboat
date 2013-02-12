package onaboat.domain.model.cargo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import onaboat.domain.model.location.Location;

import org.apache.isis.applib.annotation.MemberOrder;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class Itinerary {

	private static final Date END_OF_DAYS = new Date(Long.MAX_VALUE);

	// {{ Legs (Collection)
	private List<Leg> legs = new ArrayList<Leg>();

	@MemberOrder(sequence = "1")
	public List<Leg> getLegs() {
		return legs;
	}

	public void setLegs(final List<Leg> legs) {
		this.legs = legs;
	}
	// }}


	Location getInitialDepartureLocation() {
		if (legs.isEmpty()) {
			return Location.UNKNOWN;
		} else {
			return legs.get(0).getLoadLocation();
		}
	}

	Location getFinalArrivalLocation() {
		if (legs.isEmpty()) {
			return Location.UNKNOWN;
		} else {
			return getLastLeg().getUnloadLocation();
		}
	}

	Date getFinalArrivalDate() {
		final Leg lastLeg = getLastLeg();
		if (lastLeg == null) {
			return new Date(END_OF_DAYS.getTime());
		} else {
			return new Date(lastLeg.getUnloadTime().getTime());
		}
	}

	Leg getLastLeg() {
		if (legs.isEmpty()) {
			return null;
		} else {
			return legs.get(legs.size() -1);
		}
	}
}
