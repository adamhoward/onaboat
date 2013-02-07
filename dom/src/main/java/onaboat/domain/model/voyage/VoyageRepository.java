package onaboat.domain.model.voyage;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Prototype;
import org.apache.isis.applib.filter.Filter;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
@Named("Voyages")
public class VoyageRepository extends AbstractFactoryAndRepository {

	@ActionSemantics(Of.SAFE)
	public Voyage find(final VoyageNumber voyageNumber) {
		return firstMatch(Voyage.class, new Filter<Voyage>() {
			@Override
			public boolean accept(Voyage voyage) {
				return voyage.getVoyageNumber().equals(voyageNumber);
			}
		});
	}

	@Prototype
	@ActionSemantics(Of.SAFE)
	public List<Voyage> findAll() {
		return allInstances(Voyage.class);
	}
}
