import javafx.scene.layout.*;
import javafx.scene.control.*;

//TODO: rename this class? e.g. TableOfPosts ?
public class GUIView {
    
    private ScrollPane tableViewSP;
    private VBox tableView;
    private final static int V_GAP = 20;

    /**
     * Make a table of cells, and attach it to the
     * pane that's passed in.
     */
    public GUIView(Pane pane) {
        // + Pane -----------------
        // | + Scroll Pane --------
        // | | + VBox -------------
        // | | | | + CellView -----
        // | | | | + CellView -----
        // | | | | + ...more CellView

        tableViewSP = new ScrollPane();
        tableViewSP.prefWidthProperty().bind(pane.widthProperty());
        tableViewSP.prefHeightProperty().bind(pane.heightProperty());
            tableView = new VBox();
            tableView.getStyleClass().add("tableView");
            tableView.setSpacing(V_GAP);
        tableViewSP.setContent(tableView);

        pane.getChildren().add(tableViewSP);
    }

    //TODO: is this allowed in a View?
    /**
     * Attaches new cells to the tableView
     */
    public void add(CellView cellView) {
        tableView.getChildren().add(cellView.getParent());
    }
}
