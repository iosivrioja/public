/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Modelos.Artista;
import Negocio.EArtista;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author laboratorio_computo
 */
public class FrmArtista extends javax.swing.JFrame {

    Artista clsArtista = new Artista();
    EForm formActive;
    private final int id;
    
    public FrmArtista() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setResizable(false);

        formActive = Module.formActive;
        id = Module.id;

        if (id == 0) {
            this.setTitle("Nuevo Artista");
        } else {
            this.setTitle("Editar Artista");

            ArrayList arrayList = clsArtista.searchById(id);
            EArtista artist = (EArtista) arrayList.get(0);
            this.txtNombre.setText(artist.getNombre());
            this.cboSexo.setSelectedItem(artist.getSexo());
            this.txtNacionalidad.setText(artist.getNacionalidad());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboSexo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("NOMBRE:");

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("SEXO:");

        cboSexo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el Sexo", "Masculino", "Femenino" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("NACIONALIDAD:");

        txtNacionalidad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNacionalidad)
                            .addComponent(cboSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnGuardar)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {

            String nombre = this.txtNombre.getText().trim();
            String sexo = this.cboSexo.getSelectedItem().toString();
            String nacionalidad = this.txtNacionalidad.getText().trim();
            if (nombre.equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese un Artista", Module.titleMessage, JOptionPane.CANCEL_OPTION);
            } else {
                EArtista objArt = new EArtista();
                if (id == 0) {
                    objArt.setNombre(nombre);
                    objArt.setSexo(sexo);
                    objArt.setNacionalidad(nacionalidad);

                    String message = clsArtista.create(objArt);
                    if (message.equals("Registrado correctamente")) {
                        JOptionPane.showMessageDialog(null, message, Module.titleMessage, JOptionPane.INFORMATION_MESSAGE);
                        cleanForm();
                    } else {
                        JOptionPane.showMessageDialog(null, message, Module.titleMessage, JOptionPane.CANCEL_OPTION);
                    }
                } else {
                    objArt.setId(id);
                    objArt.setNombre(nombre);
                    objArt.setSexo(sexo);
                    objArt.setNacionalidad(nacionalidad);
                    
                    String message = clsArtista.update(objArt);
                    if (message.equals("Actualizado correctamente")) {
                        JOptionPane.showMessageDialog(null, message, Module.titleMessage, JOptionPane.INFORMATION_MESSAGE);
                        cleanForm();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, message, Module.titleMessage, JOptionPane.CANCEL_OPTION);
                    }
                }
            }
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cleanForm(){
        txtNombre.setText("");
        txtNacionalidad.setText("");
        cboSexo.setSelectedIndex(0);
        formActive.getCaller().loadTable();
    }
    
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
            java.util.logging.Logger.getLogger(FrmArtista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmArtista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmArtista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmArtista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmArtista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cboSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}