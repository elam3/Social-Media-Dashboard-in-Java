import java.time.*;
import java.util.*;

public class FacebookPost extends Post
{
    private Privacy privacy;    // M2 HOMEWORK ENUM USE
    protected static final Sharer DEFAULT_SHARER = new ShareWithFriends();

    public FacebookPost(String author, String content, LocalDate timestamp, Privacy privacy, Sharer sharer)
    {
        super(author, content, timestamp, sharer);
        this.privacy = privacy;
    }

    public Privacy getPrivacy()
    {
        return privacy;
    }

    public void setPrivacy(Privacy privacy)
    {
        this.privacy = privacy;
    }

    @Override
    public String toString()
    {
        return "\t\tFacebook\n" + super.toString() + "\n\n* Post visibility: " + privacy;
    }

    @Override
    public boolean equals(Object otherPost)
    {
        if (otherPost instanceof FacebookPost)
        {
            FacebookPost post = (FacebookPost) otherPost;

            return super.equals(post) && this.privacy.equals(post.privacy);
        }
        else
        {
            return false;
        }
    }

    @Override
    public void deletePost()
    {
        System.out.println("Facebook post deleted");

    }

    public void addLocation()
    {
        System.out.println("Facebook: \"Location added to your post\"");
    }

    // M2 HOMEWORK STATIC
    public static FacebookPost parseFacebookPost(TwitterPost tweet)
    {
        FacebookPost fbPost = new FacebookPost(
            tweet.getAuthor(),
            tweet.getContent(),
            tweet.getTimestamp(),
            Privacy.PUBLIC,          //TwitterPosts are assumed to be public
            DEFAULT_SHARER           //TwitterPosts are shared with followers
        );

        return fbPost;
    }

}
