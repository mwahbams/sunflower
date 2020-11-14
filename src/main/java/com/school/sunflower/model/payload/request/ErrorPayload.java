package com.school.sunflower.model.payload.request;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Map;

@Value
@SuperBuilder
public class ErrorPayload {

  private String enMessage;
  private String arMessage;
  private String type;
  @Builder.Default
  private LocalDateTime timestamp = LocalDateTime.now();
  private Map<String, Object> details;
}