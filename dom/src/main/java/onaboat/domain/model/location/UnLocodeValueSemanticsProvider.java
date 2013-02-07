package onaboat.domain.model.location;

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
public class UnLocodeValueSemanticsProvider extends ValueSemanticsProviderAndFacetAbstract<UnLocode> {

	private static Class<? extends Facet> type() {
		return UnLocodeValueFacet.class;
	}

	private static final int TYPICAL_LENGTH = 5;
	private static final UnLocode DEFAULT_VALUE = null;

	/**
	 * Required because implementation of {@link Parser} and
	 * {@link EncoderDecoder}.
	 */
	public UnLocodeValueSemanticsProvider() {
		this(null, null, null);
	}

	public UnLocodeValueSemanticsProvider(
			final FacetHolder holder,
			final IsisConfiguration configuration,
			final ValueSemanticsProviderContext context) {
		super(type(), holder, UnLocode.class, TYPICAL_LENGTH, Immutability.IMMUTABLE, EqualByContent.HONOURED, DEFAULT_VALUE, configuration, context);
	}

	// //////////////////////////////////////////////////////////////////
	// Parser
	// //////////////////////////////////////////////////////////////////

	@Override
	protected UnLocode doParse(final Object context, final String text) {
		final String entry = text.trim();
		return new UnLocode(entry);
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
	protected UnLocode doRestore(String data) {
		return new UnLocode(data);
	}

}
