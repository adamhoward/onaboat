package onaboat.domain.model.location;

import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.ObjectType;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@Bounded
@ObjectType("LOC")
public class Location {

	private UnLocode unLocode;
	private String name;

	public static final Location UNKNOWN = new Location(
			new UnLocode("XXXXX"), "Unknown Location"
	);

	public Location(final UnLocode unLocode, final String name) {
		this.unLocode = unLocode;
		this.name = name;
	}

	public UnLocode getUnLocode() {
		return unLocode;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + " [" + unLocode + "]";
	}
}
