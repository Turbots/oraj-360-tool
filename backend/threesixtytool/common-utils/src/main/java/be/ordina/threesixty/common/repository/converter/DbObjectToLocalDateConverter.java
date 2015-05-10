package be.ordina.threesixty.common.repository.converter;

import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDate;

/**
 * Created by stevedezitter on 15/04/15.
 */
@ReadingConverter
public class DbObjectToLocalDateConverter implements Converter<DBObject, LocalDate> {

    @Override
    public LocalDate convert(DBObject dbObject) {
        return LocalDate.of((Integer)dbObject.get("year"), (Integer)dbObject.get("month"), (Integer)dbObject.get("day"));
    }
}
