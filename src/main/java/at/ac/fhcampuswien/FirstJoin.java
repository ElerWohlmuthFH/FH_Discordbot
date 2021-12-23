package at.ac.fhcampuswien;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class FirstJoin extends ListenerAdapter {

    String loginChannelString = "920973912211353650";
    long loginChannel = 920973912211353650L;
    long roleEveryone = 920985272760537088L;

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Guild guild = event.getGuild(); // Get the guild that the user joined.
        User user = event.getUser();    // Get the user that joined.
        JDA client = event.getJDA();    // Get the already existing JDA instance.


        TextChannel adminChannel = guild.getTextChannelById(loginChannel);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert adminChannel != null;
        adminChannel.sendMessage("New member joined: " + user).queue();
        adminChannel.sendMessage("Write your desired nickname in this channel:").queue();
    }


    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return; // don't respond to other bots
        if (event.getChannel().getIdLong() != loginChannel) return; // ignore other channels


        String message = event.getMessage().getContentRaw();
        Member member = event.getMember();
        assert member != null;
        member.modifyNickname(message).queue();

        MessageChannel channel = event.getChannel();
        channel.sendMessage("your new nickname is: " + message).queue();

        Guild guild = event.getGuild();
        Role role = guild.getRoleById(roleEveryone); //INSERT ROLE ID
        assert role != null;
        guild.addRoleToMember(member.getId(), role).queue();
    }
}