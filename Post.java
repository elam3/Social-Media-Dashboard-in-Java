public class Post {
    private String author;      //
    private String timestamp;   //YYYYMMDDHH:MM:SS
    private String content;     //post content; i.e. message
    
    public Post(String author, String timestamp, String content) {
        this.author = author;
        this.timestamp = timestamp;
        this.content = content;
    }

    public Post(String author, String timestamp) {
        this.author = author;
        this.timestamp = timestamp;
        this.content = null;
    }

    public Post() {
        this.author = null;
        this.timestamp = null;
        this.content = null;
    }

    //getter & setters
    //equal
    //toString
}
