package onaboat.domain.model.voyage;

import static onaboat.fixture.DateUtils.*;

import java.util.Date;
import java.util.List;

import onaboat.domain.model.location.Location;
import onaboat.domain.model.location.LocationRepository;
import onaboat.domain.model.location.UnLocode;
import onaboat.domain.model.voyage.Voyage;
import onaboat.domain.model.voyage.VoyageNumber;

import org.apache.isis.applib.fixtures.AbstractFixture;

import com.google.common.collect.Lists;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class SampleVoyages extends AbstractFixture {

	@Override
	public void install() {
		final Location tokyo = locationRepository.find(new UnLocode("JNTKO"));
		final Location hongkong = locationRepository.find(new UnLocode("CNHKG"));
		final Location newyork = locationRepository.find(new UnLocode("USNYC"));
		final Location chicago = locationRepository.find(new UnLocode("USCHI"));
		final Location stockholm = locationRepository.find(new UnLocode("SESTO"));
		final Location rotterdam = locationRepository.find(new UnLocode("NLRTM"));
		final Location hamburg = locationRepository.find(new UnLocode("DEHAM"));
		final Location melbourne = locationRepository.find(new UnLocode("AUMEL"));
		final Location helsinki = locationRepository.find(new UnLocode("FIHEL"));
		final Location hangzhou = locationRepository.find(new UnLocode("CNHGH"));

		persist(new Builder(new VoyageNumber("V100"), hongkong).
				addMovement(tokyo, toDate("2009-03-03"), toDate("2009-03-05")).
				addMovement(newyork, toDate("2009-03-06"), toDate("2009-03-09")).
				build());
		persist(new Builder(new VoyageNumber("V200"), tokyo).
				addMovement(newyork, toDate("2009-03-06"), toDate("2009-03-08")).
				addMovement(chicago, toDate("2009-03-10"), toDate("2009-03-14")).
				addMovement(stockholm, toDate("2009-03-14"), toDate("2009-03-16")).
				build());
		persist(new Builder(new VoyageNumber("V300"), tokyo).
				addMovement(rotterdam, toDate("2009-03-08"), toDate("2009-03-11")).
				addMovement(hamburg, toDate("2009-03-11"), toDate("2009-03-12")).
				addMovement(melbourne, toDate("2009-03-14"), toDate("2009-03-18")).
				addMovement(tokyo, toDate("2009-03-19"), toDate("2009-03-21")).
				build());
		persist(new Builder(new VoyageNumber("V400"), hamburg).
				addMovement(stockholm, toDate("2009-03-14"), toDate("2009-03-15")).
				addMovement(helsinki, toDate("2009-03-15"), toDate("2009-03-16")).
				addMovement(hamburg, toDate("2009-03-20"), toDate("2009-03-22")).
				build());
		persist(new Builder(new VoyageNumber("0100S"), hongkong).
				addMovement(hangzhou, toDate("2008-10-01", "12:00"), toDate("2008-10-03", "14:30")).
				addMovement(tokyo, toDate("2008-10-03", "21:00"), toDate("2008-10-06", "06:15")).
				addMovement(melbourne, toDate("2008-10-06", "11:00"), toDate("2008-10-12", "11:30")).
				addMovement(newyork, toDate("2008-10-14", "12:00"), toDate("2008-10-23", "23:10")).
				build());
	}

	private final class Builder {
		private List<CarrierMovement> schedule = Lists.newArrayList();
		private VoyageNumber voyageNumber;
		private Location departureLocation;

		public Builder(final VoyageNumber voyageNumber, final Location departureLocation) {
			this.voyageNumber = voyageNumber;
			this.departureLocation = departureLocation;
		}
		public Builder addMovement(Location arrivalLocation, Date departureTime, Date arrivalTime) {
			CarrierMovement cm = newTransientInstance(CarrierMovement.class);
			cm.setDepartureLocation(departureLocation);
			cm.setArrivalLocation(arrivalLocation);
			cm.setDepartureTime(departureTime);
			cm.setArrivalTime(arrivalTime);
			schedule.add(cm);
			this.departureLocation = arrivalLocation;
			return this;
		}
		public Voyage build() {
			Voyage voyage = newTransientInstance(Voyage.class);
			voyage.setVoyageNumber(voyageNumber);
			voyage.setSchedule(schedule);
			return voyage;
		}
	}


	// {{ injected: LocationRepository
	private LocationRepository locationRepository;

	public void setLocationRepository(final LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	// }}

}
