package vitaliy.telizhenko.parsers;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ColumnsParserTest {

    @Test
    public void testColumnsParser(){
        ColumnsParser parser = new ColumnsParser();

        String columns = "books, author.name, sum(*) ";

        List<String> parsed = parser.parseColumns(columns);

        assertEquals(3, parsed.size());
        assertEquals("books", parsed.get(0));
        assertEquals("sum(*)", parsed.get(2));
    }
}