package onaboat.domain.model.voyage;

import java.util.Arrays;
import java.util.Date;

import onaboat.domain.model.location.Location;
import onaboat.domain.model.location.LocationRepository;
import onaboat.domain.model.location.UnLocode;
import onaboat.domain.model.voyage.Schedule;
import onaboat.domain.model.voyage.Voyage;
import onaboat.domain.model.voyage.VoyageNumber;

import org.apache.isis.applib.fixtures.AbstractFixture;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class SampleVoyages extends AbstractFixture {

	@Override
	public void install() {
		getContainer().persistIfNotAlready(createVoyage("CM001", locationRepository.find(new UnLocode("SESTO")), locationRepository.find(new UnLocode("DEHAM"))));
	}

	private Voyage createVoyage(String id, Location from, Location to) {
		return new Voyage(new VoyageNumber(id), new Schedule(Arrays.asList(
				new CarrierMovement(from, to, new Date(), new Date())
		)));
	}

	// {{ injected: LocationRepository
	private LocationRepository locationRepository;

	public void setLocationRepository(final LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	// }}



}
