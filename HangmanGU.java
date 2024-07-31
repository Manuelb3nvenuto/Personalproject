import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class HangmanGU extends JFrame {
    private String[] words = {"java", "programming", "hangman", "developer", "computer", "teletransportacion", "homoe"};
    private String word = words[(int) (Math.random() * words.length)];
    private char[] guessedWord = new char[word.length()];
    private HashSet<Character> guessedLetters = new HashSet<>();
    private int attempts = 6;

    private JPanel wordPanel;
    private JPanel drawingPanel;
    private JLabel attemptsLabel;
    private JTextField guessField;
    private JLabel messageLabel;

    public HangmanGU() {
        setTitle("Hangman Game");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize guessed word array
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        // Word panel
        wordPanel = new JPanel();
        wordPanel.setBackground(new Color(255, 228, 196));
        wordPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        updateWordPanel();
        add(wordPanel, BorderLayout.NORTH);

        // Drawing panel
        drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(400, 400));
        drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel, BorderLayout.CENTER);

        // Attempts panel
        JPanel attemptsPanel = new JPanel();
        attemptsPanel.setBackground(new Color(255, 228, 196));
        attemptsLabel = new JLabel("Attempts left: " + attempts);
        attemptsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        attemptsPanel.add(attemptsLabel);
        add(attemptsPanel, BorderLayout.SOUTH);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(255, 228, 196));
        guessField = new JTextField(5);
        guessField.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 18));
        guessButton.addActionListener(new GuessButtonListener());
        inputPanel.add(new JLabel("Enter a letter: "));
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        add(inputPanel, BorderLayout.SOUTH);

        // Message label
        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(Color.RED);
        inputPanel.add(messageLabel);

        setVisible(true);
    }

    private void updateWordPanel() {
        wordPanel.removeAll();
        for (char c : guessedWord) {
            JLabel letterLabel = new JLabel(String.valueOf(c) + " ");
            letterLabel.setFont(new Font("Arial", Font.BOLD, 24));
            wordPanel.add(letterLabel);
        }
        wordPanel.revalidate();
        wordPanel.repaint();
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw hangman stand
            g.setColor(Color.BLACK);
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
                messageLabel.setText("Invalid input or already guessed letter.");
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
                JOptionPane.showMessageDialog(HangmanGU.this, "Congratulations! You've guessed the word: " + word);
                System.exit(0);
            }

            if (attempts == 0) {
                JOptionPane.showMessageDialog(HangmanGU.this, "Game over! The word was: " + word);
                System.exit(0);
            }

            messageLabel.setText("");
        }
    }

    public static void main(String[] args) {
        new HangmanGUI();
    }
}
