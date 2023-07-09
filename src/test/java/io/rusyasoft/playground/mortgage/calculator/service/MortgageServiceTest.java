package io.rusyasoft.playground.mortgage.calculator.service;

import io.rusyasoft.playground.mortgage.calculator.engine.mortgage.MortgageCalcResult;
import io.rusyasoft.playground.mortgage.calculator.model.InputParameters;
import io.rusyasoft.playground.mortgage.calculator.model.PaymentPeriod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.rusyasoft.playground.mortgage.calculator.engine.mortgage.MortgageCalcEngine.MORTGAGE_CALC_ENGINE;

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
                .paymentSchedule(PaymentPeriod.valueOfLabel(PAYMENT_SCHEDULE))
                .build();

        MortgageCalcResult mortgageCalcResult = (MortgageCalcResult) mortgageService.calculate(MORTGAGE_CALC_ENGINE, inputParameters);
        Assertions.assertTrue(mortgageCalcResult.isCalculationSuccessfull());
        Assertions.assertEquals(2865.7242339126915, mortgageCalcResult.getResult());
    }

    @Test
    public void calculateWithPercentileTest() {
        InputParameters inputParameters = InputParameters.builder()
                .propertyPrice(PROPERTY_PRICE)
                .downPayment(DOWN_PAYMENT)
                .annualInterest(ANNUAL_INTEREST_IN_PERCENTILE)
                .ammortPeriod(AMMORT_PERIOD)
                .paymentSchedule(PaymentPeriod.valueOfLabel(PAYMENT_SCHEDULE))
                .build();

        MortgageCalcResult mortgageCalcResult = (MortgageCalcResult) mortgageService.calculate(MORTGAGE_CALC_ENGINE, inputParameters);
        Assertions.assertTrue(mortgageCalcResult.isCalculationSuccessfull());
        Assertions.assertEquals(2865.7242339126915, mortgageCalcResult.getResult());
    }
}
