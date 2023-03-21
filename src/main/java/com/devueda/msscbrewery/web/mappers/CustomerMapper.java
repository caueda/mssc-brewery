package com.devueda.msscbrewery.web.mappers;

import com.devueda.msscbrewery.domain.Customer;
import com.devueda.msscbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDto customerDto);
}
