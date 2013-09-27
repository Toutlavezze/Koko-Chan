package Workers;

import Core.Main;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.events.MessageEvent;

public class W_Bot 
{
    private MessageEvent mEvent;
    private String sender;
    
    public W_Bot(Event event)
    {
        super();
        if(event instanceof MessageEvent)
        {
            mEvent = (MessageEvent) event;
            sender = mEvent.getUser().getNick();
        }
    }
    
    public void Shutdown()
    {
        try
        {
           Thread.sleep(500);
           Main.bot.disconnect();
           Main.bot.shutdown(true);        
        } catch (Exception e) { }
    }    
}
