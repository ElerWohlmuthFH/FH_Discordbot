package at.ac.fhcampuswien;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;

import javax.security.auth.login.LoginException;

class Main {
    public static JDA jda;
    public static String prefix;

    public static void main(String[] args) throws LoginException {
        BasicConfigurator.configure();

        try(FileReader reader = new FileReader("config")){
            Properties properties = new Properties();
            properties.load(reader);

            prefix = properties.getProperty("PREFIX"); //sets prefix from config file
            String token = properties.getProperty("TOKEN"); //sets discord bot token from config file

            jda = JDABuilder.createDefault(token).build();
            jda.getPresence().setStatus(OnlineStatus.IDLE);
            jda.getPresence().setActivity(Activity.playing("ONLINE"));

            jda.addEventListener(new Commands());

            jda = JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_MEMBERS).build();
            jda.addEventListener(new FirstJoin());

        } catch (Exception e) {
            e.printStackTrace();
        }

        jda.addEventListener(new Ban());
        jda.addEventListener(new Cats());
        jda.addEventListener(new Commands());
        jda.addEventListener(new Filter());
        jda.addEventListener(new FirstJoin());
        jda.addEventListener(new Join());
        jda.addEventListener(new Mute());
    }

}