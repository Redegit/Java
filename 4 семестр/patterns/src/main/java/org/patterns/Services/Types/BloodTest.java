package org.patterns.Services.Types;

import com.sun.source.util.DocTreeFactory;
import org.patterns.Services.Service;
import org.patterns.Services.ServiceFactory;
import org.patterns.Services.ServiceTypeDecorator;
import org.patterns.Services.ServiceUrgency;
import org.patterns.Services.Visitor.Visitor;

public class BloodTest extends ServiceTypeDecorator {
    public BloodTest(Service service) {
        super(service, "Анализ крови", 500, service.getUrgency());
    }

    public void takeSample() {
        System.out.println("Взятие пробы крови...");
    }

    public void accept ( Visitor v ) {
        v.visit( this );
    }
}
