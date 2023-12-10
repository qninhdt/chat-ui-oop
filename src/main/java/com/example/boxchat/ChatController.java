package com.example.boxchat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ChatController {
    @FXML
    private ListView<String> messageList;
    @FXML
    private TextField inputField;
    @FXML
    private Button sendButton;

    @FXML
    public void initialize() {
        Platform.runLater(() -> inputField.requestFocus());

        sendButton.setOnAction(event -> handleSendMessage());

        inputField.setOnAction(event -> handleSendMessage());

        messageList.setCellFactory(param -> createListCell());
    }

    /**
     * Handles the action of sending a message.
     */
    private void handleSendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            addMessage(" " + message + " ");
            addMessage(" Auto response: " + message + " ");
            inputField.clear();
        }
    }

    /**
     * Adds a message to the message list.
     * 
     * @param message the message to add
     */
    private void addMessage(String message) {
        messageList.getItems().add(message);
    }

    /**
     * Creates a list cell for the message list.
     * 
     * @return the list cell
     */
    private ListCell<String> createListCell() {
        return new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label label = createLabel(item);
                    setGraphic(label);
                    setAlignmentAndStyle(item, label);
                }
            }

            private Label createLabel(String item) {
                Label label = new Label(item);
                label.setTextFill(Color.WHITE);
                label.setPrefWidth(label.getText().length() * 6); // adjust this value as needed
                setLabelBackground(item, label);
                return label;
            }

            private void setLabelBackground(String item, Label label) {
                Color bgColor = item.startsWith(" Auto response: ") ? Color.DEEPPINK : Color.DARKGRAY;
                Background background = new Background(new BackgroundFill(bgColor, new CornerRadii(20), null));
                label.setBackground(background);
            }

            private void setAlignmentAndStyle(String item, Label label) {
                if (item.startsWith(" Auto response: ")) {
                    setAlignment(Pos.CENTER_RIGHT); // Align to the right
                    label.setStyle("-fx-alignment: TOP-RIGHT;");
                } else {
                    setAlignment(Pos.CENTER_LEFT); // Align to the left
                    label.setStyle("-fx-alignment: TOP-LEFT;");
                }
            }
        };
    }
}
