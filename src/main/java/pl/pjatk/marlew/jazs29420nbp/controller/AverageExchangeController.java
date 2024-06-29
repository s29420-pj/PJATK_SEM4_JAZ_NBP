package pl.pjatk.marlew.jazs29420nbp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import pl.pjatk.marlew.jazs29420nbp.repository.AverageExchangeRepository;
import pl.pjatk.marlew.jazs29420nbp.service.AverageExchangeService;

@RestController
@RequestMapping("/jaz-s29420-nbp")
public class AverageExchangeController {
    private final AverageExchangeService averageExchangeService;

    public AverageExchangeController(AverageExchangeRepository averageExchangeRepository, AverageExchangeService averageExchangeService) {
        this.averageExchangeService = averageExchangeService;
    }

    @GetMapping("/avgBetweenDates/{valueName}/{startDate}/{endDate}")
    public ResponseEntity<?> getAverageExchange(@PathVariable String valueName, @PathVariable String startDate, @PathVariable String endDate) {
        try {
            return ResponseEntity.ok(averageExchangeService.saveToDatabase(valueName, startDate, endDate));
        } catch (HttpServerErrorException | HttpClientErrorException error) {
            return new ResponseEntity<>(error.getStatusText(), error.getStatusCode());
        }
    }
}
