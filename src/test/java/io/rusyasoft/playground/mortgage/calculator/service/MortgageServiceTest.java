package io.rusyasoft.playground.mortgage.calculator.service;

import io.rusyasoft.playground.mortgage.calculator.InputParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MortgageServiceTest {
    private final Long propertyPrice = 800_000L;
    private final Long downPayment = 400_000L;
    private final Double annualInterest = 0.06;
    private final Integer ammortPeriod = 20;
    private final String paymentSchedule = "monthly";

    @Autowired
    MortgageService mortgageService;
    @Test
    public void calculateTest() {
        InputParameters inputParameters = InputParameters.builder()
                .propertyPrice(propertyPrice)
                .downPayment(downPayment)
                .annualInterest(annualInterest)
                .ammortPeriod(ammortPeriod)
                .paymentSchedule(paymentSchedule)
                .build();

        Double paymentPerPaymentSchedule = mortgageService.calculate(inputParameters);
        Assertions.assertEquals(2865.7242339126915, paymentPerPaymentSchedule);
    }
}
