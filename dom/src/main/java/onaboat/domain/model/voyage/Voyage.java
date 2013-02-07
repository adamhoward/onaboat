package onaboat.domain.model.voyage;

import org.apache.isis.applib.annotation.MemberOrder;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class Voyage {

	public static final Voyage NONE = new Voyage(
			new VoyageNumber(""), Schedule.EMPTY
	);

	public Voyage(final VoyageNumber voyageNumber, final Schedule schedule) {
		this.voyageNumber = voyageNumber;
		this.schedule = schedule;
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

	@Override
	public String toString() {
		return "Voyage " + voyageNumber;
	}




}
