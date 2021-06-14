/**
 * Represents a dependency between two Microservice instances.
 */
class Dependency {

    /**
     * Creates a new instance.
     * @param id A unique identifier for the dependency.
     * @param fromService The microservice instance, as a string, that is dependent on another
     * @param toService The microservice instance, as a string, depended on by the source
     */
    constructor(id, fromService, toService) {
        this.id = id;
        this.fromService = fromService;
        this.toService = toService;
    }

    getId() {
        return this.id;
    }

    getFromService() {
        return this.fromService;
    }

    getToService() {
        return this.toService;
    }
}

export default Dependency;