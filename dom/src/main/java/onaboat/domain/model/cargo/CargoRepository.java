package onaboat.domain.model.cargo;

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
@Named("Cargo")
public class CargoRepository extends AbstractFactoryAndRepository {

	@ActionSemantics(Of.SAFE)
	public Cargo find(final TrackingId trackingId) {
		return firstMatch(Cargo.class, new Filter<Cargo>() {
			@Override
			public boolean accept(Cargo cargo) {
				return cargo.getTrackingId().equals(trackingId);
			}
		});
	}

	@ActionSemantics(Of.SAFE)
	public List<Cargo> findAll() {
		return allInstances(Cargo.class);
	}

	@ActionSemantics(Of.NON_IDEMPOTENT)
	public Cargo bookNewCargo(TrackingId trackingId, RouteSpecification routeSpecification) {
		Cargo cargo = newTransientInstance(Cargo.class);
		cargo.setTrackingId(trackingId);
		cargo.setRouteSpecification(routeSpecification);
		return cargo;
	}
}
