package io.rusyasoft.playground.mortgage.calculator.service;

import io.rusyasoft.playground.mortgage.calculator.engine.CalcEngine;
import io.rusyasoft.playground.mortgage.calculator.engine.CalcResult;
import io.rusyasoft.playground.mortgage.calculator.model.InputParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MortgageService {

    @Autowired
    Map<String, CalcEngine> engineContainer;

    public CalcResult calculate(String engine, InputParameters inputParameters) {
        if (engineContainer.containsKey(engine)) {
            return engineContainer.get(engine).calculate(inputParameters);
        }

        throw new IllegalArgumentException("Engine does not exist in container!");
    }
}
