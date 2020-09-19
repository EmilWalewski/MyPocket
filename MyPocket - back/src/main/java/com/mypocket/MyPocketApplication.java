package com.mypocket;

import com.mypocket.storeManagement.storeUtilities.MySqlStoreUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyPocketApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(MyPocketApplication.class, args);
	}

	@Autowired
	private MySqlStoreUtilities mySqlStoreUtilities;

	@Override
	public void run(String... args) throws Exception {

//		mySqlStoreUtilities.createUser();
	}
}
