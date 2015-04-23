package be.ordina.threeSixty.common.utils.dateutils;

import org.springframework.stereotype.Component;

/**
 * Created by stevedezitter on 15/04/15.
 */
public interface DateTimeFormatterUtil<T> {

    public String formatDate(T t);

    public T fromString(String date);
}
