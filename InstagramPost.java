import java.util.*;

public class InstagramPost extends Post
{
    public boolean saveToCollection;

    public InstagramPost(String author, String content, Date timestamp, boolean saveToCollection)
    {
        super(author, content, timestamp);
        this.saveToCollection = saveToCollection;
    }

    @Override
    public String toString()
    {
        if (saveToCollection)
        {
            return super.toString() + "\n\n^Save to collection label is filled";
        }
        else
        {
            return super.toString() + "\n\n^Save to collection label is empty";
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

}
