import java.util.*;

public class PostsCollection
{
    private ArrayList<Post> posts;
    public PostsCollection()
    {
        posts = new ArrayList<>();
    }

    public void addPost(String author, String content, int siteNumberAnswer, Privacy privacy,
                        boolean saveToCollection, String userName)
    {
        posts.add(PostFactory.newPost(author, content, siteNumberAnswer, privacy, saveToCollection, userName));
    }

    public ArrayList<Post> getPosts() {
        Collections.sort(posts);
        return posts;
    }
}
