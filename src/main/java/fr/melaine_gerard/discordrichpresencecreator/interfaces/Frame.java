package fr.melaine_gerard.discordrichpresencecreator.interfaces;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;
import org.simpleyaml.configuration.file.YamlFile;

import javax.swing.*;
import java.io.IOException;

public class Frame extends JFrame {
    private static Frame instance;
    private final Panel panel;
    private static YamlFile yamlFile;
    public static void main(String[] args) {
        yamlFile = new YamlFile("config.yml");
        try {
            if (!yamlFile.exists()) {
                System.out.println("New file has been created: " + yamlFile.getFilePath() + "\n");
                yamlFile.createNewFile(true);
            } else {
                System.out.println(yamlFile.getFilePath() + " already exists, loading configurations...\n");
            }
            yamlFile.loadWithComments();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        Swinger.setSystemLookNFeel();
        Swinger.setResourcePath("/");
        instance = new Frame(yamlFile);
    }




    public Frame(YamlFile yamlFile) {
        this.setTitle("Discord Rich Presence Creator");
        this.setSize(750, 600);
        this.setIconImage(Swinger.getResource("icone.png"));
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel = new Panel(yamlFile));

        WindowMover mover = new WindowMover(this);
        this.addMouseListener(mover);
        this.addMouseMotionListener(mover);
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public static Frame getInstance() {
        return instance;
    }

    public Panel getPanel() {
        return this.panel;
    }
    public void discordCon(){
        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = getPanel().clientID.getText();
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> System.out.println("Connecté a discord !");
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second
        presence.details = getPanel().details.getText();
        presence.smallImageText = getPanel().sit.getText();
        presence.smallImageKey = getPanel().sik.getText();
        presence.largeImageText = getPanel().lit.getText();
        presence.largeImageKey = getPanel().lik.getText();
        presence.state = getPanel().state.getText();
        lib.Discord_UpdatePresence(presence);
        System.out.println("Rich presence appliqué !");
        yamlFile.set("clientID", getPanel().clientID.getText());
        yamlFile.set("details", getPanel().details.getText());
        yamlFile.set("sit", getPanel().sit.getText());
        yamlFile.set("sik", getPanel().sik.getText());
        yamlFile.set("lit", getPanel().lit.getText());
        yamlFile.set("lik", getPanel().lik.getText());
        yamlFile.set("state", getPanel().state.getText());
        try {
            yamlFile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // in a worker thread
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
        }, "RPC-Callback-Handler").start();

    }
}
