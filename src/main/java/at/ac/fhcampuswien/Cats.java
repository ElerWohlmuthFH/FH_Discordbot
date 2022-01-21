package at.ac.fhcampuswien;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class Cats extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+"); // gets message from mod and splits every whitespace.
        if (args[0].equalsIgnoreCase(Main.prefix + "cat") && !event.getAuthor().isBot()) {

            EmbedBuilder eb = new EmbedBuilder();
            InputStream file = null;
            try {
                file = new URL("https://thecatapi.com/api/images/get?format=src&type=gif").openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            eb.setTitle("Random cute cat pics :)");

            MessageChannel textchannel = event.getGuild().getTextChannelById(913803468509151263L);
            // MessageChannel textchannel = event.getChannel();

            assert file != null;
            assert textchannel != null;
            textchannel.sendFile(file, "cat.png").setEmbeds(eb.build()).queue();
        }
    }
}

