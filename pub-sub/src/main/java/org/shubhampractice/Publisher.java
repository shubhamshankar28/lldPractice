package org.shubhampractice;

import java.util.*;

public class Publisher {
    private final String id;
    private Set<Topic> allowedTopics;

    public Publisher(String id) {
        this.id = id;
        this.allowedTopics = new TreeSet<>();
    }

    void addTopic(Topic t) {
        allowedTopics.add(t);
    }



    void publishMessage(Message m, Topic t) {

        if(!allowedTopics.contains(t)) {
            System.out.println("Publisher cannot publish to to this topic: " + t.getId());
        }

        System.out.println("sending message" + m.getMessage());
        t.publishMessage(m);
    }

}
