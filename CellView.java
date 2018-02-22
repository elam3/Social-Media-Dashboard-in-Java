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
                        likeLabel,
                        interactLabel;
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
        likeHBox.setSpacing(50);
        likeHBox.getChildren().addAll(likeLabel, likeBtn);

        interactLabel = new Label("");
        HBox interactHBox = new HBox(likeHBox, interactLabel);
        interactHBox.setSpacing(150);
        cellView.getChildren().add(interactHBox);
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
        reDrawLikeLabel();
    }
    public void boostLikeCount() {
        while (likeCount<9999) {
            likeCount++;
            reDrawLikeLabel();
        }
    }

    public void interact() {
        //interactLabel.;
    }

    public Parent getParent() { return cellView; }
}
