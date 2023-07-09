package io.rusyasoft.playground.mortgage.calculator.model;

import lombok.Builder;
@Builder
public record InputParameters(
        Long propertyPrice,
        Long downPayment,
        Double annualInterest,
        Integer ammortPeriod,
        PaymentPeriod paymentSchedule
) {
}
