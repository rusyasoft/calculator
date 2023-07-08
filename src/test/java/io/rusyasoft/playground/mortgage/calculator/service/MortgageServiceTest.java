package io.rusyasoft.playground.mortgage.calculator.service;

import io.rusyasoft.playground.mortgage.calculator.InputParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MortgageServiceTest {
    private static final Long PROPERTY_PRICE = 800_000L;
    private static final Long DOWN_PAYMENT = 400_000L;
    private static final Double ANNUAL_INTEREST = 0.06;

    private static final Double ANNUAL_INTEREST_IN_PERCENTILE = 6.0;
    private static final Integer AMMORT_PERIOD = 20;
    private static final String PAYMENT_SCHEDULE = "monthly";

    @Autowired
    MortgageService mortgageService;
    @Test
    public void calculateTest() {
        InputParameters inputParameters = InputParameters.builder()
                .propertyPrice(PROPERTY_PRICE)
                .downPayment(DOWN_PAYMENT)
                .annualInterest(ANNUAL_INTEREST)
                .ammortPeriod(AMMORT_PERIOD)
                .paymentSchedule(PAYMENT_SCHEDULE)
                .build();

        Double paymentPerPaymentSchedule = mortgageService.calculate(inputParameters);
        Assertions.assertEquals(2865.7242339126915, paymentPerPaymentSchedule);
    }

    @Test
    public void calculateWithPercentileTest() {
        InputParameters inputParameters = InputParameters.builder()
                .propertyPrice(PROPERTY_PRICE)
                .downPayment(DOWN_PAYMENT)
                .annualInterest(ANNUAL_INTEREST_IN_PERCENTILE)
                .ammortPeriod(AMMORT_PERIOD)
                .paymentSchedule(PAYMENT_SCHEDULE)
                .build();

        Double paymentPerPaymentSchedule = mortgageService.calculate(inputParameters);
        Assertions.assertEquals(2865.7242339126915, paymentPerPaymentSchedule);
    }
}
