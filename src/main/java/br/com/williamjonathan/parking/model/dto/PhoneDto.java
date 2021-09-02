package br.com.williamjonathan.parking.model.dto;

import br.com.williamjonathan.parking.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneDto {

    private String ddd;

    private String phoneNumber;

    private Long parkingId;

    public PhoneDto(Phone phone) {
        this.ddd = phone.getDdd();
        this.phoneNumber = phone.getPhoneNumber();
        this.parkingId = phone.getParking().getId();
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public static List<PhoneDto> convert(List<Phone> phones)    {
        List<PhoneDto> phoneDtos = new ArrayList<>();
        phones.forEach(phone -> {
            PhoneDto phoneDto = new PhoneDto(phone);
            phoneDtos.add(phoneDto);
        });
        return phoneDtos;
    }


}
