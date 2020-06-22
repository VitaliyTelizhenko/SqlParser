package vitaliy.telizhenko.parsers;

import org.springframework.stereotype.Component;
import vitaliy.telizhenko.constants.SqlDelimiter;

@Component
public class LimitParser {

    public Integer parseLimit(String limit){

        String[] limits = limit.split(SqlDelimiter.REGEX);

        if(limits.length == 0){
            return null;
        }

        Integer lim = Integer.parseInt(limits[0]);

        return lim;
    }
}