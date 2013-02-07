package onaboat.domain.model.cargo;

import org.apache.isis.applib.annotation.Value;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@Value(semanticsProviderClass=TrackingIdValueSemanticsProvider.class)
public final class TrackingId {

	private String id;

	public TrackingId(final String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id;
	}

}
