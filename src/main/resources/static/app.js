import SystemModel from './src/model/systemModel.js';
import SystemOverview from './src/model/systemOverview.js';
import MapView from './src/view/mapView.js';
import InfoView from "./src/view/infoView.js";
import MapController from './src/controller/mapController.js';
import InfoController from "./src/controller/infoController.js";
import ApiController from "./src/controller/apiController.js";
import StatusController from "./src/controller/statusController.js";
import StatusView from "./src/view/statusView.js";
import LogView from "./src/view/logView.js";

import {DUMMY_SNAPSHOT_URL} from "./src/ui.properties.js";

/**
 * Helper to fetch a containers for views, from the DOM.
 * @param viewName Name of the view.
 * @returns {HTMLElement} The corresponding container from the DOM.
 */
function getContainer(viewName) {
	return document.getElementById(`${viewName}-view`);
}

/**
 * Load a snapshot of the runtime model / system map / overview of system from the API.
 * @returns {Promise<any>} A promise that will be resolved to the server response body.
 */
async function fetchSnapshotFromApi() {
	const snapshot = await fetch(DUMMY_SNAPSHOT_URL);
	return await snapshot.json();
}

/**
 * Startup function to be run on Window onload event.
 * @returns {Promise<void>} Any
 */
async function startup() {

	const {systemOverview} = await fetchSnapshotFromApi();
	const model = new SystemModel(SystemOverview.fromPlainOverview(systemOverview));

	const mapView = new MapView(getContainer("map"), model);
	const infoView = new InfoView(getContainer("info"), model);
	const statusView = new StatusView(getContainer("status"), model);
	new LogView(getContainer("log"), model);

	new ApiController(model);
	new MapController(model, mapView);
	new InfoController(model, infoView);
	new StatusController(model, statusView);

}

window.onload = startup;