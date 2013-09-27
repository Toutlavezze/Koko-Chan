package Workers;

import Core.DB;
import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;
import java.util.regex.*;
import org.pircbotx.Colors;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.events.MessageEvent;

public class W_User 
{
    
    private MessageEvent mEvent;
    private String sender;
    
    public W_User(Event event)
    {
        super();
        if(event instanceof MessageEvent)
        {
            mEvent = (MessageEvent) event;
            sender = mEvent.getUser().getNick();
        }
    }
    
    
    public void U_Register()
    {
        try 
        {
            ResultSet result = DB.get("SELECT * FROM users WHERE nick = '" + sender + "';");
            if(!result.last())
            {
                DB.exec("INSERT INTO users (nick, greet, rank) VALUES ('" + sender + "', 'Default greet, replace it!', 1);");
                mEvent.getBot().sendNotice(mEvent.getUser(), "Your new account status is " + Colors.BOLD + "MEMBER" + Colors.NORMAL + " " + sender + "!");
            } else { 
                mEvent.getBot().sendNotice(mEvent.getUser(), "You have already registered this nickname, " + sender + "!");
            }
        } catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
    
    public void G_Enable()
    {
        try 
        {
            ResultSet result = DB.get("SELECT * FROM users WHERE nick = '" + sender + "';");
            if(!result.last())
            {
                mEvent.getBot().sendNotice(mEvent.getUser(), "It appears you have not yet registered your nick, please do so.");
            } else { 
                if(result.getInt("gactive") == 0)
                {
                    DB.exec("UPDATE users SET gactive=1 WHERE nick = '" + sender + "'");
                    mEvent.getBot().sendNotice(mEvent.getUser(), "I have enabled your greeting, to turn it off type '!disabe_greet'");
                } else { 
                    mEvent.getBot().sendNotice(mEvent.getUser(), "Your greet already seems to be enabled, did you mean to disable it with '!disable_greet' ?");
                }
            }
        } catch (SQLException ex) 
        {
            System.out.println(ex);
        }
    }
    
    public void G_Disable()
    {
        try 
        {
            ResultSet result = DB.get("SELECT * FROM users WHERE nick = '" + sender + "';");
            if(result.last())
            {
                if(result.getInt("gactive") == 1)
                {
                    DB.exec("UPDATE users SET gactive=0 WHERE nick = '" + sender + "'");
                    mEvent.getBot().sendNotice(mEvent.getUser(), "I have disabled your greeting, to turn it on type '!enable_greet'");
                } else { 
                    mEvent.getBot().sendNotice(mEvent.getUser(), "Your greet already seems to be disabled, did you mean to enable it with '!enable greet' ?");
                }
            }
        } catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
    
    public void G_Update()
    {
        try 
        {
            ResultSet result = DB.get("SELECT * FROM users WHERE nick = '" + sender + "';");
            if(!result.last())
            {
                Pattern p = Pattern.compile("\"([^\"]*)\"");
                Matcher m = p.matcher(mEvent.getMessage());
                    if(m.find())
                    {

                        String new_greet = m.group(1);
                        String text = new_greet.replace("'", "\\'");
                        DB.exec("UPDATE users SET greet = '" + text + "' WHERE nick = '" + sender + "'");
                        mEvent.getBot().sendNotice(mEvent.getUser(), "Your greet has been changed to: " + m.group(1));
                    } else {
                        mEvent.getBot().sendNotice(mEvent.getUser(), "I was unable to update your greet!");
                        mEvent.getBot().sendNotice(mEvent.getUser(), "Example: !update_greet \"Koko-Chan is quite amazing!\"");
                    }
                }
            } catch (Exception ex) { System.out.println(ex); }
        }
    }
