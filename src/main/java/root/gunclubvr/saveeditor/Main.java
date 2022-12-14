package root.gunclubvr.saveeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static root.gunclubvr.saveeditor.Upgrades.*;

public class Main {

    /* Instructions
     *   -Install Loader (FFA-AIO)
     *   -Connect Oculus, refresh all, make sure "no device" is off
     *   -Select GunClub VR and click start to start backup
     *   -Open Gunclub VR Save Editor and select the location of the save-data. You can select any directory, and the
     *       program will find it, but if you have multiple backups of GunClub, it will pick the newest one. If you want to be sure it picks
     *       the right one (and does it fast), just find put it as close as possible to the directory that has "TMBProfile" in it.
     *   -Check the box that says "Load all Non-DLC guns" (and all global attachments if you want those too), then click "Patch SAVE-GAME".
     *   -Open up Loader, click restore backup, and select the EXACT date of the save-game folder you modified (don't worry if you mess up the backup,
     *       the game keeps older backups in "TMBProfiles_BKP"). You should still make a copy of the save-game (TMBProfile) to be safe.
     *   -Once it finishes, open up the game and see if you own all the guns (and attachments if you picked that) and have fun.
     *   -If you want to unlock the 100 round drum mag and zombie rounds, you will need the buy the FIRST UPGRADE for capacity and damage
     *       on every gun that you want to unlock them for. If you bought other upgrades, thats fine, as long as you own the first one for both categories.
     *       Once you do this, repeat the above steps to restore your backup. It should override the same folder you were already working with,
     *       so no need to change the directory unless it's a different date.
     *   -This time, check the box that says "Unlock Zombie rounds and max magazine size for applicable guns" and click "Patch SAVE-GAME".
     *   -If you don't want to unlock one or the other, you can choose to do just zombie rounds or just max magazine size as well.
     *   -Open Loader, click restore backup, and pick the date of the same folder you were working in.
     *   -You should now hava zombie rounds and dual drum mags for the guns that have the first upgrades unlocked. Enjoy :)
     */

