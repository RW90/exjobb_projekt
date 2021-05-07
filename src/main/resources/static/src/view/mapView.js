import Graph from '../model/graph.js';
import Events from "../model/events.js";

/**
 * View that renders the map. The view is an observer.
 */
class MapView {

    /**
     * Creates a new instance.
     * @param container The html-element to render this view in
     * @param model An instance of SystemModel
     */
    constructor(container, model) {
        this.container = container;
        this.map = Graph.fromSystemOverview(model.getSystemOverview());
        model.addObserver(this);
    }

    /**
     * Renders this view by mounting the map.
     */
    render() {
        this.map.mount(this.container);
    }

    // TODO: should be used for update events from server
    update(event, payload) {
        if (event == Events.MAP_UPDATED) {
            this.map.update(payload);
        }
    }

    getMap() {
        return this.map;
    }
}

export default MapView;