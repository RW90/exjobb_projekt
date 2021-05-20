import {API_EVENT_SOURCE_URL} from "../ui.properties.js";

/**
 * Controller for communicating with the API.
 */
class ApiController {

    // TODO: finish this
    constructor(model) {
        this.model = model;
        this.api = new EventSource(API_EVENT_SOURCE_URL);
        this.addEventListeners()
    }

    addEventListeners() {
        this.api.onmessage = async (event) => {
            let data = await JSON.parse(event.data);
            console.log(data.systemOverview.dependencies)
            if (data.latestChange === "endofstream") {
                this.api.close();
                console.log("endofstream")
                return;
            }
            this.model.setPrefetchedOverview(data.systemOverview, data.latestChange);
        }
    }
}

export default ApiController;