/*
 * Copyright 2003-2012 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package one.cafebabe.samurai.swing;

import one.cafebabe.samurai.util.GUIResourceBundle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PlotSettingDialog extends JDialog {
    private static final GUIResourceBundle resources = GUIResourceBundle.getInstance();
    final JPanel panel1 = new JPanel();
    final GridBagLayout gridBagLayout1 = new GridBagLayout();
    public final JButton okButton = new JButton(resources.getMessage("ConfigDialog.ok"));
    public final JButton cancelButton = new JButton(resources.getMessage("ConfigDialog.cancel"));
    public final JTextField label = new JTextField();
    public final JLabel jLabel1 = new JLabel(resources.getMessage("PlotSettingDialog.label"));
    final JColorChooser colorChooser = new JColorChooser();
    public final JLabel jLabel2 = new JLabel(resources.getMessage("PlotSettingDialog.max"));
    final JTextField max = new JTextField();
    public final JCheckBox plotVisible = new JCheckBox(resources.getMessage("PlotSettingDialog.visible"));

    private boolean okPressed = false;
    private boolean cancelPressed = false;

    public PlotSettingDialog(Frame frame, String title, boolean modal) {
        super(frame, title, modal);
        okButton.addActionListener(e -> {
            okPressed = true;
            setVisible(false);
        });
        cancelButton.addActionListener(e -> {
            cancelPressed = true;
            setVisible(false);
        });
        this.getContentPane().setBackground(SystemColor.control);
        this.setModal(true);
        this.setResizable(false);
        panel1.setMinimumSize(new Dimension(450, 5000));
        panel1.setOpaque(true);
        panel1.setPreferredSize(new Dimension(450, 500));
        cancelButton.setVerifyInputWhenFocusTarget(true);
//    cancelButton.setSelected(true);
        panel1.setLayout(gridBagLayout1);
        okButton.setSelected(true);
        max.setText("");
        panel1.add(colorChooser, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
        panel1.add(label, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
        panel1.add(jLabel1, new GridBagConstraints(0, 1, 1, 2, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 5, 0), 0, 0));
        panel1.add(jLabel2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
        panel1.add(max, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0
                , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
        panel1.add(cancelButton, new GridBagConstraints(1, 5, 1, 1, 5.0, 0.0
                , GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
        panel1.add(okButton, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
        panel1.add(plotVisible, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
                , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        cancelButton.setActionCommand(resources.getMessage("ConfigDialog.cancel"));
        label.setMinimumSize(new Dimension(200, 21));
        label.setPreferredSize(new Dimension(200, 21));
        label.setText("");
        this.getContentPane().add(panel1, BorderLayout.CENTER);
        getRootPane().setDefaultButton(this.okButton);
        getRootPane().getActionMap().put(
                ESC_ACTION_KEY,
                new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        cancelPressed = true;
                        setVisible(false);
                    }
                });
        getRootPane().getInputMap(
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                ESC_ACTION_KEY
        );

        pack();
    }

    public PlotSettingDialog() {
        this(null, resources.getMessage("PlotSettingDialog.title"), false);
    }

    static final String ESC_ACTION_KEY = "ESC_ACTION_KEY";

    public void setColor(Color color) {
        colorChooser.setColor(color);
    }

    public Color getColor() {
        return colorChooser.getColor();
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    public String getLabel() {
        return this.label.getText();
    }

    public String getMax() {
        return max.getText();
    }

    public void setMax(String newMax) {
        max.setText(newMax);
    }

    public boolean isPlotVisible() {
        return plotVisible.isSelected();
    }

    public void reset(String label, String max, Color color, boolean newVisible) {
        setLabel(label);
        setMax(max);
        plotVisible.setSelected(newVisible);
        setColor(color);
        okPressed = false;
        cancelPressed = false;
    }

    public boolean okPressed() {
        return this.okPressed;
    }

    public boolean cancelPressed() {
        return this.cancelPressed;
    }
}
