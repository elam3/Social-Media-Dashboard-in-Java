import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;

public class CellView {
    
    private VBox        cellView;       //main wrapper
    private ImageView   logoImageView;
    private Label       username,
                        timestamp,
                        msg,
                        likeLabel;
    private Button      likeBtn;
    private int         likeCount;
    private static final String DEFAULT_IMG_PATH = "default.png";

    public CellView(Post post) {
        likeCount = 0;

        cellView = new VBox();
        cellView.getStyleClass().add("cellView");
        cellView.setSpacing(10);
        cellView.setPrefWidth(580);

        //TODO: separate what varies?
        Image logoImage;
        if (post instanceof FacebookPost)
            logoImage = new Image("assets/fb.png");
        else if (post instanceof InstagramPost)
            logoImage = new Image("assets/ig.png");
        else if (post instanceof TwitterPost)
            logoImage = new Image("assets/tw.png");
        else
            logoImage = new Image(DEFAULT_IMG_PATH);
        logoImageView = new ImageView(logoImage);
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
        HBox likeHBox = new HBox();
        likeLabel = new Label();
        reDrawLikeLabel();
        likeBtn = new Button("Like");
        likeBtn.setOnAction(this::likeBtnOnClick);
        likeHBox.setSpacing(10);
        likeHBox.getChildren().addAll(likeLabel, likeBtn);
        cellView.getChildren().add(likeHBox);
    }

    public void likeBtnOnClick(ActionEvent event) {
        likeCount++;
        reDrawLikeLabel();
    }
    private void reDrawLikeLabel() {
        likeLabel.setText(Integer.toString(likeCount));
    }
    public int getLikeCount() {
        return likeCount;
    }
    public void resetLikeCount() {
        likeCount = 0;
    }

    public Parent getParent() { return cellView; }
}
