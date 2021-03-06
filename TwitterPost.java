import java.time.*;
import java.util.*;

public class TwitterPost extends Post
{
    private String userName;
    protected final static String DEFAULT_USER_NAME = "@user";

    public TwitterPost(String author, String content, LocalDateTime timestamp, String userName)
    {
        super(author, content, timestamp, new ShareWithFriends());
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Override
    public String toString()
    {
        return "\t\tTwitter\n" + userName + "\n" + super.toString();
    }

    @Override
    public boolean equals(Object otherPost)
    {
        if (otherPost instanceof TwitterPost)
        {
            TwitterPost post = (TwitterPost) otherPost;

            return super.equals(post) && this.userName.equalsIgnoreCase(post.userName);
        }
        else
        {
            return false;
        }
    }

    @Override
    public void deletePost()
    {
        System.out.println("Twitter Post deleted");
    }

    public String follow()
    {
        return "\"You are following this post\"";
    }
}
