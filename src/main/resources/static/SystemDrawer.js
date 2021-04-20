import snapshot from "./Snapshot.js";
let cy = cytoscape({
    container: document.getElementById("cyCanvas"),
    style: [
        {
            selector: "node",
            style: {
                label: "data(id)",
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
    ]
});

cy.add(snapshot.systems);
cy.add(snapshot.edges)
cy.layout({
    name: 'cola',
    infinite: true,
    fit: false,
    animate: false
}).run();
console.log(snapshot);
