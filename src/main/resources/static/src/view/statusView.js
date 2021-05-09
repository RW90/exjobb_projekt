import Events from "../model/events.js";

class StatusView {

    constructor(container, model) {
        this.container = container;
        this.statusMessage = document.createElement("p");
        this.refreshButton = document.createElement("button");
        this.refreshButton.innerText = "Refresh map";
        model.addObserver(this);
    }

    /**
     * Renders this view.
     */
    render() {
        this.container.appendChild(this.statusMessage);
        this.statusMessage.innerText = "Map shows the latest snapshot of the system overview";
        this.container.classList.add("green");
    }

    update(event, payload) {
        if(event == Events.CHANGE_INCOMING) {
            this.statusMessage.innerText = "The system overview has changed";
            this.container.appendChild(this.refreshButton);
            this.container.classList.remove("green");
            this.container.classList.add("red");
        }

        if(event == Events.MAP_REFRESH) {
            this.statusMessage.innerText = "Map shows the latest snapshot of the system overview";
            this.container.classList.remove("red");
            this.container.classList.add("green");
            this.refreshButton.remove();
        }
    }

}

export default StatusView;