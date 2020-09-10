package com.mypocket;

<<<<<<< HEAD
<<<<<<< HEAD
import com.mypocket.storeManagement.storeUtilities.MySqlStoreUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
<<<<<<< HEAD
<<<<<<< HEAD
public class MyPocketApplication implements CommandLineRunner {
=======
public class MyPocketApplication {
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
=======
public class MyPocketApplication {
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59

	public static void main(String[] args) {

		SpringApplication.run(MyPocketApplication.class, args);
	}
<<<<<<< HEAD
<<<<<<< HEAD

	@Autowired
	private MySqlStoreUtilities mySqlStoreUtilities;

	@Override
	public void run(String... args) throws Exception {

//		mySqlStoreUtilities.createUser();
	}
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
}
