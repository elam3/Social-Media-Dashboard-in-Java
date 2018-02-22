import java.util.*;

public class PostsCollection
{
    private ArrayList<Post> posts;
    public PostsCollection()
    {
        posts = new ArrayList<>();
    }

    public Post addPost(String author, String content,
            int siteNumberAnswer, Privacy privacy,
            boolean saveToCollection, String userName)
    {
        Post newPost = PostFactory.newPost(
                author,
                content,
                siteNumberAnswer,
                privacy,
                saveToCollection,
                userName
        );

        posts.add(newPost);

        return newPost;
    }

    public ArrayList<Post> getPosts() {
        Collections.sort(posts);
        return posts;
    }
}
