package org.obs.service;

import org.obs.dto.*;
import org.obs.model.Agent;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    public CompanyDto getCompanyById(long id);
    public List<CompanyDto> getAllCompanies();
    public CompanyDto createCompany(CompanyCreateDto companyCreateDto);
    public CompanyDto updateCompany(long id, CompanyUpdateDto companyUpdateDto);
    public void deleteCompanyById(long id);
    public void deleteAllCompanies();
    public CompanyDto addAgentToCompany(long companyId, AgentCreateDto agentCreateDto);
    public CompanyDto addAddressToCompany(long companyId, AdresseDto adresseDto);
}
