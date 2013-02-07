package onaboat.domain.model.location;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.filter.Filter;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@Named("Locations")
public class LocationRepository extends AbstractFactoryAndRepository {

	@ActionSemantics(Of.SAFE)
	public Location find(final UnLocode unLocode) {
		return firstMatch(Location.class, new Filter<Location>() {
			@Override
			public boolean accept(Location location) {
				return location.getUnLocode().equals(unLocode);
			}
		});
	}

	@ActionSemantics(Of.SAFE)
	public List<Location> findAll() {
		return allInstances(Location.class);
	}
}
