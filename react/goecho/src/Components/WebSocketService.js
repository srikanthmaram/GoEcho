import { Stomp } from "@stomp/stompjs";
import SockJS from "sockjs-client";

class WebSocketService {
    
constructor()
{
    this.stompClient=null;

}
    connect(onConnect,onError) {
        var socket = new SockJS('https://goecho-webservice.onrender.com/GoEcho-Live');
         this.stompClient = Stomp.over(socket);
        const accessToken = localStorage.getItem('jwtToken');
        
        this.stompClient.connect({"Authorization": "Bearer " + accessToken}, function (frame) {console.log('Connected: ' + frame);onConnect()}, function (err){ console.log("Failed to connect websocket"+err);onError()})
    }






    disconnect = () => {
        if (this.stompClient.connected) {
            this.stompClient.deactivate(); 
        }
    }

  

    subscribe(destination, callback) {

        if (this.stompClient && this.stompClient.connected) {


            
            console.log("Subscribed to " + destination);
            this.stompClient.subscribe(destination, (message) => {
                callback(JSON.parse(message.body));
                console.log(message)
            });
        }
    }

    sendMessage(destination, message) {
        if (this.stompClient && this.stompClient.connected) {
            this.stompClient.publish({
                destination: destination,
                body: JSON.stringify(message),
            });
        }
    }
}

const webSocketService = new WebSocketService();
export default webSocketService;
