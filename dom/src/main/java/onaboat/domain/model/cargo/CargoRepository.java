package onaboat.domain.model.cargo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import onaboat.domain.model.location.Location;

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

	public Cargo bookNewCargo(
			@Named("Origin") Location origin,
			@Named("Destination") Location destination,
			@Named("Arrival Deadline") Date arrivalDeadline) {
		Cargo cargo = newTransientInstance(Cargo.class);
		RouteSpecification routeSpecification = newTransientInstance(RouteSpecification.class);
		routeSpecification.setOrigin(origin);
		routeSpecification.setDestination(destination);
		routeSpecification.setArrivalDeadline(arrivalDeadline);
		cargo.setRouteSpecification(routeSpecification);
		String uuid = UUID.randomUUID().toString().toUpperCase();
		cargo.setTrackingId(new TrackingId(uuid.substring(0, uuid.indexOf('-'))));
		persist(cargo);
		return cargo;
	}

}
