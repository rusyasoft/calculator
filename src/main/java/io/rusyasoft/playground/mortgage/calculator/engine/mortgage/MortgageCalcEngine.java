package io.rusyasoft.playground.mortgage.calculator.engine.mortgage;

import io.rusyasoft.playground.mortgage.calculator.engine.CalcEngine;
import io.rusyasoft.playground.mortgage.calculator.model.InputParameters;
import org.springframework.stereotype.Component;

import java.util.IllformedLocaleException;

@Component
public class MortgageCalcEngine implements CalcEngine<InputParameters, MortgageCalcResult> {

    public static final String MORTGAGE_CALC_ENGINE = "mortgageCalcEngine";
    @Override
    public MortgageCalcResult calculate(InputParameters inputParameters) {
        try {
            double principal = (double)inputParameters.propertyPrice() - (double)inputParameters.downPayment();
            double numOfPaymentPerYear = inputParameters.paymentSchedule().numOfPayments;
            double interestRate = adaptPercentile(inputParameters.annualInterest());
            double intRatePerPaymentSchedule = getInterestRatePerPaymentSchedule(interestRate, numOfPaymentPerYear);
            double totalNumOfPayments = inputParameters.ammortPeriod() * numOfPaymentPerYear;

            Double onePlusNpow = Math.pow(intRatePerPaymentSchedule + 1.0, totalNumOfPayments);
            double intRatePerPaymentScheduleMultOnePlusNpow = intRatePerPaymentSchedule * onePlusNpow;
            Double paymentPerSchedule = (principal * intRatePerPaymentScheduleMultOnePlusNpow) / (onePlusNpow - 1.0);

            if (paymentPerSchedule < 0.0) {
                throw new IllformedLocaleException("Payment per schedule is not suppose to be negative");
            }

            return new MortgageCalcResult(true, paymentPerSchedule);
        } catch (Exception ex) {
            return new MortgageCalcResult(false, 0.0);
        }
    }

    private Double getInterestRatePerPaymentSchedule(double interestRate, double numOfPaymentPerYear) {
        return interestRate / numOfPaymentPerYear;
    }

    private double adaptPercentile(double notAdaptedPercentile) {
        if (notAdaptedPercentile >= 1.0) {
            return notAdaptedPercentile / 100.0;
        }

        return notAdaptedPercentile;
    }
}
