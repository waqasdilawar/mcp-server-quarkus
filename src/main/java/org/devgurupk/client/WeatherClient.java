package org.devgurupk.client;

import java.util.Map;

import org.devgurupk.model.Alerts;
import org.devgurupk.model.Forecast;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestPath;

import io.quarkus.rest.client.reactive.Url;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@RegisterRestClient(baseUri = "https://api.weather.gov")
@ClientHeaderParam(name = "User-Agent", value = "weather-mcp-server/1.0")
public interface WeatherClient {
    @GET
    @Path("/alerts/active/area/{state}")
    Alerts getAlerts(@RestPath String state);

    @GET
    @Path("/points/{latitude},{longitude}")
    Map<String, Object> getPoints(@RestPath double latitude, @RestPath double longitude);

    @GET
    @Path("/")
    Forecast getForecast(@Url String url);
}
