public class FacebookPost extends Post {
    private int likeCount;

    public FacebookPost(String author, String timestamp, String content) {
        super(author, timestamp, content);
        this.likeCount = 0;
    }

    //getter & setter
    //equal
    //toString

    public void clickLikeButton() {
        likeCount++;
    }
}
