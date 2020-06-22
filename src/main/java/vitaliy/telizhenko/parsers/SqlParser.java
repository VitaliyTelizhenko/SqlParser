package vitaliy.telizhenko.parsers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vitaliy.telizhenko.constants.SqlKeyWords;
import vitaliy.telizhenko.parts.Join;
import vitaliy.telizhenko.parts.Sort;
import vitaliy.telizhenko.parts.Source;
import vitaliy.telizhenko.parts.WhereClause;
import vitaliy.telizhenko.query.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class SqlParser {

    private final ColumnsParser columnsParser;
    private final SourceParser sourceParser;
    private final JoinParser joinParser;
    private final WhereClauseParser clauseParser;
    private final GroupByParser groupByParser;
    private final SortParser sortParser;
    private final LimitParser limitParser;
    private final OffsetParser offsetParser;

    @Autowired
    public SqlParser(ColumnsParser columnsParser,
                     SourceParser sourceParser,
                     JoinParser joinParser,
                     WhereClauseParser clauseParser,
                     GroupByParser groupByParser,
                     SortParser sortParser,
                     LimitParser limitParser,
                     OffsetParser offsetParser) {
        this.sourceParser = sourceParser;
        this.columnsParser = columnsParser;
        this.joinParser = joinParser;
        this.clauseParser = clauseParser;
        this.groupByParser = groupByParser;
        this.sortParser = sortParser;
        this.limitParser = limitParser;
        this.offsetParser = offsetParser;
    }

    /* Checks which SQL key words the query contains, then splits
    the query into parts between this key words and passes them to appropriate parsers
    and after all prints the resulted parsed query to the console */
    public void parseQuery(String query){

        Query parsedQuery = new Query();

        List<String> currentKeyWords = findKeyWords(query);

        for(int i = 0; i < currentKeyWords.size() - 1; i ++){

            String currentWord = currentKeyWords.get(i);
            String nextWord = currentKeyWords.get(i + 1);

            String partOfQuery = StringUtils.substringBetween(query, currentWord, nextWord);

            if("select ".equals(currentWord)){

                List<String> parsedColumns = columnsParser.parseColumns(partOfQuery);

                parsedQuery.setColumns(parsedColumns);
            }

            if("from ".equals(currentWord)){

                List<Source> parsedSources = sourceParser.parseSource(partOfQuery);

                parsedQuery.setSources(parsedSources);
            }

            if("left join ".equals(currentWord) ||
                    "right join ".equals(currentWord) ||
                    "inner join ".equals(currentWord) ||
                    "full join ".equals(currentWord)){

                List<Join> parsedJoins = joinParser.parseJoins(partOfQuery);

                parsedQuery.setJoins(parsedJoins);

            }

            if("where ".equals(currentWord) || "having ".equals(currentWord)){

                List<WhereClause> parsedClauses = clauseParser.parseWhereClauses(partOfQuery);

                parsedQuery.setClauses(parsedClauses);

            }

            if("group by ".equals(currentWord)){

                List<String> parsedGroups = groupByParser.parseGroups(partOfQuery);

                parsedQuery.setGroupBy(parsedGroups);
            }

            if("order by ".equals(currentWord)){

                List<Sort> parsedSortColumns = sortParser.parseSortColumns(partOfQuery);

                parsedQuery.setSortColumns(parsedSortColumns);
            }

            if("limit ".equals(currentWord)){

                Integer lim = limitParser.parseLimit(partOfQuery);

                parsedQuery.setLimit(lim);
            }

            if("offset ".equals(currentWord)){

                Integer off = offsetParser.parseOffset(partOfQuery);

                parsedQuery.setOffset(off);
            }

        }

        System.out.println(parsedQuery);
    }

    private List<String> findKeyWords(String query) {
        List<String> currentKeyWords = new ArrayList<>();

        for(String s : SqlKeyWords.KEY_WORDS) {
            if(query.contains(s)){
                if(!currentKeyWords.contains(s)){
                    currentKeyWords.add(s);
                }
            }
        }
        return currentKeyWords;
    }
}
