package App;


public class Message {

    private String sender;
    private String content;
    private MyDate date;

    public Message(String sender, String content, MyDate date) {
        this.sender = sender;
        this.content = content;
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyDate getDate() {
        return date;
    }

    public void setDate(MyDate MyDate) {
        this.date = MyDate;
    }
}
