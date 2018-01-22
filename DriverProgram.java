import java.util.*;

public class DriverProgram
{
    public static void main (String [] args)
    {
        ArrayList <Post> posts = new ArrayList <Post> ();
        String menuSelect;

        do
        {
            displayMainMenu();

            menuSelect = Post.scan.nextLine();
            if (menuSelect.equalsIgnoreCase("p"))
            {
                //Print all posts
                printPosts(posts);
            }
            else if (menuSelect.equalsIgnoreCase("a"))
            {
                //Add New Post
                addNewPost(posts);
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

    public static void displayMainMenu()
    {
        System.out.println("What would you like to do?");
        System.out.println(
              "[   p ] Print all posts.\n"
            + "[   a ] Add a new post.\n"
            + "[   i ] Interact with posts.\n"
            + "[   d ] Delete a post.\n"
            + "[ cmp ] Compare two posts.\n"
            + "[   q ] Quit.\n");
    }

    public static void comparePosts(ArrayList<Post> posts)
    {
        //Test post comparisons
        System.out.println(posts.get(0).equals(posts.get(1)));
    }

    public static void interactWithPosts(ArrayList<Post> posts)
    {
        //Interact with posts
        for(Post p : posts)
        {
            if(p instanceof TwitterPost)
            {
                ((TwitterPost) p).follow();
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

    public static void deleteLocalPost(ArrayList<Post> posts)
    {
        //Test deleting posts
        posts.get(0).deletePost();
        posts.remove(0);
    }

    public static void addNewPost(ArrayList<Post> posts)
    {
        String author;
        String content;
        String response;

        //Add New Post
        System.out.println("Would you like to post on the social media site? (Y/N)");
        response = Post.scan.nextLine();
        if (response.equalsIgnoreCase("y"))
        {
            System.out.println("Name: ");
            author = Post.scan.nextLine();
            System.out.println("Message content:");
            content = Post.scan.nextLine();
            System.out.println("What site would you like to post on?" +
                    "\n1 for Facebook" +
                    "\n2 for Instagram" +
                    "\n3 for Twitter ");
            int siteNumberAnswer = Integer.parseInt(Post.scan.nextLine());
            switch (siteNumberAnswer)
            {
                case 1:
                    //Create Facebook Post
                    makeNewFacebookPost(posts, author, content);
                    break;
                case 2:
                    //Create Instagram Post
                    makeNewInstagramPost(posts, author, content);
                    break;
                case 3:
                    //Create Twitter Post
                    makeNewTwitterPost(posts, author, content);
                    break;
                default:
                    //Default Post
                    break;
            }
        }//"Would you like to post on the social media site? (Y/N)";
    }

    //Display Posts
    public static void printPosts(ArrayList<Post> posts)
    {
        int count = 0;
        for(Post p : posts)
        {
            System.out.println("Post #" + (++count) + p.toString() + "\n_______________\n");
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

    public static void makeNewFacebookPost(ArrayList<Post> posts, String author,
            String content)
    {
        //Create Facebook Post
        System.out.println("Choose visibility (by default it's Public):" +
                "\n1 for Public" +
                "\n2 for Friends" +
                "\n3 for Friend Except..." +
                "\n4 for Specific Friends" +
                "\n5 for Only Me");
        int privacyNumberAnswer = Integer.parseInt(Post.scan.nextLine());
        Privacy privacy = Privacy.PUBLIC;
        privacy = getPrivacy(privacyNumberAnswer);

        posts.add(new FacebookPost(author, content, new Date(), privacy));
    }//makeNewFacebookPost()

    public static void makeNewInstagramPost(ArrayList<Post> posts, String author,
            String content)
    {
        //Create Instagram Post
        System.out.println("Would you like to save this post to collection? (Y/N)");
        String collectionAnswer = Post.scan.nextLine();
        boolean saveToCollection = collectionAnswer.equalsIgnoreCase("y");
        posts.add(new InstagramPost(author, content, new Date(), saveToCollection));
    }//makeNewInstagramPost


    public static void makeNewTwitterPost(ArrayList<Post> posts, String author,
            String content)
    {
        System.out.println("Please add Username that starts with '@'");
        String userName = Post.scan.nextLine();
        posts.add(new TwitterPost(author, content, new Date(), userName));
    }//makeNewTwitterPost
}//Driver Program

/**
 * TODO:
 *  - read post data from a text file (change to api later?)
 *  - create post code, package in a module
 */
