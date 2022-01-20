package at.ac.fhcampuswien;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;


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

                    if (filterPerson.containsKey(event.getMember().getUser().getId())) {
                        filterPerson.put(event.getMember().getUser().getId(), filterPerson.get(event.getMember().getUser().getId()) + 1);
                    } else {
                        filterPerson.put(event.getMember().getUser().getId(), 1);
                    }

                    if (filterPerson.get(event.getMember().getUser().getId()) > 3) {
                        /*
                        try {
                            Thread.sleep(1000);
                            //TextChannel textChannel = event.getGuild().getTextChannelById(916345619805794324L);
                            EmbedBuilder eb = new EmbedBuilder();
                            event.getChannel().sendTyping().queue();
                            eb.setTitle("All available commands: ");
                            eb.setDescription("/commands \n /ban \n /translate \n /join \n");
                            eb.setColor(0xf45642);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                         */
                        System.out.println("TESTTESTTEST");
                        event.getGuild().ban(event.getMember().getUser(), 0, "REASON").queue();
                        //event.getGuild().ban(event.getMember().getUser(), 1);
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
