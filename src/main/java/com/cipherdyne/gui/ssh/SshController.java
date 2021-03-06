/* 
 * JFwknop is developed primarily by the people listed in the file 'AUTHORS'.
 * Copyright (C) 2016 JFwknop developers and contributors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.cipherdyne.gui.ssh;

import com.cipherdyne.gui.MainWindowView;
import com.cipherdyne.gui.ssh.SshView.EnumSshSettings;
import com.cipherdyne.jfwknop.EnumFwknopRcKey;
import com.cipherdyne.utils.InternationalizationHelper;
import com.cipherdyne.utils.SshUtils;
import com.jcraft.jsch.JSchException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Controller used to manage fil export to a remote host using SSH capabilities
 */
public class SshController {

    // Logger
    static final Logger LOGGER = LogManager.getLogger(SshController.class.getName());

    // View used to display Ssh settings
    final private SshView view;

    // Parent window we come from
    final private MainWindowView parentWindow;

    public SshController(MainWindowView frame) {
        this.parentWindow = frame;
        this.view = new SshView(this.parentWindow);

        // Initialize some parameters from the parent window
        if (!parentWindow.getVariables().get(EnumFwknopRcKey.SPA_SERVER).isDefault()) {
            this.view.getSettings().get(EnumSshSettings.REMOTEHOST).setText(
                parentWindow.getVariables().get(EnumFwknopRcKey.SPA_SERVER).getText());
        }

        // Set up action listeners
        populateBtn();

        this.view.setVisible(true);
    }

    /**
     * Set up action listener for all available buttons from the SshView
     */
    private void populateBtn() {

        // Set up action listener to add a file to the file table that contains file to export to remote host
        this.view.getBtnAddFile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle(InternationalizationHelper.getMessage("i18n.browse.file"));
                fileChooser.setFileHidingEnabled(false);
                final int result = fileChooser.showOpenDialog(null);
                SshFileTableModel tableModel = (SshFileTableModel) (SshController.this.view.getFileTable().getModel());
                if (result == JFileChooser.APPROVE_OPTION) {
                    final String filename = fileChooser.getSelectedFile().getAbsolutePath();
                    tableModel.add(filename);
                    tableModel.reload();
                }
            }
        });

        // Set up action listener to remove a file from the file table that contains file to export to remote host
        this.view.getBtnRemoveFile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = SshController.this.view.getFileTable().getSelectedRow();
                SshFileTableModel tableModel = (SshFileTableModel) (SshController.this.view.getFileTable().getModel());
                String selectedFilename = (String) (tableModel.getValueAt(selectedRow, SshFileTableModel.FILENAME_COL_ID));
                tableModel.remove(selectedFilename);
                tableModel.reload();
            }
        });

        // Perform an export action - copy all files from the table to remote host using scp
        this.view.getBtnExport().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                initializeExport();
                    
                // Get table model to access all files to export
                SshFileTableModel tableModel = (SshFileTableModel) (SshController.this.view.getFileTable().getModel());

                // Iterate over the list of file to export, and scp each of them
                for (int loop = 0; loop < tableModel.getRowCount(); loop++) {
                    String filename = (String) (tableModel.getValueAt(loop, SshFileTableModel.FILENAME_COL_ID));
                    String status = "Success";

                    try {
                        SshUtils.scpFile(SshController.this.view.getSettings().get(EnumSshSettings.REMOTEHOST).getText(),
                            Integer.parseInt(SshController.this.view.getSettings().get(EnumSshSettings.REMOTEPORT).getText()),
                            SshController.this.view.getSettings().get(EnumSshSettings.USERNAME).getText(),
                            SshController.this.view.getSettings().get(EnumSshSettings.PASSWORD).getText(),
                            filename);
                    } catch (IOException | JSchException ex) {
                        SshController.LOGGER.error("Unable to ssh copy file " + filename, ex);
                        status = "Failed";
                    }

                    // Update the file table with the transfer status
                    tableModel.updateStatus(filename, status);
                    tableModel.reload();
                }
            }
        });
    }

    /**
     * Initialize export. This method reset the transfer status for each file
     */
    private void initializeExport() {
        // Get table model to access all files to export
        SshFileTableModel tableModel = (SshFileTableModel) (SshController.this.view.getFileTable().getModel());

        // Iterate over the list of file to initialize their status
        for (int loop = 0; loop < tableModel.getRowCount(); loop++) {
            // Update the file table with the transfer status
            tableModel.updateStatus((String) (tableModel.getValueAt(loop, SshFileTableModel.FILENAME_COL_ID)),
                SshFileTableModel.EXCHANGE_FILE_STATUS_INIT);
            tableModel.reload();
        }
    }
}
