package onaboat.domain.model.voyage;

import java.util.List;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@ObjectType("VOY")
public class Voyage {

	public static final Voyage NONE = new Voyage(
			new VoyageNumber(""), Schedule.EMPTY
	);

	public Voyage(final VoyageNumber voyageNumber, final Schedule schedule) {
		this.voyageNumber = voyageNumber;
		this.schedule = schedule;
		this.carrierMovements = schedule.getCarrierMovements();
	}

	// {{ VoyageNumber (property)
	private VoyageNumber voyageNumber;

	@MemberOrder(sequence = "1")
	public VoyageNumber getVoyageNumber() {
		return voyageNumber;
	}
	// }}

	// {{ Schedule (property)
	private Schedule schedule;

	@MemberOrder(sequence = "2")
	public Schedule getSchedule() {
		return schedule;
	}
	// }}

	private List<CarrierMovement> carrierMovements;

	@MemberOrder(sequence = "3")
	public List<CarrierMovement> getCarrierMovements() {
		return carrierMovements;
	}

	@Override
	public String toString() {
		return "Voyage " + voyageNumber;
	}




}
