package onaboat.domain.model.handling;

import onaboat.domain.model.location.UnLocode;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@SuppressWarnings("serial")
public class UnknownLocationException extends CannotCreateHandlingEventException {
	private final UnLocode unlocode;

	public UnknownLocationException(final UnLocode unlocode) {
		this.unlocode = unlocode;
	}

	@Override
	public String getMessage() {
		return "No location with UN locode " + unlocode + " exists in the system.";
	}
}
