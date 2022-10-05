package sukup.lubos.phonedemo;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sukup.lubos.phonedemo.data.Phone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import sukup.lubos.phonedemo.repository.PhoneRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableWebMvc// added because of swagger
public class PhoneDemoApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext =SpringApplication.run(PhoneDemoApplication.class, args);

        Phone phone1 = new Phone();
        phone1.setName("Phone1");
        phone1.setImei(1l);

        Phone phone2 = new Phone();
        phone2.setName("APhone1");
        phone2.setImei(2l);

        Phone phone3 = new Phone();
        phone3.setName("CPhone1");
        phone3.setImei(3l);

        List<Phone> phones = new ArrayList<>();
                phones.add(phone1);
                phones.add(phone3);
                phones.add(phone2);

        PhoneRepository phoneRepository =
                applicationContext.getBean(PhoneRepository.class);
        phoneRepository.setPhoneList(phones);


    }
}
