package com.argos.argos;

import com.argos.argos.model.entities.LoginAcesso;
import com.argos.argos.model.entities.Responsavel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArgosApplicationTests {

	@Test
	void responsavelTest() {

		LoginAcesso login = new LoginAcesso("1234", "senha");

		Responsavel rtest = new Responsavel("Douglas", "32434432", "13-A", login);

	}

}
