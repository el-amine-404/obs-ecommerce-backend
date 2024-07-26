package org.obs.service;

import org.obs.dto.*;

import java.util.List;

public interface CompanyService {
    public CompanyResponseDto getCompanyById(long id);
    public List<CompanyResponseDto> getAllCompanies();
    public CompanyResponseDto createCompany(CompanyCreateDto companyCreateDto);
    public CompanyResponseDto updateCompany(long id, CompanyUpdateDto companyUpdateDto);
    public void deleteCompanyById(long id);
    public void deleteAllCompanies();
    public AgentResponseDto addAgentToCompany(long companyId, AgentCreateDto agentCreateDto);
    public AddressResponseDto addAddressToCompany(long companyId, AddressCreateDto addressCreateDto);
}
