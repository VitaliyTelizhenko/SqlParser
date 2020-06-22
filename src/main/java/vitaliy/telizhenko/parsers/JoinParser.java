package vitaliy.telizhenko.parsers;

import org.springframework.stereotype.Component;
import vitaliy.telizhenko.constants.SqlDelimiter;
import vitaliy.telizhenko.parts.Join;
import java.util.ArrayList;
import java.util.List;

@Component
public class JoinParser {

    private List<Join> parsedJoins = new ArrayList<>();

    public List<Join> parseJoins(String join) {

        String[] joins = join.split(SqlDelimiter.REGEX);

        for (String j : joins) {
            Join newJoin = new Join();
            newJoin.setJoin(j);
            parsedJoins.add(newJoin);
        }

        return parsedJoins;
    }
}
