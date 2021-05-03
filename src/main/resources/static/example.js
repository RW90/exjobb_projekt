const informationContainer = document.getElementById("informationContainer");
let nameContainer = informationContainer.firstElementChild;
let endpointsContainer =
	informationContainer.firstElementChild.nextElementSibling;

async function loadSystem() {
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
}



// let cy = cytoscape({
// 	container: document.getElementById("cyCanvas"),

// 	elements: [
// 		// flat array of nodes and edges
// 		{
// 			// node n1
// 			group: "nodes", // 'nodes' for a node, 'edges' for an edge
// 			// NB the group field can be automatically inferred for you but specifying it
// 			// gives you nice debug messages if you mis-init elements

// 			data: {
// 				// element data (put json serialisable dev data here)
// 				id: "n1", // mandatory (string) id for each element, assigned automatically on undefined
// 				parent: "order", // indicates the compound node parent id; not defined => no parent
// 				// (`parent` can be effectively changed by `eles.move()`)
// 				endpoints: ["/order", "/billing", "/customer"],
// 			},

// 			// scratchpad data (usually temp or nonserialisable data)
// 			scratch: {
// 				_foo: "bar", // app fields prefixed by underscore; extension fields unprefixed
// 			},

// 			position: {
// 				// the model position of the node (optional on init, mandatory after)
// 				x: 100,
// 				y: 100,
// 			},

// 			selected: false, // whether the element is selected (default false)

// 			selectable: true, // whether the selection state is mutable (default true)

// 			locked: false, // when locked a node's position is immutable (default false)

// 			grabbable: true, // whether the node can be grabbed and moved by the user

// 			pannable: false, // whether dragging the node causes panning instead of grabbing

// 			classes: ["foo", "bar"], // an array (or a space separated string) of class names that the element has

// 			// DO NOT USE THE `style` FIELD UNLESS ABSOLUTELY NECESSARY
// 			// USE THE STYLESHEET INSTEAD
// 			style: {
// 				// style property overrides
// 				"background-color": "red",
// 			},
// 		},

// 		{
// 			// node n2
// 			data: { id: "n2", parent: "billing" },
// 			renderedPosition: { x: 200, y: 200 }, // can alternatively specify position in rendered on-screen pixels
// 			classes: ["foo", "bar"], // an array (or a space separated string) of class names that the element has
// 		},

// 		{
// 			// node n3
// 			data: { id: "n3", parent: "order" },
// 			position: { x: 123, y: 234 },
// 		},

// 		{
// 			// node nparent
// 			data: { id: "order" },
// 		},

// 		{
// 			// node nparent
// 			data: { id: "billing" },
// 		},

// 		{
// 			// edge e1
// 			data: {
// 				id: "e1",
// 				// inferred as an edge because `source` and `target` are specified:
// 				source: "n1", // the source node id (edge comes from this node)
// 				target: "n2", // the target node id (edge goes to this node)
// 				// (`source` and `target` can be effectively changed by `eles.move()`)
// 			},

// 			pannable: true, // whether dragging on the edge causes panning
// 		},

// 		{
// 			// edge e2
// 			data: {
// 				id: "e2",
// 				// inferred as an edge because `source` and `target` are specified:
// 				source: "n3", // the source node id (edge comes from this node)
// 				target: "n2", // the target node id (edge goes to this node)
// 				// (`source` and `target` can be effectively changed by `eles.move()`)
// 			},

// 			pannable: true, // whether dragging on the edge causes panning
// 		},
// 	],

// 	layout: {
// 		name: "preset",
// 	},

// 	// so we can see the ids
// 	style: [
// 		{
// 			selector: "node",
// 			style: {
// 				label: "data(id)",
// 			},
// 		},
// 		{
// 			selector: "edge",
// 			style: {
// 				width: 3,
// 				"line-color": "#ccc",
// 				"target-arrow-color": "#ccc",
// 				"target-arrow-shape": "triangle",
// 				"curve-style": "bezier",
// 			},
// 		},
// 	],
// });

// //cy.$("#n3").selected();
// //eles.getElementById("n3");
// cy.nodes(".foo").on("mouseover", (evt) => {
// 	evt.stopPropagation();
// 	console.log(evt.target._private.data.endpoints);
// });

// document.getElementById("banan").onclick = function () {
// 	cy.add([
// 		{
// 			// node nparent
// 			data: { id: "order2", parent: "order" },
// 		},
// 		{
// 			data: {
// 				id: "e3",
// 				// inferred as an edge because `source` and `target` are specified:
// 				source: "order2", // the source node id (edge comes from this node)
// 				target: "n1", // the target node id (edge goes to this node)
// 				// (`source` and `target` can be effectively changed by `eles.move()`)
// 			},
// 		},
// 	]);
// 	cy.layout({
// 		name: "cose",
// 		fit: true,
// 		avoidOverlap: true,
// 		animate: false,
// 	}).run();
// };

// document.getElementById("bananer").onclick = function () {
// 	console.log(cy.getElementById("n1")._private.data);
// 	console.log(cy.getElementById("n1")._private.data.endpoints.push("/gurka"));
// };
