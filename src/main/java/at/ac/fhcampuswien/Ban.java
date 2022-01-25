package at.ac.fhcampuswien;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;


public class Ban extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split("\\s+"); // gets message from mod and splits every whitespace.

        if (args[0].equalsIgnoreCase(Main.prefix + "ban") && !event.getAuthor().isBot()) {
            Member target = event.getMessage().getMentionedMembers().get(0);
            if (!Objects.requireNonNull(event.getMember()).canInteract(target) || !event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                event.getChannel().sendMessage("You don't have the required permission to ban this member!").queue();
                return;
            }

            event.getGuild().ban(target, 0, "REASON").queue();
            event.getChannel().sendMessage(target.getUser().getName() + " has been banned successfully.").queue();
        }
    }
}

