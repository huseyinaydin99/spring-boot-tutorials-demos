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
public class IncomeTaxResponse {

    @JsonProperty("net_salary_usd")
    private BigDecimal netSalaryInUsd;

    @JsonProperty("net_salary_khr")
    private BigDecimal netSalaryInKhr;

    @JsonProperty("tax_on_salary_usd")
    private BigDecimal taxOnSalaryInUsd;

    @JsonProperty("tax_on_salary_khr")
    private BigDecimal taxOnSalaryInKhr;

    @JsonProperty("tax_on_fringe_benefit_usd")
    private BigDecimal taxOnFringeBenefitInUsd;

    @JsonProperty("tax_on_fringe_benefit_khr")
    private BigDecimal taxOnFringeBenefitInKhr;

    @JsonProperty("total_tax_usd")
    private BigDecimal totalTaxInUsd;

    @JsonProperty("tax_rate")
    private String taxRate;

    public void setIncomeTaxResponse(BigDecimal netSalary, BigDecimal taxOnSalary, String taxRate) {
        this.netSalaryInKhr = netSalary;
        this.taxOnSalaryInKhr = taxOnSalary;
        this.taxRate = taxRate;
    }
}
