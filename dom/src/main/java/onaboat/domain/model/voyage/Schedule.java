package onaboat.domain.model.voyage;

import java.util.Collections;
import java.util.List;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class Schedule {
	private List<CarrierMovement> carrierMovements = Collections.emptyList();

	public static final Schedule EMPTY = new Schedule();

	public Schedule() {
	}

	Schedule(final List<CarrierMovement> carrierMovements) {
		this.carrierMovements = carrierMovements;
	}

	public List<CarrierMovement> getCarrierMovements() {
		return Collections.unmodifiableList(carrierMovements);
	}

}
