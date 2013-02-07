package onaboat.domain.model.voyage;

import org.apache.isis.applib.adapters.EncoderDecoder;
import org.apache.isis.applib.adapters.Parser;
import org.apache.isis.applib.profiles.Localization;
import org.apache.isis.core.commons.config.IsisConfiguration;
import org.apache.isis.core.metamodel.facetapi.Facet;
import org.apache.isis.core.metamodel.facetapi.FacetHolder;
import org.apache.isis.core.progmodel.facets.object.value.ValueSemanticsProviderAndFacetAbstract;
import org.apache.isis.core.progmodel.facets.object.value.ValueSemanticsProviderContext;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class VoyageNumberValueSemanticsProvider extends ValueSemanticsProviderAndFacetAbstract<VoyageNumber> {

	private static Class<? extends Facet> type() {
		return VoyageNumberValueFacet.class;
	}

	private static final int TYPICAL_LENGTH = 8;
	private static final VoyageNumber DEFAULT_VALUE = null;

	/**
	 * Required because implementation of {@link Parser} and
	 * {@link EncoderDecoder}.
	 */
	public VoyageNumberValueSemanticsProvider() {
		this(null, null, null);
	}

	public VoyageNumberValueSemanticsProvider(
			final FacetHolder holder,
			final IsisConfiguration configuration,
			final ValueSemanticsProviderContext context) {
		super(type(), holder, VoyageNumber.class, TYPICAL_LENGTH, Immutability.IMMUTABLE, EqualByContent.HONOURED, DEFAULT_VALUE, configuration, context);
	}

	// //////////////////////////////////////////////////////////////////
	// Parser
	// //////////////////////////////////////////////////////////////////

	@Override
	protected VoyageNumber doParse(final Object context, final String text) {
		final String entry = text.trim();
		return new VoyageNumber(entry);
	}

	@Override
	protected String titleString(final Object object, final Localization localization) {
		if (object == null) {
			return "";
		}
		return object.toString();
	}

	@Override
	public String titleStringWithMask(final Object value, final String usingMask) {
		return titleString(value, null);
	}

	// //////////////////////////////////////////////////////////////////
	// EncoderDecoder
	// //////////////////////////////////////////////////////////////////

	@Override
	protected String doEncode(final Object object) {
		if (object == null) {
			return "";
		}
		return object.toString();
	}

	@Override
	protected VoyageNumber doRestore(String data) {
		return new VoyageNumber(data);
	}

}
