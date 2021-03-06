import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;

import java.util.ArrayList;
import java.util.Arrays;

public class GUIView {

    private ScrollPane tableViewSP;
    private VBox tableView;
    private final static int    V_GAP       = 20,
                                PREF_WIDTH  = 600,
                                PREF_HEIGHT = 700;
    private Group root;
    private  VBox vBox1;
    public ArrayList<CellView> cells;
    private RadioButton displayPosts, addPostView, interact;
    private Button submitButton, postButton, display, boostBtn, interactBtn;
    private ComboBox <Privacy> privacyOption;
    private CheckBox saveToCollection;
    private Label userNameLabel;
    private TextField name, userName;
    private TextArea message;
    protected static int siteNumber;

    public GUIView() {
        // + Group -------------------------------------
        // | + HBox ------------------------------------
        // | | + Pane1 -------------------| + Pane2 ----
        // | | | + Scroll Pane -----------| + VBox -----
        // | | | | + VBox ----------------| | ...
        // | | | | | + CellView ----------| | Control Panel Related:
        // | | | | | + CellView ----------| | - adding new posts
        // | | | | | + ...more CellView---| | - interact w/ posts

        cells = new ArrayList<>();

        root = new Group();
        root.getStyleClass().add("root");

        HBox hbox = new HBox();
        hbox.getStyleClass().add("hbox");
        root.getChildren().add(hbox);

        // Left Side Pane : Display Posts Here
        Pane pane1 = new FlowPane();
        pane1.getStyleClass().add("pane1");
        pane1.setPrefSize(PREF_WIDTH,PREF_HEIGHT);
        hbox.getChildren().add(pane1);

        tableViewSP = new ScrollPane();
        tableViewSP.prefWidthProperty().bind(pane1.widthProperty());
        tableViewSP.prefHeightProperty().bind(pane1.heightProperty());
        tableView = new VBox();
        tableView.getStyleClass().add("tableView");
        tableView.setSpacing(V_GAP);
        tableViewSP.setContent(tableView);

        pane1.getChildren().add(tableViewSP);

        // Right Side Pane : Controller Panel Here
        Pane pane2 = new FlowPane();
        pane2.getStyleClass().add("pane2");
        pane2.setPrefSize(PREF_WIDTH,PREF_HEIGHT);
        hbox.getChildren().add(pane2);

        vBox1 = new VBox();
        vBox1.getStyleClass().add("vBox1");
        vBox1.setSpacing(V_GAP);
        pane2.getChildren().add(vBox1);

        boostBtn = new Button("Boost Like Counts");
        interactBtn = new Button("Interact with Posts");

        // TODO: interactive menu
        //displayMenuView();

        // Draw from to collect data for adding a new CellView
        addPostView();

        HBox menuButtons = new HBox(boostBtn, interactBtn);
        vBox1.getChildren().add(menuButtons);

    }

    /**
     * Draw view for an interactive menu
     */
    /*private void displayMenuView()
    {
        Text message = new Text ("What would you like to do?");
        message.setFont(Font.font("Comic Sans MS", 20));

        ToggleGroup displayMenu = new ToggleGroup();

        displayPosts = new RadioButton("View all the posts");
        displayPosts.setToggleGroup(displayMenu);
        addPostView = new RadioButton("Add a new Post");
        addPostView.setToggleGroup(displayMenu);
        interact = new RadioButton("Interact with posts");
        interact.setToggleGroup(displayMenu);

        submitButton = new Button ("Submit");

        vBox1.setMargin(message, new Insets(100, 200, 50, 200));
        vBox1.setMargin(displayPosts, new Insets(0, 200, 10, 200));
        vBox1.setMargin(addPostView, new Insets(0, 200, 10, 200));
        vBox1.setMargin(interact, new Insets(0, 200, 10, 200));
        vBox1.setMargin(submitButton, new Insets(100, 275, 50, 275));

        vBox1.getChildren().add(message);
        vBox1.getChildren().add(displayPosts);
        vBox1.getChildren().add(addPostView);
        vBox1.getChildren().add(interact);
        vBox1.getChildren().add(submitButton);

        submitButton.setOnAction(this::handleAction);

    }
    */

