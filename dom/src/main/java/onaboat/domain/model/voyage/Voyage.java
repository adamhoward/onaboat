package onaboat.domain.model.voyage;

import java.util.Collections;
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
			new VoyageNumber(""), Collections.<CarrierMovement>emptyList()
	);

	public Voyage(){}

	public Voyage(final VoyageNumber voyageNumber, final List<CarrierMovement> schedule) {
		this.voyageNumber = voyageNumber;
		this.schedule = schedule;
	}

	// {{ VoyageNumber (property)
	private VoyageNumber voyageNumber;

	@MemberOrder(sequence = "1")
	public VoyageNumber getVoyageNumber() {
		return voyageNumber;
	}
	public void setVoyageNumber(VoyageNumber voyageNumber) {
		this.voyageNumber = voyageNumber;
	}
	// }}

	// {{ Schedule (property)
	private List<CarrierMovement> schedule;

	@MemberOrder(sequence = "2")
	public List<CarrierMovement> getSchedule() {
		return schedule;
	}
	public void setSchedule(List<CarrierMovement> schedule) {
		this.schedule = schedule;
	}
	// }}

	@Override
	public String toString() {
		return "Voyage " + voyageNumber;
	}




}
