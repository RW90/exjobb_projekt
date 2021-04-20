const exampleSnapshot = {
	systems: [
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
			},
		},
		{
			data: {
				id: "child2",
				parent: "billing",
			},
		},
		{
			data: {
				id: "child3",
				parent: "address",
			},
		},
		{
			data: {
				id: "child4",
				parent: "order",
			},
		},
		{
			data: {
				id: "child5",
				parent: "billing",
			},
		},
		{
			data: {
				id: "child6",
				parent: "address",
			},
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
