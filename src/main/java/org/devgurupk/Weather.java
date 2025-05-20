package org.devgurupk;

import java.util.Map;

import org.devgurupk.client.WeatherClient;
import org.devgurupk.service.WeatherFormatter;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import io.quarkus.qute.Qute;
import jakarta.inject.Inject;

public class Weather {

  @RestClient
  WeatherClient weatherClient;
  
  @Inject
  WeatherFormatter weatherFormatter;

  @Tool(description = "Get weather alerts for a US state.")
  String getAlerts(@ToolArg(description = "Two-letter US state code (e.g. CA, NY)") String state) {
    return weatherFormatter.formatAlerts(weatherClient.getAlerts(state));
  }

  @Tool(description = "Get weather forecast for a location.")
  String getForecast(@ToolArg(description = "Latitude of the location") double latitude,
                     @ToolArg(description = "Longitude of the location") double longitude) {
    var points = weatherClient.getPoints(latitude, longitude);
    var url = Qute.fmt("{p.properties.forecast}", Map.of("p", points));

    return weatherFormatter.formatForecast(weatherClient.getForecast(url));
  }
}