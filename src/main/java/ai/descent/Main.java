package ai.descent;

import ai.descent.api.QuoteSource;
import ai.descent.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@SpringBootApplication
@EnableBinding(QuoteSource.class)
@Controller
public class Main {

    @Autowired
    private QuoteSource quoteSource;

    @RequestMapping(path = "/", method = POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void quote(@RequestBody Quote quote, @RequestHeader(HttpHeaders.CONTENT_TYPE) String contentType) {
        sendMessage(quote, contentType);
    }

    private void sendMessage(Object body, Object contentType) {
        quoteSource.usdcad().send(MessageBuilder.createMessage(body,
                new MessageHeaders(Collections.singletonMap(MessageHeaders.CONTENT_TYPE, contentType))));
    }


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}