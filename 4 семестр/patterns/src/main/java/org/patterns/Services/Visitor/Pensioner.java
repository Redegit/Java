package org.patterns.Services.Visitor;

import org.patterns.Services.Types.BloodTest;
import org.patterns.Services.Types.CovidTest;
import org.patterns.Services.Types.DnaAnalysis;

public class Pensioner implements Visitor {

    public void visit(BloodTest p) {
        p.setDiscount(0.1);
    }

    public void visit(CovidTest p) {
        p.setDiscount(0.8);
    }

    public void visit(DnaAnalysis p) {
        p.setDiscount(0.1);
    }
}
