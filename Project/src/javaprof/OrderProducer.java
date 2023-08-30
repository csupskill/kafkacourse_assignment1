package javaprof;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class OrderProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (KafkaProducer<Integer, String> producer = new KafkaProducer<>(properties)) {
            ProducerRecord<Integer, String> record = new ProducerRecord<>("TruckInfo", 10, "22.5726 N, 88.3639 E");
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
