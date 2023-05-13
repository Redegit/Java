package org.patterns.Services.Visitor;

import org.patterns.Services.Service;
import org.patterns.Services.Types.BloodTest;
import org.patterns.Services.Types.CovidTest;
import org.patterns.Services.Types.DnaAnalysis;

public interface Visitor {
    public void visit(BloodTest p);

    public void visit(CovidTest p);

    public void visit(DnaAnalysis p);
}
