package dev.morera.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;






@ExtendWith(MockitoExtension.class) // use to be @RunWith in JUnit 4
public class ClientServiceTests {
	@InjectMocks
	private static ClientService clientService;
}//file
