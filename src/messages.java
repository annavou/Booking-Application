public class messages {
    private String from;
    private String topic;
    private String text;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    messages(){
        from = null;
        topic = null;
        text = null;
    }

    messages(String a, String b ,String c){
        from = a;
        topic = b;
        text = c;
    }


}
