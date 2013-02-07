package onaboat.domain.model.location;

import org.apache.isis.applib.annotation.Value;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@Value(semanticsProviderClass=UnLocodeValueSemanticsProvider.class)
public class UnLocode {

	private String unlocode;

	public UnLocode(final String countryAndLocation) {
		this.unlocode = countryAndLocation.toUpperCase();
	}

	@Override
	public String toString() {
		return unlocode;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UnLocode other = (UnLocode)o;
		return this.unlocode.equals(other.unlocode);
	}

	@Override
	public int hashCode() {
		return unlocode.hashCode();
	}
}
