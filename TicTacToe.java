import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private Button[][] buttons = new Button[5][5];
    private boolean playerX = true; // Player X starts the game
    private boolean gameOver = false;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Button button = new Button();
                button.setMinSize(100, 100); // Adjust size as needed
                button.setFont(Font.font("Arial", 36));
                button.setOnAction(e -> handleButtonClick(button));
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> resetGame());

        HBox hbox = new HBox(resetButton);
        hbox.setTranslateY(550);

        Scene scene = new Scene(new GridPane(), 500, 600);
        ((GridPane) scene.getRoot()).add(gridPane, 0, 0);
        ((GridPane) scene.getRoot()).add(hbox, 0, 1);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    private void handleButtonClick(Button button) {
        if (gameOver || !button.getText().isEmpty()) {
            return; // If button already has a mark or game over, do nothing
        }

        if (playerX) {
            button.setText("X");
        } else {
            button.setText("O");
        }

        if (checkForWin()) {
            displayWinner();
            gameOver = true;
        } else if (checkForDraw()) {
            displayDraw();
            gameOver = true;
        } else {
            playerX = !playerX; // Switch players
        }
    }

    private boolean checkForWin() {
        // Check rows
        for (int i = 0; i < 5; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                    buttons[i][2].getText().equals(buttons[i][3].getText()) &&
                    buttons[i][3].getText().equals(buttons[i][4].getText()) &&
                    !buttons[i][0].getText().isEmpty()) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 5; j++) {
            if (buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                    buttons[1][j].getText().equals(buttons[2][j].getText()) &&
                    buttons[2][j].getText().equals(buttons[3][j].getText()) &&
                    buttons[3][j].getText().equals(buttons[4][j].getText()) &&
                    !buttons[0][j].getText().isEmpty()) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                buttons[2][2].getText().equals(buttons[3][3].getText()) &&
                buttons[3][3].getText().equals(buttons[4][4].getText()) &&
                !buttons[0][0].getText().isEmpty()) {
            return true;
        }

        if (buttons[0][4].getText().equals(buttons[1][3].getText()) &&
                buttons[1][3].getText().equals(buttons[2][2].getText()) &&
                buttons[2][2].getText().equals(buttons[3][1].getText()) &&
                buttons[3][1].getText().equals(buttons[4][0].getText()) &&
                !buttons[0][4].getText().isEmpty()) {
            return true;
        }

        return false;
    }

    private boolean checkForDraw() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false; // If there is an empty cell, game is not draw
                }
            }
        }
        return true; // All cells are filled, game is draw
    }

    private void displayWinner() {
        String winner = playerX ? "Player X" : "Player O";
        Text text = new Text(winner + " wins!");
        ((GridPane) ((Scene) buttons[0][0].getScene()).getRoot()).add(text, 0, 1);
    }

    private void displayDraw() {
        Text text = new Text("It's a draw!");
        ((GridPane) ((Scene) buttons[0][0].getScene()).getRoot()).add(text, 0, 1);
    }

    private void resetGame() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
            }
        }
        gameOver = false;
        playerX = true;
        ((GridPane) ((Scene) buttons[0][0].getScene()).getRoot()).getChildren().removeIf(node -> node instanceof Text);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
