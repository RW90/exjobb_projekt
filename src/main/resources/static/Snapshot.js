const snapshot = {
    systems : [
        {
            data: {
                id: "billing",

            }
        },
        {
            data: {
                id: "order",

            }
        },
        {
            data: {
                id: "address",

            }
        },
    ],
    edges: [
        {
            data: {
                id: "e1",
                source: "billing",
                target: "order"
            }
        },
        {
            data: {
                id: "e2",
                source: "billing",
                target: "address"
            }
        },
    ]
}

export default snapshot;