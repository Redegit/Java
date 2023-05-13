package org.patterns.Services.Visitor;

import org.patterns.Services.Service;
import org.patterns.Services.Types.BloodTest;
import org.patterns.Services.Types.CovidTest;
import org.patterns.Services.Types.DnaAnalysis;

public class LargeFamily implements Visitor{
    public void visit ( BloodTest p ) {
        p.setDiscount(0.2);
    }
    public void visit ( CovidTest p ) {
        p.setDiscount(0.5);
    }

    public void visit(DnaAnalysis p) {
        p.setDiscount(0.1);

    }

}
