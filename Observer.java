import java.util.*;

public class Observer {
    public static void main(String[] args) {
        List<Subscriber> subscribers = new ArrayList<Subscriber>();
        subscribers.add(new Subscriber(true));
        subscribers.add(new Subscriber(false));
        subscribers.add(new Subscriber(true));
        subscribers.add(new Subscriber(false));
        subscribers.add(new Subscriber(true));

        Publisher publisher = new Publisher();
        for (Subscriber s: subscribers){
            if (s.reads) publisher.subscribe(s);
        }

        publisher.notifySubscribers();
    }
}

class Publisher {
    List<Subscriber> subscribers = new ArrayList<Subscriber>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        for (Subscriber s : subscribers) {
            s.getNotified();
        }
    }
}

class Subscriber {
    public boolean reads;

    Subscriber(boolean reads) {
        this.reads = reads;
    }

    public void getNotified() {
        System.out.println("thanks! I got notified");
    }
}