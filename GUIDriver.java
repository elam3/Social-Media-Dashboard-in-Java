import java.time.*;
import java.util.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * Authors: Tatiana K., Edison L.
 *
 * GUIDriver is a javafx-based interface that allows a user to
 * create, remove, and print Post objects that resembles a post
 * from social media networks like Facebook, Twitter, and
 * Instagram.
 */
public class GUIDriver extends Application
{
    private static Scanner scan = new Scanner(System.in);
    private GUIView tableView;

    public GUIDriver()
    {
        // This makes the table of cells hostings Post objects
        tableView = new GUIView();
        //Add table cells
        //TODO pass in Posts to CellView constructor
        for (int i=0; i<100; i++) {
            tableView.add(new CellView());
        }
    }

    @Override
    public void start(Stage primaryStage)
    {
        GUIDriver controller = new GUIDriver();

        Scene scene = new Scene(controller.tableView.getParent(), 1200, 800);
        scene.getStylesheets().add("assets/style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Social Media");
        primaryStage.show();

    }

    public static void main (String [] args)
    {
        launch(args);

        /*
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
                //Add New Post
                addPost(posts);
            }
            else if (menuSelect.equalsIgnoreCase("i"))
            {
                //Interact with posts
                interactWithPosts(posts);
            }
            else if (menuSelect.equalsIgnoreCase("s"))
            {
                //share posts
                sharePost(posts);
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
        */
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
                        + "[   s ] Share posts.\n"
                        + "[   d ] Delete a post.\n"
                        + "[ cmp ] Compare two posts.\n"
                        + "[   q ] Quit.\n");
    }

    public static void addPost(ArrayList<Post> posts) {
        String author, content, response, userName = TwitterPost.DEFAULT_USER_NAME;
        Privacy privacy = FacebookPost.DEFAULT_PRIVACY;
        boolean saveToCollection = false;

        System.out.println("Would you like to post on the social media site? (Y/N)");
        response = scan.nextLine();

        if (response.equalsIgnoreCase("y"))
        {
            System.out.println("Name: ");
            author = scan.nextLine();
            System.out.println("Message content:");
            content = scan.nextLine();
            System.out.println("What site would you like to post on?" +
                    "\n1 for Facebook" +
                    "\n2 for Instagram" +
                    "\n3 for Twitter ");
            int siteNumberAnswer = Integer.parseInt(scan.nextLine());

            switch (siteNumberAnswer)
            {
                case 1:
                    System.out.println("Choose visibility (by default it's Public):" +
                            "\n1 for Public" +
                            "\n2 for Friends" +
                            "\n3 for Friend Except..." +
                            "\n4 for Specific Friends" +
                            "\n5 for Only Me");
                    int privacyNumberAnswer = Integer.parseInt(scan.nextLine());
                    privacy = getPrivacy(privacyNumberAnswer);
                    break;
                case 2:
                    System.out.println("Would you like to save this post to collection? (Y/N)");
                    String collectionAnswer = scan.nextLine();
                    saveToCollection = collectionAnswer.equalsIgnoreCase("y");
                    break;
                case 3:
                    System.out.println("Please add Username that starts with '@'");
                    userName = scan.nextLine();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            // M3 USING FACTORY
            posts.add(PostFactory.newPost(author, content, siteNumberAnswer, privacy, saveToCollection, userName));
        }//"Would you like to post on the social media site? (Y/N)";
        else
        {
            throw new IllegalArgumentException();
        }
    }

    //Retrieve Privacy
    public static Privacy getPrivacy(int privacyNumberAnswer)
    {
        if (privacyNumberAnswer == 1)
        {
            return Privacy.PUBLIC;
        }
        else if (privacyNumberAnswer == 2)
        {
            return Privacy.FRIENDS;
        }
        else if (privacyNumberAnswer == 3)
        {
            return Privacy.FRIENDS_EXCEPT;
        }
        else if (privacyNumberAnswer == 4)
        {
            return Privacy.SPECIFIC_FRIENDS;
        }
        else
        {//(privacyNumberAnswer == 5)
            return Privacy.ONLY_ME;
        }
    } //getPrivacy

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
                    Post.removedPostCount();
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

    //Share posts
    public static void sharePost(ArrayList<Post> posts)
    {
        for(Post p: posts)
        {
            if (p instanceof FacebookPost)
            {
                int shareRespond;
                System.out.println("How would you like to share?");
                System.out.println("" +
                        "1 - Share with all friends\n" +
                        "2 - Send to one friend");
                shareRespond = Integer.parseInt(scan.nextLine());
                if(shareRespond == 1)
                {
                    p.setSharer(new ShareWithFriends());
                }
                else if (shareRespond == 2)
                {
                    p.setSharer(new SendToFriend());
                }
                else
                {
                    throw new IllegalArgumentException();
                }
            }
            p.share();      // M3 USING STRATEGY
        }
    }

    //Remove a post from array list
    public static void deleteLocalPost(ArrayList<Post> posts)
    {
        //Test deleting posts
        posts.get(0).deletePost();
        posts.remove(0);
        Post.removedPostCount();
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

