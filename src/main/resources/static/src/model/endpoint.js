/**
 * Represents an endpoint.
 */
class Endpoint {

    /**
     * Creates a new instance.
     * @param method HTTP Method for the endpoint
     * @param path Path of the endpoint
     */
    constructor(method, path) {
        this.method = method;
        this.path = path;
    }

    getMethod() {
        return this.method;
    }

    getPath() {
        return this.path;
    }
}

export default Endpoint;