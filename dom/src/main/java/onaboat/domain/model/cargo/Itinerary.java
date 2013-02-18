package onaboat.domain.model.cargo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import onaboat.domain.model.handling.HandlingEvent;
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


	public boolean isExpected(final HandlingEvent event) {
		if (legs.isEmpty()) {
			return true;
		}

		if (event.getType() == HandlingEvent.Type.RECEIVE) {
			// Check that the first leg's origin is the event's location
			final Leg leg = legs.get(0);
			return (leg.getLoadLocation().equals(event.getLocation()));
		}

		if (event.getType() == HandlingEvent.Type.LOAD) {
			// Check that there is one leg with the same load location and voyage
			for (Leg leg : legs) {
				if (leg.getLoadLocation().equals(event.getLocation())
						&& leg.getVoyage().equals(event.getVoyage())) {
					return true;
				}
			}
			return false;
		}

		if (event.getType() == HandlingEvent.Type.UNLOAD) {
			// Check that there is one leg with the same unload location and voyage
			for (Leg leg : legs) {
				if (leg.getUnloadLocation().equals(event.getLocation())
						&& leg.getVoyage().equals(event.getVoyage())) {
					return true;
				}
			}
			return false;
		}

		if (event.getType() == HandlingEvent.Type.CLAIM) {
			// Check that the last leg's destination is from the event's location
			final Leg leg = getLastLeg();
			return (leg.getUnloadLocation().equals(event.getLocation()));
		}

		// HandlingEvent.Type.CUSTOMS
		return true;
	}

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
