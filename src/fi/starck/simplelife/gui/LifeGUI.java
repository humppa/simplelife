package fi.starck.simplelife.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Graphical user interface for Simple Life.
 *
 * @author Tuomas Starck
 */
public class LifeGUI extends JPanel {
    public LifeGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollBox = new javax.swing.JScrollPane();
        lifeCanvas = new fi.starck.simplelife.gui.LifeCanvas();
        menuPanel = new javax.swing.JPanel();
        labelDelay = new javax.swing.JLabel();
        formDelay = new JFormattedTextField(floatFormat);
        stepButton = new javax.swing.JButton();
        startStopButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setBorder(null);
        setMinimumSize(new java.awt.Dimension(0, 0));

        scrollBox.setBorder(null);

        lifeCanvas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 0, 0), 2));
        lifeCanvas.setPreferredSize(new java.awt.Dimension(0, 0));
        lifeCanvas.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                mouseWheelEvent(evt);
            }
        });
        lifeCanvas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseClick(evt);
            }
        });

        javax.swing.GroupLayout lifeCanvasLayout = new javax.swing.GroupLayout(lifeCanvas);
        lifeCanvas.setLayout(lifeCanvasLayout);
        lifeCanvasLayout.setHorizontalGroup(
            lifeCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );
        lifeCanvasLayout.setVerticalGroup(
            lifeCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        scrollBox.setViewportView(lifeCanvas);

        labelDelay.setText("Delay (cs)");
        labelDelay.setToolTipText("Delay in centiseconds");

        formDelay.setText("10");
        formDelay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formattedChange(evt);
            }
        });

        stepButton.setText("Step");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepAction(evt);
            }
        });

        startStopButton.setText("Start");
        startStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStopAction(evt);
            }
        });

        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadAction(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAction(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addComponent(loadButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addGap(18, 18, 18)
                .addComponent(labelDelay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(stepButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startStopButton))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelDelay)
                .addComponent(formDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(stepButton)
                .addComponent(startStopButton)
                .addComponent(loadButton)
                .addComponent(saveButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollBox))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBox, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Pass mouse click coordinates to game canvas.
     *
     * @param evt Event object.
     */
    private void mouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClick
        lifeCanvas.flipBit(evt.getX(), evt.getY());
    }//GEN-LAST:event_mouseClick

    /**
     * Pass mouse wheel action to game canvas.
     *
     * @param evt Event object which holds wheel rotation direction.
     */
    private void mouseWheelEvent(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_mouseWheelEvent
        lifeCanvas.zoom(evt.getWheelRotation());
    }//GEN-LAST:event_mouseWheelEvent

    private void saveAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAction
        File file;
        FileOutputStream fileOut;
        ObjectOutputStream objectOut;
        JFileChooser choose = new JFileChooser();

        choose.showSaveDialog(this);
        file = choose.getSelectedFile();

        try {
            fileOut = new FileOutputStream(file);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(lifeCanvas.getLifeSet());
        }
        catch (FileNotFoundException fnfe) {
            showError(E_FNF);
        }
        catch (IOException ioe) {
            showError(E_IO);
        }
    }//GEN-LAST:event_saveAction

    private void loadAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadAction
        File file;
        FileInputStream fileIn;
        ObjectInputStream objectIn;
        JFileChooser choose = new JFileChooser();

        choose.showOpenDialog(this);
        file = choose.getSelectedFile();

        try {
            fileIn = new FileInputStream(file);
            objectIn = new ObjectInputStream(fileIn);
            lifeCanvas.setLifeSet(objectIn.readObject());
            repaint();
        }
        catch (FileNotFoundException fnfe) {
            showError(E_FNF);
        }
        catch (IOException ioe) {
            showError(E_IO);
        }
        catch (ClassNotFoundException cnfe) {
            showError(E_CNF);
        }
    }//GEN-LAST:event_loadAction

    /**
     * Handle start and stop button events.
     *
     * If automation is running, change the text of
     * the button to [Stop] and [Start] otherwise.
     *
     * @param evt Useless event.
     */
    private void startStopAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStopAction
        if (lifeCanvas.startStop()) {
            startStopButton.setText("Stop");
        }
        else {
            startStopButton.setText("Start");
        }
    }//GEN-LAST:event_startStopAction

    /**
     * Handle step button.
     *
     * @param evt Useless event.
     */
    private void stepAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepAction
        lifeCanvas.step();
    }//GEN-LAST:event_stepAction

    /**
     * Handle delay length change events in formatted field.
     *
     * NPE is silently ignored, because JFormattedTextField.getValue()
     * returns null when user input does not validate.
     *
     * @param evt Event object.
     */
    private void formattedChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formattedChange
        try {
            if (evt.getSource() == formDelay) {
                lifeCanvas.setDelay(((Number) formDelay.getValue()).floatValue());
            }
        }
        catch (NullPointerException ignored) {}
    }//GEN-LAST:event_formattedChange

    private final String
            E_FNF = "Could not open file! Please, check permissions.",
            E_IO  = "I/O failed! Please, be more careful when choosing a file.",
            E_CNF = "Unable to load Life from specified file.";

    private final NumberFormat floatFormat = NumberFormat.getNumberInstance();

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField formDelay;
    private javax.swing.JLabel labelDelay;
    private fi.starck.simplelife.gui.LifeCanvas lifeCanvas;
    private javax.swing.JButton loadButton;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JScrollPane scrollBox;
    private javax.swing.JButton startStopButton;
    private javax.swing.JButton stepButton;
    // End of variables declaration//GEN-END:variables
}
