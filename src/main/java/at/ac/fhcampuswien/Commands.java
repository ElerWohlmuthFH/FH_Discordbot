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
            eb.setDescription(Main.prefix + "commands \n" + Main.prefix + "ban \n" + Main.prefix + "translate \n" + Main.prefix + "join \n" + Main.prefix + "cat \n https://www.paypal.com/donate?hosted_button_id=HEQ9CJAGQM2W4 \n");
            eb.setColor(0xf45642);
            //eb.addField("FIELD", "FIELD", false);
            eb.setAuthor("Raffael, Eler, Matthias", "https://www.designtagebuch.de/wp-content/uploads/mediathek//2021/05/discord-logo-1100x825.jpg");
            eb.setFooter("LG Discord Bot Team", "https://img.icons8.com/color/search");
            //eb.setImage("IMAGE");
            //eb.setThumbnail("IMAGE");

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(eb.build()).queue();
            eb.clear();
            System.out.println("Test");

        } else if (args[0].equalsIgnoreCase(Main.prefix + "whoami")) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Hello, World!" + user).queue();
        }
    }
}

