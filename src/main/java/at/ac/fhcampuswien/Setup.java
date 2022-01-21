package at.ac.fhcampuswien;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;


public class Setup extends ListenerAdapter {

    AtomicLong getLoginChannelID() {
        return loginChannelID;
    }

    private final AtomicLong loginChannelID = new AtomicLong(); //create login Channel

    // /setup command, only for Discord admins.
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+"); // gets message from mod and splits every whitespace.
        if (args[0].equalsIgnoreCase(Main.prefix + "setup") && !event.getAuthor().isBot()) {


                event.getGuild().createTextChannel("login channel").queue(textChannel -> { //create login channel
                loginChannelID.set(textChannel.getIdLong());

                    try{
                        FileReader reader = new FileReader("config"); //reads config file
                        Properties properties = new Properties();
                        properties.load(reader);
                        reader.close();

                        properties.put("LOGINCHANNELID", String.valueOf(loginChannelID.get())); //write login channel ID to config file

                        FileOutputStream output = new FileOutputStream("config");
                        properties.store(output, null);
                        output.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        }
    }


}

