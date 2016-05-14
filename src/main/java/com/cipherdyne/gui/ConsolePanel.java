/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cipherdyne.gui;

import com.cipherdyne.jfwknop.EnumFwknopRcKey;
import com.cipherdyne.jfwknop.IFwknopVariable;
import com.cipherdyne.jfwknop.InternationalizationHelper;
import java.awt.Font;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author franck
 */
public class ConsolePanel extends JPanel {

    public JTextArea varConsole;
    public JButton btnClearConsole;
    public JButton btnExecute;

    public ConsolePanel(Map<EnumFwknopRcKey, IFwknopVariable> varMap) {
        super(new MigLayout("insets 0 10 10 10, aligny top, flowy, gap 0", "[grow]", "[grow]"));
        initialize(varMap);
    }

    private void initialize(Map<EnumFwknopRcKey, IFwknopVariable> varMap) {

        JPanel btnPanel = new JPanel(new MigLayout("insets 0, gap 0, flowx","",""));
        ImageIcon clearImg = new ImageIcon(this.getClass().getResource("/clear16.png"));
        this.btnClearConsole = new JButton(clearImg);
        this.btnClearConsole.setToolTipText(InternationalizationHelper.getMessage("i18n.btn.clear"));
        btnPanel.add(btnClearConsole);
        ImageIcon executeImg = new ImageIcon(this.getClass().getResource("/execute16.png"));
        this.btnExecute = new JButton(executeImg);
        this.btnExecute.setToolTipText(InternationalizationHelper.getMessage("i18n.btn.execute"));
        btnPanel.add(btnExecute);
        
        this.add(btnPanel);
        
        // Set up console area
        this.varConsole = new JTextArea(10, 60);
        this.varConsole.setEditable(false);
        final Font font = this.varConsole.getFont();
        this.varConsole.setFont(new Font("Free Monospaced", font.getStyle(),
                font.getSize() - 1));
        this.add(createScrollablePanel(this.varConsole), "center, grow");
    }

    /**
     * Creates a scrollable panel around a component.
     *
     * @param component Text area to wrap inside the panel.
     * @return Created panel.
     */
    private JPanel createScrollablePanel(final JComponent component) {
        final JPanel scrollablePanel = new JPanel(new MigLayout(
                "fill, flowy, insets 0", "fill", "[grow]"));
        JScrollPane scrollPane = new JScrollPane(component,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setViewportView(component);
        scrollablePanel.add(scrollPane, /* new JScrollPane(component), */
                "grow, gapright 0");
        return scrollablePanel;
    }
}
