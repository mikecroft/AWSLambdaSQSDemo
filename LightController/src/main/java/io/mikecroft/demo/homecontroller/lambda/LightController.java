package io.mikecroft.demo.homecontroller.lambda;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

/**
 * Created by mike on 10/10/17.
 */
public class LightController implements RequestHandler<Object, Object> {

    @Override
    public Object handleRequest(Object input, Context context) {
        return sendMessage(input.toString(), "lights");
    }

    private String sendMessage(String msg, String q) {
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .build();

        CreateQueueRequest createQueueRequest = new CreateQueueRequest(q);
        String myQueueURL = sqs.createQueue(createQueueRequest).getQueueUrl();

        System.out.println("Sending msg '" + msg + "' to Q: " + myQueueURL);

        SendMessageResult smr = sqs.sendMessage(new SendMessageRequest()
                .withQueueUrl(myQueueURL)
                .withMessageBody(msg));

        return "SendMessage succeeded with messageId " + smr.getMessageId()
                + ", sequence number " + smr.getSequenceNumber() + "\n";
    }
}

