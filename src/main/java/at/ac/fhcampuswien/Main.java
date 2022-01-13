package at.ac.fhcampuswien;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import javax.security.auth.login.LoginException;

class Main {
    public static JDA jda;
    public static String prefix = "/";

    public static void main(String[] args) throws LoginException {

        String token = "OTEwODUxNTQ1NDMwNjM0NTU2.YZY2mw.c5AGUE76-TUEbc8_XBWNf_jikVQ";

        jda = JDABuilder.createDefault(token).build();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setActivity(Activity.playing("Matthias du Beidl"));

        jda.addEventListener(new Commands());
        jda.addEventListener(new Ban());
        jda.addEventListener(new Filter());
        jda.addEventListener(new Cats());

    }
}