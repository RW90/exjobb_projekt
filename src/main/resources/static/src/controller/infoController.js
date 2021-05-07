/**
 * Controller for InfoView.
 */
class InfoController {

    /**
     * Creates a new instance.
     * @param model An instance of SystemModel
     * @param view An instance of InfoView
     */
    constructor(model, view) {
        this.model = model;
        this.view = view;
        this.render();
    }

    /**
     * Renders the view.
     */
    render() {
        this.view.render();
    }

}

export default InfoController;