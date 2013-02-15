package onaboat.fixture;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DOC: THIS CLASS HAS NO COMMENT!
 *
 * @author adamhoward
 */
public class DateUtils {

	private DateUtils() {}
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static Date toDate(String datePart) {
		return toDate(datePart, "00:00");
	}

	public static Date toDate(String datePart, String timePart) {
		try {
			return sdf.parse(datePart + " " + timePart);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
