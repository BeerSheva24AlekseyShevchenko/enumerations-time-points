package telran.time.adjusters;

import telran.time.TimePoint;

public interface TimePointAdjuster {
    TimePoint adjust(TimePoint timePoint);
}
