package pl.pjatk.marlew.jazs29420nbp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "nbp_repository")
@Schema(description = "AverageExchange reprezentuje model obiektu zapisywanego do bazy danych po każdym wykonanym zapytaniu")
public class AverageExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unikalny ID wykonanego zapytania", example = "1")
    private Integer id;

    @Schema(description = "Kod waluty", example = "USD")
    private String valueName;

    @Schema(description = "Początkowa data wykonywania zapytania od kiedy ma pobrać dane nbp.api.pl (YYYY-MM-DD)", example = "2024-06-10")
    private String startDate;

    @Schema(description = "Końcowa data wykonywania zapytania do kiedy ma pobrać dane nbp.api.pl (YYYY-MM-DD) - DATA NIE MOŻE BYC WCZESNIEJSZA NIZ startDate", example = "2024-06-15")
    private String endDate;

    @Schema(description = "Średnia wartość waluty w zadanym okresie czasu", example = "5.12572")
    private double average;

    @Schema(description = "Data i godzina wykonanego zapytania", example = "2024-06-29T18:17:14.896992")
    private String queryDate;

    public AverageExchange() {}

    public AverageExchange(Integer id, String valueName, String startDate, String endDate, double average, String queryDate) {
        this.id = id;
        this.valueName = valueName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.average = average;
        this.queryDate = queryDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }
}
