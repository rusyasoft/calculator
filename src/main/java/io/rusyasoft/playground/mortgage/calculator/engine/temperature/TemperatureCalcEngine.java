package io.rusyasoft.playground.mortgage.calculator.engine.temperature;

import io.rusyasoft.playground.mortgage.calculator.engine.CalcEngine;
import org.springframework.stereotype.Component;

@Component
public class TemperatureCalcEngine implements CalcEngine<Double, TemperatureCalcResult> {
    @Override
    public TemperatureCalcResult calculate(Double celsius) {
        try {
            Double result = ((celsius * 9.0) / 5.0) + 32.0;
            return new TemperatureCalcResult(true, result);
        } catch (Exception ex) {
            return new TemperatureCalcResult(false, 0.0);
        }
    }
}
