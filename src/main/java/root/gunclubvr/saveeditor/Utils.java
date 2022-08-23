package root.gunclubvr.saveeditor;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean isUpdateAvailable() throws MalformedURLException {
        String currentVersion = "";
        URL u = new URL("https://raw.githubusercontent.com/Ldalvik/GunClubVR-SaveEditor/main/version.txt");
        try (InputStream in = u.openStream()) {
            currentVersion = new String(in.readAllBytes(), StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(currentVersion);
        return !currentVersion.equals(Data.VERSION);
    }

    public static String getCash(String data) {
        int startIndex = data.indexOf("Amount\\\":");
        int endIndex = data.indexOf(',', startIndex);
        return data.substring(startIndex + 9, endIndex);
    }

    public static String getPlayTime(String data) {
        int startIndex = data.indexOf("Time\\\":");
        int endIndex = data.indexOf(',', startIndex);
        long milli = Long.parseLong(data.substring(startIndex + 7, endIndex));
        return new SimpleDateFormat("dd:HH:mm:ss").format(new Date(milli*10));
    }

}
