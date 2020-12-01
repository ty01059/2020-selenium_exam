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

public class Main_main {
	public static void main(String[] args) {
		printPpomppuFreeLatestArticles();
	}

	private static void printPpomppuFreeLatestArticles() {
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", path.toString());

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");

		ChromeDriver driver = new ChromeDriver(options);

		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(0));
		driver.get("http://www.ppomppu.co.kr/zboard/zboard.php?id=freeboard");

		Util.sleep(1000);

		List<WebElement> elements = driver.findElements(
				By.cssSelector("#revolution_main_table tbody tr:not(.list_notice) > td:nth-child(3) > a"));

		for (WebElement element : elements) {
			String title = element.getText();

			System.out.println(title);
		}
	}

	private static void downloadUnsplashNatureImgs() {
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");

		System.setProperty("webdriver.chrome.driver", path.toString());

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");

		ChromeDriver driver = new ChromeDriver(options);

		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(0));
		driver.get("https://unsplash.com/t/nature");

		File downloadsForder = new File("downloads");
		if (downloadsForder.exists() == false) {
			downloadsForder.mkdir();
		}

		Util.sleep(1000);

		List<WebElement> imgElements = driver
				.findElements(By.cssSelector("[data-test=\"masonry-grid-count-three\"] img"));

		for (WebElement imgElement : imgElements) {
			String src = imgElement.getAttribute("src");

			if (src.contains("https://images.unsplash.com/photo-") == false) {
				continue;
			}

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
