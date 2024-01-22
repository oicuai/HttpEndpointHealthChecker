package utils;

import java.util.Map;

public class DisplayAvailabilityPercentage {

    /**
     * Method to print availability percentages of every domain to the console.
     * @param mapOfDomains the mapping of domain names to an object containing the ping statistics for that domain
     */
    public static void printAllAvailabilityPercentagesToConsole(Map<String, StatisticsNode> mapOfDomains) {
        for (Map.Entry<String, StatisticsNode> entry : mapOfDomains.entrySet()) {
            String domain = entry.getKey();
            StatisticsNode statisticsNode = entry.getValue();
            int availabilityPercentage = statisticsNode.calculateAvailabilityPercentage();
            System.out.printf("%s has %d%% availability percentage%n", domain, availabilityPercentage);
        }
    }
}
