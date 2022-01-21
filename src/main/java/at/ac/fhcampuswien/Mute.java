package at.ac.fhcampuswien;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;
import java.util.Objects;

public class Mute extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split("\\s+"); // gets message from mod and splits every whitespace.
        Member member = event.getMessage().getMentionedMembers().get(0);
        // String role = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getUser().getJDA().getRolesByName("LEADERS", true));
        //if (args[0].equalsIgnoreCase(Main.prefix + "mute") && !event.getAuthor().isBot() && event.getGuild().getOwnerId().equals(event.getAuthor().getId())) {
        if (args[0].equalsIgnoreCase(Main.prefix + "mute") && !event.getAuthor().isBot() && Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
            //if (args[0].equalsIgnoreCase(Main.prefix + "mute") && !event.getAuthor().isBot() && event.getMember().getRoles().equals(roles)) {
            System.out.println("HELLOWORLD");
            member.mute(true).queue();
        } else if (args[0].equalsIgnoreCase(Main.prefix + "mute") && !event.getAuthor().isBot() && !event.getGuild().getOwnerId().equals(event.getAuthor().getId())) {
            TextChannel tx = event.getChannel();
            tx.sendMessage("Haste wohl gedacht").queue();
        }
    }
}

