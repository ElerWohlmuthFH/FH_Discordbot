package at.ac.fhcampuswien;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Objects;


public class Filter extends ListenerAdapter {

    private static HashMap<String, Integer> filterPerson = new HashMap<>();
    static boolean filterOn = true;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] LIST_OF_BAD_WORDS = {"hallo", "anal", "anus", "arse", "ass", "motherfucker", "balls", "bastard", "bitch", "blowjob", "blow job", "buttplug", "cock", "coon", "cunt", "dildo", "fag", "dyke", "fuck", "fucking", "nigger", "Goddamn", "jizz", "nigga", "pussy", "shit", "whore"};
        String[] message = event.getMessage().getContentRaw().split(" ");
        boolean badWord = false;
        for (int i = 0; i < message.length; i++) {
            int number = 1;
            //boolean badWord = false;
            //Check each message for each bad word
            for (int b = 0; b < LIST_OF_BAD_WORDS.length; b++, number++) {
                if (message[i].equalsIgnoreCase(LIST_OF_BAD_WORDS[b])) {
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(" *** is a bad word, you will get banned soon " + Objects.requireNonNull(event.getMember()).getUser().getName()).queue() ;
                    badWord = true;
                    System.out.println(filterPerson);

                    if (filterPerson.containsKey(event.getMember().getUser().getId())) {
                        filterPerson.put(event.getMember().getUser().getId(), filterPerson.get(event.getMember().getUser().getId()) + 1);
                    } else {
                        filterPerson.put(event.getMember().getUser().getId(), 1);
                    }
                }else if (filterPerson.get(Objects.requireNonNull(event.getMember()).getUser().getId()) == 3){
                    // event.getGuild().ban(event.getMember().getUser(), 0, "REASON").queue();
                    event.getAuthor().openPrivateChannel().flatMap(channel->channel.sendMessage("Du wurdest vom Server gebannt!")).queue();
                    event.getGuild().ban(event.getMember().getUser(), 0, "REASON").queue();

                }

            }
        }



        if (event.getMessage().getContentRaw().equalsIgnoreCase("/filteron") && filterOn) {
            filterOn = false;
            event.getChannel().sendMessage("The Curse-Filter has been disabled by " + event.getMember().getUser().getName()).queue();
        } else if (event.getMessage().getContentRaw().equalsIgnoreCase("/filteroff") && !filterOn) {
            filterOn = true;
            event.getChannel().sendMessage("The Curse-Filter has been enabled by " + event.getMember().getUser().getName()).queue();
        }
    }

}
