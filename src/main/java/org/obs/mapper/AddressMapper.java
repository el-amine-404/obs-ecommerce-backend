package org.obs.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.obs.dto.AdresseDto;
import org.obs.model.Addresse;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class AddressMapper {

    public Addresse toEntity(AdresseDto adresseDto){
        if (adresseDto == null) {
            return null;
        }
        Addresse addresse = new Addresse();
        addresse.setId(adresseDto.getId());
        addresse.setStreetName(adresseDto.getStreetName());
        addresse.setStreetNumber(adresseDto.getStreetNumber());
        addresse.setCity(adresseDto.getCity());
        addresse.setCountry(adresseDto.getCountry());
        addresse.setZipCode(adresseDto.getZipCode());

        return addresse;
    }

    public AdresseDto toDto(Addresse addresse){
        if (addresse == null) {
            return null;
        }
        AdresseDto adresseDto = new AdresseDto();
        adresseDto.setId(addresse.getId());
        adresseDto.setStreetName(addresse.getStreetName());
        adresseDto.setStreetNumber(addresse.getStreetNumber());
        adresseDto.setCity(addresse.getCity());
        adresseDto.setCountry(addresse.getCountry());
        adresseDto.setZipCode(addresse.getZipCode());

        return adresseDto;
    }

    public List<Addresse> toEntityList(List<AdresseDto> adresseDtoList) {
        if (adresseDtoList == null) {
            return Collections.emptyList();
        }

        return adresseDtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<AdresseDto> toDtoList(List<Addresse> addresseList) {
        if (addresseList == null) {
            return Collections.emptyList();
        }

        return addresseList.stream()
                .map(this::toDto)
                .toList();
    }

}
