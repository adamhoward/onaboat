package onaboat.domain.model.handling;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@SuppressWarnings("serial")
public class CannotCreateHandlingEventException extends Exception {
	public CannotCreateHandlingEventException(Exception e) {
		super(e);
	}

	public CannotCreateHandlingEventException() {
		super();
	}
}
