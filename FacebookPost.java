import java.util.*;

public class FacebookPost extends Post
{
    public Privacy privacy;

    public FacebookPost(String author, String content, Date timestamp, Privacy privacy)
    {
        super(author, content, timestamp);
        this.privacy = privacy;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\n\n* Post visibility: " + privacy;
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
}
