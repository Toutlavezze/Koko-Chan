package Workers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.events.MessageEvent;

public class W_Net 
{
    private MessageEvent mEvent;
    private String sender;
    private int count;
    
    public W_Net(Event event)
    {
        super();
        if(event instanceof MessageEvent)
        {
            mEvent = (MessageEvent) event;
            sender = mEvent.getUser().getNick();
        }
    }
    
    public void Ping(String addr) throws IOException
    {
        mEvent.getBot().sendNotice(mEvent.getUser(), "I will start pinging and send you the results!");
        List<String> commands = new ArrayList<>();
        commands.add("ping");
        commands.add("-n");
        commands.add("3");
        commands.add(addr);
        
        String s = null;

        ProcessBuilder pb = new ProcessBuilder(commands);
        Process process = pb.start();

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((s = stdInput.readLine()) != null)
        {
                mEvent.getBot().sendMessage(mEvent.getUser().getNick(), s);
        }
        while ((s = stdError.readLine()) != null)
        {
                mEvent.getBot().sendMessage(mEvent.getUser().getNick(), s);
        } 
        mEvent.getBot().sendNotice(mEvent.getUser(), "I have sent you the results " + mEvent.getUser().getNick() + "!");
    }
    
    public void Trace(String addr) throws IOException
    {
        mEvent.getBot().sendNotice(mEvent.getUser(), "I will start a trace and send you the results!");
        List<String> commands = new ArrayList<>();
        commands.add("tracert");
        commands.add(addr);
        
        String s = null;

        ProcessBuilder pb = new ProcessBuilder(commands);
        Process process = pb.start();

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((s = stdInput.readLine()) != null)
        {
                mEvent.getBot().sendMessage(mEvent.getUser().getNick(), s);
        }
        while ((s = stdError.readLine()) != null)
        {
                mEvent.getBot().sendMessage(mEvent.getUser().getNick(), s);
        } 
        mEvent.getBot().sendNotice(mEvent.getUser(), "I have sent you the results " + mEvent.getUser().getNick() + "!");
    }
}

    

