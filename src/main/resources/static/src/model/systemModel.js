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
        this.preFetchedPlainOverview = null;
    }

    refreshMap() {
        this.systemOverview = SystemOverview.fromPlainOverview(this.preFetchedPlainOverview);
        this.preFetchedPlainOverview = null;
        this.notifyObservers(Events.MAP_REFRESH, this.systemOverview);
    }

    setPrefetchedOverview(overview, change) {
        this.preFetchedPlainOverview = overview;
        this.notifyObservers(Events.CHANGE_INCOMING, change);
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
}

export default SystemModel;