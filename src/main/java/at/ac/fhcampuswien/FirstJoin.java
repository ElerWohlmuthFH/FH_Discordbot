package at.ac.fhcampuswien;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class FirstJoin extends ListenerAdapter {


    String roleEveryoneId;
    String loginChannelId;


    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Guild guild = event.getGuild(); // Get the guild that the user joined.
        User user = event.getUser();    // Get the user that joined.
        JDA client = event.getJDA();    // Get the already existing JDA instance.

        try(
                FileReader reader = new FileReader("config")){
            Properties properties = new Properties();
            properties.load(reader);

            roleEveryoneId = properties.getProperty("ROLEEVERYONEID");
            loginChannelId = properties.getProperty("LOGINCHANNELID"); //sets channel ID from config file

        } catch (Exception e) {
            e.printStackTrace();
        }

        TextChannel loginChannel = guild.getTextChannelById(loginChannelId);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert loginChannel != null;
        loginChannel.sendMessage("New member joined: " + user).queue();
        loginChannel.sendMessage("Write your desired nickname in this channel to continue:").queue();

    }


    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()){
            return; // don't respond to other bots
        }
        
        if (!event.getChannel().getId().equals(loginChannelId)){
            return; // ignore other channels
        }


        String message = event.getMessage().getContentRaw();
        Member member = event.getMember();
        assert member != null;
        member.modifyNickname(message).queue();

        MessageChannel channel = event.getChannel();
        channel.sendMessage("your new nickname is: " + message).queue();

        Guild guild = event.getGuild();
        Role role = guild.getRoleById(roleEveryoneId); //INSERT ROLE ID
        assert role != null;
        guild.addRoleToMember(member.getId(), role).queue();
    }
}