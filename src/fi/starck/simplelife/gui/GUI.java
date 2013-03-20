package fi.starck.simplelife.gui;

import fi.starck.simplelife.LifeSet;
import javax.swing.JPanel;

/**
 * @author Tuomas Starck
 */
public class GUI extends JPanel {
    public GUI() {
        initComponents();
        life = new LifeSet(32, 24);
        life.glider();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        delaySlider = new javax.swing.JSlider();
        stepSlider = new javax.swing.JSlider();
        runButton = new javax.swing.JButton();
        lifeCanvas = new fi.starck.simplelife.gui.LifeCanvas();

        delaySlider.setMaximum(10000);
        delaySlider.setMinimum(100);
        delaySlider.setToolTipText("Time delay between steps");
        delaySlider.setValue(1000);

        stepSlider.setMaximum(20);
        stepSlider.setMinimum(1);
        stepSlider.setToolTipText("Number of steps");
        stepSlider.setValue(1);

        runButton.setText("Run");
        runButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                runButtonMouseClicked(evt);
            }
        });

        lifeCanvas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lifeCanvasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout lifeCanvasLayout = new javax.swing.GroupLayout(lifeCanvas);
        lifeCanvas.setLayout(lifeCanvasLayout);
        lifeCanvasLayout.setHorizontalGroup(
            lifeCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        lifeCanvasLayout.setVerticalGroup(
            lifeCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lifeCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(delaySlider, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stepSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stepSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delaySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lifeCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void runButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runButtonMouseClicked
        life.step();
        System.out.println(life.toString());
    }//GEN-LAST:event_runButtonMouseClicked

    private void lifeCanvasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lifeCanvasMouseClicked
        // TODO add your handling code here:
        lifeCanvas.tr(evt.getX(), evt.getY());
    }//GEN-LAST:event_lifeCanvasMouseClicked

    private LifeSet life;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider delaySlider;
    private fi.starck.simplelife.gui.LifeCanvas lifeCanvas;
    private javax.swing.JButton runButton;
    private javax.swing.JSlider stepSlider;
    // End of variables declaration//GEN-END:variables
}
