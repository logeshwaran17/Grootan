package testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;

import base.SiteSpecificMethods;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TC_003_ImageComparison {
		
	@Test
	public void compareFolders() throws IOException {
		int tcNum=3;
		String testCaseName=this.getClass().getSimpleName();
		File f1 = new File(".//folder1/");

		File[] folder1 = f1.listFiles();

		Arrays.sort(folder1);
		System.out.println(Arrays.toString(folder1));
		String[] f1names = f1.list();

		File f2 = new File(".//folder2");

		File[] folder2 = f2.listFiles();

		Arrays.sort(folder2);

		System.out.println(Arrays.toString(folder2));

		ImageDiffer diff = new ImageDiffer();

		if (folder1.length == folder2.length) {
			for (int i = 0; i < folder1.length; i++) {
				BufferedImage srcImg = ImageIO.read(folder1[i]);
				BufferedImage tarImg = ImageIO.read(folder2[i]);

				ImageDiff makeDiff = diff.makeDiff(srcImg, tarImg);
				if (makeDiff.hasDiff()) {
					System.out.println(folder1[i].getName() + " - Images are different");

				} else {
					System.out.println(folder1[i].getName() + " - Same image");

				}
			}

		}
		SiteSpecificMethods.fileExcel(tcNum, testCaseName);
	}
	
		

}
