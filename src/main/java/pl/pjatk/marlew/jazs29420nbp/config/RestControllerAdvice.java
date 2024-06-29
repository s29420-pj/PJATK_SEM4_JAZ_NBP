package pl.pjatk.marlew.jazs29420nbp.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.pjatk.marlew.jazs29420nbp.exceptions.BadRequest;
import pl.pjatk.marlew.jazs29420nbp.exceptions.GatewayTimeout;
import pl.pjatk.marlew.jazs29420nbp.exceptions.InternalServer;
import pl.pjatk.marlew.jazs29420nbp.exceptions.NotFound;


@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<String> badRequest(BadRequest ex){
        return ResponseEntity.status(400)
                .body("Bad Request Exception occurred on request");
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> notFound(NotFound ex){
        return ResponseEntity.status(404)
                .body("Not Found Exception occurred on request");
    }

    @ExceptionHandler(InternalServer.class)
    public ResponseEntity<String> internalError(InternalServer ex){
        return ResponseEntity.status(502)
                .body("My Internal Exception occurred on request");
    }

    @ExceptionHandler(GatewayTimeout.class)
    public ResponseEntity<String> gatewayTimeout(GatewayTimeout ex){
        return ResponseEntity.status(504)
                .body("My Gateway Exception occurred on request");
    }
}
