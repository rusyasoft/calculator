package io.rusyasoft.playground.mortgage.calculator.service;

import io.rusyasoft.playground.mortgage.calculator.engine.CalcEngine;
import io.rusyasoft.playground.mortgage.calculator.engine.CalcResult;
import io.rusyasoft.playground.mortgage.calculator.model.InputParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MortgageService {

    @Autowired
    Map<String, CalcEngine> engineContainer;

    public CalcResult calculate(String engine, InputParameters inputParameters) {
        return engineContainer.get(engine).calculate(inputParameters);
    }
}
