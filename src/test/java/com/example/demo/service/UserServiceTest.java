
package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	void testGetAllUsers() {
		User user1 = new User(1L, "John");
		User user2 = new User(2L, "Jane");

		when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

		List<User> users = userService.getAllUsers(); // ✅ fixed generics

		assertEquals(2, users.size());
		verify(userRepository, times(1)).findAll();
	}

	@Test
	void testSaveUser() {
		User user = new User(1L, "John");
		when(userRepository.save(user)).thenReturn(user);

		User savedUser = userService.saveUser(user);

		assertEquals("John", savedUser.getName());
		verify(userRepository, times(1)).save(user);
	}
}
