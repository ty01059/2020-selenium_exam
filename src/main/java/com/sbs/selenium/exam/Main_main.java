package com.sbs.selenium.exam;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sbs.selenium.exam.dto.DaumNews;
import com.sbs.selenium.exam.dto.NaverFlashNews;

public class Main_main {
	public static void main(String[] args) {
		printDaumArticles();
	}

	private static void printDaumArticles() {
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", path.toString());

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");

		ChromeDriver driver = new ChromeDriver(options);

		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(0));
		driver.get("https://news.daum.net/society#1");

		Util.sleep(1000);

		List<WebElement> elements = driver.findElements(By.cssSelector(".box_timenews > ul > li"));

		for (WebElement element : elements) {
			DaumNews news = new DaumNews(
					element.findElement(By.cssSelector("strong > a")).getAttribute("data-tiara-ordnum").trim(),
					element.findElement(By.cssSelector("em")).getText().trim(),
					element.findElement(By.cssSelector("strong > a")).getText().trim(),
					element.findElement(By.cssSelector("strong > span")).getText().trim());

			System.out.println(news);
		}
	}

	private static void printNaverNewsArticles() {
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", path.toString());

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");

		ChromeDriver driver = new ChromeDriver(options);

		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(0));
		driver.get("https://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=001");

		// TODO 그림 다운로드 추가해야됨
//		File downloadsForder = new File("downloads");
//		if (downloadsForder.exists() == false) {
//			downloadsForder.mkdir();
//		}

		Util.sleep(1000);

		List<WebElement> elements = driver.findElements(By.cssSelector("#main_content > .newsflash_body > ul li"));

		for (WebElement element : elements) {

			String photoUrl = "";

			// TODO 사진없을 경우 에러남
			try {

				try {
					WebElement a = element.findElement(By.cssSelector("dt.photo"));
				} catch (NoSuchElementException e) {
				}

				photoUrl = element.findElement(By.cssSelector("dt.photo > a > img")).getAttribute("src");
			} catch (NoSuchElementException e) {
			}

			NaverFlashNews news = new NaverFlashNews(
					element.findElement(By.cssSelector("dt:not(.photo)")).getText().trim(),
					element.findElement(By.cssSelector("dd .lede")).getText().trim(),
					element.findElement(By.cssSelector("dd .writing")).getText().trim(), photoUrl);

			System.out.println(news);
		}
	}

	private static void printDCInsideTreeArticles() {
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", path.toString());

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");

		ChromeDriver driver = new ChromeDriver(options);

		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(0));
		driver.get("https://hobby.dcinside.com/board/lists/?id=tree");

		Util.sleep(1000);

		List<WebElement> elements = driver.findElements(By.cssSelector("table.gall_list > tbody > tr.us-post"));

		// TODO 이쁘게 출력하기
		for (WebElement element : elements) {
			System.out.println(element.findElements(By.cssSelector(".gall_num")).get(0).getText().trim());
//			TreeArticle treeArticle = new TreeArticle(
//					element.findElements(By.cssSelector(".gall_num")).get(0).getText().trim(),
//					element.findElements(By.cssSelector(".gall_tit")).get(0).getText().trim(),
//					element.findElements(By.cssSelector(".gall_writer")).get(0).getText().trim(),
//					element.findElements(By.cssSelector(".gall_writer")).get(0).getText().trim(),
//					element.findElements(By.cssSelector(".gall_date")).get(0).getText().trim(),
//					element.findElements(By.cssSelector(".gall_count")).get(0).getText().trim(),
//					element.findElements(By.cssSelector(".gall_recommend")).get(0).getText().trim());
		}
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
