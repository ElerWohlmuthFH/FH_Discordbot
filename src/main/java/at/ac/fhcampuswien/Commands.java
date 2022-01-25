package at.ac.fhcampuswien;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Commands extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        User user = event.getAuthor(); //get message Author

        if (args[0].equalsIgnoreCase(Main.prefix + "commands")) {
            event.getChannel().sendTyping().queue();
            EmbedBuilder eb = new EmbedBuilder();
            event.getChannel().sendTyping().queue();
            eb.setTitle("All available commands: ");
            eb.setDescription(Main.prefix + "commands \n" + Main.prefix + "translate \n" + Main.prefix + "whoami \n" + Main.prefix + "join \n" + Main.prefix + "mute \n" + Main.prefix + "setup \n" + Main.prefix + "cat \n");
            eb.setColor(0xf45642);
            //eb.addField("FIELD", "FIELD", false);
            eb.setAuthor("Eler Wohlmuth, Matthias Werkl, Raffael Tomesek", "https://www.designtagebuch.de/wp-content/uploads/mediathek//2021/05/discord-logo-1100x825.jpg");
            eb.setFooter("FH Campus bot", "https://img.icons8.com/color/search");
            //eb.setImage("IMAGE");
            //eb.setThumbnail("IMAGE");

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(eb.build()).queue();
            eb.clear();

        } else if (args[0].equalsIgnoreCase(Main.prefix + "whoami")) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("" + user).queue();
        }
    }
}

