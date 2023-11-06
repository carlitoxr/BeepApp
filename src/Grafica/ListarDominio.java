/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafica;

import Logica.Fachada;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Alejandro
 */
public class ListarDominio extends javax.swing.JFrame {
    
    private String nom_dom = "";
    private String prio = "";

    /**
     * Creates new form AltaDominio
     */
    public ListarDominio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logotipo = new javax.swing.JLabel();
        AltaDominio = new javax.swing.JLabel();
        Nom_Dominio = new javax.swing.JTextField();
        Borrar = new javax.swing.JButton();
        btnRegresarDominio = new javax.swing.JButton();
        AltaDominio1 = new javax.swing.JLabel();
        Prioridad = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaDom = new javax.swing.JTable();
        Modificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        logotipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Grafica/isoLaCalera.png"))); // NOI18N

        AltaDominio.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        AltaDominio.setText("Nombre del Dominio");

        Nom_Dominio.setEditable(false);
        Nom_Dominio.setEnabled(false);

        Borrar.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        Borrar.setText("Borrar");
        Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarActionPerformed(evt);
            }
        });

        btnRegresarDominio.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        btnRegresarDominio.setText("Regresar");
        btnRegresarDominio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarDominioActionPerformed(evt);
            }
        });

        AltaDominio1.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        AltaDominio1.setText("Prioridad del Dominio");

        TablaDom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        TablaDom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaDomMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaDom);

        Modificar.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(AltaDominio1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Prioridad))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(AltaDominio, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Nom_Dominio)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnRegresarDominio, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(logotipo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AltaDominio, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nom_Dominio, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AltaDominio1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Prioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresarDominio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(logotipo, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarDominioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarDominioActionPerformed
        new Dominio().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnRegresarDominioActionPerformed

    private void BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarActionPerformed
        // TODO add your handling code here:
        String nom_dominio = String.valueOf(TablaDom.getValueAt(TablaDom.getSelectedRow(), 0));
        
        try {
            Fachada.getInstancia().baja_dominio_BD(nom_dominio);
            TablaDom.setModel(Fachada.getInstancia().listar_dominios_tabla());
        } catch (IOException ex) {
            Logger.getLogger(ListarDominio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListarDominio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BorrarActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        // TODO add your handling code here:
        
        if(nom_dom.isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una fila");
        else{
            try {
                Fachada.getInstancia().modif_dominio_BD(nom_dom, Prioridad.getText());
                TablaDom.setModel(Fachada.getInstancia().listar_dominios_tabla());
            } catch (IOException ex) {
                Logger.getLogger(ListarDominio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ListarDominio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ModificarActionPerformed

    private void TablaDomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaDomMouseClicked
        // TODO add your handling code here:
        JTable src = (JTable)evt.getSource();
        int row = src.rowAtPoint(evt.getPoint());
        
        nom_dom = String.valueOf(TablaDom.getValueAt(row, 0));
        prio = String.valueOf(TablaDom.getValueAt(row, 1));
        
        Nom_Dominio.setText(nom_dom);
        Prioridad.setText(prio);
    }//GEN-LAST:event_TablaDomMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            // TODO add your handling code here:
            TablaDom.setModel(Fachada.getInstancia().listar_dominios_tabla());
        } catch (IOException ex) {
            Logger.getLogger(ListarDominio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListarDominio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListarDominio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarDominio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarDominio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarDominio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarDominio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AltaDominio;
    private javax.swing.JLabel AltaDominio1;
    private javax.swing.JButton Borrar;
    private javax.swing.JButton Modificar;
    private javax.swing.JTextField Nom_Dominio;
    private javax.swing.JTextField Prioridad;
    private javax.swing.JTable TablaDom;
    private javax.swing.JButton btnRegresarDominio;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel logotipo;
    // End of variables declaration//GEN-END:variables
}