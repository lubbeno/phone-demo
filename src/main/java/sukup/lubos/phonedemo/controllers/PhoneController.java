package sukup.lubos.phonedemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sukup.lubos.phonedemo.data.PhoneDto;
import sukup.lubos.phonedemo.services.PhoneService;

import java.util.List;

@RestController
@RequestMapping(value = "/phones")
public class PhoneController {

    private PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping
    public ResponseEntity getPhones(){
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.findAll());
    }

    @PostMapping
    public ResponseEntity savePhone(@RequestBody PhoneDto phoneDto){
        return  ResponseEntity.status(HttpStatus.OK).body(phoneService.save(phoneDto));
    }

}
