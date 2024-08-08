package telran.time;

import telran.time.adjusters.TimePointAdjuster;

public class TimePoint implements Comparable<TimePoint> {
    private float amount;
    private TimeUnit timeUnit;

    public TimePoint(float amount, TimeUnit timeUnit) {
        this.amount = amount;
        this.timeUnit = timeUnit;
    }

    public float getAmount() {
        return amount;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public TimePoint convert(TimeUnit timeUnit) {
        return new TimePoint((this.timeUnit.getValueOfSeconds() * amount) / timeUnit.getValueOfSeconds(), timeUnit);
    }

    public TimePoint with(TimePointAdjuster adjuster) {
        return adjuster.adjust(this);
    }

    @Override
    public int compareTo(TimePoint p) {
        return (int)TimeUnit.SECOND.between(p, this);
    }

    @Override
    public boolean equals(Object obj) {
        return TimeUnit.SECOND.between(this, (TimePoint) obj) == 0;
    }
}
