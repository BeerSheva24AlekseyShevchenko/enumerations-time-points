package telran.time;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TimeUnitTest {
    TimePoint point7200Seconds = new TimePoint(7200, TimeUnit.SECOND);
    TimePoint point120Minutes = new TimePoint(120, TimeUnit.MINUTE);
    TimePoint point2Hours = new TimePoint(2, TimeUnit.HOUR);
    TimePoint point3Hours = new TimePoint(3, TimeUnit.HOUR);

    @Test
    void betweenTest() {
        assertEquals(0, TimeUnit.SECOND.between(point7200Seconds, point120Minutes));
        assertEquals(0, TimeUnit.SECOND.between(point120Minutes, point2Hours));
        assertEquals(0, TimeUnit.SECOND.between(point7200Seconds, point2Hours));

        assertEquals(3600.0, TimeUnit.SECOND.between(point120Minutes, point3Hours));
        assertEquals(-3600.0, TimeUnit.SECOND.between(point3Hours, point120Minutes));
        assertEquals(60.0, TimeUnit.MINUTE.between(point120Minutes, point3Hours));
        assertEquals(-60.0, TimeUnit.MINUTE.between(point3Hours, point120Minutes));
        assertEquals(1.0, TimeUnit.HOUR.between(point120Minutes, point3Hours));
        assertEquals(-1.0, TimeUnit.HOUR.between(point3Hours, point120Minutes));
    }

}
