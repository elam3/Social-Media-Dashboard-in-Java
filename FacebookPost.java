import java.util.*;

public class FacebookPost extends Post
{
    public Privacy privacy;


    public FacebookPost(String author, String content, Date timestamp, Privacy privacy)
    {
        super(author, content, timestamp);
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
            Privacy.PUBLIC          //TwitterPosts are assumed to be public
        );

        return fbPost;
    }

}
