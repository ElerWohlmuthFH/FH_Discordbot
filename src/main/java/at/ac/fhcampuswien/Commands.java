package at.ac.fhcampuswien;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent  event){
        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(Main.prefix + "test")) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Hello World!").queue();
        }
    }
}
