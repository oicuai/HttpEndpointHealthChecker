package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.InternetDomainName;
import http.HttpEndpoint;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpEndpointExtractor {

    private static List<HttpEndpoint> listOfEndpoints;
    private static Map<String, StatisticsNode> mapOfCounts;

    private static void buildMapOfDomains() {
        mapOfCounts = new HashMap<>();
        for (HttpEndpoint endpoint : listOfEndpoints) {
            if (!endpoint.getDomain().isEmpty())
                mapOfCounts.computeIfAbsent(endpoint.getDomain(), node -> new StatisticsNode());
        }
    }

    /**
     * Returns a mapping of domain names to statistics about the health of the endpoints at that domain name.
     *
     * @return a map of domain names to a statistic node containing the total count of pings and total count of
     * successful pings to endpoints at that domain
     */
    public static Map<String, StatisticsNode> getMapOfDomains() {
        buildMapOfDomains();
        return mapOfCounts;
    }

    private static String extractDomain(final String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            InternetDomainName domainName = InternetDomainName.from(host).topPrivateDomain();
            return domainName.toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a list of HttpEndpoint objects representing the endpoints parsed from the yaml input file
     *
     * @param yamlPath path to yaml file
     * @return List of HttpEndpoint objects representing the endpoints listed in the yaml file
     */
    public static List<HttpEndpoint> getListOfEndpointsFromYaml(final String yamlPath) {

        ObjectMapper mapper = Constants.OBJECT_MAPPER;
        listOfEndpoints = new ArrayList<>();

        List listOfMaps = YamlParser.getYamlContents(yamlPath);
        for (Object endpointMap : listOfMaps) {
            HttpEndpoint endpoint = mapper.convertValue(endpointMap, HttpEndpoint.class);
            String domain = extractDomain(endpoint.getUrl());
            endpoint.setDomain(domain);
            listOfEndpoints.add(endpoint);

        }
        return listOfEndpoints;
    }
}
