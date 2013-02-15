package onaboat.domain.model.cargo;

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
public class TrackingIdValueSemanticsProvider extends ValueSemanticsProviderAndFacetAbstract<TrackingId> {

	private static Class<? extends Facet> type() {
		return TrackingIdValueFacet.class;
	}

	private static final int TYPICAL_LENGTH = 8;
	private static final TrackingId DEFAULT_VALUE = null;

	/**
	 * Required because implementation of {@link Parser} and
	 * {@link EncoderDecoder}.
	 */
	public TrackingIdValueSemanticsProvider() {
		this(null, null, null);
	}

	public TrackingIdValueSemanticsProvider(
			final FacetHolder holder,
			final IsisConfiguration configuration,
			final ValueSemanticsProviderContext context) {
		super(type(), holder, TrackingId.class, TYPICAL_LENGTH, Immutability.IMMUTABLE, EqualByContent.HONOURED, DEFAULT_VALUE, configuration, context);
	}

	// //////////////////////////////////////////////////////////////////
	// Parser
	// //////////////////////////////////////////////////////////////////

	@Override
	protected TrackingId doParse(final Object context, final String text) {
		final String entry = text.trim();
		return new TrackingId(entry);
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
	protected TrackingId doRestore(String data) {
		return new TrackingId(data);
	}

}
