package io.mikecroft.demo.homecontroller.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Created by mike on 10/10/17.
 */
public class LightController implements RequestHandler {

    @Override
    public String handleRequest(Object input, Context context) {
        return "Received message: " + input;
    }
}
