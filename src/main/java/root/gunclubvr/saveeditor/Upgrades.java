package root.gunclubvr.saveeditor;

public class Upgrades {

    /**
     * Gun specific upgrades. Each gun will need to have the first
     * upgrade (in both ammo capacity and damage) purchased if they wish to
     * use the "Unlock max magazine size for applicable guns" feature.
     */
    public static String PistolDrum = "PistolDrum";     //100 round drum magazine for pistols
    public static String Pistol50Drum = "Pistol50Drum"; //50 round drum magazine for pistols
    public static String Quad100 = "Quad100";           //Last ammo capacity upgrade for assault rifles


    /**
     * Damage upgrade. Each gun will need to have the first ammo type purchased (Overpressure)
     * to use the "Unlock max magazine size for applicable guns" feature.
     */
    public static String Ammo_Overpressure = "AmmunitionUpgrade_Overpressure"; //First damage (ammo) upgrade for all guns
    public static String Ammo_Zombie = "AmmunitionUpgrade_Zombie";             //Last damage (ammo) upgrade for all guns

    /**
     * Array data of all guns and attachments in game. (Not inluding DLC)
     * Some gun specific attachments (like stocks and opticals) will need to be
     * purchased manually. All other global attachments will be unlocked.
     */
    public static String ALL_GUNS_DATA = "\\\"G17\\\",\\\"CZ-75\\\",\\\"ACRDesert\\\",\\\"P320RX\\\",\\\"P30L\\\",\\\"SKO 12\\\",\\\"M1897\\\",\\\"MP40\\\",\\\"Uzi\\\",\\\"STENMKIIS\\\",\\\"M870\\\",\\\"Striker\\\",\\\"Saiga 12\\\",\\\"TS12\\\",\\\"TMP\\\",\\\"MP5A3\\\",\\\"M1928A1\\\",\\\"UMP45\\\",\\\"PP2000\\\",\\\"MPX\\\",\\\"CZSkorpion\\\",\\\"PP19\\\",\\\"PPSH41\\\",\\\"P90\\\",\\\"Vector\\\",\\\"White Vector\\\",\\\"MP5A3_Gold\\\",\\\"M9\\\",\\\"Walther P38\\\",\\\"M1911\\\",\\\"P350\\\",\\\"DesertEagle\\\",\\\"SW629\\\",\\\"6P9\\\",\\\"Rhino 60DS\\\",\\\"G18\\\",\\\"P90_Plat\\\",\\\"MP5A3_Plat\\\",\\\"P90_Gold\\\",\\\"PDX\\\",\\\"Famas F1\\\",\\\"AK47\\\",\\\"G36K\\\",\\\"ACR\\\",\\\"Bren MKII\\\",\\\"Delisle\\\",\\\"SAKO85\\\",\\\"M1918A2\\\",\\\"MK18\\\",\\\"DBR Snake\\\",\\\"EF88\\\",\\\"Stg44\\\",\\\"AK47 Gold\\\",\\\"M1Garand\\\",\\\"AWSM\\\",\\\"DSR1\\\",\\\"WA2000\\\",\\\"Model 1866\\\",\\\"VSS\\\",\\\"PSG1\\\",\\\"Yellowboy Special\\\",\\\"P90_Reap\\\",\\\"Golden Vector\\\",\\\"Rhino 60DS Steel\\\",\\\"M9_Steel\\\",\\\"Mateba\\\",\\\"Mauser C96\\\",\\\"AF2011\\\",\\\"Maxim9\\\",\\\"MK23\\\",\\\"M1911SP\\\",\\\"DesertEagle_Gold\\\",\\\"Gold Rhino 60DS\\\",\\\"DesertEagle_Plat\\\"";
    public static String ALL_ATTACHMENTS_DATA = "\\\"Laser\\\",\\\"SuppressorSocom\\\",\\\"MuzzleBreakSynergy\\\",\\\"FlashlightPistol\\\",\\\"Rail_PistolMount\\\",\\\"Rail_Short\\\",\\\"Rail_Long\\\",\\\"Vertical Foregrip\\\",\\\"Sight_HoloCompact\\\",\\\"MBUS Sights\\\",\\\"FlashlightTactical\\\",\\\"Sight_ViperRDS\\\",\\\"Vortex Venom Sight\\\",\\\"Sight_RedCompact\\\",\\\"Rail_Micro\\\",\\\"Angled Foregrip\\\",\\\"MP5A3 Foregrip\\\",\\\"StockNativeMP5A3\\\",\\\"Shotgun Speed Loader\\\",\\\"Rail_Highmount_Angled_Left\\\",\\\"Rail_Highmount_Angled_Right\\\",\\\"Rail_Highmount_Triple_Long\\\",\\\"Rail_Highmount_Triple_Short\\\",\\\"Rail_Highmount_Short\\\",\\\"Rail_Highmount_Long\\\",\\\"Bayonet\\\",\\\"TLR1 Flashlight\\\",\\\"MuzzleBreakArStoner\\\",\\\"MuzzleBreakEnforcer\\\",\\\"MuzzleBreakSlant\\\",\\\"SuppressorImpulsive\\\",\\\"Rail_MP5Mount\\\",\\\"Rail_SideGarand\\\",\\\"Rail_SideTop\\\",\\\"Rail_MP5TriMount\\\",\\\"Rail_Tactical_Side\\\",\\\"Rail_TacticalQuad\\\",\\\"Rail Grip Small\\\",\\\"Rail Grip Medium\\\",\\\"Rail Grip Large\\\",\\\"AxeHead\\\",\\\"M203Launcher\\\",\\\"SuppressorThreaded\\\",\\\"SuppressorAR\\\",\\\"SuppressorSMG\\\",\\\"SuppressorOsprey\\\",\\\"FlashlightGrip\\\",\\\"FoldingForegrip\\\",\\\"BipodForegrip\\\",\\\"Sight_MiniRDS\\\",\\\"Sight_RailwayRDS\\\",\\\"Sight_ViperRDS_0\\\",\\\"Sight_RDS15x\\\",\\\"Sight_2xHuntingRDS\\\",\\\"Sight_AimReflex\\\",\\\"Sight_C79\\\",\\\"Magnifier325x\\\",\\\"Sight_4xSpecter\\\",\\\"Sight_PST4x\\\",\\\"Sight_TacticalRDS\\\",\\\"Sight_UTGScope\\\",\\\"Sight_PST_RifleScope\\\",\\\"Sight_NXS_RifleScope\\\",\\\"StockProSkeleton\\\",\\\"StockProSocom\\\"";


}