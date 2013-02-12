package onaboat.domain.model.handling;

import onaboat.domain.model.cargo.TrackingId;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@SuppressWarnings("serial")
public class UnknownCargoException extends CannotCreateHandlingEventException {
	private final TrackingId trackingId;

	public UnknownCargoException(final TrackingId trackingId) {
		this.trackingId = trackingId;
	}

	@Override
	public String getMessage() {
		return "No cargo with tracking id " + trackingId + " exists in the system.";
	}
}
