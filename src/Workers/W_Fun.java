package Workers;

import Core.DB;
import com.mysql.jdbc.ResultSet;
import org.pircbotx.Colors;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.events.MessageEvent;

public class W_Fun
{
       
    private MessageEvent mEvent;
    private String sender;
    
    public W_Fun(Event event)
    {
        super();
        if(event instanceof MessageEvent)
        {
            mEvent = (MessageEvent) event;
            sender = mEvent.getUser().getNick();
        }
    }
    
    public void dkp()
    {
        
    }
    
    public void wow()
    {
        try 
        {
            ResultSet result =  DB.get("SELECT * FROM quotes ORDER BY RAND() LIMIT 1;");
            if(result.last())
            {
                mEvent.getBot().sendMessage(mEvent.getChannel(), result.getString("text") + " - " + Colors.BOLD + result.getString("npc"));
            }
        } catch (Exception ex) { System.out.println(ex); }
    }
    
    
    
    
    
}
