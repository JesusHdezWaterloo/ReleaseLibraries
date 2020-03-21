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
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Yo
 */
public class Main {

    public static final File jsonFile = new File(new File("").getAbsolutePath() + File.separator + "cfg.json");
    public static Configuration cfg;

    public static void main(String[] args) throws InterruptedException {

        try {
            cfg = new ObjectMapper().readValue(jsonFile, Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
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
        for (Pair pair : cfg.getFolders()) {
            try {
                FILE.copy(pair.getDesde(), pair.getHasta());
            } catch (Exception e) {
                new _NotificationDialogActionFAIL("Error copiando la carpeta.");
            }
        }
        for (Pair pair : cfg.getFiles()) {
            try {
                FILE.copy(pair.getDesde(), pair.getHasta());
            } catch (Exception e) {
                new _NotificationDialogActionFAIL("Error copiando el fichero.");
            }
        }
    }

}
