package onaboat.domain.model.voyage;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.filter.Filter;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class VoyageRepository extends AbstractFactoryAndRepository {

	public Voyage find(final VoyageNumber voyageNumber) {
		return firstMatch(Voyage.class, new Filter<Voyage>() {
			@Override
			public boolean accept(Voyage voyage) {
				return voyage.getVoyageNumber().equals(voyageNumber);
			}
		});
	}
}
