package io.rusyasoft.playground.mortgage.calculator.engine.temperature;

import io.rusyasoft.playground.mortgage.calculator.engine.CalcResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TemperatureCalcResult implements CalcResult {
    private boolean calculationSucceeded;
    @Getter
    private Double result;
    @Override
    public boolean isCalculationSuccessfull() {
        return calculationSucceeded;
    }
}
