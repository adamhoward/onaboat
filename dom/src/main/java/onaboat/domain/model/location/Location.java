package onaboat.domain.model.location;

import javax.jdo.annotations.IdentityType;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
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
