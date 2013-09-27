package Handlers;

import Extra.User;
import Workers.W_Net;
import java.io.IOException;
import java.sql.SQLException;
import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

/**
 *
 * @author Mark
 */
public class H_Net extends ListenerAdapter
{
    @Override
    public void onMessage(MessageEvent event)
    {
        try
        {
            String[] bits = Colors.removeFormattingAndColors(event.getMessage()).split(" ");
            if(bits[0].toLowerCase().equals("!ping") && bits.length == 2 && new User(event).isRank(event.getUser().getNick(), 2)) new W_Net(event).Ping(bits[1]);
            if(bits[0].toLowerCase().equals("!trace") && bits.length == 2 && new User(event).isRank(event.getUser().getNick(), 2)) new W_Net(event).Trace(bits[1]);
        } catch (SQLException | IOException e) { }
    }
}
