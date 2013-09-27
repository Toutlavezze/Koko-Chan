package Extra;

import Core.DB;
import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.events.MessageEvent;

public class User 
{
    
    public MessageEvent mEvent;
    public String sender;
    
    public User(Event event)
    {
        super();
        if(event instanceof MessageEvent)
        {
            mEvent = (MessageEvent) event;
            sender = mEvent.getUser().getNick();
        }
    }
    
    
    public boolean isRank(String user, int rank) throws SQLException
    {
        ResultSet result = DB.get("SELECT rank FROM users WHERE nick = '" + user + "'");
        if(!result.last()) 
        {
            mEvent.getBot().sendNotice(sender, "You are not yet registered, to register simply rejoin the channel!");
           return false; // This user does not exist, return false! 
        } else {
            int level = result.getInt("rank");
            if(level >= rank)
            {
                return true; // User does have a high enough permission level
            } else {
                mEvent.getBot().sendNotice(sender, "You do not have permission for that!");
                return false; // User does not have a high enough permission level
            }
         }
      }    
   }
