package Handlers;

import Core.DB;
import Extra.User;
import Workers.W_User;
import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;
import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

public class H_User extends ListenerAdapter
{
    String greet;
    
    @Override
    public void onJoin(JoinEvent event)
    {
        try 
        {
            ResultSet result = DB.get("SELECT * FROM users WHERE nick = '" + event.getUser().getNick() + "';");
            if(!result.last())
            {
                DB.exec("INSERT INTO users (nick, greet, rank) VALUES ('" + event.getUser().getNick() + "', 'Default greet, replace it!', 1);");
                event.getBot().sendNotice(event.getUser(), "Welcome " + event.getUser().getNick() + ", since this is your first time here I registered you as a member. Have fun! =)");
                
            } else {
                if(result.getInt("gactive") == 1)
                {
                   greet = result.getString("greet");
                   greet = greet.replace("%N", event.getUser().getNick());
                   greet = greet.replace("%B", "Koko-Chan");
                   if(result.getInt("gtype") == 0)
                   {
                        event.getBot().sendMessage(event.getChannel(), greet);
                   } else { 
                        event.getBot().sendAction(event.getChannel(), greet);
                   }
                }
             } 
          } catch (SQLException ex) { System.out.println(ex); }
      }
    
    @Override
    public void onMessage(MessageEvent event)
    {
        try
        {
            
            String[] bits = Colors.removeFormattingAndColors(event.getMessage()).split(" ");
            if(bits[0].toLowerCase().equals("!enable_greet")  && new User(event).isRank(event.getUser().getNick(), 1))  new W_User(event).G_Enable();
            if(bits[0].toLowerCase().equals("!update_greet")  && new User(event).isRank(event.getUser().getNick(), 1))  new W_User(event).G_Update();
            if(bits[0].toLowerCase().equals("!disable_greet") && new User(event).isRank(event.getUser().getNick(), 1)) new W_User(event).G_Disable();
            
        } catch (Exception e) { } 
    }
}
