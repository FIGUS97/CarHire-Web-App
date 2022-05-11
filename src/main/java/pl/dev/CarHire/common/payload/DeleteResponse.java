package pl.dev.CarHire.common.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteResponse {

  private Long id;
  private String message;
}
