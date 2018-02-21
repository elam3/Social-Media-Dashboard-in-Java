import java.time.*;
import java.util.Comparator;

public abstract class Post implements Comparable<Post>
{
    private String author, content;
    private LocalDateTime timestamp;
    private static int postsCount = 0; // M2 HOMEWORK STATIC
    private Sharer sharer;

    private static class PostAuthorComparator implements Comparator<Post>
    {
        @Override
        public int compare(Post p1, Post p2)
        {
            return p1.author.compareTo(p2.author);
        }
    }
    private static class PostTimeStampComparator implements Comparator<Post>
    {
        @Override
        public int compare(Post p1, Post p2)
        {
            return p1.timestamp.compareTo(p2.timestamp);
        }
    }

    public Post(String author, String content, LocalDateTime timestamp, Sharer sharer)
    {
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        postsCount++;
        this.sharer = sharer;

    }


    public String getAuthor()
    {
        return author;
    }
    public String getContent()
    {
        return content;
    }
    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }
    public static int getPostsCount()
    {
        return postsCount;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public void setSharer(Sharer sharer)
    {
        this.sharer = sharer;
    }

    // I don't think we need setter for timestamp
    /*
    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }
    */

    public abstract void deletePost();

    public void share()
    {
        sharer.share();
    }


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

    //First,    will compare subclass name
    //Second,   will compare author of the post and sort in alphabetical order and based
    //          on capitalization
    //Third,    will compare timestamp of the post
    @Override
    public int compareTo(Post otherPost)
    {
		String myClassName      = getClass().getSimpleName(),
               otherClassName   = otherPost.getClass().getSimpleName();

        if (myClassName.equals(otherClassName))
        {
            if (author.equals(otherPost.author))
            {
                return timestamp.compareTo(otherPost.timestamp);
            }
            else if (author.toLowerCase().charAt(0) == otherPost.author.toLowerCase().charAt(0))
            {
                return author.compareTo(otherPost.author);
            }
            return author.compareToIgnoreCase(otherPost.author);
        }
            return myClassName.compareTo(otherClassName);
    }//compareTo()

    // Updating post count when the post is removed
    public static int removedPostCount()
    {
        return postsCount = postsCount - 1;
    }// removedPost()
}//class Post
