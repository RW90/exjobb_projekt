import Events from "../model/events.js";

class LogView {

    constructor(container, model) {
        this.container = container;
        this.header = document.createElement("h3");
        this.header.classList.add("list-header");
        this.logList = document.createElement("ul");
        this.logList.classList.add("log-list");
        model.addObserver(this);
        this.render();
    }

    /**
     * Renders this view.
     */
    render() {
        this.header.innerText = "Change log";
        this.container.appendChild(this.header);
        this.container.appendChild(this.logList);
    }

    update(event, payload) {    // payload is a string here
        if (event == Events.CHANGE_INCOMING) {
            let change = document.createElement("li");
            change.innerText = payload;
            change.classList.add("list-item");
            this.logList.insertBefore(change, this.logList.firstElementChild);
        }
    }

}

export default LogView;