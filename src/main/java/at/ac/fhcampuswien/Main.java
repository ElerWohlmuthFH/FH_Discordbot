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

        String token = "INSERT TOKEN";

        jda = JDABuilder.createDefault(token).build();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setActivity(Activity.playing("Matthias du Beidl"));

        jda.addEventListener(new Commands());
    }
}