/**
 * ORIPA - Origami Pattern Editor
 * Copyright (C) 2005-2009 Jun Mitani http://mitani.cs.tsukuba.ac.jp/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package diamond.view.main;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import diamond.Config;
import diamond.DIAMOND;
import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.doc.exporter.ExporterXML;
import diamond.file.FileChooser;
import diamond.file.FileChooserFactory;
import diamond.file.FileFilterEx;
import diamond.file.FileHistory;
import diamond.file.FileVersionError;
import diamond.file.FilterDB;
import diamond.file.ImageResourceLoader;
import diamond.file.SavingAction;
import diamond.fold.OrigamiModel;
import diamond.fold.OrigamiModelFactory;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.creasepattern.Painter;
import diamond.resource.Constants;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.menubar.MenuEdit;
import diamond.view.main.menubar.MenuFile;
import diamond.view.main.menubar.MenuHelp;
import diamond.viewsetting.main.MainFrameSettingDB;
import diamond.viewsetting.main.MainScreenSettingDB;

public class MainFrame extends JFrame implements ActionListener,
        ComponentListener, WindowListener, Observer {

    private static MainFrame instance = null;

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }



    private MainScreenSettingDB screenSetting = MainScreenSettingDB
            .getInstance();

    private PainterScreen mainScreen;

    private JMenuBar menuBar = new JMenuBar();
    private MenuFile menuFile;
    private MenuEdit menuEdit;

    private RepeatCopyDialog arrayCopyDialog;
    private CircleCopyDialog circleCopyDialog;

    public static JLabel hintLabel = new JLabel();
    public UIPanel uiPanel;


    private static FilterDB filterDB = FilterDB.getInstance();
    private FileFilterEx[] fileFilters = new FileFilterEx[] {

            filterDB.getFilter("opx"), filterDB.getFilter("pict") };

    public MainFrame() {
        MainFrameSettingDB.getInstance().addObserver(this);
        addWindowListener(this);
        mainScreen = new PainterScreen();
        uiPanel = new UIPanel(mainScreen);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(uiPanel, BorderLayout.WEST);
        getContentPane().add(mainScreen, BorderLayout.CENTER);
        getContentPane().add(hintLabel, BorderLayout.SOUTH);
        setIconImage(new ImageResourceLoader()
                .loadAsIcon("icon/diamond.gif", getClass())
                .getImage());
        buildMenuBar();
        loadIniFile();
        addSavingActions();
        setSize(Config.INITIAL_MAIN_FRAME_WIDTH,
                Config.INITIAL_MAIN_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateTitleText();
        setVisible(true);
    }

    private void buildMenuBar() {
        menuFile = new MenuFile(this);
        menuEdit = new MenuEdit(this);
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(new MenuHelp());
        setJMenuBar(menuBar);
    }


    public void updateMenu(String filePath) {
        if (filterDB.getLoadableFilterOf(filePath) == null) {
            return;
        }
        FileHistory.useFile(filePath);
        menuFile.addItems();
    }



    private void addSavingActions() {

        filterDB.getFilter("pict").setSavingAction(new SavingAction() {

            @Override
            public boolean save(String path) {
                try {
                    savePictureFile(mainScreen.getCreasePatternImage(), path);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
                }

                return true;
            }
        });

        filterDB.getFilter("opx").setSavingAction(new SavingAction() {

            @Override
            public boolean save(String path) {
                try {
                    saveOpxFile(path);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
                }
                return true;

            }
        });
    }

    private void saveOpxFile(String filePath) {
        ExporterXML exporter = new ExporterXML();
        exporter.export(DocHolder.getInstance().getDoc(), filePath);
        DocHolder.getInstance().getDoc().setDataFilePath(filePath);

        updateMenu(filePath);

        DocHolder.getInstance().getDoc().clearChanged();
    }

    private void savePictureFile(Image cpImage, String filePath)
            throws IOException {
        BufferedImage image = new BufferedImage(cpImage.getWidth(this),
                cpImage.getHeight(this), BufferedImage.TYPE_INT_RGB);

        image.getGraphics().drawImage(cpImage, 0, 0, this);

        File file = new File(filePath);
        ImageIO.write(image, filePath.substring(filePath.lastIndexOf(".") + 1),
                file);
    }

    public void initialize() {
        arrayCopyDialog = new RepeatCopyDialog(this);
        circleCopyDialog = new CircleCopyDialog(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Doc document = DocHolder.getInstance().getDoc();
        CreasePattern creasePattern = document.getCreasePattern();

        // Check the last opened files
        for (int i = 0; i < Config.MRUFILE_NUM; i++) {
            if (e.getSource() == menuFile.getMRUFilesMenuItem()[i]) {
                try {
                    String filePath = menuFile.getMRUFilesMenuItem()[i]
                            .getText();
                    openFile(filePath);
                    updateTitleText();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, e.toString(),
                            ResourceHolder.getInstance().getString(
                                    ResourceKey.WARNING,
                                    "Error_FileLoadFailed"),
                            JOptionPane.ERROR_MESSAGE);
                }
                mainScreen.repaint();
                return;
            }
        }

        //TODO Refactor the long, long if-else sequences!

        // String lastPath = fileHistory.getLastPath();
        String lastDirectory = FileHistory.getLastDirectory();

        if (e.getSource() == menuFile.getMenuItemOpen()) {
            openFile(null);
            mainScreen.repaint();
            updateTitleText();
        } else if (e.getSource() == menuFile.getMenuItemSave()
                && !DocHolder.getInstance().getDoc().getDataFilePath().equals("")) {
            saveOpxFile(DocHolder.getInstance().getDoc().getDataFilePath());

        } else if (e.getSource() == menuFile.getMenuItemSaveAs()
                || e.getSource() == menuFile.getMenuItemSave()) {

            String path = saveFile(lastDirectory, DocHolder.getInstance().getDoc().getDataFileName(),
                    fileFilters);

            updateMenu(path);
            updateTitleText();

        } else if (e.getSource() == menuFile.getMenuItemSaveAsImage()) {

            saveFile(lastDirectory, DocHolder.getInstance().getDoc().getDataFileName(),
                    new FileFilterEx[] { filterDB.getFilter("pict") });

        } else if (e.getSource() == menuFile.getMenuItemExportDXF()) {
            exportFile("dxf");
        } else if (e.getSource() == menuFile.getMenuItemExportOBJ()) {
            exportFile("obj");
        } else if (e.getSource() == menuFile.getMenuItemExportCP()) {
            exportFile("cp");
        } else if (e.getSource() == menuFile.getMenuItemExportSVG()) {
            exportFile("svg");
        } else if (e.getSource() == menuEdit.getMenuItemChangeOutline()) {
            // Globals.preEditMode = Globals.editMode;
            // Globals.editMode = Constants.EditMode.EDIT_OUTLINE;

            // Globals.setMouseAction(new EditOutlineAction());

        } else if (e.getSource() == menuFile.getMenuItemExit()) {
            saveIniFile();
            System.exit(0);
        } else if (e.getSource() == menuEdit.getMenuItemUndo()) {
            if (PaintConfig.getMouseAction() != null) {
                PaintConfig.getMouseAction().undo(PaintContext.getInstance());
            } else {
                DocHolder.getInstance().getDoc().loadUndoInfo();
            }
            mainScreen.repaint();
        } else if (e.getSource() == menuFile.getMenuItemClear()) {
            DocHolder.getInstance()
                    .setDoc(new Doc(Constants.DEFAULT_PAPER_SIZE));
            DIAMOND.modelFrame.repaint();

            DIAMOND.modelFrame.setVisible(false);
            DIAMOND.renderFrame.setVisible(false);

            screenSetting.setGridVisible(true);
            screenSetting.notifyObservers();

            // ORIPA.mainFrame.uiPanel.dispGridCheckBox.setSelected(true);
            updateTitleText();
        } else if (e.getSource() == menuFile.getMenuItemProperty()) {
            PropertyDialog dialog = new PropertyDialog(this);
            dialog.setValue();
            Rectangle rec = getBounds();
            dialog.setLocation(
                    (int) (rec.getCenterX() - dialog.getWidth() / 2),
                    (int) (rec.getCenterY() - dialog.getHeight() / 2));
            dialog.setModal(true);
            dialog.setVisible(true);
        } else if (e.getSource() == menuEdit.getMenuItemRepeatCopy()) {
            Painter painter = new Painter();
            if (painter.countSelectedLines(creasePattern) == 0) {
                JOptionPane.showMessageDialog(this, "Select target lines",
                        "ArrayCopy", JOptionPane.WARNING_MESSAGE);

            } else {
                arrayCopyDialog.setVisible(true);
            }
        } else if (e.getSource() == menuEdit.getMenuItemCircleCopy()) {
            Painter painter = new Painter();
            if (painter.countSelectedLines(creasePattern) == 0) {
                JOptionPane.showMessageDialog(this, "Select target lines",
                        "ArrayCopy", JOptionPane.WARNING_MESSAGE);

            } else {
                circleCopyDialog.setVisible(true);
            }
        }

    }


    public void updateTitleText() {
        String fileName;
        if ((DocHolder.getInstance().getDoc().getDataFilePath()).equals("")) {
            fileName = ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                    "DefaultFileName");
        } else {
            fileName = DocHolder.getInstance().getDoc().getDataFileName();
        }

        setTitle(fileName + " - "
                + ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                        StringID.Main.TITLE_ID));
    }

    private String saveFile(String directory, String fileName,
            FileFilterEx[] filters) {

        File givenFile = new File(directory, fileName);

        return saveFile(givenFile.getPath(), filters);
    }

    private String saveFile(String homePath, FileFilterEx[] filters) {
        FileChooserFactory chooserFactory = new FileChooserFactory();
        FileChooser chooser = chooserFactory.createChooser(homePath, filters);

        String path = chooser.saveFile(this);
        if (path != null) {
            // if(path.endsWith(".opx")){
            // ORIPA.doc.setDataFilePath(path);
            // ORIPA.doc.clearChanged();
            //
            // updateMenu(path);
            // }
        } else {
            path = homePath;
        }

        return path;

    }

    public void exportFile(String ext) {
        Doc document = DocHolder.getInstance().getDoc();
        CreasePattern creasePattern = document.getCreasePattern();
        OrigamiModel origamiModel = document.getOrigamiModel();

        boolean hasModel = origamiModel.hasModel();

        OrigamiModelFactory modelFactory = new OrigamiModelFactory();
        origamiModel = modelFactory.buildOrigami(creasePattern,
                document.getPaperSize(), true);
        document.setOrigamiModel(origamiModel);

        if ("obj".equals(ext) == false) {

        } else if (!hasModel && !origamiModel.isProbablyFoldable()) {

            JOptionPane.showConfirmDialog(null,
                    "Warning: Building a set of polygons from crease pattern "
                            + "was failed.",
                    "Warning",
                    JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
        }

        saveFile(null, new FileFilterEx[] { filterDB.getFilter(ext) });
    }



    /**
     * if filePath is null, this method opens a dialog to select the target.
     * otherwise, it tries to read data from the path.
     *
     * @param filePath
     */
    private void openFile(String filePath) {
        DIAMOND.modelFrame.setVisible(false);
        DIAMOND.renderFrame.setVisible(false);

        screenSetting.setGridVisible(false);
        screenSetting.notifyObservers();

        // ORIPA.mainFrame.uiPanel.dispGridCheckBox.setSelected(false);

        String path = null;

        if (filePath != null) {
            path = loadFile(filePath);
        } else {
            FileChooserFactory factory = new FileChooserFactory();
            FileChooser fileChooser = factory.createChooser(FileHistory
                    .getLastPath(), FilterDB.getInstance().getLoadables());

            fileChooser.setFileFilter(FilterDB.getInstance().getFilter("opx"));

            path = fileChooser.loadFile(this);

        }

        if (path == null) {
            path = DocHolder.getInstance().getDoc().getDataFilePath();
        } else {
            updateMenu(path);

        }

    }

    /**
     * Do not call directly. Please use openFile().
     *
     * @param filePath
     * @return
     */
    private String loadFile(String filePath) {

        FileFilterEx[] filters = FilterDB.getInstance().getLoadables();

        File file = new File(filePath);

        // find appropriate loader
        boolean loaded = false;
        for (FileFilterEx filter : filters) {
            if (!filter.accept(file)) {
                continue;
            }
            if (file.isDirectory()) {
                continue;
            }

            try {
                loaded = filter.getLoadingAction().load(filePath);
            } catch (FileVersionError e) {
                JOptionPane.showMessageDialog(
                        this,
                        "This file is compatible with a new version. "
                                + "Please obtain the latest version of ORIPA",
                        "Failed to load the file",
                        JOptionPane.ERROR_MESSAGE);
            }
            break;

        }

        if (!loaded) {
            return null;
        }

        return filePath;
    }

    private void saveIniFile() {
        FileHistory.saveToFile(Config.INI_FILE_PATH);
    }

    private void loadIniFile() {
        FileHistory.loadFromFile(Config.INI_FILE_PATH);
    }

    @Override
    public void componentResized(ComponentEvent arg0) {
    }

    @Override
    public void componentMoved(ComponentEvent arg0) {
    }

    @Override
    public void componentShown(ComponentEvent arg0) {
    }

    @Override
    public void componentHidden(ComponentEvent arg0) {
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
    }

    @Override
    public void windowClosing(WindowEvent arg0) {

        if (DocHolder.getInstance().getDoc().isChanged()) {
            // TODO: confirm saving edited opx
            int selected = JOptionPane
                    .showConfirmDialog(
                            this,
                            "The crease pattern has been modified. Would you like to save?",
                            "Comfirm to save", JOptionPane.YES_NO_OPTION);
            if (selected == JOptionPane.YES_OPTION) {
                String path = saveFile(FileHistory.getLastDirectory(),
                        DocHolder.getInstance().getDoc().getDataFileName(),
                        fileFilters);
                if (path == null) {

                }
            }
        }

        saveIniFile();
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
    }

    // @Override
    // public void keyTyped(KeyEvent e) {
    // if(e.isControlDown()){
    // screenUpdater.updateScreen();
    // }
    // }
    //
    // @Override
    // public void keyPressed(KeyEvent e) {
    // if(e.isControlDown()){
    // screenUpdater.updateScreen();
    // }
    // }
    //
    // @Override
    // public void keyReleased(KeyEvent e) {
    // if(e.isControlDown()){
    // screenUpdater.updateScreen();
    // }
    //
    // }

    @Override
    public void update(Observable o, Object arg) {
        if (o.toString() == MainFrameSettingDB
                .getInstance().getName()) {
            hintLabel.setText("    " + MainFrameSettingDB
                    .getInstance().getHint());
            hintLabel.repaint();
        }
    }

    public PainterScreen getMainScreen() {
        return this.mainScreen;
    }
}
