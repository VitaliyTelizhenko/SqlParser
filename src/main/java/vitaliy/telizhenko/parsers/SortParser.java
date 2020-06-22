package vitaliy.telizhenko.parsers;

import org.springframework.stereotype.Component;
import vitaliy.telizhenko.constants.SqlDelimiter;
import vitaliy.telizhenko.parts.Sort;
import java.util.ArrayList;
import java.util.List;

@Component
public class SortParser {

    public List<Sort> parseSortColumns(String sortColumns){

        List<Sort> parsedSortColumns = new ArrayList<>();

        String[] columns = sortColumns.split(SqlDelimiter.REGEX);

        for(String s : columns){
            Sort sort = new Sort();
            sort.setSort(s);
            parsedSortColumns.add(sort);
        }

        return parsedSortColumns;
    }
}
