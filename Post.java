import java.util.*;

public abstract class Post implements Comparable<Post>
{
    public static Scanner scan = new Scanner(System.in);
    private String author, content;
    private Date timestamp;

    public Post(String author, String content, Date timestamp)
    {
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getAuthor()
    {
        return author;
    }
    public String getContent()
    {
        return content;
    }
    public Date getTimestamp()
    {
        return timestamp;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    // I don't think we need setter for timestamp
    /*
    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }
    */

    public abstract void deletePost();


    @Override
    public String toString()
    {
        return author + "\n" + timestamp + "\n\n\t///////////////\n\t" + content + "\n\t///////////////";
    }

    @Override
    public boolean equals(Object otherPost)
    {
        if(otherPost instanceof Post)
        {
            Post post = (Post) otherPost;

            //return statement for the program where timestamp is always different
            return this.author.equalsIgnoreCase(post.author) && this.content.equalsIgnoreCase(post.content);

            //return statement for thr program where the time stamp can be the same
            /*
            return this.author.equalsIgnoreCase(post.author) && this.content.equalsIgnoreCase(post.content)
                    && this.timestamp.equals(post.timestamp);
                    */
        }
        else
        {
            return false;
        }
    }
    //First, will compare subclass name
    //Second, will compare author of the post and sort in alphabetical order and based on capitalization
    //Third, will compare timestamp of the post
    @Override
    public int compareTo(Post otherPost)
    {
        if(getClass().getSimpleName().equals(otherPost.getClass().getSimpleName()))
        {
            if (author.equals(otherPost.author))
            {
                return timestamp.compareTo(otherPost.timestamp);
            }
            else if (author.toLowerCase().toCharArray()[0] == otherPost.author.toLowerCase().toCharArray()[0])
            {
                return author.compareTo(otherPost.author);
            }
            else
            {
                return author.compareToIgnoreCase(otherPost.author);
            }
        }
        else
        {
            return getClass().getSimpleName().compareTo(otherPost.getClass().getSimpleName());
        }
    }
}
