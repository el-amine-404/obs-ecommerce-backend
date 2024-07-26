package org.obs.service.Impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.apache.commons.validator.routines.EmailValidator;
import org.obs.dto.*;
import org.obs.model.*;
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


    public CompanyServiceImpl(CompanyRepository companyRepository,
                              AddressRepository addressRepository,
                              AgentRepository agentRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
        this.agentRepository = agentRepository;
    }

    @Override
    public CompanyResponseDto getCompanyById(long id) {
       Company company = companyRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException("Company not found"));
       return CompanyResponseDto.ofEntity(company);
    }

    @Override
    public List<CompanyResponseDto> getAllCompanies() {
        return companyRepository.listAll().stream().map(CompanyResponseDto::ofEntity).toList();
    }

    @Transactional
    @Override
    public CompanyResponseDto createCompany(CompanyCreateDto companyCreateDto) {
        if (!EmailValidator.getInstance().isValid(companyCreateDto.getEmail())) {
            throw new RuntimeException("Invalid email address: " + companyCreateDto.getEmail() + " (please provide something like: username@domain.com");
        }
        Company company = CompanyCreateDto.toEntity(companyCreateDto);

        companyRepository.persist(company);

        return CompanyResponseDto.ofEntity(company);
    }

    @Transactional
    @Override
    public CompanyResponseDto updateCompany(long id, CompanyUpdateDto companyUpdateDto) {
        Company company = companyRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException("Company not found"));
        company.setName(companyUpdateDto.getName());
        company.setEmail(companyUpdateDto.getEmail());
        company.setPhoneNumber(companyUpdateDto.getPhoneNumber());
        company.setDescription(companyUpdateDto.getDescription());
        company.setAddresses(companyUpdateDto.getAddresses());
        company.setAgents(companyUpdateDto.getAgents());
        
        return CompanyResponseDto.ofEntity(company);
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
    public AgentResponseDto addAgentToCompany(long companyId, AgentCreateDto agentCreateDto) {
        Company company = companyRepository.findByIdOptional(companyId).orElseThrow(() -> new RuntimeException("Company not found"));

        Agent agent = AgentCreateDto.toEntity(agentCreateDto);

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .agent(agent)
                .creationDate(LocalDateTime.now())
                .status(ShoppingCartStatus.NEW)
                .build();

        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        shoppingCarts.add(shoppingCart);

        agent.setCompany(company);
        agent.setShoppingCarts(shoppingCarts);
        agent.setCreatedOn(LocalDateTime.now());

        agentRepository.persist(agent);

        company.getAgents().add(agent);
        
        return AgentResponseDto.ofEntity(agent);
    }

    @Transactional
    @Override
    public AddressResponseDto addAddressToCompany(long companyId, AddressCreateDto addressCreateDto) {
        Company company = companyRepository.findByIdOptional(companyId).orElseThrow(() -> new RuntimeException("Company not found"));

        Address address = AddressCreateDto.toEntity(addressCreateDto);

        address.setCompany(company);

        addressRepository.persist(address);

        company.getAddresses().add(address);

        return AddressResponseDto.ofEntity(address);
    }
}
