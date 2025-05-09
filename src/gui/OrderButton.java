package gui;

import container.Container;
import container.ContainerPlate;
import container.ContainerTray;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class OrderButton extends StackPane {
    private final Pane root;
    private final double x, y;
    private Container currentContainer;

    public OrderButton(Pane root, double x, double y) {
        this.root = root;
        this.x = x;
        this.y = y;

        ImageView imageView = new ImageView(new Image(ClassLoader.getSystemResource("order.png").toString()));
        setAlignment(imageView, Pos.CENTER);
        this.getChildren().add(imageView);

        setLayoutX(x+45);
        setLayoutY(y+50);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem ribeyeItem = new MenuItem("Sliced Ribeye");
        MenuItem tongueItem = new MenuItem("Sliced Tongue");
        MenuItem porkItem = new MenuItem("Sliced Pork");
        MenuItem baconItem = new MenuItem("Bacon");
        MenuItem shrimpItem = new MenuItem("Shrimp");
        MenuItem spinachItem = new MenuItem("Spinach");
        MenuItem chineseCabbageItem = new MenuItem("Chinese cabbage");
        MenuItem salmonItem = new MenuItem("Sliced Salmon");
        MenuItem watermelonItem = new MenuItem("Watermelon");
        MenuItem frenchfriesItem = new MenuItem("French Fries");
        
        contextMenu.getItems().addAll(ribeyeItem, tongueItem, porkItem, baconItem,
        		shrimpItem, spinachItem, chineseCabbageItem, salmonItem, watermelonItem, frenchfriesItem);
        
        ribeyeItem.setOnAction(e -> createTray("ribeye"));
        tongueItem.setOnAction(e -> createTray("tongue"));
        porkItem.setOnAction(e -> createTray("pork"));
        baconItem.setOnAction(e -> createTray("bacon"));
        shrimpItem.setOnAction(e -> createTray("shrimp"));
        spinachItem.setOnAction(e -> createTray("spinach"));
        chineseCabbageItem.setOnAction(e -> createTray("chinese cabbage"));
        salmonItem.setOnAction(e -> createPlate("salmon"));
        watermelonItem.setOnAction(e -> createPlate("watermelon"));
        frenchfriesItem.setOnAction(e -> createPlate("french fries"));

        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(this, event.getScreenX(), event.getScreenY());
            }
        });
    }

    private void createTray(String ingredientName) {
        currentContainer = new ContainerTray(ingredientName);
        currentContainer.setLayoutX(x);
        currentContainer.setLayoutY(y);
        root.getChildren().add(currentContainer);
    }
    
    private void createPlate(String ingredientName) {
        if (currentContainer != null) {
            root.getChildren().remove(currentContainer);
        }
        
        currentContainer = new ContainerPlate(ingredientName);
        currentContainer.setLayoutX(x);
        currentContainer.setLayoutY(y);
        root.getChildren().add(currentContainer);
    }
    
	public Container getCurrentContainer() {
		return currentContainer;
	}

	public void setCurrentContainer(Container currentContainer) {
		this.currentContainer = currentContainer;
	}
    
}