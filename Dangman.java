package com.dang.man;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Dangman extends JFrame implements ActionListener {
    private final Words words;
    private JLabel hangmanImage,categoryLabel, hiddenWordLabel,resultLabel,wordLabel;
    private int incorrectGuesses;
    private String[] wordChallenge;

    private final JButton[] letterButtons;
    private JDialog result;
    public Dangman(){
        super("Hangman Game");
        setSize(Constants.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Constants.BG_COLOR);
        words = new Words();
        letterButtons = new JButton[26];
        wordChallenge = words.loadChallenge();
        createResult();
        updateGUI();
    }

    private void updateGUI(){
        hangmanImage = CustomUtil.loadImage(Constants.IMAGE_PATH);
        hangmanImage.setBounds(0,0, 640,560);
        categoryLabel = new JLabel(wordChallenge[0]);
        categoryLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
categoryLabel.setOpaque(true);
        categoryLabel.setForeground(Color.white);
        categoryLabel.setBorder(BorderFactory.createLineBorder(Constants.SECONDARY_COLOR));
categoryLabel.setBackground(Constants.SECONDARY_COLOR);
        categoryLabel.setBounds(0,hangmanImage.getPreferredSize().height-28,Constants.FRAME_SIZE.width,categoryLabel.getPreferredSize().height);

        hiddenWordLabel = new JLabel(CustomUtil.hideWords(wordChallenge[1]));
        hiddenWordLabel.setForeground(Color.white);
        hiddenWordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        hiddenWordLabel.setBounds(0,categoryLabel.getY()+categoryLabel.getPreferredSize().height+50,Constants.FRAME_SIZE.width,hiddenWordLabel.getPreferredSize().height);
        GridLayout gridLayout = new GridLayout(5,6);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(-5,hiddenWordLabel.getY()+hiddenWordLabel.getPreferredSize().height,Constants.BUTTON_PANEL_SIZE.width,Constants.BUTTON_PANEL_SIZE.height);
        buttonPanel.setLayout(gridLayout);

        for (char c = 'A' ;  c <= 'Z' ; c++) {
            JButton button = new JButton(Character.toString(c));
            button.setBackground(Constants.PRIMARY_COLOR);
            button.setForeground(Color.white);
             button.addActionListener(this);
             int currentIndex = c - 'A';
             letterButtons[currentIndex] = button;
             buttonPanel.add(letterButtons[currentIndex]);
        }

        JButton reset = new JButton("Reset");
        reset.setForeground(Color.white);
        reset.setBackground(Constants.SECONDARY_COLOR);
        reset.addActionListener(this);
        buttonPanel.add(reset);

        JButton exit = new JButton("Exit");
        exit.setForeground(Color.white);
        exit.setBackground(Constants.SECONDARY_COLOR);
        exit.addActionListener(this);
        buttonPanel.add(exit);

        getContentPane().add(categoryLabel);
        getContentPane().add(hangmanImage);
        getContentPane().add(hiddenWordLabel);
        getContentPane().add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("Reset") || command.equals("Restart")){
            resetGame();
            if(command.equals("Restart")){
                result.setVisible(false);
            }
        }else if(command.equals("Exit")){
            dispose();
        }else{
            JButton button = (JButton) e.getSource();
            button.setEnabled(false);

            if(wordChallenge[1].contains(command)){
                button.setBackground(Color.green);

                char[] hiddenWord = hiddenWordLabel.getText().toCharArray();
                for(int i = 0 ; i < wordChallenge[1].length(); i++){
                    if(wordChallenge[1].charAt(i)==command.charAt(0)){
                        hiddenWord[i] = command.charAt(0);
                    }
                }

                hiddenWordLabel.setText(String.valueOf(hiddenWord));

                if(hiddenWordLabel.getText().contains("*")){
                    resultLabel.setText("That's correct");
                    resultLabel.setVisible(true);
                }


            }else{
                button.setBackground(Color.red);
                incorrectGuesses++;
               CustomUtil.updateImage(hangmanImage,"/"+(incorrectGuesses+1)+".png");
                if(incorrectGuesses>=6){
                    resultLabel.setText("Game over");
                    result.setVisible(true);
                }
            }
            wordLabel.setText("It was "+wordChallenge[1]);
            System.out.println(incorrectGuesses);
        }
    }

    private void createResult(){
        result = new JDialog();
        result.setTitle("Score");
        result.setSize(Constants.RESULT_SIZE);
        result.getContentPane().setBackground(Constants.BG_COLOR);
        result.setResizable(false);
        result.setLocationRelativeTo(this);
        result.setLayout(new GridLayout(3,1));
        result.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                resetGame();
            }
        });
        resultLabel = new JLabel();
        resultLabel.setForeground(Color.white);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordLabel = new JLabel();
        wordLabel.setForeground(Color.white);
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JButton restart = new JButton("Restart");
        restart.setForeground(Color.white);
        restart.setBackground(Constants.SECONDARY_COLOR);
        restart.addActionListener(this);
        result.add(resultLabel);
        result.add(wordLabel);
        result.add(restart);
    }
    private void resetGame(){
        wordChallenge = words.loadChallenge();
        incorrectGuesses = 0;

        CustomUtil.updateImage(hangmanImage,Constants.IMAGE_PATH);

        categoryLabel.setText(wordChallenge[0]);

        String hiddenWord = CustomUtil.hideWords(wordChallenge[1]);
        hiddenWordLabel.setText(hiddenWord);

        for(int i  = 0 ; i < letterButtons.length ; i++){
            letterButtons[i].setEnabled(true);
            letterButtons[i].setBackground(Constants.PRIMARY_COLOR);

        }
    }
}
