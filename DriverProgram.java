import java.util.*;

public class DriverProgram
{
    public static void main (String [] args)
    {
        Date date;
        String author;
        String content;
        String response;
        Privacy privacy = Privacy.PUBLIC;
        boolean saveToCollection;
        String userName;
        ArrayList <Post> posts = new ArrayList <Post> ();
        Scanner scan = new Scanner (System.in);
        do
        {
            date = new Date();
            System.out.println("Would you like to post on the social media site? (Y/N)");
            response = scan.nextLine();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Name: ");
                author = scan.nextLine();
                System.out.println("Message content:");
                content = scan.nextLine();
                System.out.println("What site would you like to post on?" +
                        "\n1 for Facebook" +
                        "\n2 for Instagram" +
                        "\n3 for Twitter ");
                int siteNumberAnswer = Integer.parseInt(scan.nextLine());
                if (siteNumberAnswer == 1) {
                    System.out.println("Choose visibility (by default it's Public):" +
                            "\n1 for Public" +
                            "\n2 for Friends" +
                            "\n3 for Friend Except..." +
                            "\n4 for Specific Friends" +
                            "\n5 for Only Me");

                    int privacyNumberAnswer = Integer.parseInt(scan.nextLine());
                    if (privacyNumberAnswer == 1) {
                        privacy = Privacy.PUBLIC;
                    }
                    if (privacyNumberAnswer == 2) {
                        privacy = Privacy.FRIENDS;
                    }
                    if (privacyNumberAnswer == 3) {
                        privacy = Privacy.FRIENDS_EXCEPT;
                    }
                    if (privacyNumberAnswer == 4) {
                        privacy = Privacy.SPECIFIC_FRIENDS;
                    }
                    if (privacyNumberAnswer == 5) {
                        privacy = Privacy.ONLY_ME;
                    }
                    posts.add(new FacebookPost(author, content, date, privacy));
                }
                if (siteNumberAnswer == 2) {
                    System.out.println("Would you like to save this post to collection? (Y/N)");
                    String collectionAnswer = scan.nextLine();
                    if(collectionAnswer.equalsIgnoreCase("y"))
                    {
                        saveToCollection = true;
                    }
                    else
                    {
                        saveToCollection = false;
                    }
                    posts.add(new InstagramPost(author, content, date, saveToCollection));
                }
                if (siteNumberAnswer == 3)
                {
                    System.out.println("Please add Username that starts with '@'");
                    userName = scan.nextLine();
                    posts.add(new TwitterPost(author, content, date, userName));
                }
            }
        }
        while(response.equalsIgnoreCase("y"));

        for(Post p : posts)
        {
            System.out.println(p.toString() + "\n_______________\n");
        }

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

        posts.get(0).deletePost();
        posts.remove(0);

        for(Post p : posts)
        {
            System.out.println(p.toString() + "\n_______________\n");
        }

        System.out.println(posts.get(0).equals(posts.get(1)));
    }
}
