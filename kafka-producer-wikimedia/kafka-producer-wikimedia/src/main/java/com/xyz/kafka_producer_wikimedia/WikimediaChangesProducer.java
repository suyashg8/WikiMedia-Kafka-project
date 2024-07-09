package com.xyz.kafka_producer_wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class WikimediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);
    private KafkaTemplate<String,String> template;

    public WikimediaChangesProducer(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_recentchange";
        // to read real time streaming data from wikimedia, we use event source
        EventHandler eventHandler = new WikimediaChangesHandler(template,topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();
        Thread.sleep(10000);
    }
}
