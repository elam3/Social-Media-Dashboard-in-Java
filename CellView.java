import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class CellView {
    
    private VBox        cellView;       //main wrapper
    private ImageView   logoImageView;
    private Label       username,
                        timestamp,
                        msg;
    private Button      like;

    //TODO: pass in a Post object?
    public CellView() {
        cellView = new VBox();
        cellView.getStyleClass().add("cellView");
        cellView.setSpacing(10);
        cellView.setPrefWidth(580);

        //TODO: separate what varies?
        logoImageView = new ImageView(new Image("assets/fb.png"));
        cellView.getChildren().add(logoImageView);
        //
        username = new Label("Username");
        cellView.getChildren().add(username);
        //
        timestamp = new Label("2018-01-31 12:31:00 PST");
        cellView.getChildren().add(timestamp);
        //
        msg = new Label("This is the post content message.");
        cellView.getChildren().add(msg);
        //
        like = new Button("Like");
        cellView.getChildren().add(like);
    }

    public CellView(Post post) {
        cellView = new VBox();
        cellView.getStyleClass().add("cellView");
        cellView.setSpacing(10);
        cellView.setPrefWidth(580);

        //TODO: separate what varies?
        logoImageView = new ImageView(new Image("assets/fb.png"));
        cellView.getChildren().add(logoImageView);
        //
        String postUsername = post.getAuthor();
        username = new Label(postUsername);
        cellView.getChildren().add(username);
        //
        String postTimestamp = post.getTimestamp().toString();
        timestamp = new Label(postTimestamp);
        cellView.getChildren().add(timestamp);
        //
        String postMsg = post.getContent();
        msg = new Label(postMsg);
        cellView.getChildren().add(msg);
        //
        like = new Button("Like");
        cellView.getChildren().add(like);
    }

    public Parent getParent() { return cellView; }
}
