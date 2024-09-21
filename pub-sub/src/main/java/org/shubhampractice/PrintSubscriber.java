package org.shubhampractice;

public class PrintSubscriber implements Subscriber, Comparable
         {
    private final String id;

    PrintSubscriber(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void processMessage(Message m) {
        System.out.println(id + " " + m.getMessage());
    }

    void subscribe(Topic topic) {
        topic.subscribe(this);
    }

    void unsubscribe(Topic topic) {
        topic.unsubscribe(this);
    }

     @Override
     public int compareTo(Object o) {
        String currentId = id;
        String objectId = ((PrintSubscriber) o).getId();

        return currentId.compareTo(objectId);
     }
         }
