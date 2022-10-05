package sukup.lubos.phonedemo.services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import sukup.lubos.phonedemo.data.Phone;
import sukup.lubos.phonedemo.data.PhoneDto;
import sukup.lubos.phonedemo.repository.PhoneRepository;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {

    @Mock
    PhoneRepository phoneRepository;

    @InjectMocks
    PhoneService phoneService;

    @Test
    void findAll() {
        when(phoneRepository.getAll()).thenReturn(Optional.of(phoneList()));
        List<PhoneDto> phoneDtos = phoneService.findAll();
        List<PhoneDto> sortedPhones =  phoneDtoSortedList();

        assertThat(phoneDtos.size()).isEqualTo(phoneList().size());
        assertThat(phoneDtos).isEqualTo(sortedPhones);
        verify(phoneRepository, times(1)).getAll();
    }

    @Test
    void findAllNotFound() {
        when(phoneRepository.getAll()).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> phoneService.findAll());
        verify(phoneRepository, times(1)).getAll();
    }

    @Test
    void saveUnique() {
        PhoneDto phoneToSave = new PhoneDto();
        phoneToSave.setImei(1l);
        phoneToSave.setName("phone1");

        Phone phone= new Phone();
        phone.setImei(1l);
        phone.setName("phone1");

        when(phoneRepository.save(any(Phone.class))).thenReturn(phone);
        when(phoneRepository.getAll()).thenReturn( Optional.of(Collections.emptyList()));
        PhoneDto savedPhone = phoneService.save(phoneToSave);
        assertThat(savedPhone).usingRecursiveComparison().isEqualTo(phoneToSave);
        verify(phoneRepository, times(1)).save(any(Phone.class));
    }

    @Test
    void saveDuplicity() {
        PhoneDto phoneToSave = new PhoneDto();
        phoneToSave.setImei(1l);
        phoneToSave.setName("phone1");

        Phone phone= new Phone();
        phone.setImei(1l);
        phone.setName("phone1");

        when(phoneRepository.getAll()).thenReturn( Optional.of(new ArrayList<>(Arrays.asList(phone))));
        assertThrows(ResponseStatusException.class, () -> phoneService.save(phoneToSave));
        verify(phoneRepository, times(0)).save(any(Phone.class));
    }

    private List<Phone> phoneList(){
        Phone phone1 = new Phone();
        phone1.setName("A");
        phone1.setImei(1l);

        Phone phone2 = new Phone();
        phone2.setName("B");
        phone2.setImei(2l);

        Phone phone3 = new Phone();
        phone3.setName("C");
        phone3.setImei(3l);

        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone3);
        phones.add(phone2);
        return phones;
    }

    private List<PhoneDto> phoneDtoSortedList(){
        PhoneDto phone1 = new PhoneDto();
        phone1.setName("A");
        phone1.setImei(1l);

        PhoneDto phone2 = new PhoneDto();
        phone2.setName("B");
        phone2.setImei(2l);

        PhoneDto phone3 = new PhoneDto();
        phone3.setName("C");
        phone3.setImei(3l);

        List<PhoneDto> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);
        phones.add(phone3);
        return phones;
    }
}