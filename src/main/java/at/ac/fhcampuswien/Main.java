package at.ac.fhcampuswien;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

class Main {
    public static JDA jda;
    public static String prefix = "/";

    public static void main(String[] args) throws LoginException {

        String token = "OTEwODUxNTQ1NDMwNjM0NTU2.YZY2mw.fxtZISrOrN7JLg4WLEdF4aE_2G8";

        jda = JDABuilder.createDefault(token).build();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setActivity(Activity.playing("Hello World"));

        jda.addEventListener(new Commands());

        jda = JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_MEMBERS).build();
        jda.addEventListener(new FirstJoin());
    }
}