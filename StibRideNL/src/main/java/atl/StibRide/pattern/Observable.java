package atl.StibRide.pattern;

/**
 *
 * @author g55301
 */
public interface Observable {

    void notifyObservers(Object argument);

    void addObserver(Observer obs);
}
