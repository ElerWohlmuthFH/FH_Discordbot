package at.ac.fhcampuswien;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
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
                    event.getChannel().sendMessage(" *** is a bad word " + event.getMember().getUser().getName()).queue();
                    badWord = true;
                    System.out.println(filterPerson);
                    TextChannel textChannel = event.getChannel();

                    if (filterPerson.containsKey(event.getMember().getUser().getId())) {
                        filterPerson.put(event.getMember().getUser().getId(), filterPerson.get(event.getMember().getUser().getId()) + 1);
                    } else {
                        filterPerson.put(event.getMember().getUser().getId(), 1);
                    }
                    if (filterPerson.get(event.getMember().getUser().getId()) == 1) {
                        textChannel.sendMessage("Noch zweimal und du wirst gebannt" + event.getMember().getUser().getName());
                    }
                    else if (filterPerson.get(event.getMember().getUser().getId()) == 2) {
                        textChannel.sendMessage("Noch einmal und du wirst gebannt" + event.getMember().getUser().getName());
                    }else if (filterPerson.get(event.getMember().getUser().getId()) == 3){
/*
                        try {
                            Thread.sleep(1000);
                            textChannel.sendMessage( event.getMember().getUser().getName() +" wurde gebannt").queue();
                            event.getGuild().ban(event.getMember().getUser(), 0, "REASON").queue();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
*/
                        System.out.println("TESTTESTTEST");
                        event.getGuild().ban(event.getMember().getUser(), 0, "REASON").queue();

                        //event.getGuild().ban(Objects.requireNonNull(event.getMember().getNickname()), 0, "REASON").queue();
                        System.out.println("HELLOWORLD");

                    }
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
