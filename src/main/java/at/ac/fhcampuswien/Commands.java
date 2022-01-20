package at.ac.fhcampuswien;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
        User user = event.getAuthor();

        if(args[0].equalsIgnoreCase(Main.prefix + "whoami")) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("" + user).queue();
        }
    }
}