    private JPanel rootPanel, howToUsePanel, patchingPanel;
    private JTabbedPane tabbedPane;
    ViewCreator vc = new ViewCreator();
    JTextField backupDirectory_TextField;
    JTextArea savegameData_TextArea;
    JButton unlockAllGuns_Button, unlockAllGunsAndAttachments_Button, unlockEverything_Button,
            unlockMaxMagazineSize_Button, unlockZombieRounds_Button, overrideWithLoadedSave_Button,
            wipeAllPurchases_Button;
    JLabel currentCash_Label, currentPlaytime_Label;
    static String SAVEGAME_FILE = "";

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ignored) {
        }

        JFrame frame = new JFrame("GunClub VR Save Editor v" + Data.VERSION + "b  -  made with love by Root :)");
        frame.setContentPane(new Main().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setSize(1000, 1000);
        //frame.setMaximumSize(new Dimension(1000, 1000));
        frame.setMinimumSize(new Dimension(700, 700));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public Main() {
        try {
            if (Utils.isUpdateAvailable()) {
                int result = JOptionPane.showConfirmDialog(rootPanel, "An update is available. Would you like to open the download page?", "Update available", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Desktop.getDesktop().browse(new URI("https://github.com/Ldalvik/GunClubVR-SaveEditor/releases"));
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        howToUsePane();
        patchingPane();
    }

    private void howToUsePane() {
        howToUsePanel.setLayout(null);
        vc.makeLabel(howToUsePanel,
                """
                        <html>
                            <h5>For help, message me on discord at root.#6923</h5>
                            
                            <h4 style="color: red">WARNING: Make sure you make backups of your save and keep them somewhere safe on your computer.
                            You never know if something happens and you can't reverse it.</h4>
                            <h3>HOW TO USE</h3>
                            <ul style="font-size: 8px">
                                <li>Install Loader (FFA-AIO)
                                <li>Connect your Oculus, click refresh all in the Loader (Quest) app, and make sure "no device" is off
                                <li>Click the "BACKUP" button and select GunClub VR. Click start to begin the backup.</li>
                                <li>MAKE A BACKUP OF YOUR SAVE-GAME. Copy the folder that has the date of the backup.</li>
                                <li>Open up GunClubVR Save Editor and go to the "patching" tab. Paste in the ENTIRE directory of the save-game you want to modify into the text-box. The closer you are to the "TBMProfiles" file, the better. (Faster too)</li>
                                <li>Choose which option you want. At the time of this writing, these are the available features:
                                    <ol>
                                        <li><p style="color:red">Add all guns.</p> (THIS WILL ERASE ALL ATTACHMENTS YOU'VE PURCHASED!)</li>
                                        <li><p style="color:red">Add all guns and attachments.</p> (This will remove all purchases, but you get most of them back anyway. There are some gun specific upgrades you will still have to purchase.)</li>
                                        <li><p style="color:red">Add EVERYTHING (guns, all global/gun specific attachments, ammo, magazines).</p> Good for the sandbox people or gun enthusiasts. This adds all weapons, global attachments, gun specific attachments, magazine sizes, and ammo types. This is pretty much what the "Override with completed save-game" is but it keeps your current campaign progress.</li>
                                        <li><p style="color:red">Unlock 100 round drum size.</p> You will NEED to purchase the 50 round upgrade for ammo capacity on the pistol(s) you want to unlock it for. This is because any gun that doesn't have it, won't have a 100 drum either (Like the Deagle or revolvers). Note that this may not work for guns that have a specified ammo type, like .45 acp drum mags.</li>
                                        <li><p style="color:red">Unlock Zombie rounds.</p> You will also need to purchase the very first upgrade for damage (Overpressure) on the gun(s) you want to unlock it for. Ammo type is universal throughout all guns, so whatever weapon you purchase Overpressure rounds on will have Zombie rounds after patching.</li>
                                        <li><p style="color:red">Override with completed save-game.</p> THIS WILL OVERRIDE YOUR SAVE-GAME WITH MY OWN. As long as you backup your save-game, you can always return to it afterwards by restoring it with Loader. This save-game has every single item unlocked and every mission completed, and a bunch of money you'll never need (unless you buy the DLC).</li>
                                        <li><p style="color:red">Wipe all owned items.</p> This will wipe all guns and attachments and leave you with a only a Glock-17. Useful if something gets messed up, or you just want to restart for whatever reason.</li>
                                    </ol>
                                </li>
                                <li>After you've chosen your options, open up Loader and click "RESTORE", then select the date of the save-game you modified. As of right now, it has to be in the "/_LoaderBackups/Saves-Only/00.00.00/" folder to work. If it goes through without any warnings, you should be all set! Enjoy :)
                            </ul>
                        </html>
                        """,
                15, 0, 635, 600);

    }

    private void patchingPane() {
        patchingPanel.setLayout(null);

        vc.makeLabel(patchingPanel, "Save-game location", 10, 0, 225, 30);

        backupDirectory_TextField = vc.makeTextField(patchingPanel, 10, 30, 525, 30);
        backupDirectory_TextField.setText(System.getProperty("user.home") + "\\Downloads\\_LoaderBackups\\Saves-Only");

        vc.makeLabel(patchingPanel, "<html>Pick the directory closest to your save-game file (TBMProfiles). If there are multiple save-games under that folder, it might not get the one you want.</html>", 10, 45, 500, 60);

        vc.makeButton(patchingPanel, "Check for save-game", 540, 30, 130, 30).addActionListener(this::checkSaveGame);

        currentCash_Label = vc.makeLabel(patchingPanel, "<html><em>Cash (Default profile):<em></html> $0", 10, 90, 200, 30);
        currentPlaytime_Label = vc.makeLabel(patchingPanel, "<html><em>Playtime (Default profile):<em></html> ", 10, 105, 200, 30);

        unlockAllGuns_Button = vc.makeButton(patchingPanel, "Add all guns", 10, 140, 90, 30);
        unlockAllGuns_Button.addActionListener(this::unlockAllGuns);
        unlockAllGuns_Button.setEnabled(false);

        unlockAllGunsAndAttachments_Button = vc.makeButton(patchingPanel, "Add all guns/attachments", 100, 140, 160, 30);
        unlockAllGunsAndAttachments_Button.addActionListener(this::unlockGunsAndAttachments);
        unlockAllGunsAndAttachments_Button.setEnabled(false);

        unlockEverything_Button = vc.makeButton(patchingPanel, "(Soon) Unlock EVERYTHING (guns, all global/gun specific attachments, ammo, magazines)", 260, 140, 410, 30);
        unlockEverything_Button.addActionListener(this::unlockEverything);
        unlockEverything_Button.setEnabled(false);

        unlockMaxMagazineSize_Button = vc.makeButton(patchingPanel, "Unlock max magazine size (must have 50 drum installed)", 10, 175, 300, 30);
        unlockMaxMagazineSize_Button.addActionListener(this::unlockMagazines);
        unlockMaxMagazineSize_Button.setEnabled(false);

        unlockZombieRounds_Button = vc.makeButton(patchingPanel, "Unlock Zombie rounds (all weapons, must have Overpressure ammo)", 315, 175, 355, 30);
        unlockZombieRounds_Button.addActionListener(this::unlockZombieRounds);
        unlockZombieRounds_Button.setEnabled(false);

        overrideWithLoadedSave_Button = vc.makeButton(patchingPanel, "(Soon) Override with completed save-game (Backup your game-save before doing this)", 10, 210, 660, 30);
        overrideWithLoadedSave_Button.setForeground(Color.red);
        overrideWithLoadedSave_Button.addActionListener(this::overrideSave);
        overrideWithLoadedSave_Button.setEnabled(false);

        wipeAllPurchases_Button = vc.makeButton(patchingPanel, "Wipe all owned items (besides G17)", 10, 250, 660, 30);
        wipeAllPurchases_Button.setForeground(Color.red);
        wipeAllPurchases_Button.addActionListener(this::wipeAll);
        wipeAllPurchases_Button.setEnabled(false);

        savegameData_TextArea = vc.makeTextArea(patchingPanel, 100, 100, 10, 290, 660, 335);
        savegameData_TextArea.setLineWrap(true);
    }

    protected static void find(String searchDirectory) throws IOException {
        File[] files = new File(searchDirectory).listFiles();
        assert files != null;
        for (File file : files) {
            BasicFileAttributes fileAttr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            if (fileAttr.isDirectory()) find(file.getAbsolutePath());
            if (fileAttr.isRegularFile() && file.getName().equals("TBMProfiles")) {
                SAVEGAME_FILE = file.getAbsolutePath();
            }
        }
    }

    /**
     * Check that the save-game file exists
     * Enables patching buttons when save-game check is successful
     */
    public void checkSaveGame(ActionEvent event) {
        String directory = backupDirectory_TextField.getText();
        try {
            find(directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (SAVEGAME_FILE.isEmpty()) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Save-game not found", "This usually happens when there is no TBMProfiles found under the directory you picked. Please make sure you put it in correctly and that the TBMProfiles actually exists.", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Location: " + SAVEGAME_FILE, "Save-game found!", JOptionPane.INFORMATION_MESSAGE);
            backupDirectory_TextField.setText(SAVEGAME_FILE);
            savegameData_TextArea.setText(getSaveGameFile());
            currentCash_Label.setText("<html><em>Cash (Default profile):<em> $" + Utils.getCash(getSaveGameFile()) + "</html>");
            currentPlaytime_Label.setText("<html><em>Playtime (Default profile):<em> " + Utils.getPlayTime(getSaveGameFile()) + "</html>");
            unlockAllGuns_Button.setEnabled(true);
            unlockAllGunsAndAttachments_Button.setEnabled(true);
            //unlockEverything_Button.setEnabled(true);
            wipeAllPurchases_Button.setEnabled(true);
            unlockMaxMagazineSize_Button.setEnabled(true);
            unlockZombieRounds_Button.setEnabled(true);
            //overrideWithLoadedSave_Button.setEnabled(true);
        }
    }

    /**
     * Save-game patching methods.
     *
     * {@code @TODO:} -Add option to choose second profile instead of the first (default) one.
     * E.G: int p1 = data.indexOf(firstDelim, data.indexOf(firstDelim));
     */
    public void unlockAllGuns(ActionEvent event) {
        String data = getSaveGameFile();
        String firstDelim = "\\\"m_purchaseData\\\":{\\\"ownedItems\\\":[";
        int p1 = data.indexOf(firstDelim);
        String lastDelim = "]},";
        int p2 = data.indexOf(lastDelim, p1);
        System.out.println(p1 + ":" + p2);
        if (p1 >= 0 && p2 > p1) {
            String result = data.substring(0, p1 + firstDelim.length())
                    + Upgrades.ALL_GUNS_DATA
                    + data.substring(p2);
            try {
                FileWriter myWriter = new FileWriter(SAVEGAME_FILE);
                myWriter.write(result);
                myWriter.close();
                savegameData_TextArea.setText(result);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),  "Your save-game profile has been successfully patched.", "SAVE-GAME patching complete :)", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Sadly the regex failed to grab your purchased items. Check to make sure \"ownedItems:[]\" exists in your TBMProfiles.","SAVE-GAME patching failed :(", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void unlockGunsAndAttachments(ActionEvent event) {
        String data = getSaveGameFile();
        String firstDelim = "\\\"m_purchaseData\\\":{\\\"ownedItems\\\":[";
        int p1 = data.indexOf(firstDelim);
        String lastDelim = "]},";
        int p2 = data.indexOf(lastDelim, p1);
        System.out.println(p1 + ":" + p2);
        if (p1 >= 0 && p2 > p1) {
            String result = data.substring(0, p1 + firstDelim.length())
                    + Upgrades.ALL_GUNS_DATA + "," + Upgrades.ALL_ATTACHMENTS_DATA
                    + data.substring(p2);
            try {
                FileWriter myWriter = new FileWriter(SAVEGAME_FILE);
                myWriter.write(result);
                myWriter.close();
                savegameData_TextArea.setText(result);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),  "Your save-game profile has been successfully patched.","SAVE-GAME patching complete :)", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Sadly the regex failed to grab your purchased items. Check to make sure \"ownedItems:[]\" exists in your TBMProfiles.", "SAVE-GAME patching failed :(",  JOptionPane.ERROR_MESSAGE);
        }
    }

    //IN PROGRESS, STILL PLAYING THE GAME AND UNLOCKING EVERYTHING LOL.
    private void unlockEverything(ActionEvent event) {
        String data = getSaveGameFile();
        String newData = "";
        if (newData.contains("")) {
            try {
                FileWriter myWriter = new FileWriter(SAVEGAME_FILE);
                myWriter.write(newData);
                myWriter.close();
                savegameData_TextArea.setText(newData);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),  "Your save-game profile has been successfully patched.", "SAVE-GAME patching complete :)", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Sadly the regex failed to grab the required data. Check to make sure your profile isn't corrupted.", "SAVE-GAME patching failed :(", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void unlockMagazines(ActionEvent event) {
        String data = getSaveGameFile();
        String newData = data.replaceAll(Pistol50Drum, PistolDrum);
        if (newData.contains(PistolDrum)) {
            try {
                FileWriter myWriter = new FileWriter(SAVEGAME_FILE);
                myWriter.write(newData);
                myWriter.close();
                savegameData_TextArea.setText(newData);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Your save-game profile has been successfully patched.", "SAVE-GAME patching complete :)", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Sadly the regex failed to grab the magazine type. Check to make sure you purchased the 50 round drum mag for at least one weapon.", "SAVE-GAME patching failed :(", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void unlockZombieRounds(ActionEvent event) {
        String data = getSaveGameFile();
        String newData = data.replaceAll(Ammo_Overpressure, Ammo_Zombie);
        if (newData.contains(Ammo_Zombie)) {
            try {
                FileWriter myWriter = new FileWriter(SAVEGAME_FILE);
                myWriter.write(newData);
                myWriter.close();
                savegameData_TextArea.setText(newData);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Your save-game profile has been successfully patched.", "SAVE-GAME patching complete :)", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),  "Sadly the regex failed to grab the ammunition type. Check to make sure you purchased the Overpressure ammo type for at least one weapon.", "SAVE-GAME patching failed :(", JOptionPane.ERROR_MESSAGE);
        }
    }


    //ALSO WORK IN PROGRESS, STILL BEATING THE GAME
    public void overrideSave(ActionEvent event) {

    }

    public void wipeAll(ActionEvent event) {
        String data = getSaveGameFile();
        String firstDelim = "\\\"m_purchaseData\\\":{\\\"ownedItems\\\":[";
        int p1 = data.indexOf(firstDelim);
        String lastDelim = "]},";
        int p2 = data.indexOf(lastDelim, p1);
        System.out.println(p1 + ":" + p2);
        if (p1 >= 0 && p2 > p1) {
            String result = data.substring(0, p1 + firstDelim.length())
                    + "\"G17\""
                    + data.substring(p2);
            try {
                FileWriter myWriter = new FileWriter(SAVEGAME_FILE);
                myWriter.write(result);
                myWriter.close();
                savegameData_TextArea.setText(result);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "SAVE-GAME patching complete :)", "Your save-game profile has been successfully patched.", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "SAVE-GAME patching failed :(", "Sadly the regex failed to grab your purchased items. Check to make sure \"ownedItems:[]\" exists in your TBMProfiles.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getSaveGameFile() {
        String savegame = "";
        try {
            savegame = Files.readString(Path.of(SAVEGAME_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savegame;
    }
}