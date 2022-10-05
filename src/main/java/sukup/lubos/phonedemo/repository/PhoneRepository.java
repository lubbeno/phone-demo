package sukup.lubos.phonedemo.repository;

import org.springframework.stereotype.Repository;
import sukup.lubos.phonedemo.data.Phone;
import org.springframework.stereotype.Component;
import sukup.lubos.phonedemo.data.PhoneDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PhoneRepository{

    private List<Phone> phoneList = new ArrayList<>();

    public PhoneRepository() {
    }

    public Optional<List<Phone>> getAll() {
        return Optional.of(getPhoneList());
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public Phone save(Phone phone) {
      phoneList.add(phone);
      return phone;
    }
}
