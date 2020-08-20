/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import diamond.model.cyborg.diagram.Diagram;
import diamond.view.resource.string.Labels;
import diamond.view.ui.panel.Util;

/**
 * @author Kei Morisue
 *
 */
public class LoaderXML implements Loader {

    @Override
    public Diagram load(JFileChooser chooser) {
        String filepath = chooser.getSelectedFile().getPath();
        if (!filepath.endsWith("." + Labels.get("format_name"))) {
            filepath += "." + Labels.get("format_name");
        }
        File file = new File(filepath);
        try {
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(file)));
            Diagram diagram = (Diagram) decoder.readObject();
            decoder.close();
            return diagram;
        } catch (FileNotFoundException e) {
            Util.warn("no_file");
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public void set(JFileChooser chooser) {
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter(
                Labels.get("xml_file_des"),
                Labels.get("format_name")));
    }
}
