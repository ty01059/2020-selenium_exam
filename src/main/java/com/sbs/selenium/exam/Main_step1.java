package com.sbs.selenium.exam;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main_step1 {
	public static void main(String[] args) {

		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");

		// WebDriver 경로 지정
		System.setProperty("webdriver.chrome.driver", path.toString());

		// WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");

		// WebDriver 객체 생성
		ChromeDriver driver = new ChromeDriver(options);

		// 객체 실행 - 빈 탭 생성
//		driver.executeScript("window.open('about:blank','_blank');");

		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		// 첫번째 탭으로 전환
		driver.switchTo().window(tabs.get(0));
		driver.get("https://unsplash.com/t/nature");

		File downloadsForder = new File("downloads");
		if (downloadsForder.exists() == false) {
			downloadsForder.mkdir();
		}

		Util.sleep(1000);

		// TODO Main_main 과 다른 코드
		List<WebElement> imgElements = driver.findElements(
				By.cssSelector("[data-test=\"masonry-grid-count-three\"] img[data-test=\"photo-grid-multi-col-img\"]"));

		for (WebElement imgElement : imgElements) {
			
			// TODO Main_main 과 다른 코드
			String src = imgElement.getAttribute("src");
			BufferedImage saveImg = null;

			try {
				saveImg = ImageIO.read(new URL(src));
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (saveImg != null) {
				try {
					String fileName = src.split("/")[3];
					fileName = fileName.split("\\?")[0];
					ImageIO.write(saveImg, "jpg", new File("downloads/" + fileName + ".jpg"));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			System.out.println(src);
		}
	}
}
