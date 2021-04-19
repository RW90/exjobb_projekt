const eventSource = new EventSource("/logstream");
const container = document.getElementById("serviceNames");
eventSource.onmessage = function ({data}){
    const serviceName = document.createElement("p");
    serviceName.innerText = data;
    container.appendChild(serviceName);
    console.log(data);
}
