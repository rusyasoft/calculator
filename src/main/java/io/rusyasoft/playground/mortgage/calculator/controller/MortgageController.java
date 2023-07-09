package io.rusyasoft.playground.mortgage.calculator.controller;

import io.rusyasoft.playground.mortgage.calculator.model.InputParameters;
import io.rusyasoft.playground.mortgage.calculator.model.PaymentPeriod;
import io.rusyasoft.playground.mortgage.calculator.service.MortgageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mortgage/")
@AllArgsConstructor
public class MortgageController {

    private MortgageService mortgageService;

    @GetMapping("/health")
    public String healthCheck() {
        return "Healthy Service is running";
    }

    @GetMapping("/calculate")
    public Double calculate(
            @RequestParam(name = "propertyPrice")
            Long propertyPrice,

            @RequestParam(name = "downPayment")
            Long downPayment,

            @RequestParam(name = "annualInterest")
            Double annualInterest,

            @RequestParam(name = "ammortPeriod")
            Integer ammortPeriod,

            @RequestParam(name = "paymentSchedule")
            String paymentSchedule
    ) {
        InputParameters inputParameters = InputParameters.builder()
                .propertyPrice(propertyPrice)
                .downPayment(downPayment)
                .annualInterest(annualInterest)
                .ammortPeriod(ammortPeriod)
                .paymentSchedule(PaymentPeriod.valueOfLabel(paymentSchedule))
                .build();

        return mortgageService.calculate(inputParameters);
    }
}
