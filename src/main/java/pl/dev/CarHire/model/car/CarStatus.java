package pl.dev.CarHire.model.car;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Status
 */
public enum CarStatus {
    RENTED("RENTED"),
    FREE("FREE"),
    SERVICE("SERVICE");

  private String value;

  CarStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CarStatus fromValue(String text) {
    for (CarStatus b : CarStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
