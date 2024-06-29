package pl.pjatk.marlew.jazs29420nbp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.marlew.jazs29420nbp.exceptions.BadRequest;
import pl.pjatk.marlew.jazs29420nbp.exceptions.GatewayTimeout;
import pl.pjatk.marlew.jazs29420nbp.exceptions.InternalServer;
import pl.pjatk.marlew.jazs29420nbp.exceptions.NotFound;
import pl.pjatk.marlew.jazs29420nbp.model.AverageExchange;
import pl.pjatk.marlew.jazs29420nbp.model.QueryNBP;
import pl.pjatk.marlew.jazs29420nbp.model.Rate;
import pl.pjatk.marlew.jazs29420nbp.repository.AverageExchangeRepository;

import java.time.LocalDateTime;

@Service
public class AverageExchangeService {

    private final RestTemplate restTemplate;
    private final AverageExchangeRepository averageExchangeRepository;

    public AverageExchangeService(RestTemplate restTemplate, AverageExchangeRepository averageExchangeRepository) {
        this.restTemplate = restTemplate;
        this.averageExchangeRepository = averageExchangeRepository;
    }

    public double getAverage(String startDate, String endDate, String valueName) {
        try {
            QueryNBP NBP = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/A/" + valueName + "/" + startDate + "/" + endDate + "/", QueryNBP.class);
            return NBP.getRates()
                    .stream()
                    .mapToDouble(Rate::getMid)
                    .average()
                    .orElseThrow();
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NotFound();
        } catch (HttpClientErrorException.BadRequest exception) {
            throw new BadRequest();
        } catch (HttpServerErrorException.InternalServerError exception) {
            throw new InternalServer();
        } catch (HttpServerErrorException.GatewayTimeout exception) {
            throw new GatewayTimeout();
        }
    }

    public AverageExchange saveToDatabase(String valueName, String startDate, String endDate) {
        try {
            AverageExchange averageExchange = new AverageExchange();
            averageExchange.setValueName(valueName);
            averageExchange.setStartDate(startDate);
            averageExchange.setEndDate(endDate);
            averageExchange.setAverage(getAverage(startDate, endDate, valueName));
            averageExchange.setQueryDate(String.valueOf(LocalDateTime.now()));

            return averageExchangeRepository.save(averageExchange);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFound();
        } catch (HttpClientErrorException.BadRequest b) {
            throw new BadRequest();
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServer();
        } catch (HttpServerErrorException.GatewayTimeout m) {
            throw new GatewayTimeout();
        }
    }
}
