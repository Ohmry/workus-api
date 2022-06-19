package workus.api.sign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SignController {
    private final SignService signService;

    public SignController (SignService signService) {
        this.signService = signService;
    }
}
