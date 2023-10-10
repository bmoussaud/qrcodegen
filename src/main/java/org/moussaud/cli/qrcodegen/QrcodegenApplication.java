package org.moussaud.cli.qrcodegen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@SpringBootApplication
@ShellComponent
public class QrcodegenApplication {

	@ShellMethod
	public String generate(@ShellOption(defaultValue = "https://google.com") String url,
			@ShellOption(defaultValue = "background.png") String imagePath,
			@ShellOption(defaultValue = "generated-qrcode.png") String outPath) {

		System.out.println("Generate qrcode for " + url + " to " + outPath + " using " + imagePath + " as background");
		QrCode qr = new QrCode(url, imagePath, outPath);
		try {
			qr.writeQrCode();
			return "generate a new qrcode";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error : " + e.getMessage();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(QrcodegenApplication.class, args);
	}

}
