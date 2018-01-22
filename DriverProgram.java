import java.util.*;

public class DriverProgram
{
    public static void main (String [] args)
    {
        Date date = new Date();
        Post [] posts = new Post [3];
        posts [0] = new FacebookPost ("Tati","Hello Everyone!", date, Privacy.ONLY_ME);
        posts [1] = new FacebookPost("John", "I'm here", date, Privacy.PUBLIC);
        posts [2] = new InstagramPost("James", "I can see everyone", date, false);

        for(Post p : posts)
        {
            System.out.println(p.toString() + "\n_______________\n");
        }

        System.out.println(posts[1].equals(posts[0]));
    }
}
