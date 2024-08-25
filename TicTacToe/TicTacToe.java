import java.awt.*;
import java.awt.event.*;
public class TicTacToe extends Frame implements ActionListener {
    private Button[] buttons;
    private boolean playerX;
    private int countXWins,countOWins;
    private Label xWinsLabel,oWinsLabel;
    public TicTacToe() {
        super("Tic Tac Toe");
        buttons = new Button[9];
        playerX = true;
        countXWins = 0;
        countOWins = 0;
        setLayout(new BorderLayout());


        Panel gamePanel = new Panel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].setBackground(Color.WHITE);
            buttons[i].addActionListener(this);
            gamePanel.add(buttons[i]);
        }
       add(gamePanel, BorderLayout.CENTER);


        Panel scorePanel = new Panel(new GridLayout(1, 2));
        xWinsLabel = new Label("X Wins: " + countXWins);
        xWinsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scorePanel.add(xWinsLabel);
        oWinsLabel = new Label("O Wins: " + countOWins);
        oWinsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scorePanel.add(oWinsLabel);
        add(scorePanel, BorderLayout.NORTH);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setBackground(Color.LIGHT_GRAY);
        setSize(300, 350);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {
        Button buttonClicked = (Button) ae.getSource();
        if (playerX) {
            buttonClicked.setLabel("X");
            buttonClicked.setEnabled(false);
            buttonClicked.setBackground(Color.BLUE);
            buttonClicked.setForeground(Color.WHITE);
        } else {
            buttonClicked.setLabel("O");
            buttonClicked.setEnabled(false);
            buttonClicked.setBackground(Color.RED);
            buttonClicked.setForeground(Color.WHITE);
        }
        playerX = !playerX;
        if (checkForWin()) {
            if (!playerX) {
                countXWins++;
                xWinsLabel.setText("X Wins: " + countXWins);
            } else {
                countOWins++;
                oWinsLabel.setText("O Wins: " + countOWins);
            }
            String currentPlayer = (!playerX) ? "X" : "O";
            showMessageDialog(this, "Player " + currentPlayer + " wins!");
            resetGame();
        } else if (checkForDraw()) {
            showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }


    private boolean checkForWin() {
        String[] positions = new String[9];
        for (int i = 0; i < 9; i++) {
            positions[i] = buttons[i].getLabel();}
        return (checkLine(positions, 0, 1, 2) ||
                checkLine(positions, 3, 4, 5) ||
                checkLine(positions, 6, 7, 8) ||
                checkLine(positions, 0, 3, 6) ||
                checkLine(positions, 1, 4, 7) ||
                checkLine(positions, 2, 5, 8) ||
                checkLine(positions, 0, 4, 8) ||
                checkLine(positions, 2, 4, 6));
    }


    private boolean checkLine(String[] positions, int a, int b, int c) {
        return (!positions[a].isEmpty() && positions[a].equals(positions[b]) && positions[b].equals(positions[c]));
    }


    private boolean checkForDraw() {
        for (Button button : buttons) {
            if (button.getLabel().isEmpty()) {
                return false;
            }
        }return true;
    }


    private void resetGame() {
        for (Button button : buttons) {
            button.setLabel("");
            button.setEnabled(true);
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
        } playerX = true;
    }


    private void showMessageDialog(Frame parent, String message) {
        Dialog dialog = new Dialog(parent, "Message", true);
        dialog.setLayout(new FlowLayout());
        dialog.add(new Label(message));
        Button okButton = new Button("OK");
        okButton.addActionListener(e -> dialog.dispose());
        dialog.add(okButton);
        dialog.setSize(200, 100);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
    public static void main(String[] args) {
        new TicTacToe();
    }
}
