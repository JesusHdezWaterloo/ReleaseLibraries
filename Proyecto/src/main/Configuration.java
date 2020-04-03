/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.JACKSON;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import others.Pair;

/**
 *
 * @author Yo
 */
public class Configuration implements Serializable {

    private ArrayList<Pair<String>> files = new ArrayList<>();

    public Configuration() {
    }

    public boolean saveToJSON() {
        try {
            JACKSON.write(Main.cfgJsonFile, new Configuration());
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public ArrayList<Pair<String>> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<Pair<String>> files) {
        this.files = files;
    }

}
