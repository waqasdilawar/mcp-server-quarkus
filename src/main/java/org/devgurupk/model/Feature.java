package org.devgurupk.model;

public record Feature(
  String id,
  String type,
  Object geometry,
  Properties properties) {
}
