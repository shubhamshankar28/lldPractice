package org.shubhampractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Topic implements Comparable {

    Set<Subscriber> subscribers;
    private final String id;
    private ExecutorService executorService;
    Topic(String id) {
        this.id = id;
        this.subscribers = new TreeSet<>();
        executorService = Executors.newFixedThreadPool(5);
    }

    public String getId() {
        return id;
    }

    void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }
    void publishMessage(Message m) {
        System.out.println("sending messages");

        Integer successfulCompletion = 1;
        List<Future<Integer>> results =
                subscribers.stream()
                        .map(subscriber ->
                                executorService.submit(() -> subscriber.processMessage(m), successfulCompletion))
                .collect(Collectors.toList());
        int size = results.size();

        executorService.shutdown();

        for(int i=0; i<size; ++i) {
            try {
                results.get(i).get();
            }
            catch (Exception e) {
                System.out.println("Failure observed while using executor service: " + e.getMessage());
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        String currentId = this.id;
        String objectId = ((Topic) o).getId();

        return currentId.compareTo(objectId);
    }
}
