/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacionGUI;

import controladores.Fabrica;

import controladores.iSistema;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class GuardarTemaListaAlbum extends javax.swing.JFrame {
    iSistema sys;
    int indice;
    /**
     * Creates new form Guardar_Tema_Lista_Album
     */
    public GuardarTemaListaAlbum() {
        sys = new Fabrica().getSistema();
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

        buttonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_Cliente = new javax.swing.JComboBox<>();
        jRadioButton_LISTA = new javax.swing.JRadioButton();
        jRadioButton_TEMA = new javax.swing.JRadioButton();
        jRadioButton_ALBUM = new javax.swing.JRadioButton();
        SELECCIONAR_LISTA = new javax.swing.JLabel();
        SELECCIONAR_TEMA = new javax.swing.JLabel();
        SELECCIONAR_ALBUM = new javax.swing.JLabel();
        jComboBox_SELECCIONAR_LISTA = new javax.swing.JComboBox<>();
        jComboBox_SELECCIONAR_TEMA = new javax.swing.JComboBox<>();
        jComboBox_SELECCIONAR_ALBUM = new javax.swing.JComboBox<>();
        jLabel_LISTA = new javax.swing.JLabel();
        jLabel_TEMA = new javax.swing.JLabel();
        jLabel_ALBUM = new javax.swing.JLabel();
        ACEPTAR = new javax.swing.JButton();
        CANCELAR = new javax.swing.JButton();
        jLabel_ERROR = new javax.swing.JLabel();
        jLabel_ERROR2 = new javax.swing.JLabel();
        Operacion_Exitosa = new javax.swing.JLabel();

        setTitle("Guardar Tema/Lista/Album");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("SELECCIONE CLIENTE.");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 19, -1, -1));

        jComboBox_Cliente.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox_ClientePopupMenuWillBecomeVisible(evt);
            }
        });
        getContentPane().add(jComboBox_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 43, 137, -1));

        buttonGroup.add(jRadioButton_LISTA);
        jRadioButton_LISTA.setText("LISTA");
        jRadioButton_LISTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_LISTAActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton_LISTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 104, -1, -1));

        buttonGroup.add(jRadioButton_TEMA);
        jRadioButton_TEMA.setText("TEMA");
        jRadioButton_TEMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_TEMAActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton_TEMA, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 104, -1, -1));

        buttonGroup.add(jRadioButton_ALBUM);
        jRadioButton_ALBUM.setText("ALBUM");
        jRadioButton_ALBUM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_ALBUMActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton_ALBUM, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 104, -1, -1));

        SELECCIONAR_LISTA.setText("SELECCIONE LISTA.");
        getContentPane().add(SELECCIONAR_LISTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 144, 138, -1));

        SELECCIONAR_TEMA.setText("SELECCIONE TEMA.");
        getContentPane().add(SELECCIONAR_TEMA, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 144, 138, 19));

        SELECCIONAR_ALBUM.setText("SELECCIONE ALBUM.");
        getContentPane().add(SELECCIONAR_ALBUM, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 144, 170, -1));

        jComboBox_SELECCIONAR_LISTA.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox_SELECCIONAR_LISTAPopupMenuWillBecomeVisible(evt);
            }
        });
        getContentPane().add(jComboBox_SELECCIONAR_LISTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 175, 138, -1));

        jComboBox_SELECCIONAR_TEMA.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox_SELECCIONAR_TEMAPopupMenuWillBecomeVisible(evt);
            }
        });
        getContentPane().add(jComboBox_SELECCIONAR_TEMA, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 175, 138, -1));

        jComboBox_SELECCIONAR_ALBUM.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox_SELECCIONAR_ALBUMPopupMenuWillBecomeVisible(evt);
            }
        });
        jComboBox_SELECCIONAR_ALBUM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_SELECCIONAR_ALBUMActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_SELECCIONAR_ALBUM, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 175, 138, -1));

        jLabel_LISTA.setText("Seleccione Lista.");
        getContentPane().add(jLabel_LISTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 205, -1, -1));

        jLabel_TEMA.setText("Seleccione Tema.");
        getContentPane().add(jLabel_TEMA, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 205, -1, -1));

        jLabel_ALBUM.setText("Seleccione Album.");
        getContentPane().add(jLabel_ALBUM, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 205, -1, -1));

        ACEPTAR.setText("Aceptar");
        ACEPTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACEPTARActionPerformed(evt);
            }
        });
        getContentPane().add(ACEPTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 302, 100, 34));

        CANCELAR.setText("Cancelar");
        CANCELAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCELARActionPerformed(evt);
            }
        });
        getContentPane().add(CANCELAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 302, 100, 34));

        jLabel_ERROR.setText("Seleccione alguna categoria.");
        getContentPane().add(jLabel_ERROR, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 342, 169, -1));

        jLabel_ERROR2.setText("Seleccione Cliente.");
        getContentPane().add(jLabel_ERROR2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 46, -1, -1));

        Operacion_Exitosa.setText("Operacion Exitosa!!");
        getContentPane().add(Operacion_Exitosa, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 272, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_ClientePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox_ClientePopupMenuWillBecomeVisible
      jComboBox_SELECCIONAR_ALBUM.removeAllItems();
        jComboBox_SELECCIONAR_TEMA.removeAllItems();
        jComboBox_SELECCIONAR_LISTA.removeAllItems();
    }//GEN-LAST:event_jComboBox_ClientePopupMenuWillBecomeVisible

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Operacion_Exitosa.setVisible(false);
        jLabel_ERROR2.setVisible(false);
        jLabel_TEMA.setVisible(false);
        jLabel_LISTA.setVisible(false);
        jLabel_ALBUM.setVisible(false);
        jComboBox_SELECCIONAR_ALBUM.setVisible(false);
        jComboBox_SELECCIONAR_TEMA.setVisible(false);
        jComboBox_SELECCIONAR_LISTA.setVisible(false);
        SELECCIONAR_ALBUM.setVisible(false);
        SELECCIONAR_LISTA.setVisible(false);
        SELECCIONAR_TEMA.setVisible(false);
        jLabel_ERROR.setVisible(false);
        ArrayList<String> meto = sys.Nicknames_De_Clientes();
        for (int i = 0; i < meto.size(); i++) {
      jComboBox_Cliente.addItem(meto.get(i));
    }
    
    }//GEN-LAST:event_formWindowOpened

    private void jRadioButton_LISTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_LISTAActionPerformed
        SELECCIONAR_ALBUM.setVisible(false);
            SELECCIONAR_LISTA.setVisible(true);
            SELECCIONAR_TEMA.setVisible(false);
            jComboBox_SELECCIONAR_ALBUM.setVisible(false);
            jComboBox_SELECCIONAR_TEMA.setVisible(false);
            jComboBox_SELECCIONAR_LISTA.setVisible(true);
    }//GEN-LAST:event_jRadioButton_LISTAActionPerformed

    private void jRadioButton_TEMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_TEMAActionPerformed
      SELECCIONAR_ALBUM.setVisible(false);
            SELECCIONAR_LISTA.setVisible(false);
            SELECCIONAR_TEMA.setVisible(true);
            jComboBox_SELECCIONAR_ALBUM.setVisible(false);
            jComboBox_SELECCIONAR_TEMA.setVisible(true);
            jComboBox_SELECCIONAR_LISTA.setVisible(false);
    }//GEN-LAST:event_jRadioButton_TEMAActionPerformed

    private void jRadioButton_ALBUMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_ALBUMActionPerformed
       SELECCIONAR_ALBUM.setVisible(true);
            SELECCIONAR_LISTA.setVisible(false);
            SELECCIONAR_TEMA.setVisible(false);
            jComboBox_SELECCIONAR_ALBUM.setVisible(true);
            jComboBox_SELECCIONAR_TEMA.setVisible(false);
            jComboBox_SELECCIONAR_LISTA.setVisible(false);
    }//GEN-LAST:event_jRadioButton_ALBUMActionPerformed

    private void ACEPTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACEPTARActionPerformed
        jLabel_ERROR.setVisible(false);
        jLabel_LISTA.setVisible(false);
        jLabel_TEMA.setVisible(false);
        jLabel_ALBUM.setVisible(false);
        if(jComboBox_Cliente.getSelectedItem() != null){
        if(buttonGroup.getSelection() == jRadioButton_LISTA.getModel() ){
           if(jComboBox_SELECCIONAR_LISTA.getSelectedItem() == null){
               
               jLabel_LISTA.setVisible(true);
           }else{
               try {
                   Operacion_Exitosa.setVisible(true);
                   sys.Seguir_LISTA(jComboBox_Cliente.getSelectedItem().toString(),jComboBox_SELECCIONAR_LISTA.getSelectedItem().toString());
               } catch (Exception ex) {
                   Logger.getLogger(GuardarTemaListaAlbum.class.getName()).log(Level.SEVERE, null, ex);
               }
               
           }
        }else{
           if(buttonGroup.getSelection() == jRadioButton_TEMA.getModel()) {
               if(jComboBox_SELECCIONAR_TEMA.getSelectedItem() == null){
                jLabel_TEMA.setVisible(true);
           }else{
                   try {
                       Operacion_Exitosa.setVisible(true);
                       sys.Seguir_TEMA(jComboBox_Cliente.getSelectedItem().toString(),jComboBox_SELECCIONAR_TEMA.getSelectedItem().toString());
                   } catch (Exception ex) {
                       Logger.getLogger(GuardarTemaListaAlbum.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
        }else{
               if(buttonGroup.getSelection() == jRadioButton_ALBUM.getModel()){
                   if(jComboBox_SELECCIONAR_ALBUM.getSelectedItem() == null){
                jLabel_ALBUM.setVisible(true);
               }else{
                       try {
                   
                           Operacion_Exitosa.setVisible(true);
                           sys.Seguir_ALBUM(jComboBox_Cliente.getSelectedItem().toString(),jComboBox_SELECCIONAR_ALBUM.getSelectedItem().toString());
                       } catch (Exception ex) {
                           Logger.getLogger(GuardarTemaListaAlbum.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
           }else{
                  jLabel_ERROR.setVisible(true); 
               }
           }
        }
        }else{
            
                jLabel_ERROR2.setVisible(true);
                
        }
        jComboBox_SELECCIONAR_ALBUM.removeAllItems();
        jComboBox_SELECCIONAR_TEMA.removeAllItems();
        jComboBox_SELECCIONAR_LISTA.removeAllItems();
    }//GEN-LAST:event_ACEPTARActionPerformed

    private void CANCELARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCELARActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_CANCELARActionPerformed

    private void jComboBox_SELECCIONAR_ALBUMPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox_SELECCIONAR_ALBUMPopupMenuWillBecomeVisible
        if(jComboBox_Cliente.getSelectedItem() != null){
            
                 
        if(jComboBox_SELECCIONAR_ALBUM.getItemCount() == 0){
                     
        String cliente_seleccionado = jComboBox_Cliente.getSelectedItem().toString();
             ArrayList<String> meto  = sys.Listar_Albumes_A_Seguir(cliente_seleccionado);
             for (int i = 0; i < meto.size(); i++) {
            jComboBox_SELECCIONAR_ALBUM.addItem(meto.get(i));
            }
    }
        }
    }//GEN-LAST:event_jComboBox_SELECCIONAR_ALBUMPopupMenuWillBecomeVisible

    private void jComboBox_SELECCIONAR_TEMAPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox_SELECCIONAR_TEMAPopupMenuWillBecomeVisible
           if(jComboBox_Cliente.getSelectedItem() != null){
            if(jComboBox_SELECCIONAR_TEMA.getItemCount() == 0){
                     
        String cliente_seleccionado = jComboBox_Cliente.getSelectedItem().toString();
             ArrayList<String> meto  = sys.Listar_Temas_A_Seguir(cliente_seleccionado);
             for (int i = 0; i < meto.size(); i++) {
            jComboBox_SELECCIONAR_TEMA.addItem(meto.get(i));
            }
    } 
           }
    }//GEN-LAST:event_jComboBox_SELECCIONAR_TEMAPopupMenuWillBecomeVisible

    private void jComboBox_SELECCIONAR_LISTAPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox_SELECCIONAR_LISTAPopupMenuWillBecomeVisible
         if(jComboBox_Cliente.getSelectedItem() != null){
        if(jComboBox_SELECCIONAR_LISTA.getItemCount() == 0){
                     
            String cliente_seleccionado = jComboBox_Cliente.getSelectedItem().toString();
             ArrayList<String> meto  = sys.Listar_Listas_A_Seguir(cliente_seleccionado);
             for (int i = 0; i < meto.size(); i++) {
            jComboBox_SELECCIONAR_LISTA.addItem(meto.get(i));
            
            }
    }
         }
    }//GEN-LAST:event_jComboBox_SELECCIONAR_LISTAPopupMenuWillBecomeVisible

    private void jComboBox_SELECCIONAR_ALBUMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_SELECCIONAR_ALBUMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_SELECCIONAR_ALBUMActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACEPTAR;
    private javax.swing.JButton CANCELAR;
    private javax.swing.JLabel Operacion_Exitosa;
    private javax.swing.JLabel SELECCIONAR_ALBUM;
    private javax.swing.JLabel SELECCIONAR_LISTA;
    private javax.swing.JLabel SELECCIONAR_TEMA;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JComboBox<String> jComboBox_Cliente;
    private javax.swing.JComboBox<String> jComboBox_SELECCIONAR_ALBUM;
    private javax.swing.JComboBox<String> jComboBox_SELECCIONAR_LISTA;
    private javax.swing.JComboBox<String> jComboBox_SELECCIONAR_TEMA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_ALBUM;
    private javax.swing.JLabel jLabel_ERROR;
    private javax.swing.JLabel jLabel_ERROR2;
    private javax.swing.JLabel jLabel_LISTA;
    private javax.swing.JLabel jLabel_TEMA;
    private javax.swing.JRadioButton jRadioButton_ALBUM;
    private javax.swing.JRadioButton jRadioButton_LISTA;
    private javax.swing.JRadioButton jRadioButton_TEMA;
    // End of variables declaration//GEN-END:variables
}
