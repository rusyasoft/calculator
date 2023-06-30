package io.rusyasoft.playground.mortgage.calculator.service;

import io.rusyasoft.playground.mortgage.calculator.InputParameters;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MortgageService {

    private Map<String, Integer> numOfPaymentsPerYear;

    public MortgageService() {
        fillNumOfPaymentsPerYear();

    }
    public Double calculate(InputParameters inputParameters) {
        double principal = (double)inputParameters.propertyPrice() - (double)inputParameters.downPayment();

        double numOfPaymentPerYear = numOfPaymentsPerYear.get(inputParameters.paymentSchedule());

        Double intRatePerPaymentSchedule = inputParameters.annualInterest() / numOfPaymentPerYear;
        double totalNumOfPayments = inputParameters.ammortPeriod() * numOfPaymentPerYear;

        Double onePlusNpow = Math.pow(intRatePerPaymentSchedule + 1.0, totalNumOfPayments);
        double intRatePerPaymentScheduleMultOnePlusNpow = intRatePerPaymentSchedule * onePlusNpow;
        Double paymentPerSchedule = (principal * intRatePerPaymentScheduleMultOnePlusNpow) / (onePlusNpow - 1.0);
        return paymentPerSchedule;
    }

    private void fillNumOfPaymentsPerYear() {
        numOfPaymentsPerYear = new HashMap<>();
        numOfPaymentsPerYear.put("monthly", 12);
        numOfPaymentsPerYear.put("semi-monthly", 24);
        numOfPaymentsPerYear.put("bi-weekly", 26);
        numOfPaymentsPerYear.put("weekly", 52);
    }
}
