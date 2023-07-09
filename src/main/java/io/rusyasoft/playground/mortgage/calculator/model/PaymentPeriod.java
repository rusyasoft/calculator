package io.rusyasoft.playground.mortgage.calculator.model;

import java.util.HashMap;
import java.util.Map;

public enum PaymentPeriod {
    MONTHLY("monthly", 12),
    SEMI_MONTHLY("semi-monthly", 24),
    BI_WEEKLY("bi-weekly", 26),
    WEEKLY("weekly", 52);

    private static final Map<String, PaymentPeriod> byLabel = new HashMap<>();

    static {
        for (PaymentPeriod e : values()) {
            byLabel.put(e.label, e);
        }
    }

    public final String label;
    public final int numOfPayments;

    private PaymentPeriod(String label, int numOfPayments) {
        this.label = label;
        this.numOfPayments = numOfPayments;
    }

    public static PaymentPeriod valueOfLabel(String label) {
        if (!byLabel.containsKey(label)) {
            throw new IllegalArgumentException("label is not recognized!");
        }

        return byLabel.get(label);
    }
}
