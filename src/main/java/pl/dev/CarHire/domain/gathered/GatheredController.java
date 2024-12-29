package pl.dev.CarHire.domain.gathered;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dev.CarHire.domain.gathered.payload.HomeParameters;

@RestController
@RequiredArgsConstructor
public class GatheredController {
    private final GatheredService gatheredService;

    @GetMapping("/homeParams")
    public HomeParameters getHomeParameters() {
        return gatheredService.getHomeParams();
    }
}
