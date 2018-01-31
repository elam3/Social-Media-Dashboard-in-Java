import java.time.*;
import java.util.*;

public class PostFactory {

    private static Scanner scan = new Scanner(System.in);

    //Create New Post
    public static Post newPost()
    {
        String author;
        String content;
        String response;
        Post post;

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
            switch (siteNumberAnswer) {
                case 1:
                    //Create Facebook Post
                    post = newFacebookPost(author, content);
                    break;
                case 2:
                    //Create Instagram Post
                    post = newInstagramPost(author, content);
                    break;
                case 3:
                    //Create Twitter Post
                   post = newTwitterPost(author, content);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        else
        {
            throw new IllegalArgumentException();
        }//"Would you like to post on the social media site? (Y/N)";
        return post;
    }//newPost()

    //Retrieve Privacy
    private static Privacy getPrivacy(int privacyNumberAnswer)
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

    //Create Facebook Post
    private static FacebookPost newFacebookPost(String author, String content)
    {
        System.out.println("Choose visibility (by default it's Public):" +
                "\n1 for Public" +
                "\n2 for Friends" +
                "\n3 for Friend Except..." +
                "\n4 for Specific Friends" +
                "\n5 for Only Me");

        int privacyNumberAnswer = Integer.parseInt(scan.nextLine());
        Privacy privacy;
        privacy = getPrivacy(privacyNumberAnswer);

        return new FacebookPost(author, content, LocalDate.now(), privacy);
    }//newFacebookPost()

    //Create Instagram Post
    private static InstagramPost newInstagramPost(String author, String content)
    {
        System.out.println("Would you like to save this post to collection? (Y/N)");
        String collectionAnswer = scan.nextLine();
        boolean saveToCollection = collectionAnswer.equalsIgnoreCase("y");

        return new InstagramPost(author, content, LocalDate.now(), saveToCollection);
    }//newInstagramPost()

    //Create Twitter Post
    private static TwitterPost newTwitterPost(String author, String content)
    {
        System.out.println("Please add Username that starts with '@'");
        String userName = scan.nextLine();
        return new TwitterPost(author, content, LocalDate.now(), userName);
    }//newTwitterPost()
}
