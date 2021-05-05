import SystemModel from './src/model/systemModel.js';
import SystemOverview from './src/model/systemOverview.js';
import MapView from './src/view/mapView.js';
import MapController from './src/controller/mapController.js';

function getContainer(viewName) {
	return document.getElementById(`${viewName}-view`);
}

async function fetchSnapshotFromApi() {
	const snapshot = await fetch("/system");
	return await snapshot.json();
}

async function startup() {

	const snapshot = await fetchSnapshotFromApi();
	const model = new SystemModel(SystemOverview.fromApiSnapshot(snapshot));

	const mapView = new MapView(getContainer("map"), model);

	//const apiController = new ApiController(model);
	new MapController(model, mapView);


}

window.onload = startup;

/*const informationContainer = document.getElementById("informationContainer");
let nameContainer = informationContainer.firstElementChild;
let endpointsContainer =
	informationContainer.firstElementChild.nextElementSibling;*/

/*async function loadSystem() {
	const system = await fetch("/system");
	return await system.json();
}

function displayServiceInfo(info) {
	nameContainer.innerText = info.name;

	info.endpoints.forEach((endpoint) => {
		let endpointItem = document.createElement("li");
		endpointItem.innerText = `${endpoint.method} ${endpoint.path}`;
		endpointsContainer.appendChild(endpointItem);
	});
}

function clearDisplayedServiceInfo() {
	nameContainer.innerText = "";
	endpointsContainer.innerHTML = "";
}

loadSystem().then(snap => {
	let overview = SystemOverview.fromApiSnapshot(snap);
	console.log(overview);
});
loadSystem().then(drawSystem);

function systemToDrawableData(system) {
	return system.services.map(service => ({
		data: {
			id: service.name,
			endpoints: service.endpoints
		},
		classes: ["ms"],
		selectable: false,
	}))
}

function drawSystem(system) {
	let cy = cytoscape({
		container: document.getElementById("cyCanvas"),
		elements: systemToDrawableData(system),
		style: [
			{
				selector: "node",
				style: {
					label: "data(id)",
				},
			},
			{
				selector: ".msSelected",
				style: {
					"background-color": "red",
				},
			},
			{
				selector: "edge",
				style: {
					width: 3,
					"line-color": "#ccc",
					"target-arrow-color": "#ccc",
					"target-arrow-shape": "triangle",
					"curve-style": "bezier",
				},
			},
		],
	});
	cy.layout({
		name: "cose",
		fit: true,
		avoidOverlap: true,
		animate: false,
	}).run();

	cy.nodes(".ms").on("mouseover", (event) => {
		event.stopPropagation();
		displayServiceInfo({
			name: event.target._private.data.id,
			endpoints: event.target._private.data.endpoints,
		});
		event.target.addClass("msSelected");
	});

	cy.nodes(".ms").on("mouseout", (event) => {
		event.stopPropagation();
		clearDisplayedServiceInfo();
		event.target.removeClass("msSelected");
	});
}*/
