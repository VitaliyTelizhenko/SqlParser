package vitaliy.telizhenko.parsers;

import org.springframework.stereotype.Component;
import vitaliy.telizhenko.constants.SqlDelimiter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ColumnsParser {

    public List<String> parseColumns(String columns) {

        List<String> parsedColumns = new ArrayList<>();

        String[] sepColumns = columns.split(SqlDelimiter.REGEX);

        for(String s : sepColumns){
            parsedColumns.add(s);
        }

        return parsedColumns;
    }
}
