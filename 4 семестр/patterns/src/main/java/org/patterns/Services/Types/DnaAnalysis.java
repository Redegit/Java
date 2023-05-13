package org.patterns.Services.Types;

import org.patterns.Services.Service;
import org.patterns.Services.ServiceTypeDecorator;
import org.patterns.Services.ServiceUrgency;
import org.patterns.Services.Visitor.Visitor;

public class DnaAnalysis extends ServiceTypeDecorator {

    public DnaAnalysis(Service service) {
        super(service, "Анализ ДНК", 3000, service.getUrgency());
    }

    public void takeSample() {
        System.out.println("Взятие пробы биоматериала...");
    }


    public void accept ( Visitor v ) {
        v.visit( this );
    }
}
