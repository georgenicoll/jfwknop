package com.cipherdyne.jfwknop;

import com.cipherdyne.gui.MainWindowView;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RcFileModel {

    static final Logger logger = LogManager.getLogger(RcFileModel.class.getName());

    private Map<EnumFwknopRcKey, String> context;
    private final MainWindowView         view;
    private RcFile                       rcFile;

    public RcFileModel(final MainWindowView view) {
        super();
        this.view = view;
    }

    private void updateListeners() {
        this.view.onRcFileChange(this.context);
    }

    public void loadRcFile(final String filePath) {
        this.rcFile = new RcFile(filePath);
        if (this.rcFile.parse()) {
            this.context = this.rcFile.getConfig();
            updateListeners();
        }
    }

    public void saveRcFile(final Map<EnumFwknopRcKey, String> context) {
        this.rcFile.setConfig(context);
        logger.info("Save config");
        this.rcFile.save();
    }

    public void saveAsRcFile(final Map<EnumFwknopRcKey, String> newContext, final String filename) {
        this.context = newContext;
        if (!this.exists()) {
            this.rcFile = new RcFile("fwknoprctmp");
        }
        this.rcFile.setConfig(this.context);
        logger.info("Save config as :" + filename);
        this.rcFile.saveAs(filename);
    }
    
    /**
     * @return true if the configuration exists in a savec file, false otherwise
     */
    public boolean exists() {
        return (this.rcFile != null) ;
    }
}
