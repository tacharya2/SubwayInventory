package com.subwayInventory.subway.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {
    public void getCurrentTimeStamp(){
        final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD-hh.mm.ss.ssssss");
        Date date = new Date();
        System.out.println("The current timestamp is:"+ sdf.format(date));
    }
}
