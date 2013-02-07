package onaboat.domain.model.voyage;

import org.apache.isis.applib.annotation.Value;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@Value(semanticsProviderClass=VoyageNumberValueSemanticsProvider.class)
public class VoyageNumber {

	private String number;

	public VoyageNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return number;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		VoyageNumber other = (VoyageNumber)o;
		return this.number.equals(other.number);
	}

	@Override
	public int hashCode() {
		return number.hashCode();
	}
}
