const exampleSnapshot = {
	elements: [
		{
			data: {
				id: "billing",
			},
		},
		{
			data: {
				id: "order",
			},
		},
		{
			data: {
				id: "address",
			},
		},
		{
			data: {
				id: "child1",
				parent: "order",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "child2",
				parent: "billing",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "child3",
				parent: "address",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "child4",
				parent: "order",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "child5",
				parent: "billing",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "child6",
				parent: "address",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
	],
	edges: [
		{
			data: {
				id: "e1",
				source: "child1",
				target: "child6",
			},
		},
		{
			data: {
				id: "e2",
				source: "child2",
				target: "child4",
			},
		},
	],
};

export default exampleSnapshot;
