package vitaliy.telizhenko.parsers;

import org.springframework.stereotype.Component;
import vitaliy.telizhenko.constants.SqlDelimiter;

@Component
public class OffsetParser {

    public Integer parseOffset(String offset){

        String[] offsets = offset.split(SqlDelimiter.REGEX);

        if(offsets.length == 0){
            return null;
        }

        Integer off = Integer.parseInt(offsets[0]);

        return off;
    }
}