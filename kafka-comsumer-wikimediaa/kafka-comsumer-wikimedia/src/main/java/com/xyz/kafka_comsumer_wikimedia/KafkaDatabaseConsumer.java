package com.xyz.kafka_comsumer_wikimedia;

import com.xyz.kafka_comsumer_wikimedia.entity.WikimediaData;
import com.xyz.kafka_comsumer_wikimedia.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikimediaDataRepository dataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(topics="wikimedia_recentchange",groupId = "myGroup")
    public void consume(String eventMessage){
        LOGGER.info("Message received from topic :"+ eventMessage);
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage.substring(0,10));

        dataRepository.save(wikimediaData);
    }
}
