package vitaliy.telizhenko.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class SqlParserTest{

    @Mock
    private ColumnsParser columnsParser;
    @Mock
    private SourceParser sourceParser;
    @Mock
    private JoinParser joinParser;
    @Mock
    private WhereClauseParser clauseParser;
    @Mock
    private GroupByParser groupByParser;
    @Mock
    private SortParser sortParser;
    @Mock
    private LimitParser limitParser;
    @Mock
    private OffsetParser offsetParser;

    private SqlParser sqlParser;

    @BeforeEach
    public void init(){
        initMocks(this);
        sqlParser = new SqlParser(columnsParser, sourceParser,
                joinParser, clauseParser,
                groupByParser, sortParser,
                limitParser, offsetParser);
    }

    @Test
    public void testColumns(){

        String query = "select username, count(book.id), sum(price) from books;";
        sqlParser.parseQuery(query);
        verify(columnsParser, times(1)).parseColumns("username, count(book.id), sum(price) ");

        query = "select * from users;";
        sqlParser.parseQuery(query);
        verify(columnsParser, times(1)).parseColumns("* ");
    }

    @Test
    public void testSources(){

        String query = "select * from books, films;";
        sqlParser.parseQuery(query);
        verify(sourceParser, times(1)).parseSource("books, films");

        String subSelect = "select * from (select * from users) u;";
        sqlParser.parseQuery(subSelect);
        verify(sourceParser, times(1)).parseSource("(select * from users) u");
    }

    @Test
    public void testJoins(){

        String withoutJoins = "select * from books;";
        sqlParser.parseQuery(withoutJoins);
        verify(joinParser, times(0)).parseJoins(anyString());

        String multipleJoins = "select * from books left join authors " +
                "inner join films " +
                "full join users;";
        sqlParser.parseQuery(multipleJoins);
        verify(joinParser, times(3)).parseJoins(anyString());

        String query = "select * from books left join authors on book.author_id = author.id;";
        sqlParser.parseQuery(query);
        verify(joinParser, times(1)).parseJoins("authors on book.author_id = author.id");
    }

    @Test
    public void testWhereAndHavingClauses(){

        String withoutClauses = "select * from books;";
        sqlParser.parseQuery(withoutClauses);
        verify(clauseParser, times(0)).parseWhereClauses(anyString());

        String multipleClauses = "select * from books where book.id = 1 group by author having count(*) >10;";
        sqlParser.parseQuery(multipleClauses);
        verify(clauseParser, times(2)).parseWhereClauses(anyString());

        String query = "select * from books where book.id = 1 and sum(*) > 100;";
        sqlParser.parseQuery(query);
        verify(clauseParser, times(1)).parseWhereClauses("book.id = 1 and sum(*) > 100");
    }

    @Test
    public void testGroups(){

        String withoutGroups = "select * from books;";
        sqlParser.parseQuery(withoutGroups);
        verify(groupByParser, times(0)).parseGroups(anyString());

        String query = "select * from books group by name;";
        sqlParser.parseQuery(query);
        verify(groupByParser, times(1)).parseGroups("name");
    }

    @Test
    public void testSort(){

        String withoutSort = "select * from books;";
        sqlParser.parseQuery(withoutSort);
        verify(sortParser, times(0)).parseSortColumns(anyString());

        String query = "select * from books order by name asc, price desc;";
        sqlParser.parseQuery(query);
        verify(sortParser, times(1)).parseSortColumns("name asc, price desc");
    }

    @Test
    public void testLimitAndOffset(){

        String withoutLimitAndOffset = "select * from books;";
        sqlParser.parseQuery(withoutLimitAndOffset);
        verify(limitParser, times(0)).parseLimit(anyString());
        verify(offsetParser, times(0)).parseOffset(anyString());

        String query = "select * from books limit 6 offset 3;";
        sqlParser.parseQuery(query);
        verify(limitParser, times(1)).parseLimit("6 ");
        verify(offsetParser, times(1)).parseOffset("3");
    }
}