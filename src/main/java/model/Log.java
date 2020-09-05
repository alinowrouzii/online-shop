package model;

import java.util.Date;

public abstract class Log {
    private String logId ;
    private Date logDate ;

    Log(String logId, Date logDate) {
        this.logId = logId;
        this.logDate = logDate;
    }

    public String getLogId() {
        return logId;
    }

    public Date getLogDate() {
        return logDate;
    }

}
