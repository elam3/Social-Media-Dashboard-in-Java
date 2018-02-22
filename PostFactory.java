import java.time.*;
import java.util.*;

public class PostFactory {

    //Create New Post
    public static Post newPost(String author, String content,
            int siteNumberAnswer, Privacy privacy,
            boolean saveToCollection, String userName)
    {
        Post post;

            switch (siteNumberAnswer) {
                case 1:
                    //Create Facebook Post
                    post = newFacebookPost(author, content, privacy);
                    break;
                case 2:
                    //Create Instagram Post
                    post = newInstagramPost(
                                author,
                                content,
                                saveToCollection
                            );
                    break;
                case 3:
                    //Create Twitter Post
                   post = newTwitterPost(author, content, userName);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        return post;
    }//newPost()

    //Create Facebook Post
    private static FacebookPost newFacebookPost(String author,
            String content, Privacy privacy)
    {
        return new FacebookPost(
                        author,
                        content,
                        LocalDateTime.now(),
                        privacy,
                        FacebookPost.DEFAULT_SHARER
                    );
    }//newFacebookPost()

    //Create Instagram Post
    private static InstagramPost newInstagramPost(String author,
            String content, boolean saveToCollection)
    {
        return new InstagramPost(author, content, LocalDateTime.now(), saveToCollection);
    }//newInstagramPost()

    //Create Twitter Post
    private static TwitterPost newTwitterPost(String author,
            String content, String userName)
    {
        return new TwitterPost(
                        author,
                        content,
                        LocalDateTime.now(),
                        userName
                    );
    }//newTwitterPost()
}
