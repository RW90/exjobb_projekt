/**
 * Controller for MapView.
 */
class MapController {

    /**
     * Creates a new instance.
     * @param model An instance of SystemModel
     * @param view An instance of MapView
     */
    constructor(model, view) {
        this.model = model;
        this.view = view;
        this.render();
    }

    render() {
        this.view.render();
    }
}

export default MapController;