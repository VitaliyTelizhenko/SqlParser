package vitaliy.telizhenko.query;

import org.springframework.stereotype.Component;
import vitaliy.telizhenko.parts.Join;
import vitaliy.telizhenko.parts.Sort;
import vitaliy.telizhenko.parts.Source;
import vitaliy.telizhenko.parts.WhereClause;
import java.util.List;

/*Class keeps the results of parsing the query*/
@Component
public class Query {

    private List<String> columns;
    private List<Source> sources;
    private List<Join> joins;
    private List<WhereClause> clauses;
    private List<String> groupBy;
    private List<Sort> sortColumns;
    private Integer limit;
    private Integer offset;

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public List<Join> getJoins() {
        return joins;
    }

    public void setJoins(List<Join> joins) {
        this.joins = joins;
    }

    public List<WhereClause> getClauses() {
        return clauses;
    }

    public void setClauses(List<WhereClause> clauses) {
        this.clauses = clauses;
    }

    public List<String> getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(List<String> groupBy) {
        this.groupBy = groupBy;
    }

    public List<Sort> getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(List<Sort> sortColumns) {
        this.sortColumns = sortColumns;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Query {" + "\n" +
                (columns == null?"":"Columns = " + columns + "\n") +
                (sources == null?"":"From = " + sources + "\n") +
                (joins == null?"":"Joins = " + joins + "\n") +
                (clauses == null?"":"WhereClauses = " + clauses + "\n") +
                (groupBy == null?"":"GroupBy = " + groupBy + "\n") +
                (sortColumns == null?"":"SortBy = " + sortColumns + "\n") +
                (limit == null?"":"Limit = " + limit + "\n") +
                (offset == null?"":"Offset = " + offset+ "\n") +
                '}';
    }
}
