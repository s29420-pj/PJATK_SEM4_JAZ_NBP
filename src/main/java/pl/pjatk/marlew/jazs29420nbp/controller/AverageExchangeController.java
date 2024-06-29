package pl.pjatk.marlew.jazs29420nbp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import pl.pjatk.marlew.jazs29420nbp.exceptions.Exception;
import pl.pjatk.marlew.jazs29420nbp.exceptions.*;
import pl.pjatk.marlew.jazs29420nbp.model.AverageExchange;
import pl.pjatk.marlew.jazs29420nbp.repository.AverageExchangeRepository;
import pl.pjatk.marlew.jazs29420nbp.service.AverageExchangeService;

@RestController
@RequestMapping("/jaz-s29420-nbp")
public class AverageExchangeController {
    private final AverageExchangeService averageExchangeService;

    public AverageExchangeController(AverageExchangeRepository averageExchangeRepository, AverageExchangeService averageExchangeService) {
        this.averageExchangeService = averageExchangeService;
    }

    @Operation(summary = "Pobiera zadany przez użytkownika zakres wartości dla konkretnej waluty i zwraca obiekt AverageExchange z obliczną średnią wartością dla danej waluty w tym okresie. Zapisuje zapytanie do bazy danych.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utworzono obiekt i zapisano go do bazy"),
            @ApiResponse(responseCode = "404", description = "Nie znaleziono danych", content = @Content),
            @ApiResponse(responseCode = "400", description = "Nieprawidłowe żądanie", content = @Content),
            @ApiResponse(responseCode = "502", description = "Błąd po stronie serwera", content = @Content)
    })
    @GetMapping("/averageExchange/{valueName}/{startDate}/{endDate}")
    public ResponseEntity<AverageExchange> getAverageExchange(@PathVariable String valueName, @PathVariable String startDate, @PathVariable String endDate) {
        try {
            return ResponseEntity.ok(averageExchangeService.saveToDatabase(valueName, startDate, endDate));
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NotFound();
        } catch (HttpClientErrorException.BadRequest exception) {
            throw new BadRequest();
        } catch (HttpServerErrorException.InternalServerError exception) {
            throw new InternalServer();
        } catch (HttpServerErrorException.GatewayTimeout exception) {
            throw new GatewayTimeout();
        } catch (HttpServerErrorException | HttpClientErrorException exception) {
            throw new Exception();
        }
    }
}
