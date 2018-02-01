import java.time.*;
import java.util.*;

/**
 * Authors: Tatiana K., Edison L.
 *
 * DriverProgram is a text-based interface that allows a user to
 * create, remove, and print Post objects that resembles a post
 * from social media networks like Facebook, Twitter, and
 * Instagram.
 */
public class DriverProgram
{
    private static Scanner scan = new Scanner(System.in);

    public static void main (String [] args)
    {
        //Hold all posts
        ArrayList <Post> posts = new ArrayList <Post> ();
        String menuSelect;

        do
        {
            displayMainMenu();

            menuSelect = scan.nextLine();
            if (menuSelect.equalsIgnoreCase("p"))
            {
                //Print all posts
                printPosts(posts);
            }
            else if (menuSelect.equalsIgnoreCase("a"))
            {
                // M3 USING FACTORY
                //Add New Post
                posts.add(PostFactory.newPost());
            }
            else if (menuSelect.equalsIgnoreCase("i"))
            {
                //Interact with posts
                interactWithPosts(posts);
            }
            else if (menuSelect.equalsIgnoreCase("d"))
            {
                //Delete a post
                deleteLocalPost(posts);
            }
            else if (menuSelect.equalsIgnoreCase("cmp"))
            {
                //Compare two posts
                comparePosts(posts);
            }

        }
        while(!menuSelect.equalsIgnoreCase("q"));
    }//main()

    //Print main menu
    public static void displayMainMenu()
    {
        System.out.println("Total: " + Post.getPostsCount() + " post(s)");
        System.out.println("What would you like to do?");
        System.out.println(
              "[   p ] Print all posts.\n"
            + "[   a ] Add a new post.\n"
            + "[   i ] Interact with posts.\n"
            + "[   d ] Delete a post.\n"
            + "[ cmp ] Compare two posts.\n"
            + "[   q ] Quit.\n");
    }

    //Compare two posts in the array list
    //TODO: allow users to specify which two posts to compare
    public static void comparePosts(ArrayList<Post> posts)
    {
        //Test post comparisons
        System.out.println(posts.get(0).equals(posts.get(1)));
    }

    //Invoke class-specific methods
    //TODO: allow user to specify which post to interact with
    public static void interactWithPosts(ArrayList<Post> posts)
    {
        int userResponse;
        Post modifiedPost;
        //Interact with posts
        for(Post p : posts)
        {
            if(p instanceof TwitterPost)
            {
                System.out.println("What would you like to do?");
                System.out.println(
                "1 - Follow\n" +
                "2 - Convert to Facebook post\n");

                userResponse = Integer.parseInt(scan.nextLine());

                if(userResponse == 1)
                {
                    ((TwitterPost) p).follow();
                }
                if(userResponse == 2)
                {
                    modifiedPost = FacebookPost.parseFacebookPost((TwitterPost) p);
                    posts.remove(p);
                    posts.add(modifiedPost);
                }
            }
            if(p instanceof FacebookPost)
            {
                ((FacebookPost) p).addLocation();
            }
            if(p instanceof InstagramPost)
            {
                ((InstagramPost) p).sendToFriend();
            }
        }
    }

    //Remove a post from array list
    public static void deleteLocalPost(ArrayList<Post> posts)
    {
        //Test deleting posts
        posts.get(0).deletePost();
        posts.remove(0);
        Post.setPostsCount(Post.getPostsCount()-1);

    }

    //Sort and Display Posts
    public static void printPosts(ArrayList<Post> posts)
    {
	    if (posts.size() == 0)
	    {
		    System.out.println("No posts yet. Start by adding new posts!");
		    return;
	    }

	    Collections.sort(posts);
        int count = 0;
        for(Post p : posts)
        {
            System.out.println("Post #" + (++count) + p.toString() + "\n_______________\n");
        }
    }
}//Driver Program

/**
 * TODO:
 *  - read post data from a text file (change to api later?)
 *  - input validation
 */
