package root.gunclubvr.saveeditor;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
}
