package pl.pjatk.marlew.jazs29420nbp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nbp_repository")
public class AverageExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String valueName;
    private String startDate;
    private String endDate;
    private double average;
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
