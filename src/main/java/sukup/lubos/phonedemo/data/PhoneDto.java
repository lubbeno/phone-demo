package sukup.lubos.phonedemo.data;

import java.util.Objects;

public class PhoneDto {

    private String name;
    private long imei;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneDto phoneDto = (PhoneDto) o;
        return imei == phoneDto.imei && Objects.equals(name, phoneDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imei);
    }
}
