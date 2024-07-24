package org.obs.service.Impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.apache.commons.validator.routines.EmailValidator;
import org.obs.dto.*;
import org.obs.mapper.AddressMapper;
import org.obs.mapper.AgentMapper;
import org.obs.mapper.CompanyMapper;
import org.obs.model.Addresse;
import org.obs.model.Agent;
import org.obs.model.Company;
import org.obs.repository.AddressRepository;
import org.obs.repository.AgentRepository;
import org.obs.repository.CompanyRepository;
import org.obs.service.CompanyService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final AgentRepository agentRepository;
    private final CompanyMapper companyMapper;
    private final AddressMapper addressMapper;
    private final AgentMapper agentMapper;


    public CompanyServiceImpl(CompanyRepository companyRepository,
                              AddressRepository addressRepository,
                              AgentRepository agentRepository,
                              CompanyMapper companyMapper,
                              AddressMapper addressMapper,
                              AgentMapper agentMapper) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
        this.agentRepository = agentRepository;
        this.companyMapper = companyMapper;
        this.addressMapper = addressMapper;
        this.agentMapper = agentMapper;
    }

    @Override
    public CompanyDto getCompanyById(long id) {
       return  companyMapper.toDto(companyRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException("Company not found")));
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyMapper.toDtoList(companyRepository.listAll());
    }

    @Transactional
    @Override
    public CompanyDto createCompany(CompanyCreateDto companyCreateDto) {
        if (!EmailValidator.getInstance().isValid(companyCreateDto.getEmail())) {
            throw new RuntimeException("Invalid email address: " + companyCreateDto.getEmail() + " (please provide something like: username@domain.com");
        }
        Company company = new Company();
        company.setName(companyCreateDto.getName());
        company.setDescription(companyCreateDto.getDescription());
        company.setAddresses(new ArrayList<>());
        company.setAgents(new ArrayList<>());
        company.setEmail(companyCreateDto.getEmail());
        company.setPhoneNumber(companyCreateDto.getPhoneNumber());

        companyRepository.persist(company);
        return companyMapper.toDto(company);
    }

    @Transactional
    @Override
    public CompanyDto updateCompany(long id, CompanyUpdateDto companyUpdateDto) {
        Company company = companyRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException("Company not found"));
        company.setName(companyUpdateDto.getName());
        company.setEmail(companyUpdateDto.getEmail());
        company.setPhoneNumber(companyUpdateDto.getPhoneNumber());
        company.setDescription(companyUpdateDto.getDescription());
        company.setAddresses(companyUpdateDto.getAddresses());
        company.setAgents(companyUpdateDto.getAgents());
        return companyMapper.toDto(company);
    }

    @Transactional
    @Override
    public void deleteCompanyById(long id) {
        Company company = companyRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException("Company not found"));
        companyRepository.delete(company);
    }

    @Transactional
    @Override
    public void deleteAllCompanies() {
        companyRepository.deleteAll();
    }

    @Transactional
    @Override
    public CompanyDto addAgentToCompany(long companyId, AgentCreateDto agentCreateDto) {
        Company company = companyRepository.findByIdOptional(companyId).orElseThrow(() -> new RuntimeException("Company not found"));

        Agent agent = new Agent();
        agent.setRole(agentCreateDto.getRole());
        agent.setFirstName(agentCreateDto.getFirstName());
        agent.setLastName(agentCreateDto.getLastName());
        agent.setAge(agentCreateDto.getAge());
        agent.setGender(agentCreateDto.getGender());
        agent.setEmail(agentCreateDto.getEmail());
        agent.setUsername(agentCreateDto.getUsername());
        agent.setPassword(agentCreateDto.getPassword());
        agent.setCompany(company);
        agent.setShoppingCarts(new ArrayList<>());
        agent.setCreatedOn(LocalDateTime.now());

        agentRepository.persist(agent);

        company.getAgents().add(agent);
        
        return companyMapper.toDto(company);
    }

    @Transactional
    @Override
    public CompanyDto addAddressToCompany(long companyId, AdresseDto adresseDto) {
        Company company = companyRepository.findByIdOptional(companyId).orElseThrow(() -> new RuntimeException("Company not found"));

        Addresse addresse = addressMapper.toEntity(adresseDto);
        addresse.setCompany(company);

        addressRepository.persist(addresse);

        company.getAddresses().add(addresse);

        return companyMapper.toDto(company);
    }
}
