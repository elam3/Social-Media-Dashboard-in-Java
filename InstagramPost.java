import java.time.*;
import java.util.*;

public class InstagramPost extends Post
{
    private boolean saveToCollection;

    public InstagramPost(String author, String content, LocalDateTime timestamp, boolean saveToCollection)
    {
        super(author, content, timestamp, new SendToFriend());
        this.saveToCollection = saveToCollection;
    }

    public boolean isSaveToCollection()
    {
        return saveToCollection;
    }

    public void setSaveToCollection(boolean saveToCollection)
    {
        this.saveToCollection = saveToCollection;
    }

    @Override
    public String toString()
    {
        if (saveToCollection)
        {
            return "\t\tInstagram\n" + super.toString() + "\n\n^Save to collection label is filled";
        }
        else
        {
            return "\t\tInstagram\n" + super.toString() + "\n\n^Save to collection label is empty";
        }
    }

    @Override
    public boolean equals(Object otherPost)
    {
        if (otherPost instanceof InstagramPost)
        {
            InstagramPost post = (InstagramPost) otherPost;

            return super.equals(post) && (this.saveToCollection == post.saveToCollection);
        }
        else
        {
            return false;
        }
    }

    @Override
    public void deletePost()
    {
        System.out.println("Instagram post deleted");

    }

    public void sendToFriend()
    {
        System.out.println("Instagram: \"Post sent to friend\"");
    }
}
