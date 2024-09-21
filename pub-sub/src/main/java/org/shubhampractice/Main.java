package org.shubhampractice;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        PrintSubscriber s1 = new PrintSubscriber(UUID.randomUUID().toString());
        PrintSubscriber s2 = new PrintSubscriber(UUID.randomUUID().toString());
        PrintSubscriber s3 = new PrintSubscriber(UUID.randomUUID().toString());

        Topic t1 = new Topic(UUID.randomUUID().toString());
        Topic t2 = new Topic(UUID.randomUUID().toString());

        Publisher p1 = new Publisher(UUID.randomUUID().toString());

        Message m1 = new Message(UUID.randomUUID().toString());
        m1.setMessage("first-message");
        Message m2 = new Message(UUID.randomUUID().toString());
        m2.setMessage("second-message");

        s1.subscribe(t1);
        s2.subscribe(t1);
        s3.subscribe(t2);

        p1.addTopic(t1);
        p1.addTopic(t2);

        p1.publishMessage(m1, t1);
        System.out.println("-----");
        p1.publishMessage(m2, t2);

    }
}