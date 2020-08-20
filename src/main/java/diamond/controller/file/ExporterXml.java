/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.d1.SegmentType;
import diamond.view.resource.string.Labels;
import diamond.view.ui.panel.Util;

/**
 * @author Kei Morisue
 *
 */
public class ExporterXml implements Exporter {

    @Override
    public boolean export(Diagram diagram, JFileChooser chooser) {
        File file = new File(chooser.getSelectedFile().getPath()
                + "." + Labels.get("format_name"));
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Util.warn("access_denied");
                return false;
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            output(diagram, out);
        } catch (StackOverflowError e) {
            Util.warn("memory_out");
            return false;
        } catch (IOException e) {
            Util.warn("failed");
            return false;
        }
        return true;
    }

    private void output(Diagram diagram, FileOutputStream out)
            throws IOException, StackOverflowError {
        BufferedOutputStream buffer = new BufferedOutputStream(out);
        XMLEncoder enc = new XMLEncoder(buffer);
        enc.setPersistenceDelegate(SegmentType.class,
                new EnumPersistenceDelegate());
        enc.writeObject(diagram);
        enc.close();
        buffer.close();
        out.close();
    }

    @Override
    public void set(JFileChooser chooser) {
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter(
                Labels.get("xml_file_des"),
                Labels.get("format_name")));
    }
}
