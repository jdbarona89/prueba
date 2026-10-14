package com.chubb.test.service;

import java.util.ArrayList;
import java.util.List;


import com.chubb.test.commun.ClienteNoexiste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chubb.test.model.Client;
import com.chubb.test.model.dtos.ClientDto;
import com.chubb.test.respository.IClientRepository;


@Service
public class ClientService implements IClientService {

	@Autowired
	private IClientRepository clientRepository;
	

	 
	@Override
	public List<ClientDto> findAll() {
		List<ClientDto> clients = new ArrayList<ClientDto>();
		
		try {
			clientRepository.findAll().forEach((c) -> {
				//convert  this to auto mapped
				ClientDto cl = new ClientDto(c.getId(), c.getFirstName(), c.getLastName(), c.getSurname(), c.getPhone());
				clients.add(cl);
			});
        
		}catch(Exception ex){
			throw ex;
		}
		
		return clients;
	}



	@Override
	public int save(ClientDto clientdto) {
		int row = 0;
		try {
			
			Client client = new Client();
			
			//convert  this to auto mapped
			
			client.setFirstName(clientdto.getFirstName());
			client.setLastName(clientdto.getLastName());
			client.setSurname(clientdto.getSurname());
			client.setPhone(clientdto.getPhone());
			
			row = clientRepository.save(client);
        
		}catch(Exception ex){
			throw ex;
		}
		
		return row;
	}

	@Override
	public int delete(Integer id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("El id debe ser positivo");
		}

		int rows = clientRepository.delete(id);

		if (rows == 0) {
			throw new ClienteNoexiste("No existe un cliente con id " + id);
		}

		return rows;
	}

}
