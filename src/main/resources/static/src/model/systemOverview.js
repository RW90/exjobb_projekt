import Microservice from './microservice.js';
import Endpoint from './endpoint.js';

/**
 * Represents a map / runtime model / runtime architecture of the system.
 */
class SystemOverview {

    /**
     * Creates a new instance.
     * @param services Array of Microservice instances
     * @param dependencies Array of Dependency instances
     */
    constructor(services = [], dependencies = []) {
        this.services = services;
        this.dependencies = dependencies;
    }

    /**
     * Static factory to create an instance from a snapshot from the API.
     * @param snapshot A snapshot from the API containing services and dependencies
     * @returns {SystemOverview} An instance of SystemOverview
     */
    static fromApiSnapshot(snapshot) {
        console.log(snapshot)
        // helper to map endpoints in snapshot to instances of Endpoint
        const extractEndpoints = (endpoints) => endpoints.map(endpoint => new Endpoint(endpoint.method, endpoint.path));

        const services = snapshot.services
            .map(({name, endpoints}) => new Microservice(name, extractEndpoints(endpoints)));

        const dependencies = [];
        return new SystemOverview(services, dependencies);
    }

    getServices() {
        return this.services;
    }

    getDependencies() {
        return this.dependencies;
    }

    getServiceByName(name) {
        return this.services.find(service => service.name === name);
    }
}

export default SystemOverview;