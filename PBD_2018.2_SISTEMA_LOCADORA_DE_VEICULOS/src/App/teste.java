/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;

/**
 *
 * @author Felipe
 */
public class teste {

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        //System.out.println(format.format(new Date()));
        Calendar calendar = new GregorianCalendar();

        LocalDate localDate = LocalDate.now();

        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        calendar.setTime(date);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());

        

        calendar.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, c1.get(Calendar.SECOND));
        
        
        System.err.println(format.format(calendar.getTime()));

        

    }

}
