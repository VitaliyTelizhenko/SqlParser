package vitaliy.telizhenko.parsers;

import org.springframework.stereotype.Component;
import vitaliy.telizhenko.constants.SqlDelimiter;
import vitaliy.telizhenko.parts.Source;
import java.util.ArrayList;
import java.util.List;

@Component
public class SourceParser {

    List<Source> parseSource(String sources){

        List<Source> parsedSources = new ArrayList<>();

        String[] subSelects = sources.split(SqlDelimiter.REGEX);

        for (String s : subSelects){
            Source newSource = new Source();
            newSource.setSource(s);
            parsedSources.add(newSource);
        }

        return parsedSources;

    }
}
