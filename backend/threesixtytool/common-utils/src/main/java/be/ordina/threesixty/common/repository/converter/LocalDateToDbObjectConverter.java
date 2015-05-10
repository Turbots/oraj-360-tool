package be.ordina.threesixty.common.repository.converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.LocalDate;

/**
 * Created by stevedezitter on 15/04/15.
 */
@WritingConverter
public class LocalDateToDbObjectConverter implements Converter<LocalDate, DBObject>{

    @Override
    public DBObject convert(LocalDate localDate) {
        return null;
    }
}
