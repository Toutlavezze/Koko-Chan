package Handlers;

import Extra.User;
import Workers.*;
import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class H_Bot extends ListenerAdapter
{
    @Override
    public void onMessage(MessageEvent event)
    {
        try
        {
            String[] bits = Colors.removeFormattingAndColors(event.getMessage()).split(" ");
            if(bits[0].toLowerCase().equals("!shutdown") && new User(event).isRank(event.getUser().getNick(), 5)) new W_Bot(event).Shutdown();
        } catch (Exception e) { }
    }
}
