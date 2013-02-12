package onaboat.domain.model.handling;

import onaboat.domain.model.voyage.VoyageNumber;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@SuppressWarnings("serial")
public class UnknownVoyageException extends CannotCreateHandlingEventException {
	private final VoyageNumber voyageNumber;

	public UnknownVoyageException(VoyageNumber voyageNumber) {
		this.voyageNumber = voyageNumber;
	}

	@Override
	public String getMessage() {
		return "No voyage with number " + voyageNumber + " exists in the system";
	}
}
