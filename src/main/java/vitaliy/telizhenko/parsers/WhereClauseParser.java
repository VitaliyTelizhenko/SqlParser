package vitaliy.telizhenko.parsers;

import org.springframework.stereotype.Component;
import vitaliy.telizhenko.constants.SqlDelimiter;
import vitaliy.telizhenko.parts.WhereClause;
import java.util.ArrayList;
import java.util.List;

@Component
public class WhereClauseParser {

    private List<WhereClause> parsedClauses = new ArrayList<>();

    public List<WhereClause> parseWhereClauses(String whereClauses){

        String[] clauses = whereClauses.split(SqlDelimiter.REGEX);

        for (String c : clauses) {
            WhereClause clause = new WhereClause();
            clause.setWhereClause(c);
            parsedClauses.add(clause);

        }

        return parsedClauses;
    }
}
