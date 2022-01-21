package at.ac.fhcampuswien;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+"); // gets message from mod and splits every whitespace.
        //String i = args[2];
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        if (args[0].equalsIgnoreCase(Main.prefix + "play") && !event.getAuthor().isBot()) {
            File audioFile = new File("\\C:\\Users\\werkl\\Downloads\\file_example_WAV_1MG.wav").getAbsoluteFile();
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            } catch (UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
            try {
                assert clip != null;
                clip.open(audioInputStream);
            } catch (LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
            clip.start();//Plays audio once
            //   }else if (args[0].equalsIgnoreCase(Main.prefix + "stop") && !event.getAuthor().isBot()) {
            //       assert clip != null;
            //      clip.stop();
            //   }else if (args[0].equalsIgnoreCase(Main.prefix + "loop" + i) && !event.getAuthor().isBot()) {
            //       assert clip != null;
            //      clip.loop(Integer.parseInt(i));

        }
    }
}