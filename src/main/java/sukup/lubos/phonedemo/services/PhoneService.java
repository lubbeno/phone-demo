package sukup.lubos.phonedemo.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import sukup.lubos.phonedemo.data.Phone;
import sukup.lubos.phonedemo.data.PhoneDto;
import org.springframework.stereotype.Service;
import sukup.lubos.phonedemo.repository.PhoneRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    private PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public List<PhoneDto> findAll() {
        Optional<List<Phone>> phones =phoneRepository.getAll();
        if(phones.isPresent()){
            return  phones.get().stream()
                    .sorted()
                    .map( a -> mapToDTO( a , new PhoneDto()))
                    .collect(Collectors.toList());
        }else{
            // here we can create custom error and exeption for proper handling duplicities
            throw   new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public PhoneDto save(PhoneDto phoneDto) {
        Phone phoneToInsert = mapToEntity(phoneDto, new Phone());
        if(! phoneRepository.getAll().get().contains(phoneToInsert)){
            Phone phone= phoneRepository.save(phoneToInsert);
            return mapToDTO(phone, new PhoneDto());
        }else{
            // here we can create custom error and exeption for proper handling duplicities
         throw   new ResponseStatusException(HttpStatus.BAD_REQUEST,"Phone already exists !!!");
        }


    }


    private PhoneDto mapToDTO(final Phone phone,
                                      final PhoneDto phoneDto) {
        phoneDto.setName(phone.getName());
        phoneDto.setImei(phone.getImei());
        return phoneDto;
    }

    private Phone mapToEntity(final PhoneDto phoneDto,
                                      final Phone phone) {
        phone.setName(phoneDto.getName());
        phone.setImei(phoneDto.getImei());
        return phone;
    }


}
