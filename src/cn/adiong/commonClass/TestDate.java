package cn.adiong.commonClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
    public static void main(String[] args) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Date date = df.parse("2020年7月20日 16:06:20");
        // System.out.println(df.format(new Date()));
        System.out.println(date);

        DateFormat df2 = new SimpleDateFormat("D");
        System.out.println(df2.format(new Date()));
    }
}
