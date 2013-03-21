package fi.starck.simplelife.gui;

import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

/**
 * @author Tuomas Starck
 */
public class GUI extends JPanel {
    public GUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollBox = new javax.swing.JScrollPane();
        lifeCanvas = new fi.starck.simplelife.gui.LifeCanvas();
        menuPanel = new javax.swing.JPanel();
        labelSize = new javax.swing.JLabel();
        formX = new JFormattedTextField(intFormat);
        formY = new JFormattedTextField(intFormat);
        labelCell = new javax.swing.JLabel();
        formCell = new JFormattedTextField(intFormat);
        labelDelay = new javax.swing.JLabel();
        formDelay = new JFormattedTextField(floatFormat);
        stepButton = new javax.swing.JButton();
        walkButton = new javax.swing.JButton();

        setBorder(null);
        setMinimumSize(new java.awt.Dimension(566, 454));

        scrollBox.setBorder(null);

        lifeCanvas.setBorder(null);
        lifeCanvas.setPreferredSize(new java.awt.Dimension(420, 360));
        lifeCanvas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseClick(evt);
            }
        });

        javax.swing.GroupLayout lifeCanvasLayout = new javax.swing.GroupLayout(lifeCanvas);
        lifeCanvas.setLayout(lifeCanvasLayout);
        lifeCanvasLayout.setHorizontalGroup(
            lifeCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 564, Short.MAX_VALUE)
        );
        lifeCanvasLayout.setVerticalGroup(
            lifeCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        scrollBox.setViewportView(lifeCanvas);

        labelSize.setText("Size of Game");

        formX.setText("42");
        formX.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formattedChange(evt);
            }
        });

        formY.setText("36");
        formY.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formattedChange(evt);
            }
        });

        labelCell.setText("Cell size");

        formCell.setText("10");
        formCell.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formattedChange(evt);
            }
        });

        labelDelay.setText("Delay");

        formDelay.setText("1");
        formDelay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formattedChange(evt);
            }
        });

        stepButton.setText("Step");

        walkButton.setText("Walk");

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addComponent(labelSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formX, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formY, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelCell)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formCell, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelDelay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(stepButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(walkButton))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelSize)
                .addComponent(formX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(formY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelCell)
                .addComponent(formCell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelDelay)
                .addComponent(formDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(stepButton)
                .addComponent(walkButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(scrollBox)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBox, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Receive and handle change events in formatted text fields.
     *
     * NPE is ignored, because in some situations JFormattedTextField.getValue()
     * returns null. It is ignored silently, because the value does not interest
     * us in those situations.
     *
     * @param evt Event.
     */
    private void formattedChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formattedChange
        try {
            if (evt.getSource() == formX) {
                lifeCanvas.setWidth(((Number) formX.getValue()).intValue());
            }
            else if (evt.getSource() == formY) {
                lifeCanvas.setHeight(((Number) formY.getValue()).intValue());
            }
            else if (evt.getSource() == formCell) {
                lifeCanvas.setUnit(((Number) formCell.getValue()).intValue());
            }
            else if (evt.getSource() == formDelay) {
                lifeCanvas.setDelay(((Number) formDelay.getValue()).floatValue());
            }
        }
        catch (NullPointerException ignored) {}
    }//GEN-LAST:event_formattedChange

    /**
     * Pass mouse clicks to canvas.
     *
     * @param evt Event.
     */
    private void mouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClick
        lifeCanvas.flipBit(evt.getX(), evt.getY());
    }//GEN-LAST:event_mouseClick

    private final NumberFormat intFormat = NumberFormat.getIntegerInstance();
    private final NumberFormat floatFormat = NumberFormat.getNumberInstance();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField formCell;
    private javax.swing.JFormattedTextField formDelay;
    private javax.swing.JFormattedTextField formX;
    private javax.swing.JFormattedTextField formY;
    private javax.swing.JLabel labelCell;
    private javax.swing.JLabel labelDelay;
    private javax.swing.JLabel labelSize;
    private fi.starck.simplelife.gui.LifeCanvas lifeCanvas;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JScrollPane scrollBox;
    private javax.swing.JButton stepButton;
    private javax.swing.JButton walkButton;
    // End of variables declaration//GEN-END:variables
}
