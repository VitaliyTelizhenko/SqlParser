package vitaliy.telizhenko.parsers;

import org.junit.jupiter.api.Test;
import vitaliy.telizhenko.parts.Join;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JoinParserTest {

    @Test
    public void testJoins(){

        Join simpleJoin = new Join();
        simpleJoin.setJoin("authors a on a.id = b.author_id");

        List<Join> expected = List.of(simpleJoin);

        JoinParser parser = new JoinParser();

        List<Join> actual = parser.parseJoins("authors a on a.id = b.author_id ");

        assertEquals(1, actual.size());

        assertEquals(expected.get(0).getJoin(), actual.get(0).getJoin());
    }
}