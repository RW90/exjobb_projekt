import Events from "../model/events.js";

/**
 * View that renders information about specific Microservice instances. The view is an observer.
 */
class InfoView {

    /**
     * Creates a new instance.
     * @param container The html-element to render this view in
     * @param model An instance of SystemModel
     */
    constructor(container, model) {
        this.container = container;
        this.header = document.createElement("h3");
        this.endpoints = document.createElement("ul");
        model.addObserver(this);
    }

    /**
     * Renders this view.
     */
    render() {
        this.container.appendChild(this.header);
        this.container.appendChild(this.endpoints);
    }

    update(event, payload) {
        if (event === Events.SERVICE_SELECTED) {
            this.showSelectedService(payload); // in this case, payload is an instance of Microservice
        }

        if (event == Events.SERVICE_UNSELECTED) {
            this.unshowSelectedService();
        }
    }

    /**
     * Renders a information about a Microservice instance.
     * @param service The Microservice instance to render
     */
    showSelectedService(service) {
        this.header.innerText = service.getName();
        service.getEndpoints()
            .map(endpoint => {
                let element = document.createElement("li");
                element.innerText = endpoint.getPath();
                return element;
            })
            .forEach(el => this.endpoints.appendChild(el));
    }

    /**
     * Makes sure that no information about any Microservice instance is shown in the view.
     */
    unshowSelectedService() {
        this.header.innerText = "";
        this.endpoints.innerText = "";
    }

}

export default InfoView;