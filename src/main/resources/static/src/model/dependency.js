/**
 * Represents a dependency between two Microservice instances.
 */
class Dependency {

    /**
     * Creates a new instance.
     * @param id A unique identifier for the dependency.
     * @param source The microservice instance that is dependent on another
     * @param target The microservice instance depended on by the source
     */
    constructor(id, source, target) {
        this.id = id;
        this.source = source;
        this.target = target;
    }
}

export default Dependency;