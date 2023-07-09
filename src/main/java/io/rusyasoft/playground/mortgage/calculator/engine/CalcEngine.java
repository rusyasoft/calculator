package io.rusyasoft.playground.mortgage.calculator.engine;

public interface CalcEngine <T, R extends CalcResult> {
    R calculate(T inputParameters);
}
