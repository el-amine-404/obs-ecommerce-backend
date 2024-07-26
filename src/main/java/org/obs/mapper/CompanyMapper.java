package org.obs.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.obs.dto.CompanyResponseDto;
import org.obs.model.Company;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class CompanyMapper {

    private final AgentMapper agentMapper;
    private final AddressMapper addressMapper;

    public CompanyMapper(AgentMapper agentMapper, AddressMapper addressMapper) {
        this.agentMapper = agentMapper;
        this.addressMapper = addressMapper;
    }

    public Company toEntity(CompanyResponseDto companyDto){
        if (companyDto == null) {
            return null;
        }
        Company company = new Company();
        company.setId(companyDto.getId());
        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setAddresses(addressMapper.toEntityList(companyDto.getAddresses()));
        company.setPhoneNumber(companyDto.getPhoneNumber());
        company.setAgents(agentMapper.toEntityList(companyDto.getAgents()));
        company.setEmail(companyDto.getEmail());

        return company;
    }

    public CompanyResponseDto toDto(Company company){
        if (company == null) {
            return null;
        }
        CompanyResponseDto companyDto = new CompanyResponseDto();
        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setDescription(company.getDescription());
        companyDto.setAddresses(addressMapper.toDtoList(company.getAddresses()));
        companyDto.setPhoneNumber(company.getPhoneNumber());
        companyDto.setAgents(agentMapper.toDtoList(company.getAgents()));
        companyDto.setEmail(company.getEmail());

        return companyDto;
    }

    public List<Company> toEntityList(List<CompanyResponseDto> companyDtoList) {
        if (companyDtoList == null) {
            return Collections.emptyList();
        }

        return companyDtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<CompanyResponseDto> toDtoList(List<Company> companyEntityList) {
        if (companyEntityList == null) {
            return Collections.emptyList();
        }

        return companyEntityList.stream()
                .map(this::toDto)
                .toList();
    }

}
