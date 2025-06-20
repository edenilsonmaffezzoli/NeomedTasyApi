/*package com.NeomedTasyApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeomedTasyApi {

	public static void main(String[] args) {
		SpringApplication.run(NeomedTasyApi.class, args);
	}
}

*/
package com.NeomedTasyApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
@Component
public class NeomedTasyApi {

	@Value("${springdoc.swagger-ui.path:/swagger-ui.html}")
	private String swaggerPath;

	@Value("${server.port:8080}")
	private String serverPort;

	public static void main(String[] args) {
		SpringApplication.run(NeomedTasyApi.class, args);
	}

	/*@Override
	public void run(ApplicationArguments args) throws Exception {
		String url = "http://localhost:" + serverPort + swaggerPath;
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			Desktop.getDesktop().browse(new URI(url));
		} else {
			// Fallback para sistemas que não suportam Desktop
			Runtime.getRuntime().exec("cmd /c start chrome " + url); // Para Windows
			// Para Linux: Runtime.getRuntime().exec("google-chrome " + url);
			// Para Mac: Runtime.getRuntime().exec("open -a 'Google Chrome' " + url);
		}*/

}