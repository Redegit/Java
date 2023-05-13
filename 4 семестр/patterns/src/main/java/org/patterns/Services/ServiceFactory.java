package org.patterns.Services;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    static Map<String, ServiceUrgency> addSpecs = new HashMap<>();

    public static ServiceUrgency getServiceUrgency(String name, float paymentScale, int timeDays) {
        ServiceUrgency result = addSpecs.get(name);
        if (result == null) {
            result = new ServiceUrgency(name, paymentScale, timeDays);
            addSpecs.put(name, result);
        }
        return result;
    }
    public static ServiceUrgency getServiceUrgencyByName(String name) {
        ServiceUrgency result = addSpecs.get(name);
        if (result == null) {
            result = new ServiceUrgency("none", 1, 0);
            addSpecs.put(name, result);
        }
        return result;
    }
}
