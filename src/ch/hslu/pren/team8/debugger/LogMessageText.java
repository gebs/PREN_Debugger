package ch.hslu.pren.team8.debugger;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by gebs on 3/17/17.
 */
public class LogMessageText extends LogMessageBase implements Serializable {
    private String logText;

    public LogMessageText(LogLevel logLevel,MessageType messageType,String logText){
        super(logLevel,messageType);
        this.logText = logText;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    @Override
    public String toString() {
        SimpleDateFormat dt = new SimpleDateFormat("dd.mm.yyyy hh:mm:ss");
        return dt.format(super.getLogDate()) + " - " +  logText;
    }
}
