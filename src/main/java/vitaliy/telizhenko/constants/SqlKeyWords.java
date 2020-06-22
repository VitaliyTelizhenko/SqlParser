package vitaliy.telizhenko.constants;

import java.util.List;

public interface SqlKeyWords {

    List<String> KEY_WORDS = List.of("select ", "from ",
            "left join ", "right join ",
            "inner join ", "full join ",
            "where ", "group by ",
            "having ","order by ",
            "limit ", "offset ", ";");
}
