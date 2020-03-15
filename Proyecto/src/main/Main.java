/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Yo
 */
public class Main {

    public static final File jsonFile = new File(new File("").getAbsolutePath() + File.separator + "cfg.json");
    public static Configuration cfg;

    public static void main(String[] args) {
        try {
            cfg = new ObjectMapper().readValue(jsonFile, Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
            cfg = new Configuration();
            cfg.saveToJSON();
        }
        desplegarRelease();
    }

    private static void desplegarRelease() {
        File actualFolder = new File(new File("").getAbsolutePath());
        for (Pair pair : cfg.getFolders()) {
            try {
                File folderFrom = new File(actualFolder.getAbsolutePath() + File.separator + pair.getDesde());
                File folderTo = new File(actualFolder.getAbsolutePath() + File.separator + pair.getHasta());
                FileUtils.deleteDirectory(folderTo);//borro el nuevo si no lo he borrado
                FileUtils.copyDirectory(folderFrom, folderTo);
            } catch (Exception e) {
                JOptionPane.showInternalInputDialog(null, "Error copiando la carpeta " + pair.getDesde() + " a " + pair.getHasta(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        for (Pair pair : cfg.getFiles()) {
            try {
                File fileFrom = new File(actualFolder.getAbsolutePath() + File.separator + pair.getDesde());
                File fileTo = new File(actualFolder.getAbsolutePath() + File.separator + pair.getHasta());
                fileTo.delete();
                FileUtils.copyFile(fileFrom, fileTo);
            } catch (Exception e) {
                JOptionPane.showInternalInputDialog(null, "Error copiando el fichero " + pair.getDesde() + " a " + pair.getHasta(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
