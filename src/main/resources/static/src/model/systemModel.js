import Events from "./events.js";
import SystemOverview from "./systemOverview.js";

/**
 * Model for the application. The model is observable.
 */
class SystemModel {

    /**
     * Creates a new instance.
     * @param systemOverview An instance of SystemOverview
     */
    constructor(systemOverview) {
        this.systemOverview = systemOverview;
        this.observers = [];
        this.preFetchedOverview = null;
    }

    setPrefetchedOverview(overview) {
        this.preFetchedOverview = overview;
        this.systemOverview = SystemOverview.fromApiSnapshot(this.preFetchedOverview);
        this.notifyObservers(Events.MAP_UPDATED, this.systemOverview);
    }

    selectService(serviceName) {
        this.notifyObservers(Events.SERVICE_SELECTED, this.getServiceByName(serviceName));
    }

    unselectService(serviceName) {
        this.notifyObservers(Events.SERVICE_UNSELECTED, {});
    }

    getServiceByName(name) {
        return this.systemOverview
            .getServiceByName(name)
    }

    getSystemOverview() {
        return this.systemOverview;
    }

    addObserver(observer) {
        this.observers.push(observer);
    }

    notifyObservers(event, payload) {
        this.observers.forEach(observer => observer.update(event, payload));
    }

    removeObserver(observerToRemove) {
        this.observers = this.observers.filter(observer !== observerToRemove);
    }
}

export default SystemModel;