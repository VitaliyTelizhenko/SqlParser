package vitaliy.telizhenko;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vitaliy.telizhenko.config.AppConfig;
import vitaliy.telizhenko.handlers.SqlFileHandler;

public class SQLParserApplication {

    public static void main(String[] args) {

        if(args.length != 1) {
            System.out.println("Please specify the filepath");
            System.exit(1);
        }

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SqlFileHandler handler = context.getBean("sqlFileHandler", SqlFileHandler.class);

        handler.readFileAndPassToParser(args[0]);

    }
}
