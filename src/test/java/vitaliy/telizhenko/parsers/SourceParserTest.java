package vitaliy.telizhenko.parsers;

import org.junit.jupiter.api.Test;
import vitaliy.telizhenko.parts.Source;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SourceParserTest {

    @Test
    public void testSources(){

        Source subSelect = new Source();
        subSelect.setSource("(select * from users) u");

        Source simpleSource = new Source();
        simpleSource.setSource("books");

        List<Source> expected = List.of(subSelect, simpleSource);

        SourceParser parser = new SourceParser();

        List<Source> actual = parser.parseSource("(select * from users) u, books ");

        assertEquals(2, actual.size());

        assertEquals(expected.get(0).getSource(), actual.get(0).getSource());
        assertEquals(expected.get(1).getSource(), actual.get(1).getSource());
    }
}