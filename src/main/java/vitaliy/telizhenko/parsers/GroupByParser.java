package vitaliy.telizhenko.parsers;

import org.springframework.stereotype.Component;
import vitaliy.telizhenko.constants.SqlDelimiter;
import java.util.ArrayList;
import java.util.List;

@Component
public class GroupByParser {

    public List<String> parseGroups(String groups) {

        List<String> parsedGroups = new ArrayList<>();

        String[] group = groups.split(SqlDelimiter.REGEX);

        for(String g : group){
            parsedGroups.add(g);
        }

        return parsedGroups;
    }
}
