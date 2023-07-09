package io.rusyasoft.playground.mortgage.calculator.engine.temperature;

import io.rusyasoft.playground.mortgage.calculator.engine.CalcResult;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TemperatureCalcResult implements CalcResult {
    private boolean calculationSucceeded;
    private Double result;
    @Override
    public boolean isCalculationSuccessfull() {
        return calculationSucceeded;
    }
}
