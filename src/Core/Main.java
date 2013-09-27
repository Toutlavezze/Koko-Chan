package Core;

import Handlers.*;
import com.mysql.jdbc.ResultSet;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;

public class Main 
{
    public static PircBotX bot = new PircBotX();
    public static void main(String[] args) throws Exception 
    {
        System.out.println("KokoV2 - Previous version was just setback!\r\n");
        
        System.out.println("[INFO]: Initializing bot...");

        
        bot.setVerbose(false);
        bot.setLogin(Globals.USERNAME);        
        bot.identify(Globals.PASSWORD);
        bot.setName(Globals.USERNAME);
        bot.setVersion(Globals.VERSTEXT);
        
        System.out.println("[INFO]: Connecting to database...");
        DB.init();
        
        System.out.println("[INFO]: Loading the listeners...");
        
        bot.getListenerManager().addListener(new H_Bot());
        bot.getListenerManager().addListener(new H_Net());
        bot.getListenerManager().addListener(new H_Fun());
        bot.getListenerManager().addListener(new H_User());
        
        System.out.println("[INFO]: Connecting to server...");
        bot.connect("irc.rizon.net", 6697, new UtilSSLSocketFactory().trustAllCertificates());
        System.out.println("[INFO]: Connection to server established!");
        
        ResultSet result = DB.get("SELECT * FROM channels");
        while(result.next())
        {
            String channel = result.getString("name");
            bot.joinChannel(channel);
        }
    }
}
