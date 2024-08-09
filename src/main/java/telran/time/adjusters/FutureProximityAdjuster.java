package telran.time.adjusters;

import java.util.Arrays;

import telran.time.TimePoint;

public class FutureProximityAdjuster implements TimePointAdjuster {
    TimePoint [] timePoints;

    public FutureProximityAdjuster(TimePoint[] timePoints) {
        timePoints = timePoints.clone();
        Arrays.sort(timePoints);
        this.timePoints = timePoints;
    }

    @Override
    public TimePoint adjust(TimePoint timePoint) {
        int begin = 0;
        int end = timePoints.length -1;

        while (begin <= end) {
            int mid = (begin + end) / 2;
            int cmp = timePoints[mid].compareTo(timePoint);

            if (cmp <= 0) {
                begin = mid + 1;
            } else if (cmp > 0) {
                end = mid - 1;
            }
        }

        return begin < timePoints.length ? timePoints[begin].convert(timePoint.getTimeUnit()) : null;

    }
}
