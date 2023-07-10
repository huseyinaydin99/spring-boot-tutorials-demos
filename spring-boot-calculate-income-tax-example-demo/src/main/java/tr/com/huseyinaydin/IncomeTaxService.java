package tr.com.huseyinaydin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot Examples
 * @since 1994
 **/

@Slf4j
@Service
public class IncomeTaxService {

    public IncomeTaxResponse calculatingIncomeTax(IncomeTaxRequest request) {

        BigDecimal salaryInKhr = this.checkingSalaryBaseCurrency(request.getCurrency(), request.getSalary(), request.getExchangeRate());
        IncomeTaxResponse incomeTaxResponse = new IncomeTaxResponse();
        if (request.isResident()) {
            incomeTaxResponse = this.incomeTaxResponseResident(salaryInKhr);
        } else {
            incomeTaxResponse = this.incomeTaxResponseNonResident(salaryInKhr);
        }
        incomeTaxResponse = this.incomeTaxResponseBenefit(incomeTaxResponse, request);

        BigDecimal netSalaryInUsd = this.convertToDollar(incomeTaxResponse.getNetSalaryInKhr(),
                BigDecimal.valueOf(request.getExchangeRate()));
        incomeTaxResponse.setNetSalaryInUsd(netSalaryInUsd.setScale(2, RoundingMode.UP));
        incomeTaxResponse.setNetSalaryInKhr(incomeTaxResponse.getNetSalaryInKhr().setScale(0, RoundingMode.UP));

        BigDecimal taxInUsd = this.convertToDollar(incomeTaxResponse.getTaxOnSalaryInKhr(),
                BigDecimal.valueOf(request.getExchangeRate()));
        incomeTaxResponse.setTaxOnSalaryInUsd(taxInUsd.setScale(2, RoundingMode.UP));
        incomeTaxResponse.setTaxOnSalaryInKhr(incomeTaxResponse.getTaxOnSalaryInKhr().setScale(0, RoundingMode.UP));

        incomeTaxResponse.setTotalTaxInUsd(incomeTaxResponse.getTaxOnSalaryInUsd()
                .add(incomeTaxResponse.getTaxOnFringeBenefitInUsd().setScale(2, RoundingMode.UP)));
        return incomeTaxResponse;
    }

    protected IncomeTaxResponse incomeTaxResponseBenefit(IncomeTaxResponse response, IncomeTaxRequest request) {
        BigDecimal benefitTax = new BigDecimal(0);
        if (request.getFringeBenefit().compareTo(benefitTax) > 0) {
            BigDecimal benefitInKhr = this.checkingSalaryBaseCurrency(request.getCurrency(), request.getFringeBenefit(),
                    request.getExchangeRate());
            benefitTax = benefitInKhr.multiply(new BigDecimal("0.2"));
            BigDecimal netBenefit = benefitInKhr.subtract(benefitTax);
            response.setNetSalaryInKhr(response.getNetSalaryInKhr().add(netBenefit));
        }
        response.setTaxOnFringeBenefitInKhr(benefitTax);
        BigDecimal benefitTaxInUsd = this.convertToDollar(benefitTax, BigDecimal.valueOf(request.getExchangeRate()));
        response.setTaxOnFringeBenefitInUsd(benefitTaxInUsd);
        return response;
    }

    protected IncomeTaxResponse incomeTaxResponseNonResident(BigDecimal salary) {
        return this.incomeTaxResponse(salary, 0.2, "20%", null, false);
    }

    protected IncomeTaxResponse incomeTaxResponseResident(BigDecimal salary) {

        IncomeTaxResponse response = new IncomeTaxResponse();
        if (salary.compareTo(new BigDecimal(130_0000)) <= 0) {
            response.setIncomeTaxResponse(salary, new BigDecimal(0), "0%");
            return response;
        }

        if (salary.compareTo(new BigDecimal(130_0001)) >= 0 &&
                salary.compareTo(new BigDecimal(200_0000)) <= 0) {
            return this.incomeTaxResponse(salary, 0.05, "5%", 65_000.0, true);
        }

        if (salary.compareTo(new BigDecimal(200_0001)) >= 0 &&
                salary.compareTo(new BigDecimal(850_0000)) <= 0) {
            return this.incomeTaxResponse(salary, 0.1, "10%", 165_000.0, true);
        }

        if (salary.compareTo(new BigDecimal(850_0001)) >= 0 &&
                salary.compareTo(new BigDecimal(125_000_00)) <= 0) {
            return this.incomeTaxResponse(salary, 0.15, "15%", 590_000.0, true);
        }

        if (salary.compareTo(new BigDecimal(125_000_01)) >= 0) {
            return this.incomeTaxResponse(salary, 0.2, "20%", 1215_000.0, true);
        }
        return response;
    }

    protected IncomeTaxResponse incomeTaxResponse(BigDecimal salary, Double tax, String taxPercentage,
                                                  Double taxBase, boolean resident) {
        IncomeTaxResponse response = new IncomeTaxResponse();
        BigDecimal taxOnSalary;
        if (resident) {
            taxOnSalary = salary.multiply(new BigDecimal(tax)).subtract(new BigDecimal(taxBase));
        } else {
            taxOnSalary = salary.multiply(new BigDecimal(tax));
        }
        BigDecimal netIncome = salary.subtract(taxOnSalary);
        response.setIncomeTaxResponse(netIncome, taxOnSalary, taxPercentage);
        return response;
    }

    protected BigDecimal checkingSalaryBaseCurrency(String ccy, BigDecimal amount, Double exchangeRate) {
        if (this.checkingUsdCurrency(ccy)) {
            return this.convertToRiel(amount, new BigDecimal(exchangeRate));
        }
        return amount;
    }

    protected BigDecimal convertToRiel(BigDecimal amount, BigDecimal exchangeRate) {
        return amount.multiply(exchangeRate);
    }

    protected BigDecimal convertToDollar(BigDecimal amount, BigDecimal exchangeRate) {
        return amount.divide(exchangeRate, RoundingMode.CEILING);
    }

    protected boolean checkingUsdCurrency(String ccy) {
        return !"KHR".equalsIgnoreCase(ccy);
    }
}