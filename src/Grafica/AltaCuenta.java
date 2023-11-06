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

/**
 *
 * @author Alejandro
 */
public class AltaCuenta extends javax.swing.JFrame {
    
    private static String nom_cuenta = "", nom_dominio = "", cedula = "";

    /**
     * Creates new form AltaCuenta
     */
    public AltaCuenta() {
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

        logotipoAltaCuenta = new javax.swing.JLabel();
        AltaCuentaNombre = new javax.swing.JLabel();
        AltaCuentaNombre1 = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        CrearCuenta = new javax.swing.JButton();
        RegresarCuenta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listDominios = new javax.swing.JList<>();
        Habilitada = new javax.swing.JLabel();
        habilitado = new javax.swing.JRadioButton();
        cedulaUser = new javax.swing.JTextField();
        AltaCuentaCedula = new javax.swing.JLabel();
        cbDominios = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        logotipoAltaCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Grafica/isoLaCalera.png"))); // NOI18N

        AltaCuentaNombre.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        AltaCuentaNombre.setText("Nombre");

        AltaCuentaNombre1.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        AltaCuentaNombre1.setText("Dominio");

        CrearCuenta.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        CrearCuenta.setText("Crear Cuenta");
        CrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearCuentaActionPerformed(evt);
            }
        });

        RegresarCuenta.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        RegresarCuenta.setText("Regresar");
        RegresarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarCuentaActionPerformed(evt);
            }
        });

        listDominios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "cerpsw.edu.uy", "anep.edu.uy", "cetp.edu.uy", "cfe.edu.uy", "ces.edu.uy" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listDominios);

        Habilitada.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        Habilitada.setText("Habilitada");

        cedulaUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cedulaUserMouseClicked(evt);
            }
        });

        AltaCuentaCedula.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        AltaCuentaCedula.setText("Cédula");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Habilitada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(AltaCuentaCedula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AltaCuentaNombre1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AltaCuentaNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textName, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(cedulaUser, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(habilitado, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDominios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(418, 418, 418)
                        .addComponent(CrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(RegresarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(logotipoAltaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cedulaUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AltaCuentaCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AltaCuentaNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AltaCuentaNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDominios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Habilitada, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(habilitado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(logotipoAltaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RegresarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegresarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarCuentaActionPerformed
        new Cuenta().setVisible(true);
        dispose();
    }//GEN-LAST:event_RegresarCuentaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            // TODO add your handling code here:
            cbDominios.setModel(Fachada.getInstancia().listar_dominios_cb());
        } catch (IOException | SQLException ex) {
            Logger.getLogger(AltaCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void CrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearCuentaActionPerformed
        try {
            // TODO add your handling code here:
            cedula = cedulaUser.getText();
            nom_cuenta = textName.getText();
            nom_dominio = String.valueOf(cbDominios.getSelectedItem());
            
            Fachada.getInstancia().alta_cuenta(nom_cuenta, nom_dominio, cedula);
        } catch (IOException ex) {
            Logger.getLogger(AltaCuenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AltaCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CrearCuentaActionPerformed

    private void cedulaUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cedulaUserMouseClicked
        // TODO add your handling code here:
//        new FrmCuentaUsuarios().setVisible(true);
        
//        new asdasd(this, rootPaneCheckingEnabled).setVisible(true);
        new DialogCuentaUsuarios(this, rootPaneCheckingEnabled).setVisible(true);
        System.out.println("txt " + cedula);
        cedulaUser.setText(cedula);
    }//GEN-LAST:event_cedulaUserMouseClicked

    public static void setCedula(String cedula) {
        AltaCuenta.cedula = cedula;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AltaCuentaCedula;
    private javax.swing.JLabel AltaCuentaNombre;
    private javax.swing.JLabel AltaCuentaNombre1;
    private javax.swing.JButton CrearCuenta;
    private javax.swing.JLabel Habilitada;
    private javax.swing.JButton RegresarCuenta;
    private javax.swing.JComboBox<String> cbDominios;
    private javax.swing.JTextField cedulaUser;
    private javax.swing.JRadioButton habilitado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listDominios;
    private javax.swing.JLabel logotipoAltaCuenta;
    private javax.swing.JTextField textName;
    // End of variables declaration//GEN-END:variables
}
