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
    //TODO: fix spacing for labels etc.
    public CellView() {
        cellView = new VBox();
        cellView.getStyleClass().add("cellView");
        cellView.setPrefWidth(500);

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

    public Parent getParent() { return cellView; }
}
