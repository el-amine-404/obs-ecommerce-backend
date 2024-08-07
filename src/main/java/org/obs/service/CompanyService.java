package org.obs.service;

import org.obs.dto.*;

import java.util.List;

public interface CompanyService {
    CompanyResponseDto getCompanyById(long id);
    List<CompanyResponseDto> getAllCompanies();
    CompanyResponseDto createCompany(CompanyCreateDto companyCreateDto);
    CompanyResponseDto updateCompany(long id, CompanyUpdateDto companyUpdateDto);
    void deleteCompanyById(long id);
    void deleteAllCompanies();
    AgentResponseDto addAgentToCompany(long companyId, AgentCreateDto agentCreateDto);
    AddressResponseDto addAddressToCompany(long companyId, AddressCreateDto addressCreateDto);
}
