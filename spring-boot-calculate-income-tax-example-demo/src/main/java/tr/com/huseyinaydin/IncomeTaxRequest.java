package tr.com.huseyinaydin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@Data
public class IncomeTaxRequest {

    @JsonProperty("resident")
    private boolean resident;
    @JsonProperty("salary")
    private BigDecimal salary;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("exchange_rate")
    private Double exchangeRate;
    @JsonProperty("fringe_benefit")
    private BigDecimal fringeBenefit;
    @JsonProperty("spouse")
    private boolean spouse;
    @JsonProperty("total_dependent")
    private int totalDependent;
}