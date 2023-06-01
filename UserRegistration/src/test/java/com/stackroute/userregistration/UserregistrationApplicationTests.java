package com.stackroute.userregistration;

import com.stackroute.userregistration.dao.UserRepository;
import com.stackroute.userregistration.entity.User;
import com.stackroute.userregistration.exception.UserException;
import com.stackroute.userregistration.service.UserDetailsServiceImpl;
import com.stackroute.userregistration.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@SpringBootTest
class UserregistrationApplicationTests {

	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userService;
	@InjectMocks
	private UserDetailsServiceImpl userDetailsService;

	@Test
	public void registerAsSellerTestValid() throws UserException {

		User user = new User();
		user.setId(1);
		user.setFirstName("user1");
		user.setLastName("lastname");
		user.setPassword("test");
		user.setEmail("user@gmail.com");
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(null));


		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));

		Assertions.assertEquals("SELLER",userService.registerUserAsSeller(user).getUserType().name());
	}

	@Test
	public void registerAsSellerTestInValid() throws UserException {

		User user = new User();
		user.setId(1);
		user.setFirstName("user1");
		user.setLastName("lastname");
		user.setPassword("test");
		user.setEmail("user@gmail.com");
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));


		Assertions.assertThrows(UserException.class,() ->userService.registerUserAsSeller(user));
	}

	@Test
	public void registerAsBuyerTestValid() throws UserException {

		User user = new User();
		user.setId(1);
		user.setFirstName("user1");
		user.setLastName("lastname");
		user.setPassword("test");
		user.setEmail("user@gmail.com");
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(null));


		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));

		Assertions.assertEquals("BUYER",userService.registerUserAsBuyer(user).getUserType().name());
	}

	@Test
	public void registerAsBuyerTestInValid() throws UserException {

		User user = new User();
		user.setId(1);
		user.setFirstName("user1");
		user.setLastName("lastname");
		user.setPassword("test");
		user.setEmail("user@gmail.com");
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));


		Assertions.assertThrows(UserException.class,() ->userService.registerUserAsBuyer(user));
	}

	@Test
	public void updateUserValidTest() throws UserException {

		User user = new User();
		user.setId(1);
		user.setFirstName("user1");
		user.setLastName("lastname");
		user.setPassword("test");
		user.setEmail("user@gmail.com");
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

		User user2 = user;
		user.setPassword("test1");
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user2);

		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user2));

		Assertions.assertEquals("test1",userService.updateUser(user).getPassword());
	}

	@Test
	public void updateUserInValidTest() throws UserException {

		User user = new User();
		user.setId(1);
		user.setFirstName("user1");
		user.setLastName("lastname");
		user.setPassword("test");
		user.setEmail("user@gmail.com");
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(null));

		Assertions.assertThrows(UserException.class,() -> userService.updateUser(user));
	}
	@Test
	public void loadUserByUsernameValidTest(){

		User user = new User();
		user.setId(1);
		user.setFirstName("user1");
		user.setLastName("lastname");
		user.setPassword("test");
		user.setEmail("user@gmail.com");
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

		Assertions.assertEquals("test",userDetailsService.loadUserByUsername("user@gmail.com").getPassword());
	}

	@Test
	public void loadUserByUsernameInValidTest() throws UsernameNotFoundException {

		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(null));

		Assertions.assertThrows(UsernameNotFoundException.class,() -> userDetailsService.loadUserByUsername("user@gmail.com"));
	}
}
