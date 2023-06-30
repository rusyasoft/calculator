package io.rusyasoft.playground.mortgage.calculator.controller;

import io.rusyasoft.playground.mortgage.calculator.InputParameters;
import io.rusyasoft.playground.mortgage.calculator.service.MortgageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mortgage/")
@RequiredArgsConstructor
public class MortgageController {

    private MortgageService mortgageService;

    @GetMapping("/health")
    public String healthCheck() {
        return "Healthy Service is running";
    }

    @GetMapping("/calculate")
    public Double calculate(
            @RequestParam(name = "propertyPrice", required = true)
            Long propertyPrice,

            @RequestParam(name = "downPayment", required = true)
            Long downPayment,

            @RequestParam(name = "annualInterest", required = true)
            Double annualInterest,

            @RequestParam(name = "ammortPeriod", required = true)
            Integer ammortPeriod,

            @RequestParam(name = "paymentSchedule", required = true)
            String paymentSchedule
    ) {
        InputParameters inputParameters = InputParameters.builder()
                .propertyPrice(propertyPrice)
                .downPayment(downPayment)
                .annualInterest(annualInterest)
                .ammortPeriod(ammortPeriod)
                .paymentSchedule(paymentSchedule)
                .build();

        return mortgageService.calculate(inputParameters);
    }
}
