package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillplugins.json.UserFileLoader;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.swagger.model.UserData;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Duration;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-11T10:50:00.847Z[GMT]")
@RestController
public class UserAuthApiController implements UserAuthApi {

    private static final Logger log = LoggerFactory.getLogger(UserAuthApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final Bucket bucket = Bucket4j.builder().addLimit(Bandwidth.classic(5,
            Refill.greedy(20, Duration.ofMinutes(1)))).build();

    private final UserFileLoader userFileLoader = new UserFileLoader();

    @org.springframework.beans.factory.annotation.Autowired
    public UserAuthApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        userFileLoader.loadFile();
    }

    public ResponseEntity<Void> userAuth(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody UserData body) {
        if (bucket.tryConsume(1)) {
            return new ResponseEntity<Void>(userFileLoader.addUserData(body));
        }
        return new ResponseEntity<Void>(HttpStatus.TOO_MANY_REQUESTS);
    }

}
