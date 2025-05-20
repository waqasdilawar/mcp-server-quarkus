package org.devgurupk.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.devgurupk.model.Alerts;
import org.devgurupk.model.Forecast;

import io.quarkus.qute.Qute;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeatherFormatter {

  public String formatForecast(Forecast forecast) {
    return forecast.properties().periods().stream().map(period -> {
      return Qute.fmt(
        """
        Temperature: {p.temperature}°{p.temperatureUnit}
        Wind: {p.windSpeed} {p.windDirection}
        Forecast: {p.detailedForecast}
        """,
        Map.of("p", period)
      );
    }).collect(Collectors.joining("\n---\n"));
  }

  public String formatAlerts(Alerts alerts) {
    return alerts.features().stream().map(feature -> {
      return Qute.fmt(
        """
        Event: {p.event}
        Area: {p.areaDesc}
        Severity: {p.severity}
        Description: {p.description}
        Instructions: {p.instruction}
        """,
        Map.of("p", feature.properties())
      );
    }).collect(Collectors.joining("\n---\n"));
  }
}
