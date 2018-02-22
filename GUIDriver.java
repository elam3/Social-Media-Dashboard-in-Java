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
    private static Scanner scan = new Scanner(System.in);
    private GUIView tableView;
    private PostsCollection posts;
    private ActionEvent event;
    private Post newPost;

    public GUIDriver()
    {
        posts = new PostsCollection();
        tableView = new GUIView();

        newPost = null;

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

    public void post(ActionEvent event)
    {
        this.event = event;
        String name = tableView.getNameField();
        String content = tableView.getMessage();
        int siteNumber = tableView.getSiteNumber();
        Privacy privacy = tableView.getPrivacy();
        boolean saveToCollection = tableView.getSaveToCollection();
        String userName = tableView.getUserName();

        newPost = posts.addPost(name, content, siteNumber, privacy,
                saveToCollection, userName);

        CellView newCellView = new CellView();
        newCellView.setUsername(newPost.getAuthor());
        LocalDateTime time = newPost.getTimestamp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm:ss a");
        String timestamp = time.format(formatter);
        newCellView.setTimestamp(timestamp);
        //newCellView.setTimestamp(newPost.getTimestamp().toString());
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
        for (CellView cell : tableView.getCells()) {
            cell.boostLikeCount();
        }
    }

    public void setInteractBtn(ActionEvent event) {
        for (CellView cell : tableView.getCells()) {
            cell.interact();
        }
    }
}//Driver Program

/**
 * TODO:
 *  - read post data from a text file (change to api later?)
 *  - input validation
 */

