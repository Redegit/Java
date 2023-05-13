package org.patterns.Services.Types;

import org.patterns.Services.Service;
import org.patterns.Services.ServiceTypeDecorator;
import org.patterns.Services.ServiceUrgency;
import org.patterns.Services.Visitor.Visitor;

public class CovidTest extends ServiceTypeDecorator {
    public CovidTest(Service service) {
        super(service, "Диагностика коронавируса COVID-19", 1000, service.getUrgency());
    }


    public void accept ( Visitor v ) {
        v.visit( this );
    }
}
