import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface implements ActionListener {
    final int frameWidth = 300;
    final int frameHeight = 250;
    final int buttonWidth = 100;
    final int buttonHeight = 100;
    final int frameMargin = 20;
    JFrame frame = new JFrame();
    JButton btnAdd1Home = new JButton("+1");
    JButton btnAdd1Guest = new JButton("+1");
    JButton btnRemove1Home = new JButton("-1");
    JButton btnRemove1Guest = new JButton("-1");

    FileManager fileManager = new FileManager();


    public Interface(){

    }

    public void createFrame(){
        frame.setTitle("ScoreTracker");
        frame.getContentPane().setLayout(null);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // stop esecution when frame is closed
        frame.setVisible(true);
    }

    public void setPointsTracker(){
        // Home Label
        JLabel labelHome = new JLabel();
        labelHome.setBounds(frameMargin, frameMargin, 50, 20);
        labelHome.setText("Home");
        frame.add(labelHome);

        // button +1 home
        btnAdd1Home.setBounds(frameMargin, frameMargin * 2, buttonWidth, buttonHeight);
        btnAdd1Home.addActionListener(this);    //Registering ActionListener to the button
        frame.add(btnAdd1Home);

        // Guest Label
        JLabel labelGuest = new JLabel();
        labelGuest.setBounds(frameWidth - 50 - frameMargin, frameMargin, 50, 20);
        labelGuest.setText("Guest");
        frame.add(labelGuest);

        // button +1 guest
        btnAdd1Guest.setBounds(frameWidth - buttonWidth - frameMargin, frameMargin * 2, buttonWidth, buttonHeight);
        btnAdd1Guest.addActionListener(this);   //Registering ActionListener to the button
        frame.add(btnAdd1Guest);

        // button -1 home
        btnRemove1Home.setBounds(frameMargin, frameMargin * 8, buttonWidth / 2, buttonHeight / 2);
        btnRemove1Home.addActionListener(this);   //Registering ActionListener to the button
        frame.add(btnRemove1Home);

        // button -1 guest
        btnRemove1Guest.setBounds(frameWidth - buttonWidth / 2 - frameMargin, frameMargin * 8, buttonWidth / 2, buttonHeight / 2);
        btnRemove1Guest.addActionListener(this);   //Registering ActionListener to the button
        frame.add(btnRemove1Guest);
    }

    @Override
    // when one button is pressed:
    public void actionPerformed(ActionEvent e) {
        // checks which buttons has been pressed
        if(e.getSource() == btnAdd1Home){
            fileManager.add1Home();
        }else if(e.getSource() == btnAdd1Guest){
            fileManager.add1Guest();
        }else if(e.getSource() == btnRemove1Home){
            fileManager.remove1Home();
        }else if(e.getSource() == btnRemove1Guest){
            fileManager.remove1Guest();
        }
    }
}
