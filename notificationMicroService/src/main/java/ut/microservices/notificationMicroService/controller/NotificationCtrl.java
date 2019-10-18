package ut.microservices.notificationMicroService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.notificationMicroService.model.MrTemplate;
import ut.microservices.notificationMicroService.repository.MrTemplateRepository;

@RestController
@RequestMapping("/notify")
public class NotificationCtrl {

    @Autowired
    MrTemplateRepository mrTemplateRepository;

    @Autowired
    ObjectMapper mapper;

    @KafkaListener(topics = "sendMail")
    public void notifyMail(String param) {
        HashMap<String, String> parentMap = new HashMap<String, String>();
        try {
            parentMap = (HashMap<String, String>) mapper.readValue(param, new TypeReference<Map<String, String>>() {
            });
            List<MrTemplate> mrTemplateList=mrTemplateRepository.findById(Integer.valueOf(parentMap.get("id")));
            MrTemplate mrTemplate=mrTemplateList.get(0);
            String template=mrTemplate.getMrTemplateDescriptionEnglish();
            String data=parentMap.get("values");
            HashMap<String, String> valueMap=(HashMap<String, String>) mapper.readValue(data, new TypeReference<Map<String, String>>() {
            });
            for (Entry entry : valueMap.entrySet()) {
                template=template.replace(":"+entry.getKey().toString(),entry.getValue().toString());
            }
            System.out.println(template);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}