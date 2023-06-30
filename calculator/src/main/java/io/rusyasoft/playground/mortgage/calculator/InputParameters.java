package io.rusyasoft.playground.mortgage.calculator;

import lombok.Builder;
@Builder
public record InputParameters(
        Long propertyPrice,
        Long downPayment,
        Double annualInterest,
        Integer ammortPeriod,
        String paymentSchedule
) {
}
