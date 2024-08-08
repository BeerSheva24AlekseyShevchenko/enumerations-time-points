package telran.time;

public enum TimeUnit {
    SECOND(1), MINUTE(60), HOUR(3600);

    private int valueOfSeconds;

    private TimeUnit(int valueOfSeconds) {
        this.valueOfSeconds = valueOfSeconds;
    }

    public int getValueOfSeconds() {
        return valueOfSeconds;
    }

    public float between(TimePoint p1, TimePoint p2) {
        float p1Time =  p1.getTimeUnit().getValueOfSeconds() * p1.getAmount(); 
        float p2Time =  p2.getTimeUnit().getValueOfSeconds() * p2.getAmount();

        return (p2Time - p1Time) / valueOfSeconds;
    }

}
