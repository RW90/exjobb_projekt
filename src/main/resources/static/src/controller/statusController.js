class StatusController {

    // TODO: finish this
    constructor(model, view) {
        this.model = model;
        this.view = view;
        this.addEventListeners()
        this.render();
    }

    render() {
        this.view.render();
    }

    addEventListeners() {
        this.view.refreshButton.addEventListener("click", (event) => {
            this.model.refreshMap();
        });
    }
}

export default StatusController;