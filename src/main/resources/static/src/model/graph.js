/**
 * Represents the actual graph of the system / runtime model / overview, that can be rendered on
 * an HTML element. Acts as a wrapper for Cytoscape.js so that it can work with the model objects
 * defined in this application.
 */
class Graph {

    /**
     * Creates a new instance.
     * @param elements An Array of elements (nodes, edges) that can be understood by Cytoscape.js
     */
    constructor(elements) {
        this.cy = cytoscape({
            style: styleConfiguration, // see bottom of file
        })
        this.elements = this.cy.add(elements); // elements are used for removal etc.
    }

    /**
     * Creates an instance of Graph from an instance of SystemOverview.
     * @param systemOverview The instance of SystemOverview
     * @returns {Graph} A new Graph instance
     */
    static fromSystemOverview(systemOverview) {
        const elements = Graph.transformOverviewToCytoscapeObjects(systemOverview);
        return new Graph(elements);
    }

    /**
     * Transforms an instance of SystemOverview to plain JavaScript objects that can be understood by Cytoscape.
     * @param systemOverview An instance of SystemOverview
     * @returns {*} An array of plain objects, parsable by the Cytoscape map, representing the SystemOverview instance
     */
    static transformOverviewToCytoscapeObjects(systemOverview) {

        const services = systemOverview
            .getServices()
            .map(service => ({
                data: {
                    id: service.getName(),
                    endpoints: service.getEndpoints()
                },
                classes: service.getClasses(),
                selectable: service.getSelectable()
            }));

        const edges = systemOverview
            .getDependencies()
            .map(dependency => ({
              data: {
                  id: dependency.getId(),
                  source: dependency.getFromService(),
                  target: dependency.getToService()
              }
            }));
        return services.concat(edges);
    }

    /**
     * Mounts (renders) this instance on a HTML element.
     * @param container The HTML element to render this Graph instance on
     */
    mount(container) {
        this.cy.mount(container);
        this.runLayout();
    }

    /**
     *
     * @param systemOverview An instance of SystemOverview
     */
    update(systemOverview) {
        this.cy.remove(this.elements);
        this.elements = this.cy.add(Graph.transformOverviewToCytoscapeObjects(systemOverview));
        this.runLayout();
    }

    /**
     * Get all nodes with a specific CSS-selector.
     * @param selector
     * @returns {*} The nodes in the graph matching the specified selector
     */
    getNodes(selector) {
        return this.cy.nodes(selector);
    }

    getElements() {
        return this.elements;
    }

    /**
     * Organizes the map and lays out the elements nicely according to layout "cose"
     */
    runLayout() {
        this.cy.layout({
            name: "cose",
            fit: true,
            avoidOverlap: true,
            animate: false,
        }).run();
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

export default Graph;