import javafx.application.Application;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class ComboBoxDemo extends Application {
  // Declare an array of Strings for flag titles
  private final String[] flagTitles = {
      "Canada", "China", "Denmark", 
      "France", "Germany", "India", "Norway", "United Kingdom",
      "United States of America" };

  // Declare an ImageView array for the national flags of 9 countries
  private final ImageView[] flagImage = {
      new ImageView("flags/ca.gif"),
      new ImageView("flags/china.gif"),
      new ImageView("flags/denmark.gif"),
      new ImageView("flags/fr.gif"),
      new ImageView("flags/germany.gif"),
      new ImageView("flags/india.gif"),
      new ImageView("flags/norway.gif"),
      new ImageView("flags/uk.gif"),
      new ImageView("flags/us.gif")      
    };

  // Declare an array of strings for flag descriptions
  private final String[] flagDescription =
  {
      "This is the flag of Canada",
      "This is the flag of China",
      "This is the flag of Denmark",
      "This is the flag of France",
      "This is the flag of Germany",
      "This is the flag of India",
      "This is the flag of Norway",
      "This is the flag of the United Kingdom",
      "This is the flag of the United States"
  };

  // Declare and create a description pane
  private DescriptionPane descriptionPane = new DescriptionPane();

  // Create a combo box for selecting countries
  private ComboBox<String> cbo = new ComboBox<>(); // flagTitles);

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {

    // Set the first country (Canada) for display
    setDisplay(0);

    // Add combo box and description pane to the border pane
    BorderPane pane = new BorderPane();
      
    BorderPane paneForComboBox = new BorderPane();
    paneForComboBox.setLeft(new Label("Select a country: "));
    paneForComboBox.setCenter(cbo);
    pane.setTop(paneForComboBox);
    cbo.setPrefWidth(400);
    cbo.setValue("Canada");
    
    ObservableList<String> items = 
      FXCollections.observableArrayList(flagTitles);
    cbo.getItems().addAll(items); 
    pane.setCenter(descriptionPane);
    
    // Display the selected country
    cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 450, 170);
    primaryStage.setTitle("Combo Box Demo"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /** Set display information on the description pane */
  public void setDisplay(int index) {
    descriptionPane.setTitle(flagTitles[index]);
    descriptionPane.setImageView(flagImage[index]);
    descriptionPane.setDescription(flagDescription[index]);
  }

  public static void main(String[] args) {
    launch(args);
  }
}

class DescriptionPane extends BorderPane {
  /** Label for displaying an image and a title */
  private Label lblImageTitle = new Label();

  /** Text area for displaying text */
  private TextArea taDescription = new TextArea();
  
  public DescriptionPane() {
    // Center the icon and text and place the text under the icon
    lblImageTitle.setContentDisplay(ContentDisplay.TOP);
    lblImageTitle.setPrefSize(200,  100);
    
    // Set the font in the label and the text field
    lblImageTitle.setFont(new Font("SansSerif", 16));
    taDescription.setFont(new Font("Serif", 14));
    
    taDescription.setWrapText(true);
    taDescription.setEditable(false);

    // Create a scroll pane to hold the text area
    ScrollPane scrollPane = new ScrollPane(taDescription);

    // Place label and scroll pane in the border pane
    setLeft(lblImageTitle);
    setCenter(scrollPane);
    setPadding(new Insets(5, 5, 5, 5));
  }
  
  /** Set the title */
  public void setTitle(String title) {
    lblImageTitle.setText(title);
  }

  /** Set the image view */
  public void setImageView(ImageView icon) {
    lblImageTitle.setGraphic(icon);
  }

  /** Set the text description */
  public void setDescription(String text) {
    taDescription.setText(text);
  }
}