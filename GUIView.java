import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.Arrays;

//TODO: rename this class? e.g. TableOfPosts ?
public class GUIView {

    private ScrollPane tableViewSP;
    private VBox tableView;
    private final static int V_GAP = 20;
    private Group root;
    private  VBox vBox1;
    private ArrayList<CellView> cells;
    private RadioButton displayPosts, addPost;
    private Button submitButton;
    private ComboBox <Privacy> privacyOption;
    private CheckBox saveToCollection;
    private Label userNameLabel;
    private TextField userName;


    /**
     * Make a table of cells, and attach it to the
     * pane that's passed in.
     */
    public GUIView() {
        // + Group ---------------------
        // | + HBox ------------------
        // | | + Pane1 --------------
        // | | | + Scroll Pane -----
        // | | | | + VBox ----------
        // | | | | | + CellView ----
        // | | | | | + CellView ----
        // | | | | | + ...more CellView
        // | | + Pane2 --------------
        // | | | + VBox ------------


        root = new Group();
        root.getStyleClass().add("root");

        HBox hbox = new HBox();
        hbox.getStyleClass().add("hbox");
        root.getChildren().add(hbox);

        // Left Side Pane : Display Posts Here
        Pane pane1 = new FlowPane();
        pane1.getStyleClass().add("pane1");
        pane1.setPrefSize(600,800);
        hbox.getChildren().add(pane1);

        tableViewSP = new ScrollPane();
        tableViewSP.prefWidthProperty().bind(pane1.widthProperty());
        tableViewSP.prefHeightProperty().bind(pane1.heightProperty());
        tableView = new VBox();
        tableView.getStyleClass().add("tableView");
        tableView.setSpacing(V_GAP);
        tableViewSP.setContent(tableView);

        pane1.getChildren().add(tableViewSP);

        //Right Side Pane : Controller Panel Here
        //TODO: Maybe add controls to create posts here?
        Pane pane2 = new FlowPane();
        pane2.getStyleClass().add("pane2");
        pane2.setPrefSize(600,800);
        hbox.getChildren().add(pane2);

        vBox1 = new VBox();
        vBox1.getStyleClass().add("vBox1");
        vBox1.setSpacing(V_GAP);
        pane2.getChildren().add(vBox1);

        displayMenuView();


    }

    private void displayMenuView()
    {
        Text message = new Text ("What would you like to do?");
        message.setFont(Font.font("Comic Sans MS", 20));


        ToggleGroup displayMenu = new ToggleGroup();

        displayPosts = new RadioButton("View all the posts");
        displayPosts.setToggleGroup(displayMenu);
        addPost = new RadioButton("Add a new Post");
        addPost.setToggleGroup(displayMenu);

        submitButton = new Button ("Submit");

        vBox1.setMargin(message, new Insets(100, 200, 50, 200));
        vBox1.setMargin(displayPosts, new Insets(0, 200, 10, 200));
        vBox1.setMargin(addPost, new Insets(0, 200, 10, 200));
        vBox1.setMargin(submitButton, new Insets(100, 275, 50, 275));


        vBox1.getChildren().add(message);
        vBox1.getChildren().add(displayPosts);
        vBox1.getChildren().add(addPost);
        vBox1.getChildren().add(submitButton);

        submitButton.setOnAction(this::handleAction);

    }

    private void addPost()
    {
        vBox1.getChildren().clear();

        Label nameLabel = new Label ("Name:");
        TextField name = new TextField();
        Label messageLabel = new Label("Message");
        TextArea message = new TextArea();

        ObservableList<String> siteOption = FXCollections.observableArrayList(
                "Facebook Post",
                "Instagram Post",
                "Twitter Post"
        );
        ComboBox <String> siteOptionBox = new ComboBox(siteOption);
        siteOptionBox.setValue("Choose site");
        siteOptionBox.getSelectionModel().selectedItemProperty().addListener(this::selectSite);

        ObservableList<Privacy> privacy = FXCollections.observableArrayList(Arrays.asList(Privacy.values()));
        privacyOption = new ComboBox(privacy);
        privacyOption.setValue(Privacy.PUBLIC);
        privacyOption.setVisible(false);

        saveToCollection = new CheckBox("Save to collection");
        saveToCollection.setVisible(false);

        userNameLabel = new Label("Username that starts with '@'");
        userName = new TextField();
        userNameLabel.setVisible(false);
        userName.setVisible(false);

        Button postButton = new Button("Post");
        vBox1.setMargin(postButton, new Insets(50, 275, 0, 275));

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
            privacyOption.setVisible(true);
            saveToCollection.setVisible(false);
            userNameLabel.setVisible(false);
            userName.setVisible(false);
        }
        else if(newValue.equalsIgnoreCase("Instagram Post"))
        {

            saveToCollection.setVisible(true);
            privacyOption.setVisible(false);
            userNameLabel.setVisible(false);
            userName.setVisible(false);
        }
        else if(newValue.equalsIgnoreCase("Twitter Post"))
        {
            userNameLabel.setVisible(true);
            userName.setVisible(true);
            privacyOption.setVisible(false);
            saveToCollection.setVisible(false);
        }
    }

    public Parent getParent()
    {
        return root;
    }

    //TODO: is this allowed in a View?
    /**
     * Attaches new cells to the tableView
     */
    public void add(CellView cellView) {
        tableView.getChildren().add(cellView.getParent());
    }

    private void handleAction(ActionEvent event)
    {
        if (event.getTarget().equals(submitButton)) {
            if (addPost.isSelected()) {
                addPost();
            } else if (displayPosts.isSelected()) {

            }
        }
    }
}