package com.Heather;

import java.util.Date;

/**
 * Created by cryst on 3/24/2016.
 */
public class resolvedTicket extends Ticket {
    private Date dateResolved;
    private String resolution;

    public resolvedTicket(String desc, int p, String rep, Date date, Date rdate, String resolve) {
        super(desc, p, rep, date);
        this.dateResolved=rdate;
        this.resolution=resolve;
    }
}
