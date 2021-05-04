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
const eventSource = new EventSource("/logstream");
let i = 1;
eventSource.onmessage = function ({data:systemId}){
    let cyData = {
            systems: [
            {
                data: {
                    id: systemId
                }
            },
            {
                data: {
                    id: i++,
                    parent: systemId
                }
            }

    ]};
    cy.add(cyData.systems);
    cy.layout({
        name: 'cose',
        infinite: true,
        fit: false,
        animate: false
    }).run();
}

//cy.add(snapshot.systems);
//cy.add(snapshot.edges)
console.log(snapshot);
