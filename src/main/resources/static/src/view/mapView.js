/**
 * View that renders the map.
 */
class MapView {

    /**
     * Creates a new instance.
     * @param container The html-element to render this view in
     * @param model An instance of SystemModel
     */
    constructor(container, model) {
        this.container = container;
    }

    render() {
        this.container.innerText = "appended";
    }
}

export default MapView;