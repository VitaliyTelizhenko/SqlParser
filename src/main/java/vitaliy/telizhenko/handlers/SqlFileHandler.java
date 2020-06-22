package vitaliy.telizhenko.handlers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vitaliy.telizhenko.parsers.SqlParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class SqlFileHandler {

    private final SqlParser sqlParser;

    @Autowired
    public SqlFileHandler(SqlParser sqlParser) {
        this.sqlParser = sqlParser;
    }

    /*Checks if the filepath is correct and then passes the String representation of file to parser*/
    public void readFileAndPassToParser(String filePath){

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))){

            String line;
            while((line = br.readLine()) != null) {

                if(!StringUtils.isBlank(line))
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            System.out.println("Problems with loading the file, check the correctness of filepath and file");
            System.exit(1);
        }

        String query = sb.toString().toLowerCase();

        sqlParser.parseQuery(query);
    }
}
