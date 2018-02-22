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
    private GUIView tableView;

    // ArrayList of Maps containing the models,
    // where each string key points to
    // a Post object, and their CellView counterpart
    private ArrayList<Map<String,Object>> masterList;
    private final static String POST_KEY = "post",
                                CELL_KEY = "cellView";

    public GUIDriver()
    {
        tableView = new GUIView();
        masterList = new ArrayList<>();

        tableView.setPostAction(this::post);
        tableView.setBoostBtnOnAction(this::boostBtnOnClick);
        tableView.interactBtnOnClick(this::setInteractBtn);
    }

    @Override
    public void start(Stage primaryStage)
    {
        GUIDriver controller = new GUIDriver();

        Scene scene = new Scene(
            controller.tableView.getParent(),
            1200,
            800
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
        String name = tableView.getNameField();
        String content = tableView.getMessage();
        int siteNumber = tableView.getSiteNumber();
        Privacy privacy = tableView.getPrivacy();
        boolean saveToCollection = tableView.getSaveToCollection();
        String userName = tableView.getUserName();

        // Create Post object
        Post newPost = PostFactory.newPost(name, content, siteNumber,
                privacy, saveToCollection, userName);

        // Create CellView object
        CellView newCellView = new CellView();

        // Bookkeeping/wrapper for Post & CellView object
        HashMap<String,Object> map = new HashMap<>();
        map.put(POST_KEY, newPost);
        map.put(CELL_KEY, newCellView);
        masterList.add(0,map);

        newCellView.setUsername(newPost.getAuthor());
        LocalDateTime time = newPost.getTimestamp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm:ss a");
        String timestamp = time.format(formatter);
        newCellView.setTimestamp(timestamp);
        newCellView.setContent(newPost.getContent());

        if (newPost instanceof FacebookPost) {
            newCellView.setImageLogo("assets/fb.png");
            newCellView.setInteractLabel(
                ((FacebookPost)newPost).addLocation()
            );
        }
        else if (newPost instanceof InstagramPost) {
            newCellView.setImageLogo("assets/ig.png");
            newCellView.setInteractLabel(
                ((InstagramPost)newPost).sendToFriend()
            );
        }
        else if (newPost instanceof TwitterPost) {
            newCellView.setImageLogo("assets/tw.png");
            newCellView.setInteractLabel(
                ((TwitterPost)newPost).follow()
            );
        }

        this.tableView.add(newCellView);
    }

    public void boostBtnOnClick(ActionEvent event) {
        for (Map<String,Object> map : masterList) {
            //Post post = (Post)map.get(POST_KEY);
            CellView cell = (CellView)map.get(CELL_KEY);
            cell.boostLikeCount();
        }
    }

    public void setInteractBtn(ActionEvent event) {
        for (Map<String,Object> map : masterList) {
            Post post = (Post)map.get(POST_KEY);
            CellView cell = (CellView)map.get(CELL_KEY);
            cell.interact();
        }
    }
    private String getPostType(Post post) {
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

