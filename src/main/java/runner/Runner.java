package runner;

import http.Ping;
import http.HttpEndpoint;
import utils.ConsoleUtils;
import utils.Constants;
import utils.DisplayAvailabilityPercentage;
import utils.HttpEndpointExtractor;
import utils.StatisticsNode;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Runner {

    /**
     * The entry point for the Http health checker program. Requires a path to a well-formed yaml document to run.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Runner RUNNER = new Runner();
        String yamlPath = ConsoleUtils.getYamlFromCLI();
        try {
            RUNNER.runProgram(yamlPath);
        } catch (InterruptedException e) {
            System.exit(1);
        }
    }

    private void runProgram(String yamlPath) throws InterruptedException {
        List<HttpEndpoint> listOfEndpoints = HttpEndpointExtractor.getListOfEndpointsFromYaml(yamlPath);
        Map<String, StatisticsNode> mapOfDomains = HttpEndpointExtractor.getMapOfDomains();
        // while true so that the program runs until terminated by user
        while (true) {
            Ping.pingEndpoints(listOfEndpoints, mapOfDomains);
            DisplayAvailabilityPercentage.printAllAvailabilityPercentagesToConsole(mapOfDomains);
            TimeUnit.MILLISECONDS.sleep(Constants.WAIT_TIME);
        }

    }
}
