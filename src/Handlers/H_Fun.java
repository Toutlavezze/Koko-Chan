package Handlers;

import Extra.User;
import Workers.*;
import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class H_Fun extends ListenerAdapter
{ 
     @Override
     public void onMessage(MessageEvent event)
     {
        try
        {
            String[] bits = Colors.removeFormattingAndColors(event.getMessage()).split(" ");
            if(bits[0].toLowerCase().equals("!dkp") && new User(event).isRank(event.getUser().getNick(), 1)) new W_Fun(event).dkp();
            if(bits[0].toLowerCase().equals("!wow") && new User(event).isRank(event.getUser().getNick(), 1)) new W_Fun(event).wow();
        } catch (Exception e) { } 
     }    
}
