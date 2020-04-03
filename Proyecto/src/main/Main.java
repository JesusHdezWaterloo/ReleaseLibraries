/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import components.dialog.notification.types._NotificationDialogActionFAIL;
import components.dialog.notification.types._NotificationDialogActionOK;
import file.FILE;
import jackson.JACKSON;
import java.io.File;
import java.io.IOException;
import others.ExceptionHandlerUtil;
import others.Pair;

/**
 *
 * @author Yo
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
            new _NotificationDialogActionFAIL("Error en configuración, usando default.");
            cfg = new Configuration();
            cfg.saveToJSON();
        }
        desplegarRelease();
        new _NotificationDialogActionOK("Terminado el despliegue.");
        Thread.sleep(3 * 1000);
        System.exit(0);
    }

    private static void desplegarRelease() {
        for (Pair<String> pair : cfg.getFiles()) {
            try {
                FILE.copy(pair.getA(), pair.getB());
            } catch (Exception e) {
                ExceptionHandlerUtil.saveException(errorJsonFile, e);
                new _NotificationDialogActionFAIL("Error copiando el fichero.");
            }
        }
    }
}
