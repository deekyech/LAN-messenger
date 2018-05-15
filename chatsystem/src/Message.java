
import java.io.Serializable;


class Message implements Serializable {
    private int messageID;
    private final Client sender;
    private final Client reciever;
    private final byte[] content;
    private final int type;
    private String status;
    Message(Client sender, Client reciever, byte[] content, int type) {
        this.sender = sender;
        this.reciever = reciever;
        this.content = content;
        this.type = type;                
    }
    public int getMessageID() {
        return this.messageID;
    }
    public Client getSender() {
        return this.sender;
    }
    public Client getReciever() {
        return this.reciever;
    }
    public byte[] getContent() {
        return this.content;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getMessageType() {
        return this.type;
    }
}