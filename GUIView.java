import java.time.*;
import java.util.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class GUIView {
    
    private ScrollPane tableViewSP;
    private VBox tableView;

    public GUIView(Pane pane) {
        tableViewSP = new ScrollPane();
            tableViewSP.setPrefWidth(600);
            tableViewSP.prefHeightProperty().bind(
                pane.heightProperty()
            );
            tableView = new VBox();
            tableView.getStyleClass().add("tableView");
            tableView.setSpacing(20);
        tableViewSP.setContent(tableView);
        pane.getChildren().add(tableViewSP);
    }

    //TODO: is this allowed in a View?
    public void add(CellView cellView) {
        tableView.getChildren().add(cellView.getParent());
    }
}
