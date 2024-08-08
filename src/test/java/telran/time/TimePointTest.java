package telran.time;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.time.adjusters.FutureProximityAdjuster;
import telran.time.adjusters.PlusTimePointAdjuster;

public class TimePointTest {
    TimePoint point60Seconds = new TimePoint(60, TimeUnit.SECOND);
    TimePoint point7200Seconds = new TimePoint(7200, TimeUnit.SECOND);
    TimePoint point60Minutes = new TimePoint(60, TimeUnit.MINUTE);
    TimePoint point120Minutes = new TimePoint(120, TimeUnit.MINUTE);
    TimePoint point2Hours = new TimePoint(2, TimeUnit.HOUR);
    TimePoint point3Hours = new TimePoint(3, TimeUnit.HOUR);
    TimePoint point3HoursNegative = new TimePoint(-3, TimeUnit.HOUR);
    TimePoint point5HoursNegative = new TimePoint(-5, TimeUnit.HOUR);

    @Test
    void getAmountTest() {
        assertEquals(7200, point7200Seconds.getAmount());
        assertEquals(120, point120Minutes.getAmount());
        assertEquals(2, point2Hours.getAmount());
    }
    
    @Test
    void getTimeUnitTest() {
        assertEquals(TimeUnit.SECOND, point7200Seconds.getTimeUnit());
        assertEquals(TimeUnit.MINUTE, point120Minutes.getTimeUnit());
        assertEquals(TimeUnit.HOUR, point2Hours.getTimeUnit());
    }

    @Test
    void convertTest() {
        assertEquals(120, point7200Seconds.convert(TimeUnit.MINUTE).getAmount());
        assertEquals(TimeUnit.MINUTE, point7200Seconds.convert(TimeUnit.MINUTE).getTimeUnit());
        assertEquals(2, point7200Seconds.convert(TimeUnit.HOUR).getAmount());
        assertEquals(TimeUnit.HOUR, point7200Seconds.convert(TimeUnit.HOUR).getTimeUnit());
    
        assertEquals(7200, point120Minutes.convert(TimeUnit.SECOND).getAmount());
        assertEquals(TimeUnit.SECOND, point120Minutes.convert(TimeUnit.SECOND).getTimeUnit());
        assertEquals(2, point120Minutes.convert(TimeUnit.HOUR).getAmount());
        assertEquals(TimeUnit.HOUR, point120Minutes.convert(TimeUnit.HOUR).getTimeUnit());

        assertEquals(7200, point2Hours.convert(TimeUnit.SECOND).getAmount());
        assertEquals(TimeUnit.SECOND, point2Hours.convert(TimeUnit.SECOND).getTimeUnit());
        assertEquals(120, point2Hours.convert(TimeUnit.MINUTE).getAmount());
        assertEquals(TimeUnit.MINUTE, point2Hours.convert(TimeUnit.MINUTE).getTimeUnit());
    }

    @Test
    void compareToTest() {
        assertTrue(0 == point7200Seconds.compareTo(point120Minutes));
        assertTrue(0 == point7200Seconds.compareTo(point2Hours));
        assertTrue(0 == point120Minutes.compareTo(point2Hours));

        assertTrue(0 < point3Hours.compareTo(point7200Seconds));
        assertTrue(0 < point3Hours.compareTo(point120Minutes));
        assertTrue(0 < point3Hours.compareTo(point2Hours));

        assertTrue(0 > point7200Seconds.compareTo(point3Hours));
        assertTrue(0 > point120Minutes.compareTo(point3Hours));
        assertTrue(0 > point2Hours.compareTo(point3Hours));
    }

    @Test
    void equalsTest() {
        assertTrue(point7200Seconds.equals(point120Minutes));
        assertTrue(point7200Seconds.equals(point2Hours));
        assertTrue(point120Minutes.equals(point2Hours));

        assertFalse(point3Hours.equals(point7200Seconds));
        assertFalse(point3Hours.equals(point120Minutes));
        assertFalse(point3Hours.equals(point2Hours));
    }

    @Test
    void withTest() {
        // Plus time test
        PlusTimePointAdjuster plus1Hour = new PlusTimePointAdjuster(1, TimeUnit.HOUR);
        PlusTimePointAdjuster plus60Minutes = new PlusTimePointAdjuster(60, TimeUnit.MINUTE);
        PlusTimePointAdjuster plus3600Seconds = new PlusTimePointAdjuster(3600, TimeUnit.SECOND);

        assertEquals(3, point2Hours.with(plus1Hour).getAmount());
        assertEquals(TimeUnit.HOUR, point2Hours.with(plus1Hour).getTimeUnit());
        assertEquals(3, point2Hours.with(plus60Minutes).getAmount());
        assertEquals(TimeUnit.HOUR, point2Hours.with(plus60Minutes).getTimeUnit());
        assertEquals(3, point2Hours.with(plus3600Seconds).getAmount());
        assertEquals(TimeUnit.HOUR, point2Hours.with(plus3600Seconds).getTimeUnit());

        // Future time test
        TimePoint[] points = {
            point3HoursNegative,
            point60Seconds,
            point120Minutes,
            point2Hours,
            point3Hours,
        };

        FutureProximityAdjuster futureProximity = new FutureProximityAdjuster(points);

        assertEquals(point3HoursNegative, point5HoursNegative.with(futureProximity));
        assertEquals(point60Seconds, point3HoursNegative.with(futureProximity));
        assertEquals(point120Minutes, point60Seconds.with(futureProximity));
        assertEquals(point120Minutes, point60Minutes.with(futureProximity));
        assertEquals(point3Hours, point120Minutes.with(futureProximity));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> point3Hours.with(futureProximity));
    }
}
