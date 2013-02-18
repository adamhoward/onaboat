package onaboat.domain.model.handling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class HandlingHistory {

	private final List<HandlingEvent> handlingEvents;

	public static final HandlingHistory EMPTY = new HandlingHistory(Collections.<HandlingEvent>emptyList());

	public HandlingHistory(Collection<HandlingEvent> handlingEvents) {
		this.handlingEvents = new ArrayList<HandlingEvent>(handlingEvents);
	}

	public List<HandlingEvent> getDistinctEventsByCompletionTime() {
		final List<HandlingEvent> ordered = new ArrayList<HandlingEvent>(
				new HashSet<HandlingEvent>(handlingEvents)
		);
		Collections.sort(ordered, BY_COMPLETION_TIME_COMPARATOR);
		return Collections.unmodifiableList(ordered);
	}

	public HandlingEvent getMostRecentlyCompletedEvent() {
		final List<HandlingEvent> distinctEvents = getDistinctEventsByCompletionTime();
		if (distinctEvents.isEmpty()) {
			return null;
		} else {
			return distinctEvents.get(distinctEvents.size() - 1);
		}
	}

	private static final Comparator<HandlingEvent> BY_COMPLETION_TIME_COMPARATOR =
		new Comparator<HandlingEvent>() {
			public int compare(final HandlingEvent he1, final HandlingEvent he2) {
				return he1.getCompletionTime().compareTo(he2.getCompletionTime());
			}
		};
}
