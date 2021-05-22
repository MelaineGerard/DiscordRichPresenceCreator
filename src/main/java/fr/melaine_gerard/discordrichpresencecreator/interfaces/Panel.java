package fr.melaine_gerard.discordrichpresencecreator.interfaces;

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;
import org.simpleyaml.configuration.file.YamlFile;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements SwingerEventListener{

    private final Image background = Swinger.getResource("background.png");

    JTextField clientID = new JTextField();
    JTextField details = new JTextField();
    JTextField state = new JTextField();
    JTextField sik = new JTextField();
    JTextField sit = new JTextField();
    JTextField lit = new JTextField();
    JTextField lik = new JTextField();

    private final STexturedButton quit = new STexturedButton(Swinger.getResource("quit.png"));
    private final STexturedButton hide = new STexturedButton(Swinger.getResource("hide.png"));
    private final STexturedButton launch = new STexturedButton(Swinger.getResource("launch.png"));


    public Panel(YamlFile yamlFile) {
        this.setLayout(null);
        clientID.setBounds(236,320, 284, 45);
        clientID.setBorder(null);
        clientID.setOpaque(false);
        clientID.setFont(clientID.getFont().deriveFont(30f));
        clientID.setForeground(Color.WHITE);
        clientID.setCaretColor(Color.WHITE);
        if(yamlFile.contains("clientID"))
            clientID.setText(yamlFile.getString("clientID"));
        this.add(clientID);


        details.setBounds(19,102, 284, 45);
        details.setBorder(null);
        details.setOpaque(false);
        details.setFont(clientID.getFont());
        details.setForeground(Color.WHITE);
        details.setCaretColor(Color.WHITE);
        if(yamlFile.contains("details"))
            details.setText(yamlFile.getString("details"));
        this.add(details);


        state.setBounds(447,102, 284, 45);
        state.setBorder(null);
        state.setOpaque(false);
        state.setFont(state.getFont().deriveFont(30f));
        state.setForeground(Color.WHITE);
        state.setCaretColor(Color.WHITE);
        if(yamlFile.contains("state"))
            state.setText(yamlFile.getString("state"));
        this.add(state);


        sik.setBounds(19,170, 284, 45);
        sik.setBorder(null);
        sik.setOpaque(false);
        sik.setFont(clientID.getFont().deriveFont(30f));
        sik.setForeground(Color.WHITE);
        sik.setCaretColor(Color.WHITE);
        if(yamlFile.contains("sik"))
            sik.setText(yamlFile.getString("sik"));
        this.add(sik);


        sit.setBounds(447,170, 284, 45);
        sit.setBorder(null);
        sit.setOpaque(false);
        sit.setFont(sit.getFont().deriveFont(30f));
        sit.setForeground(Color.WHITE);
        sit.setCaretColor(Color.WHITE);
        if(yamlFile.contains("sit"))
            sit.setText(yamlFile.getString("sit"));
        this.add(sit);


        lit.setBounds(447,238, 284, 45);
        lit.setBorder(null);
        lit.setOpaque(false);
        lit.setFont(lit.getFont().deriveFont(30f));
        lit.setForeground(Color.WHITE);
        lit.setCaretColor(Color.WHITE);
        if(yamlFile.contains("lit"))
            lit.setText(yamlFile.getString("lit"));
        this.add(lit);


        lik.setBounds(19,238, 284, 45);
        lik.setBorder(null);
        lik.setOpaque(false);
        lik.setFont(lik.getFont().deriveFont(30f));
        lik.setForeground(Color.WHITE);
        lik.setCaretColor(Color.WHITE);
        if(yamlFile.contains("lik"))
            lik.setText(yamlFile.getString("lik"));
        this.add(lik);


        quit.setBounds(708,0 , 42, 42);
        quit.addEventListener(this);
        this.add(quit);


        hide.setBounds(663,0 , 42, 42);
        hide.addEventListener(this);
        this.add(hide);


        launch.setBounds(250,445 , 250, 70);
        launch.addEventListener(this);
        this.add(launch);
    }
    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    @Override
    public void onEvent(SwingerEvent event) {
        if(event.getSource() == quit){
            System.exit(0);
        }else if (event.getSource() == hide){
            Frame.getInstance().setState(JFrame.ICONIFIED);
        }else if(event.getSource() == launch)
                Frame.getInstance().discordCon();
    }
}
