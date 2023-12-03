import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface implements ActionListener {
    final int frameWidth = 300;
    final int frameHeight = 300;
    final int buttonWidth = 100;
    final int buttonHeight = 100;
    final int frameMargin = 20;
    JFrame frame = new JFrame();
    JButton btnAdd1Home = new JButton("+1");
    JButton btnAdd1Guest = new JButton("+1");
    JButton btnRemove1Home = new JButton("-1");
    JButton btnRemove1Guest = new JButton("-1");


    public Interface(){

    }

    public void createFrame(){
        frame.setTitle("PointsTracker");
        frame.getContentPane().setLayout(null);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // stop esecution when frame is closed
        frame.setVisible(true);
    }

    public void addInteractions(){
        // Home Label
        JLabel labelHome = new JLabel();
        labelHome.setBounds(frameMargin, frameMargin, 50, 20);
        labelHome.setText("Home");
        frame.add(labelHome);

        // button 1
        btnAdd1Home.setBounds(frameMargin, frameMargin * 2, buttonWidth, buttonHeight);
        btnAdd1Home.addActionListener(this);    //Registering ActionListener to the button
        frame.add(btnAdd1Home);

        // Home Label
        JLabel labelGuest = new JLabel();
        labelGuest.setBounds(frameWidth - 50 - frameMargin, frameMargin, 50, 20);
        labelGuest.setText("Guest");
        frame.add(labelGuest);

        // button 2
        btnAdd1Guest.setBounds(frameWidth - buttonWidth - frameMargin, frameMargin * 2, buttonWidth, buttonHeight);
        btnAdd1Guest.addActionListener(this);   //Registering ActionListener to the button
        frame.add(btnAdd1Guest);
    }

    @Override
    // when one button is pressed:
    public void actionPerformed(ActionEvent e) {
        // checks which buttons has been pressed
        if("1 player".equals(e.getActionCommand())){
            frame.getContentPane().setBackground(Color.red);       // Changing Background Color
        }else{

        }
    }
}
