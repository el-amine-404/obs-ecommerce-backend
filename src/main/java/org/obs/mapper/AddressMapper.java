package org.obs.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.obs.dto.AddressResponseDto;
import org.obs.model.Address;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class AddressMapper {

    public Address toEntity(AddressResponseDto adresseDto){
        if (adresseDto == null) {
            return null;
        }
        Address address = new Address();
        address.setId(adresseDto.getId());
        address.setStreetName(adresseDto.getStreetName());
        address.setStreetNumber(adresseDto.getStreetNumber());
        address.setCity(adresseDto.getCity());
        address.setCountry(adresseDto.getCountry());
        address.setZipCode(adresseDto.getZipCode());

        return address;
    }

    public AddressResponseDto toDto(Address address){
        if (address == null) {
            return null;
        }
        AddressResponseDto adresseDto = new AddressResponseDto();
        adresseDto.setId(address.getId());
        adresseDto.setStreetName(address.getStreetName());
        adresseDto.setStreetNumber(address.getStreetNumber());
        adresseDto.setCity(address.getCity());
        adresseDto.setCountry(address.getCountry());
        adresseDto.setZipCode(address.getZipCode());

        return adresseDto;
    }

    public List<Address> toEntityList(List<AddressResponseDto> adresseDtoList) {
        if (adresseDtoList == null) {
            return Collections.emptyList();
        }

        return adresseDtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<AddressResponseDto> toDtoList(List<Address> addressList) {
        if (addressList == null) {
            return Collections.emptyList();
        }

        return addressList.stream()
                .map(this::toDto)
                .toList();
    }

}
