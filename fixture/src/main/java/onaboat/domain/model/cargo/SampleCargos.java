package onaboat.domain.model.cargo;

import static onaboat.fixture.DateUtils.*;

import java.util.Date;

import onaboat.domain.model.location.Location;
import onaboat.domain.model.location.LocationRepository;
import onaboat.domain.model.location.UnLocode;

import org.apache.isis.applib.fixtures.AbstractFixture;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class SampleCargos extends AbstractFixture {

	@Override
	public void install() {
		Location origin = locationRepository.find(new UnLocode("JNTKO"));
		Location destination = locationRepository.find(new UnLocode("CNHKG"));
		Date arrivalDeadline = toDate("2013-02-28");
		cargoRepository.bookNewCargo(origin, destination, arrivalDeadline);
	}

	// {{ injected dependencies

	// {{ injected: CargoRepository
	private CargoRepository cargoRepository;

	public void setCargoRepository(final CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	// }}

	// {{ injected: LocationRepository
	private LocationRepository locationRepository;

	public void setLocationRepository(final LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	// }}


	// }}



}
