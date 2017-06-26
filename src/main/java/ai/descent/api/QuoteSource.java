package ai.descent.api;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface QuoteSource {


    @Output
    MessageChannel usdcad();

}
