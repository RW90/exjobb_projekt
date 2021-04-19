const eventSource = new EventSource("http://localhost:8080/logstream");
const container = document.getElementById("serviceNames");
eventSource.onmessage = function ({data}){
    const serviceName = document.createElement("p");
    serviceName.innerText = data;
    container.appendChild(serviceName);
    console.log(data);
}