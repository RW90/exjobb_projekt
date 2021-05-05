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
        model.addObserver(this);
        this.map = cytoscape({
            elements: this.transformOverviewToCytoscapeObjects(model.getSystemOverview()),
            style: styleConfiguration, // see bottom of file
        }); // this is the created map / runtime model / overview of the system
    }

    /**
     * Renders this view.
     */
    render() {
        this.map.mount(this.container);
        this.runLayout();
    }

    /**
     * Organizes the map and lays out the elements nicely according to layout "cose"
     */
    runLayout() {
        this.map.layout({
            name: "cose",
            fit: true,
            avoidOverlap: true,
            animate: false,
        }).run();
    }

    // TODO: should be used for update events from server
    update(event, payload) {
    }

    /**
     * Transforms an instance of SystemOverview to plain JavaScript objects that can be understood by Cytoscape.
     * @param systemOverview An instance of SystemOverview
     * @returns {*} An array of plain objects, parsable by the Cytoscape map, representing the SystemOverview instance
     */
    transformOverviewToCytoscapeObjects(systemOverview) {
        return systemOverview
            .getServices()
            .map(service => ({
                data: {
                    id: service.getName(),
                    endpoints: service.getEndpoints()
                },
                classes: ["ms"]
            }));
    }

    getMap() {
        return this.map;
    }
}

// style configuration for cytoscape
const styleConfiguration = [
    {
        selector: "node",
        style: {
            label: "data(id)",
        },
    },
    {
        selector: ".msSelected",
        style: {
            "background-color": "red",
        },
    },
    {
        selector: "edge",
        style: {
            width: 3,
            "line-color": "#ccc",
            "target-arrow-color": "#ccc",
            "target-arrow-shape": "triangle",
            "curve-style": "bezier",
        },
    },
]

export default MapView;