    /**
     * Draw Pane2 Items
     */
    public void addPostView()
    {
        vBox1.getChildren().clear();

        Label nameLabel = new Label ("Name:");
        nameLabel.getStyleClass().add("addPostView-nameLabel");
        nameLabel.setTextFill(Color.WHITE);
        name = new TextField();
        Label messageLabel = new Label("Message:");
        messageLabel.getStyleClass().add("addPostView-messageLabel");
        message = new TextArea();
        message.setWrapText(true);

        ObservableList<String> siteOption =
            FXCollections.observableArrayList(
                "Facebook Post",
                "Instagram Post",
                "Twitter Post"
        );

        ComboBox <String> siteOptionBox = new ComboBox(siteOption);

        siteOptionBox.setValue("Choose site");

        siteOptionBox.getSelectionModel().selectedItemProperty()
            .addListener(this::selectSite);

        ObservableList<Privacy> privacy =
            FXCollections.observableArrayList(
                Arrays.asList(Privacy.values())
        );

        privacyOption = new ComboBox(privacy);
        privacyOption.setValue(Privacy.PUBLIC);
        privacyOption.setVisible(false);

        saveToCollection = new CheckBox("Save to collection");
        saveToCollection.setVisible(false);

        userNameLabel = new Label("Username that starts with '@'");
        userName = new TextField();
        userNameLabel.setVisible(false);
        userName.setVisible(false);

        postButton = new Button("Post");
        vBox1.setMargin(postButton, new Insets(50, 275, 0, 275));

        display = new Button("Display");
        vBox1.setMargin(display, new Insets(50, 275, 0, 275));

        vBox1.getChildren().add(nameLabel);
        vBox1.getChildren().add(name);
        vBox1.getChildren().add(messageLabel);
        vBox1.getChildren().add(message);
        vBox1.getChildren().add(siteOptionBox);
        vBox1.getChildren().add(privacyOption);
        vBox1.getChildren().add(saveToCollection);
        vBox1.getChildren().add(userNameLabel);
        vBox1.getChildren().add(userName);
        vBox1.getChildren().add(postButton);
    }

    private void selectSite(ObservableValue<? extends String> ov, String oldValue, String newValue)
    {
        if(newValue.equalsIgnoreCase("Facebook Post"))
        {
            siteNumber = 1;
            privacyOption.setVisible(true);
            saveToCollection.setVisible(false);
            userNameLabel.setVisible(false);
            userName.setVisible(false);
        }
        else if(newValue.equalsIgnoreCase("Instagram Post"))
        {

            siteNumber = 2;
            saveToCollection.setVisible(true);
            privacyOption.setVisible(false);
            userNameLabel.setVisible(false);
            userName.setVisible(false);
        }
        else if(newValue.equalsIgnoreCase("Twitter Post"))
        {
            siteNumber = 3;
            userNameLabel.setVisible(true);
            userName.setVisible(true);
            privacyOption.setVisible(false);
            saveToCollection.setVisible(false);
        }
    }

    public String getNameField()
    {
        return name.getText();
    }

    public String getMessage()
    {
        return message.getText();
    }

    public int getSiteNumber()
    {
        return siteNumber;
    }

    public Privacy getPrivacy()
    {
        if(privacyOption.getValue().equals(Privacy.PUBLIC))
        {
            return Privacy.PUBLIC;
        }
        else if(privacyOption.getValue().equals(Privacy.FRIENDS))
        {
            return Privacy.FRIENDS;
        }
        else if(privacyOption.getValue().equals(Privacy.FRIENDS_EXCEPT))
        {
            return Privacy.FRIENDS_EXCEPT;
        }
        else if(privacyOption.getValue().equals(Privacy.SPECIFIC_FRIENDS))
        {
            return Privacy.SPECIFIC_FRIENDS;
        }
        else
        {
            return Privacy.ONLY_ME;
        }
    }

    public boolean getSaveToCollection()
    {
        if(saveToCollection.isSelected())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getUserName()
    {
        return userName.getText();
    }


    /**
     * Attaches a new cell to the tableView
     */
    public void add(CellView cellView) {
        //Add to top of ArrayList<CellView>
        cells.add(0, cellView);
        reDrawCells();
    }
    public void reDrawCells() {
        tableView.getChildren().clear();
        for (CellView cell : cells) {
            tableView.getChildren().add(cell.getParent());
        }
    }


    /**
     * Hooks up the postButton to add a new cellView
     */
    public void setPostAction(EventHandler<ActionEvent> handler) {
        postButton.setOnAction(handler);
    }

    /**
     * Hooks up the BoostBtn to interact with all cellView's
     */
    public void setBoostBtnOnAction(EventHandler<ActionEvent> handler) {
        boostBtn.setOnAction(handler);
    }

    /**
     * Hooks up the interactBtn to call class-specific methods on each cell
     */
    public void interactBtnOnClick(EventHandler<ActionEvent> handler) {
        interactBtn.setOnAction(handler);
    }

    // TODO: interactive menu's action events
    /*private void handleAction(ActionEvent event)
    {
        if (event.getTarget().equals(submitButton)) {
            if (addPostView.isSelected()) {
                addPostView();
            } else if (displayPosts.isSelected()) {
                for (int i = 0; i < cells.size(); i++)
                {
                    this.add(cells.get(i));
                }
            }
            else if(interact.isSelected())
            {
                ;
            }
        }
    }
    */

    public Parent getParent()
    {
        return root;
    }
}
