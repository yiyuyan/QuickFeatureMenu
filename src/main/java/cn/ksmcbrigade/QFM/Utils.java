package cn.ksmcbrigade.QFM;

import com.google.gson.JsonObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.ksmcbrigade.QFM.QuickFeatureMenu.hacks;

public class Utils {
    //Registry path:HKEY_CURRENT_USER\Software\JavaSoft\Prefs
    public static void writeRegistry(String path, String key, String value) {
        Preferences prefs = Preferences.userRoot();
        prefs = prefs.node(path);
        prefs.put(key, value);
    }

    public static String readRegistry(String path, String key) {
        Preferences prefs = Preferences.userRoot();
        prefs = prefs.node(path);
        return prefs.get(key, null);
    }

    public static void deleteRegistry(String path, String key) {
        Preferences prefs = Preferences.userRoot();
        prefs = prefs.node(path);
        prefs.remove(key);
    }

    public static boolean existsRegistry(String path, String key) {
        Preferences prefs = Preferences.userRoot().node(path);
        String[] keys;
        try {
            keys = prefs.keys();
        } catch (BackingStoreException e) {
            e.printStackTrace();
            return false;
        }
        if (keys != null) {
            for (String k : keys) {
                if (k.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Hack[] getHacks(){
        ArrayList<Hack> hacksList = new ArrayList<>();
        try {
            ClassLoader classLoader = Utils.class.getClassLoader();
            Enumeration<URL> resources = classLoader.getResources("cn/ksmcbrigade/QFM/hacks");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                QuickFeatureMenu.LOGGER.info("Running Property:"+url.getProtocol());
                if (url.getProtocol().equals("jar") || url.getProtocol().equals("union")) {
                    String jarPath = URLDecoder.decode(url.getPath().split("%")[0].substring(1), StandardCharsets.UTF_8);
                    try {
                        JarFile jarFile = new JarFile(jarPath);
                        Enumeration<JarEntry> entries = jarFile.entries();
                        while (entries.hasMoreElements()) {
                            JarEntry entry = entries.nextElement();
                            if (entry.getName().startsWith("cn/ksmcbrigade/QFM/hacks") && entry.getName().endsWith(".class")) {
                                String className = entry.getName().replace("/", ".")
                                        .replace(".class", "");
                                Class<?> clazz = classLoader.loadClass(className);
                                if (Hack.class.isAssignableFrom(clazz) && !clazz.equals(Hack.class)) {
                                    hacksList.add((Hack) clazz.getDeclaredConstructor().newInstance());
                                }
                            }
                        }
                        jarFile.close();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                        QuickFeatureMenu.LOGGER.info("Test Property.");
                        File file = new File(url.getFile());
                        if (file.isDirectory()) {
                            File[] classFiles = file.listFiles((dir, name) -> name.endsWith(".class"));
                            for (File classFile : classFiles) {
                                String className = classFile.getName().replace(".class", "");
                                Class<?> clazz = classLoader.loadClass("cn.ksmcbrigade.QFM.hacks." + className);
                                if (Hack.class.isAssignableFrom(clazz) && !clazz.equals(Hack.class)) {
                                    hacksList.add((Hack) clazz.getDeclaredConstructor().newInstance());
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return hacksList.toArray(new Hack[0]);
    }

    public static void SetHackEnabled(String hack2,boolean enabled) {
        for(Hack hack1:hacks){
            if(hack1.getName().equalsIgnoreCase(hack2)){
                hack1.setEnabled(enabled);
                save();
                break;
            }
        }
        if(Main.Init){
            Main MainGUI = Main.getInstance();
            Point xy = MainGUI.getLocation();
            MainGUI.dispose();
            Main.Init = false;
            Main.main(xy.x,xy.y);
        }
    }

    public static boolean getEnabled(String hack){
        for(Hack hack1:hacks){
            if(hack1.getName().equalsIgnoreCase(hack)){
                return hack1.isEnabled();
            }
        }
        return false;
    }

    public static void StartMenu(){
        QuickFeatureMenu.LOGGER.info("Headless:"+System.getProperty("java.awt.headless"));
        if(!Main.Init){
            Main.main(0,0);
        }
    }

    public static void save() {
        JsonObject json = new JsonObject();
        for(Hack hack:hacks){
            if(hack.isEnabled()){
                json.add(hack.getName(),null);
            }
        }
        writeRegistry("qfm","enabled",json.toString());
    }

    public static void saveConfig() {
        JsonObject json = new JsonObject();
        json.addProperty("boatFly",QuickFeatureMenu.BF);
        json.addProperty("timer",QuickFeatureMenu.timer);
        writeRegistry("qfm","config",json.toString());
    }

    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\w{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }
}
