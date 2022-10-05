package sukup.lubos.phonedemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sukup.lubos.phonedemo.data.PhoneDto;
import sukup.lubos.phonedemo.services.PhoneService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PhoneControllerTest {

    @Mock
    PhoneService phoneService;

    @InjectMocks
    PhoneController phoneController;

    MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(phoneController).build();
    }


    @Test
    void getPhones() throws Exception {
        List<PhoneDto> phoneDtoList = phoneDtoSortedList();
        when(phoneService.findAll()).thenReturn(phoneDtoList);
        mockMvc.perform(get("/phones"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void savePhone() throws Exception {
        PhoneDto phone1 = new PhoneDto();
        phone1.setName("A");
        phone1.setImei(1l);

        when(phoneService.save(phone1)).thenReturn(phone1);
        mockMvc.perform(post("/phones")
                     .content(asJsonString(phone1))
                     .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}