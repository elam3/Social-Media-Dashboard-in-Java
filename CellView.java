import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;

public class CellView {
    
    private VBox        cellView;       //main wrapper
    private Image       logoImage;
    private ImageView   logoImageView;
    private Label       username,
                        timestamp,
                        msg,
                        likeLabel,
                        interactLabel;
    private Button      likeBtn;
    private int         likeCount;
    private static final String DEFAULT_IMG_PATH = "assets/default.png";

    public CellView() {
        //TODO: move this out?
        //Does likeCount belong in a model? Or in a view? Ctrlr even?
        likeCount = 0;

        cellView = new VBox();
        cellView.getStyleClass().add("cellView");
        cellView.setSpacing(10);
        cellView.setPrefWidth(580);

        logoImage = new Image(DEFAULT_IMG_PATH);
        logoImageView = new ImageView(logoImage);
        cellView.getChildren().add(logoImageView);
     
        username = new Label();
        cellView.getChildren().add(username);
      
        timestamp = new Label();
        cellView.getChildren().add(timestamp);
       
        msg = new Label();
        msg.setWrapText(true);
        cellView.getChildren().add(msg);
        
        HBox likeHBox = new HBox();
        likeLabel = new Label();
        reDrawLikeLabel();
        likeBtn = new Button("Like");
        likeBtn.setOnAction(this::likeBtnOnClick);
        likeHBox.setSpacing(50);
        likeHBox.getChildren().addAll(likeLabel, likeBtn);

        interactLabel = new Label();
        interactLabel.setVisible(false);

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
        this.setInteractLabelVisible();
    }
    private void setInteractLabelVisible() {
        interactLabel.setVisible(true);
    }

    public void setInteractLabel(String interact) {
        interactLabel.setText(interact);
    }

    public String getUsername() {
        return this.username.getText();
    }
    public void setUsername(String username) {
        this.username.setText(username);
    }

    public String getTimestamp() {
        return this.timestamp.getText();
    }
    public void setTimestamp(String timestamp) {
        this.timestamp.setText(timestamp);
    }

    public String getContent() {
        return this.msg.getText();
    }
    public void setContent(String content) {
        this.msg.setText(content);
    }

    public void setImageLogo(String imgUrl) {
        logoImageView.setImage(new Image(imgUrl));
    }

    public Parent getParent() { return cellView; }
}
