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
				id: "webfront",
			},
		},
		{
			data: {
				id: "storage",
			},
		},
		{
			data: {
				id: "shipping",
			},
		},
		{
			data: {
				id: "customer",
			},
		},
		{
			data: {
				id: "ms1",
				parent: "order",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms2",
				parent: "order",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms3",
				parent: "order",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms4",
				parent: "order",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms5",
				parent: "order",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms6",
				parent: "billing",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms7",
				parent: "billing",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms8",
				parent: "billing",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms9",
				parent: "billing",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms1",
				parent: "billing",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms11",
				parent: "billing",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms12",
				parent: "address",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms13",
				parent: "address",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms14",
				parent: "address",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms15",
				parent: "address",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms16",
				parent: "webfront",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms17",
				parent: "webfront",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms18",
				parent: "webfront",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms19",
				parent: "storage",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms20",
				parent: "storage",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms21",
				parent: "storage",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms22",
				parent: "storage",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms23",
				parent: "storage",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms24",
				parent: "storage",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms25",
				parent: "shipping",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms26",
				parent: "shipping",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms27",
				parent: "shipping",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms28",
				parent: "shipping",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms29",
				parent: "shipping",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms30",
				parent: "shipping",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms31",
				parent: "customer",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms32",
				parent: "customer",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms33",
				parent: "customer",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "ms34",
				parent: "customer",
				endpoints: ["/billing", "/customer"],
			},
			classes: ["ms"],
			selectable: false,
		},
		{
			data: {
				id: "e1",
				source: "ms16",
				target: "ms1",
			},
		},
		{
			data: {
				id: "e2",
				source: "ms16",
				target: "ms2",
			},
		},
		{
			data: {
				id: "e3",
				source: "ms17",
				target: "ms4",
			},
		},
		{
			data: {
				id: "e4",
				source: "ms18",
				target: "ms3",
			},
		},
		{
			data: {
				id: "e5",
				source: "ms1",
				target: "ms19",
			},
		},
		{
			data: {
				id: "e6",
				source: "ms1",
				target: "ms20",
			},
		},
		{
			data: {
				id: "e7",
				source: "ms2",
				target: "ms21",
			},
		},
		{
			data: {
				id: "e8",
				source: "ms3",
				target: "ms19",
			},
		},
		{
			data: {
				id: "e9",
				source: "ms4",
				target: "ms22",
			},
		},
		{
			data: {
				id: "e10",
				source: "ms5",
				target: "ms23",
			},
		},
		{
			data: {
				id: "e11",
				source: "ms4",
				target: "ms24",
			},
		},
		{
			data: {
				id: "e12",
				source: "ms1",
				target: "ms6",
			},
		},
		{
			data: {
				id: "e13",
				source: "ms2",
				target: "ms7",
			},
		},
		{
			data: {
				id: "e14",
				source: "ms3",
				target: "ms8",
			},
		},
		{
			data: {
				id: "e15",
				source: "ms4",
				target: "ms9",
			},
		},
		{
			data: {
				id: "e16",
				source: "ms16",
				target: "ms31",
			},
		},
		{
			data: {
				id: "e17",
				source: "ms17",
				target: "ms32",
			},
		},
		{
			data: {
				id: "e18",
				source: "ms18",
				target: "ms33",
			},
		},
		{
			data: {
				id: "e19",
				source: "ms18",
				target: "ms34",
			},
		},
		{
			data: {
				id: "e20",
				source: "ms31",
				target: "ms13",
			},
		},
		{
			data: {
				id: "e21",
				source: "ms32",
				target: "ms12",
			},
		},
		{
			data: {
				id: "e22",
				source: "ms33",
				target: "ms14",
			},
		},
		{
			data: {
				id: "e23",
				source: "ms34",
				target: "ms15",
			},
		},
		{
			data: {
				id: "e24",
				source: "ms1",
				target: "ms25",
			},
		},
		{
			data: {
				id: "e25",
				source: "ms2",
				target: "ms26",
			},
		},
		{
			data: {
				id: "e26",
				source: "ms3",
				target: "ms27",
			},
		},
		{
			data: {
				id: "e27",
				source: "ms4",
				target: "ms28",
			},
		},
	],
};

export default exampleSnapshot;
