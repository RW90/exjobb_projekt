import Events from "../model/events.js";

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
        this.addEventListeners();
        this.render();
    }

    /**
     * Renders the view.
     */
    render() {
        this.view.render();
    }

    /**
     * Adds event listeners to relevant elements in the view.
     */
    addEventListeners() {
        this.addHoverListeners();
        this.view.container.addEventListener(Events.MAP_RERENDERED, (evt) => {
            this.removeHoverListeners();
            this.addHoverListeners();
        });
    }

    removeHoverListeners() {
        this.view.getMap().getElements().removeAllListeners();
    }

    addHoverListeners() {
        this.view.getMap()
            .getNodes(".ms")
            .on("mouseover", (event) => {
                event.stopPropagation();
                this.model.selectService(event.target._private.data.id);
                event.target.addClass("msSelected")
            });

        this.view.getMap()
            .getNodes(".ms")
            .on("mouseout", (event) => {
                event.stopPropagation();
                this.model.unselectService(event.target._private.data.id);
                event.target.removeClass("msSelected");
            });
    }
}

export default MapController;