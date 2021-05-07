/**
 * Represents a microservice
 */
class Microservice {

    /**
     * Creates a new instance.
     * @param name Identifier for the microservice
     * @param endpoints Array of Endpoint instances
     */
    constructor(name, endpoints) {
        this.name = name;
        this.endpoints = endpoints;
        this.classes = ["ms"];
        this.selectable = false;
    }

    getName() {
        return this.name;
    }

    getEndpoints() {
        return this.endpoints;
    }

    getClasses() {
        return this.classes;
    }

    getSelectable() {
        return this.selectable;
    }
}

export default Microservice;