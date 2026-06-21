package mmdev.consumer;

import mmdev.event.MaterialUploadedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "material-events",groupId = "student-platform-group")
    public void consumeMaterialEvent(MaterialUploadedEvent event){
        System.out.println("=========================================");
        System.out.println("New material: " + event.getTitle());
        System.out.println("From author:" + event.getAuthorUsername());
        System.out.println("Here we can send email or push-notification for student");
        System.out.println("=========================================");
    }

}
