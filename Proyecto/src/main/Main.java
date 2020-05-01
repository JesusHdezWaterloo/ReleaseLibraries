package main;

import bundles.toast.TOAST;
import exceptions.ExceptionHandlerUtil;
import file.FILE;
import jackson.JACKSON;
import java.io.File;
import java.io.IOException;
import others.Pair;
import personalization.PersonalizationMaterial;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class Main {

    public static final File cfgJsonFile = new File(new File("").getAbsolutePath() + File.separator + "cfg_release_library.json");
    public static final File errorJsonFile = new File(new File("").getAbsolutePath() + File.separator + "error_release_library.json");
    public static Configuration cfg;

    public static void main(String[] args) throws InterruptedException {
        try {
            cfg = JACKSON.read(cfgJsonFile, Configuration.class);
        } catch (IOException e) {
            ExceptionHandlerUtil.saveException(errorJsonFile, e);
            TOAST.makeNotificationFAIL("Error en configuración, usando default.");
            cfg = new Configuration();
            cfg.saveToJSON();
        }
        desplegarRelease();
        TOAST.makeNotificationOK("Terminado el despliegue.");
        Thread.sleep(2 * PersonalizationMaterial.getInstance().getSecondsActive() * 1000);
        System.exit(0);
    }

    private static void desplegarRelease() {
        for (Pair<String> pair : cfg.getFiles()) {
            try {
                FILE.copy(pair.getA(), pair.getB());
            } catch (Exception e) {
                ExceptionHandlerUtil.saveException(errorJsonFile, e);
                TOAST.makeNotificationFAIL("Error copiando el fichero.");
            }
        }
    }
}
