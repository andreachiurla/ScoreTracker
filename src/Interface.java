import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface implements ActionListener {
    final int frameWidth = 400;
    final int frameHeight = 350;
    final int buttonWidth = 150;
    final int buttonHeight = 150;
    final int frameMargin = 10;
    JFrame frame = new JFrame();
    JButton btnTeams = new JButton("Squadre");
    JButton btnPointsTracker = new JButton("Points Tracker");
    JButton btnSubmitTeams = new JButton("Submit");
    JButton btnReturnToHome = new JButton("Home");

    // POINTS TRACKER PAGE
    JButton btnAdd1Home = new JButton("+1");
    JButton btnAdd1Guest = new JButton("+1");
    JButton btnRemove1Home = new JButton("-1");
    JButton btnRemove1Guest = new JButton("-1");

    JTextField txtHome = new JTextField();
    JTextField txtGuest = new JTextField();
    JLabel lblErrorTextField = new JLabel();

    FileManager fileManager = new FileManager();
    String strHome = "Home";
    String strGuest = "Guest";

    private boolean isAlreadyEsecutedOnce1;
    private boolean isAlreadyEsecutedOnce2;



    public Interface(){
        isAlreadyEsecutedOnce1 = false;
        isAlreadyEsecutedOnce2 = false;
    }

    public void createFrame(){
        frame.setTitle("Score Tracker");
        frame.getContentPane().setLayout(null);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // stop esecution when frame is closed
        frame.setVisible(true);
    }

    public void setTeamsName(String inHome, String inGuest){
        strHome = inHome;
        strGuest = inGuest;
    }

    public void setHomePage(){
        // removing everything that's in the frame
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.getContentPane().revalidate();

        // button choose teams
        btnTeams.setBounds(frameMargin, frameMargin , buttonWidth, buttonHeight);
        btnTeams.addActionListener(this);    // Registering ActionListener to the button
        frame.add(btnTeams);

        // button points tracker
        btnPointsTracker.setBounds(frameWidth - frameMargin - buttonWidth, frameMargin , buttonWidth, buttonHeight);
        btnPointsTracker.addActionListener(this);    // Registering ActionListener to the button
        frame.add(btnPointsTracker);
    }

    public void setTeamsPage(){
        // removing everything that's in the frame
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.getContentPane().revalidate();

        // Home label
        JLabel lblHome = new JLabel("Casa:");
        lblHome.setBounds(frameMargin, frameHeight / 5, 50, 20);
        frame.add(lblHome);

        // Home text field
        txtHome.setBounds(frameMargin + 60, frameHeight / 5, 170, 20);
        frame.add(txtHome);

        // Guest label
        JLabel lblGuest = new JLabel("Ospiti:");
        lblGuest.setBounds(frameMargin, frameHeight / 5 * 2, 50, 20);
        frame.add(lblGuest);

        // Guest text field
        txtGuest.setBounds(frameMargin + 60, frameHeight / 5 * 2, 170, 20);
        frame.add(txtGuest);

        // Return to Home Page button
        btnReturnToHome.setBounds(frameWidth - frameMargin - 70, frameMargin, 70, 30);
        frame.add(btnReturnToHome);

        // Submit button
        btnSubmitTeams.setBounds(frameMargin * 2, 180, 100, 40);
        frame.add(btnSubmitTeams);

        if(!isAlreadyEsecutedOnce2){        // Registering ActionListener to the button
            isAlreadyEsecutedOnce2 = true;
            btnSubmitTeams.addActionListener(this);
            btnReturnToHome.addActionListener(this);
        }

    }

    public void setPointsTrackerPage(){
        // removing everything that's in the frame
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.getContentPane().revalidate();

        // Home Label
        JLabel labelHome = new JLabel();
        labelHome.setBounds(frameMargin, frameMargin * 4, 150, 20);
        labelHome.setText(strHome);
        frame.add(labelHome);

        // button +1 home
        btnAdd1Home.setBounds(frameMargin, frameMargin * 6, buttonWidth, buttonHeight);
        frame.add(btnAdd1Home);

        // Guest Label
        JLabel labelGuest = new JLabel();
        labelGuest.setBounds(frameWidth - 150 - frameMargin, frameMargin * 4, 150, 20);
        labelGuest.setHorizontalAlignment(SwingConstants.RIGHT);
        labelGuest.setText(strGuest);
        frame.add(labelGuest);

        // button +1 guest
        btnAdd1Guest.setBounds(frameWidth - buttonWidth - frameMargin, frameMargin * 6, buttonWidth, buttonHeight);
        frame.add(btnAdd1Guest);

        // -1 home button
        btnRemove1Home.setBounds(frameMargin, frameHeight - buttonHeight / 2 - frameMargin * 4, buttonWidth / 2, buttonHeight / 2);
        frame.add(btnRemove1Home);

        // -1 guest button
        btnRemove1Guest.setBounds(frameWidth - buttonWidth / 2 - frameMargin, frameHeight - buttonHeight / 2 - frameMargin * 4, buttonWidth / 2, buttonHeight / 2);
        frame.add(btnRemove1Guest);

        // Return to Home Page button
        btnReturnToHome.setBounds(frameWidth / 2 - 35, frameMargin, 70, 30);
        frame.add(btnReturnToHome);

        if(!isAlreadyEsecutedOnce1){    // Registering ActionListener to the button
            isAlreadyEsecutedOnce1 = true;
            btnRemove1Guest.addActionListener(this);
            btnAdd1Home.addActionListener(this);
            btnAdd1Guest.addActionListener(this);
            btnRemove1Home.addActionListener(this);
            btnReturnToHome.addActionListener(this);
        }
    }

    @Override
    // when one button is pressed:
    public void actionPerformed(ActionEvent e) {
        // checks which buttons has been pressed
        if(e.getSource() == btnTeams){
            setTeamsPage();
        }else if(e.getSource() == btnPointsTracker){
            setPointsTrackerPage();
        }else if(e.getSource() == btnAdd1Home){
            fileManager.add1Home();
        }else if(e.getSource() == btnAdd1Guest){
            fileManager.add1Guest();
        }else if(e.getSource() == btnRemove1Home){
            fileManager.remove1Home();
        }else if(e.getSource() == btnRemove1Guest){
            fileManager.remove1Guest();
        }else if(e.getSource() == btnReturnToHome){
            setHomePage();
        }else if(e.getSource() == btnSubmitTeams){
            if(txtHome.getText().equals("") || txtGuest.getText().equals("")){
                lblErrorTextField.setBounds(130, 180, 150, 40);
                frame.add(lblErrorTextField);
                lblErrorTextField.setText("Nothing Entered");
                //frame.setSize(frameWidth + 1, frameHeight);
                System.out.println("Nothing entered");
            }else{
                frame.remove(lblErrorTextField);
                String[] teamsName = fileManager.setTeams(txtHome.getText(), txtGuest.getText());
                setTeamsName(teamsName[0], teamsName[1]);
            }
        }
    }
}
