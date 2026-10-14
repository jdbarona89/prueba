package com.chubb.test.service;

import com.chubb.test.model.Client;
import com.chubb.test.respository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;


    @InjectMocks
    private ClientService clientService;



    @Test
    void eleminacion_exitosa() {

        Integer id=1;
        when(clientRepository.delete(id)).thenReturn(1);
        Integer idCliente=clientService.delete(id);
        assertEquals(1,idCliente);
        verify(clientRepository).delete(id);

    }

}