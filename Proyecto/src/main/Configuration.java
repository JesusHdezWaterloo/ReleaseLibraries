/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Yo
 */
public class Configuration implements Serializable {

    private ArrayList<Pair> folders = new ArrayList<>();
    private ArrayList<Pair> files = new ArrayList<>();

    public Configuration() {
        String materialJarFile = "Proyecto" + File.separator + "dist" + File.separator + "MateialImplementation.jar";

        Pair own = new Pair();
        own.setDesde(materialJarFile);
        own.setHasta("libs" + File.separator + "visuales" + File.separator + "MateialImplementation.jar");
        files.add(own);
        
        Pair general = new Pair();
        general.setDesde(materialJarFile);
        general.setHasta("C:\\Program Files\\Java\\VisualesMios\\MateialImplementation.jar");
        files.add(general);
        
        Pair meca = new Pair();
        meca.setDesde(materialJarFile);
        meca.setHasta("C:\\Users\\Yo\\Documents\\GIT Projects\\Meca\\libs\\visuales\\MateialImplementation.jar");
        files.add(meca);
        
        Pair updateMeca = new Pair();
        updateMeca.setDesde(materialJarFile);
        updateMeca.setHasta("C:\\Users\\Yo\\Documents\\GIT Projects\\UpdateMeca\\libs\\visuales\\MateialImplementation.jar");
        files.add(updateMeca);
    }

    public boolean saveToJSON() {
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(Main.jsonFile, new Configuration());
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Pair> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<Pair> folders) {
        this.folders = folders;
    }

    public ArrayList<Pair> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<Pair> files) {
        this.files = files;
    }

}
