import java.util.*;
import java.time.*;
import java.time.format.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.ActionEvent;

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
    private GUIView view;

    // Hashtable contains pair of CellView object and Post object,
    // that relate to each other
    private Hashtable<Object,Object> table;
    private Set<Object> keys;

    public GUIDriver()
    {
        view = new GUIView();
        table = new Hashtable<>();

        view.setPostAction(this::post);
        view.setBoostBtnOnAction(this::boostBtnOnClick);
        view.interactBtnOnClick(this::setInteractBtn);
    }

    @Override
    public void start(Stage primaryStage)
    {
        GUIDriver controller = new GUIDriver();

        Scene scene = new Scene(
            controller.view.getParent(),
            1200,
            700
        );

        scene.getStylesheets().add("assets/style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Social Media");
        primaryStage.show();

    }

    public static void main (String [] args)
    {
        launch(args);
    }

    /**
     * Create a representation of a Post object as a CellView
     */
    public void post(ActionEvent event)
    {
        String name = view.getNameField();
        String content = view.getMessage();
        int siteNumber = view.getSiteNumber();
        Privacy privacy = view.getPrivacy();
        boolean saveToCollection = view.getSaveToCollection();
        String userName = view.getUserName();

        // Create Post object
        Post newPost = PostFactory.newPost(name, content, siteNumber,
                privacy, saveToCollection, userName);

        // Create CellView object
        CellView newCellView = new CellView();

        // Adding the pair of specific CellView and specific Post to the Hashtable
        table.put(newCellView, newPost);

        newCellView.setUsername(newPost.getAuthor());
        LocalDateTime time = newPost.getTimestamp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm:ss a");
        String timestamp = time.format(formatter);
        newCellView.setTimestamp(timestamp);
        newCellView.setContent(newPost.getContent());

        if (newPost instanceof FacebookPost) {
            newCellView.setImageLogo("assets/fb.png");
        }
        else if (newPost instanceof InstagramPost) {
            newCellView.setImageLogo("assets/ig.png");
        }
        else if (newPost instanceof TwitterPost) {
            newCellView.setImageLogo("assets/tw.png");
        }

        this.view.add(newCellView);
    }

    public void boostBtnOnClick(ActionEvent event) {

        keys = table.keySet();
        for (Object key : keys)
        {
            if (key instanceof CellView) {
                ((CellView) key).boostLikeCount();
            }
        }
    }

    public void setInteractBtn(ActionEvent event) {
        keys = table.keySet();
        for (Object key : keys)
        {
            if (key instanceof CellView) {
                ((CellView ) key).setInteractLabelVisible();
                if(table.get(key) instanceof FacebookPost)
                {
                    ((CellView ) key).setInteractLabel(
                            ((FacebookPost) table.get(key)).addLocation()
                    );
                }
                else if(table.get(key) instanceof InstagramPost)
                {
                    ((CellView ) key).setInteractLabel(
                            ((InstagramPost) table.get(key)).sendToFriend()
                    );
                }
                else if(table.get(key) instanceof TwitterPost)
                {
                    ((CellView ) key).setInteractLabel(
                            ((TwitterPost) table.get(key)).follow()
                    );
                }
            }
        }
    }

    private String getPostType(Post post)
    {
        if (post instanceof FacebookPost) {
            return "Facebook";
        } else if (post instanceof InstagramPost) {
            return "Instagram";
        } else if (post instanceof TwitterPost) {
            return "Twitter";
        } else throw new IllegalStateException();
    }

}// Driver Program

/**
 * TODO:
 *  - read post data from a text file (change to api later?)
 *  - input validation
 */

