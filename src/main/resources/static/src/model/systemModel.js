import Events from "./events.js";

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

    setPrefetchedOverview(overview, change) {
        this.preFetchedPlainOverview = overview;
        console.log(change);
        this.notifyObservers(Events.CHANGE_INCOMING, change);

        // this should be done on another event
        // TODO: move to statusViewController
        /*this.systemOverview = SystemOverview.fromPlainOverview(this.preFetchedPlainOverview);
        this.notifyObservers(Events.MAP_UPDATE, this.systemOverview);*/
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