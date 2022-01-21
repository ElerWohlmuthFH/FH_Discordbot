package at.ac.fhcampuswien;

import at.ac.fhcampuswien.Main;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Objects;


public class Join extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split("\\s+"); // gets message from mod and splits every whitespace.
        boolean isInChannel = false;
        if (args[0].equalsIgnoreCase(Main.prefix + "join") && !event.getAuthor().isBot()) {
            AudioManager audioManager = event.getGuild().getAudioManager(); // get Audiomanager
            VoiceChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // get Voicechannel
            audioManager.openAudioConnection(connectedChannel); // Connects to the channel.
            isInChannel = true;
        }
    }
}