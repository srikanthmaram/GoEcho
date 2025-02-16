package com.sri.GoEcho.chatroom;




public class ChatRoom {


    private String senderId;
    private String receiverId;

    public ChatRoom() {
    }

    public ChatRoom( String senderId, String receiverId) {

        this.senderId = senderId;
        this.receiverId = receiverId;
    }





    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +

                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                '}';
    }
}
