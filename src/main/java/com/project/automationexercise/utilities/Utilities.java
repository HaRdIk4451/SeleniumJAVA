package com.project.automationexercise.utilities;

import java.io.*;
import java.util.Properties;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.json.JSONObject;
import org.json.JSONArray;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Utilities {
    public static Properties prop;
    public static final String GLOBAL_FILE_PATH = "./src/main/java/config/config.properties";
    public static final String SECRETS_FILE_PATH = "./src/main/java/config/secrets.properties";
    public static final String SCREENSHOT_DIR = "./test-output/screenshots/";
    public static final int IMPLICIT_WAIT_TIME = 5;

    public static void initGlobalConfiguration() {
        prop = new Properties();
        try (FileInputStream input = new FileInputStream(GLOBAL_FILE_PATH)) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // New method to write to secrets.properties
    public static void storeTestData(String key, String value) {
        Properties secrets = new Properties();
        try (FileInputStream in = new FileInputStream(SECRETS_FILE_PATH)) {
            secrets.load(in);
        } catch (IOException e) {
            // File doesn't exist yet, we'll create it
        }

        try (FileOutputStream out = new FileOutputStream(SECRETS_FILE_PATH)) {
            secrets.setProperty(key, value);
            secrets.store(out, "Automatically generated test data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // New method to read from secrets.properties
    public static String getStoredData(String key) {
        Properties secrets = new Properties();
        try (FileInputStream in = new FileInputStream(SECRETS_FILE_PATH)) {
            secrets.load(in);
            return secrets.getProperty(key);
        } catch (IOException e) {
            return null;
        }
    }

    // Existing methods...
    public static String getBrowserName() {
        return prop.getProperty("browser");
    }

    public static String getBaseUrl() {
        return prop.getProperty("baseurl");
    }

    // Screenshot comparison methods
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Create screenshots directory if it doesn't exist
            Path screenshotDir = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }

            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = SCREENSHOT_DIR + screenshotName + ".png";
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean compareScreenshots(String screenshot1Path, String screenshot2Path) {
        try {
            BufferedImage img1 = ImageIO.read(new File(screenshot1Path));
            BufferedImage img2 = ImageIO.read(new File(screenshot2Path));

            if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
                return false;
            }

            for (int y = 0; y < img1.getHeight(); y++) {
                for (int x = 0; x < img1.getWidth(); x++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // API response handling methods
    public static JSONObject getApiResponse(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return new JSONObject(response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verifyApiResponse(JSONObject response, String key, String expectedValue) {
        try {
            return response.getString(key).equals(expectedValue);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean verifyApiResponseArray(JSONObject response, String arrayKey, String expectedValue) {
        try {
            JSONArray array = response.getJSONArray(arrayKey);
            for (int i = 0; i < array.length(); i++) {
                if (array.getString(i).equals(expectedValue)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}