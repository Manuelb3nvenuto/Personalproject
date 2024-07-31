import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class HangmanGUI extends JFrame {
    private String[] words = {"java", "programming", "hangman", "developer", "computer"};
    private String word = words[(int) (Math.random() * words.length)];
    private char[] guessedWord = new char[word.length()];
    private HashSet<Character> guessedLetters = new HashSet<>();
    private int attempts = 6;

    private JPanel wordPanel;
    private JPanel drawingPanel;
    private JLabel attemptsLabel;
    private JTextField guessField;

    public HangmanGUI() {
        setTitle("Hangman Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize guessed word array
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        // Word panel
        wordPanel = new JPanel();
        updateWordPanel();
        add(wordPanel, BorderLayout.NORTH);

        // Drawing panel
        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        // Attempts panel
        JPanel attemptsPanel = new JPanel();
        attemptsLabel = new JLabel("Attempts left: " + attempts);
        attemptsPanel.add(attemptsLabel);
        add(attemptsPanel, BorderLayout.SOUTH);

        // Input panel
        JPanel inputPanel = new JPanel();
        guessField = new JTextField(5);
        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());
        inputPanel.add(new JLabel("Enter a letter: "));
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateWordPanel() {
        wordPanel.removeAll();
        for (char c : guessedWord) {
            wordPanel.add(new JLabel(String.valueOf(c) + " "));
        }
        wordPanel.revalidate();
        wordPanel.repaint();
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw hangman stand
            g.drawLine(50, 300, 200, 300);
            g.drawLine(125, 300, 125, 100);
            g.drawLine(125, 100, 200, 100);
            g.drawLine(200, 100, 200, 130);
            
            // Draw hangman based on attempts left
            if (attempts <= 5) { // Head
                g.drawOval(185, 130, 30, 30);
            }
            if (attempts <= 4) { // Body
                g.drawLine(200, 160, 200, 220);
            }
            if (attempts <= 3) { // Left arm
                g.drawLine(200, 180, 170, 200);
            }
            if (attempts <= 2) { // Right arm
                g.drawLine(200, 180, 230, 200);
            }
            if (attempts <= 1) { // Left leg
                g.drawLine(200, 220, 180, 270);
            }
            if (attempts == 0) { // Right leg
                g.drawLine(200, 220, 220, 270);
            }
        }
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = guessField.getText().toLowerCase();
            guessField.setText("");

            if (input.length() != 1 || guessedLetters.contains(input.charAt(0))) {
                return;
            }

            char guess = input.charAt(0);
            guessedLetters.add(guess);

            boolean correctGuess = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    guessedWord[i] = guess;
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                attempts--;
            }

            updateWordPanel();
            drawingPanel.repaint();
            attemptsLabel.setText("Attempts left: " + attempts);

            if (new String(guessedWord).equals(word)) {
                JOptionPane.showMessageDialog(HangmanGUI.this, "Congratulations! You've guessed the word: " + word);
                System.exit(0);
            }

            if (attempts == 0) {
                JOptionPane.showMessageDialog(HangmanGUI.this, "Game over! The word was: " + word);
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new HangmanGUI();
    }
}
