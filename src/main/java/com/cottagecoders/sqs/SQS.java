package com.cottagecoders.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;

import java.util.ArrayList;
import java.util.List;

public class SQS {

  public static void main(String... args) {
    final String BASE_NAME = "bob-test";
    final int NUM_QUEUES = 5;
    final int NUM_MESSAGES = 10;
    final int NUM_BATCHES = 400;
    final long start = System.currentTimeMillis();
    final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

    //  make some queues:
    String[] queueUrl = new String[NUM_QUEUES];
    try {
      for (int i = 0; i < NUM_QUEUES; i++) {
        sqs.createQueue(BASE_NAME + i);
        queueUrl[i] = sqs.getQueueUrl(BASE_NAME + i).getQueueUrl();
      }
    } catch (AmazonSQSException ex) {
      System.out.println("exception: " + ex.getMessage());
      if (!ex.getErrorCode().equals("QueueAlreadyExists")) {
        throw ex;
      }
    }

    // make some data (SQS only supports 10 per batch):
    final List<SendMessageBatchRequestEntry> entries = new ArrayList<>();
    for (int i = 0; i < NUM_MESSAGES; i++) {
      entries.add(new SendMessageBatchRequestEntry("msg" + i, i + " Hello World message."));
    }

    // batch post to queue
    for (int j = 0; j < NUM_BATCHES; j++) {
      for (int i = 0; i < NUM_QUEUES; i++) {
        SendMessageBatchRequest batch = new SendMessageBatchRequest(queueUrl[i], entries);
        sqs.sendMessageBatch(batch);
      }
    }
    final long elapsed = System.currentTimeMillis() - start;
    System.out.println("elapsed ms " + elapsed);
  }
}
