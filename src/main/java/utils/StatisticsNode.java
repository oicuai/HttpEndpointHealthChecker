package utils;

import lombok.Getter;

@Getter
public class StatisticsNode {

    private int totalPings;
    private int successfulPings;

    public StatisticsNode() {
        totalPings = 0;
        successfulPings = 0;
    }

    /**
     * Method to increase the total number of times the endpoint associated with the node instance was pinged.
     */
    public void incrementTotalPings() {
        this.totalPings++;
    }

    /**
     * Method to increase the total number of times the endpoint responded with a response code between 200-299 within
     * the given latency bound of 500ms.
     */
    public void incrementSuccessfulPings() {
        this.successfulPings++;
    }

    /**
     * Returns the availability percentage representing the ratio of successful pings to total pings.
     * @return the availability percentage as an integer
     */
    public int calculateAvailabilityPercentage() {
        return (int) Math.round((double) getSuccessfulPings() / (double) getTotalPings() * 100);
    }
}
