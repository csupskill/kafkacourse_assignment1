package javaprof;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OrderConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("group.id", "orderGroup");

        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("TruckInfo"));

        ConsumerRecords<Integer, String> truckInfoRecords = consumer.poll(Duration.ofMillis(750));

        for (ConsumerRecord<Integer, String> record : truckInfoRecords) {
            System.out.println("The truck with id " + record.key() + " is at Long: " + record.value().split(", ")[0] + ", Lat: " + record.value().split(", ")[1]);
        }

        consumer.close();
    }
}
