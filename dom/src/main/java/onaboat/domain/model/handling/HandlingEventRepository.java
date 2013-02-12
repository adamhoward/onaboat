package onaboat.domain.model.handling;

import java.util.Date;

import onaboat.domain.model.cargo.Cargo;
import onaboat.domain.model.cargo.CargoRepository;
import onaboat.domain.model.cargo.TrackingId;
import onaboat.domain.model.location.Location;
import onaboat.domain.model.location.LocationRepository;
import onaboat.domain.model.location.UnLocode;
import onaboat.domain.model.voyage.Voyage;
import onaboat.domain.model.voyage.VoyageNumber;
import onaboat.domain.model.voyage.VoyageRepository;

import org.apache.isis.applib.AbstractFactoryAndRepository;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class HandlingEventRepository extends AbstractFactoryAndRepository {

	// {{ injected dependencies
	private CargoRepository cargoRepository;
	public void setCargoRepository(final CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	private VoyageRepository voyageRepository;
	public void setVoyageRepository(final VoyageRepository voyageRepository) {
		this.voyageRepository = voyageRepository;
	}

	private LocationRepository locationRepository;
	public void setLocationRepository(final LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	// }}

	public HandlingEvent createHandlingEvent(Date registrationTime, Date completionTime, TrackingId trackingId, VoyageNumber voyageNumber, UnLocode unlocode, HandlingEvent.Type type) throws CannotCreateHandlingEventException {
		final Cargo cargo = findCargo(trackingId);
		final Voyage voyage = findVoyage(voyageNumber);
		final Location location = findLocation(unlocode);

		HandlingEvent event = newTransientInstance(HandlingEvent.class);
		try {
			event.setCargo(cargo);
			event.setCompletionTime(completionTime);
			event.setRegistrationTime(registrationTime);
			event.setLocation(location);
			event.setType(type);
			event.setVoyage(voyage);
			String validationError = event.validate();
			if (validationError != null) {
				throw new Exception(validationError);
			}
		} catch (Exception e) {
			throw new CannotCreateHandlingEventException(e);
		}
		return event;
	}

	private Cargo findCargo(TrackingId trackingId) throws UnknownCargoException {
		final Cargo cargo = cargoRepository.find(trackingId);
		if (cargo == null) {
			throw new UnknownCargoException(trackingId);
		}

		return cargo;
	}

	private Voyage findVoyage(VoyageNumber voyageNumber) throws UnknownVoyageException {
		if (voyageNumber == null) {
			return null;
		}

		final Voyage voyage = voyageRepository.find(voyageNumber);
		if (voyage == null) {
			throw new UnknownVoyageException(voyageNumber);
		}

		return voyage;
	}

	private Location findLocation(UnLocode unlocode) throws UnknownLocationException {
		final Location location = locationRepository.find(unlocode);
		if (location == null) {
			throw new UnknownLocationException(unlocode);
		}

		return location;
	}

}
