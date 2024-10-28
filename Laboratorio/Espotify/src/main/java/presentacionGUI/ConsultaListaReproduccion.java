/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacionGUI;

import controladores.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import datatypes.*;
import excepciones.UsuarioNoExisteException;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import persistencia.ControladorPersistencia;

public class ConsultaListaReproduccion extends javax.swing.JInternalFrame {
    ControladorPersistencia cpu;
    
    iSistema sys = new Fabrica().getSistema(cpu);
    DefaultTableModel model2 = new DefaultTableModel();
    String extra;
    String nombre;
    public ConsultaListaReproduccion() {
        initComponents();
        //sys = new Fabrica().getSistema();
        getContentPane().add(jLabel_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 5, 220, 180));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbLista = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        cmbExtra = new javax.swing.JComboBox<>();
        lblExtra = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        jLabel_imagen = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTemas = new javax.swing.JTable();
        lblEstado = new javax.swing.JLabel();
        txtEstado = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setTitle("Consulta ListaReproduccion");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Lista:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        cmbLista.setSelectedItem(null);
        cmbLista.setToolTipText("");
        cmbLista.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbListaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmbLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbListaActionPerformed(evt);
            }
        });
        getContentPane().add(cmbLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 100, 158, -1));

        jLabel3.setText("Tipo:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cmbTipo.addItem("Por Defecto");
        cmbTipo.addItem("Particular");
        cmbTipo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbTipoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });
        getContentPane().add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 19, 158, -1));

        cmbExtra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        cmbExtra.setVisible(false);
        cmbExtra.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbExtraPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmbExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbExtraActionPerformed(evt);
            }
        });
        getContentPane().add(cmbExtra, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 60, 158, -1));
        getContentPane().add(lblExtra, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 60, 0, -1));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));
        getContentPane().add(jLabel_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 6, 250, 150));

        tblTemas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblTemas.setEnabled(false);
        jScrollPane1.setViewportView(tblTemas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 610, 180));

        lblEstado.setText("Estado:");
        getContentPane().add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        txtEstado.setText(" ");
        getContentPane().add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbListaActionPerformed

    }//GEN-LAST:event_cmbListaActionPerformed

    private void cmbExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbExtraActionPerformed
        
    }//GEN-LAST:event_cmbExtraActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        cmbExtra.setSelectedItem(null);
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void cmbListaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbListaPopupMenuWillBecomeInvisible
        
        if (cmbLista.getSelectedItem() != null){
            extra = cmbExtra.getSelectedItem().toString();
            nombre = cmbLista.getSelectedItem().toString();
            if(cmbTipo.getSelectedItem().toString().equals("Particular")){
                DataLista dl = sys.traerListaPartClienteNombre(extra, nombre);

                String imagePath = dl.getImagen();
                if (imagePath != null && !imagePath.isEmpty()) {
                    java.net.URL imageURL = getClass().getResource("/imagesL/" + imagePath);
                    if (imageURL != null) {
                        ImageIcon imageIcon = new ImageIcon(imageURL);
                        java.awt.Image scaledImage = imageIcon.getImage().getScaledInstance(jLabel_imagen.getWidth(), jLabel_imagen.getHeight(), java.awt.Image.SCALE_SMOOTH);
                        jLabel_imagen.setIcon(new ImageIcon(scaledImage));
                    } else {
                        System.out.println("No se pudo encontrar la imagen en la ruta: " + "/imagesL/" + imagePath);
                        jLabel_imagen.setIcon(null);
                    }
                } else {
                    jLabel_imagen.setIcon(null); //
                }
                lblEstado.setVisible(true);
                if(dl.isEstado()){
                    txtEstado.setText("Publica");
                }else{ txtEstado.setText("Privada"); }  
                txtEstado.setVisible(true);
                cargarTablaPartTemas();
            }
            else if (cmbTipo.getSelectedItem().toString().equals("Por Defecto")){
                
                lblEstado.setVisible(false);
                txtEstado.setVisible(false);
                txtEstado.setText(" ");
                extra = cmbExtra.getSelectedItem().toString();
                nombre = cmbLista.getSelectedItem().toString();
                DataLista dl = sys.traerListaDefGeneroNombre(extra, nombre);
                
                String imagePath = dl.getImagen();
                if (imagePath != null && !imagePath.isEmpty()) {
                    java.net.URL imageURL = getClass().getResource("/imagesL/" + imagePath);
                    if (imageURL != null) {
                        ImageIcon imageIcon = new ImageIcon(imageURL);
                        java.awt.Image scaledImage = imageIcon.getImage().getScaledInstance(jLabel_imagen.getWidth(), jLabel_imagen.getHeight(), java.awt.Image.SCALE_SMOOTH);
                        jLabel_imagen.setIcon(new ImageIcon(scaledImage));
                    } else {
                        System.out.println("No se pudo encontrar la imagen en la ruta: " + "/imagesL/" + imagePath);
                        jLabel_imagen.setIcon(null);
                    }
                } else {
                    jLabel_imagen.setIcon(null); //
                }
                
                cargarTablaDefTemas();
            }
        }else{}
    }//GEN-LAST:event_cmbListaPopupMenuWillBecomeInvisible

    private void cmbExtraPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbExtraPopupMenuWillBecomeInvisible
        cmbLista.removeAllItems();
        model2.setRowCount(0);
        jLabel_imagen.setIcon(null);
        
        if(cmbTipo.getSelectedItem() != null){
            if(cmbTipo.getSelectedItem().toString().equals("Por Defecto")){
                List<DataLista> dtLista = sys.traerListasDefectoPorGenero(cmbExtra.getSelectedItem().toString());
                if (!dtLista.isEmpty()){
                    for(DataLista dtL : dtLista){
                        if(dtL != null){
                            cmbLista.addItem(dtL.getNombre());
                        }
                    }
                }
            }else{
            if(cmbTipo.getSelectedItem() != null){
                if(cmbTipo.getSelectedItem().toString().equals("Particular") || cmbExtra.getSelectedItem() != null){
                    List<DataLista> dtLista = sys.getListasParticulares(cmbExtra.getSelectedItem().toString());
                    if (dtLista != null){
                        for(DataLista dtL : dtLista){
                            if(dtL != null){
                                cmbLista.addItem(dtL.getNombre());
                            }else{}
                        }
                    }else{System.out.println("null");}
                }else{System.out.println("mas arriba");}
            }
        }
        }else{}
        
    }//GEN-LAST:event_cmbExtraPopupMenuWillBecomeInvisible

    private void cmbTipoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbTipoPopupMenuWillBecomeInvisible
        cmbExtra.removeAllItems();
        cmbLista.removeAllItems();
        model2.setRowCount(0);
        cmbExtra.setSelectedItem(null);
        jLabel_imagen.setIcon(null);
        if(cmbTipo.getSelectedItem().toString().equals("Por Defecto")){
            //genero
            lblExtra.setText("Genero");
            cmbExtra.setVisible(true);

            for(String s:sys.listarGeneros()){
                cmbExtra.addItem(s);
            }
            
        }else{
            if(cmbTipo.getSelectedItem().toString().equals("Particular")){
                lblExtra.setText("Cliente");
                try {
                    DataUsuario[] dataClientes = sys.getClientes();
                    String[] nicksC = new String[dataClientes.length];
                    for (int i = 0; i < dataClientes.length; i++) {
                        nicksC[i] = dataClientes[i].getNick();
                    }
                    cmbExtra.setModel(new javax.swing.DefaultComboBoxModel<>(nicksC));
                    cmbExtra.setVisible(true);
                    
                } catch (UsuarioNoExisteException ex) {
                    Logger.getLogger(ConsultaListaReproduccion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                cmbExtra.removeAllItems();
                lblExtra.setText("");
            }
        }
    }//GEN-LAST:event_cmbTipoPopupMenuWillBecomeInvisible

        public void cargarTablaPartTemas(){
        model2.setRowCount(0);
        model2.setColumnIdentifiers(new Object[]{"Nombre", "Duracion", "Direccion Web", "Archivo"});

        DataLista dtLista = sys.traerListaPartClienteNombre(cmbExtra.getSelectedItem().toString(), cmbLista.getSelectedItem().toString());
        Collection<DataTema> temas = dtLista.getTemas();


        Iterator<DataTema> it = temas.iterator();
        while(it.hasNext()){
            DataTema dtTema = it.next();
            model2.addRow(new Object[]{dtTema.getNombre(),dtTema.getDuracion(),dtTema.getDireccionWeb(),dtTema.getArchivo()});
        }
        tblTemas.setModel(model2);
        TableColumn column = tblTemas.getColumnModel().getColumn(0);
        column.setPreferredWidth(200); 
        column.setMaxWidth(600); 
        

        column = tblTemas.getColumnModel().getColumn(1);
        column.setPreferredWidth(50); 
        column.setMinWidth(50);
        column.setMaxWidth(100);
        
        column = tblTemas.getColumnModel().getColumn(2);
        column.setPreferredWidth(100); 
        column.setMaxWidth(100); 
        
        column = tblTemas.getColumnModel().getColumn(3);
        column.setPreferredWidth(200); 
        column.setMaxWidth(200);  
    }

    public void cargarTablaDefTemas(){
        model2.setRowCount(0);
        model2.setColumnIdentifiers(new Object[]{"Nombre", "Duracion", "Direccion Web", "Archivo"});

        DataLista dtLista = sys.getListaDefecto(cmbLista.getSelectedItem().toString());
        Collection<DataTema> temas = dtLista.getTemas();


        Iterator<DataTema> it = temas.iterator();
        while(it.hasNext()){
            DataTema dtTema = it.next();
            model2.addRow(new Object[]{dtTema.getNombre(),dtTema.getDuracion(),dtTema.getDireccionWeb(),dtTema.getArchivo()});
        }
        tblTemas.setModel(model2);
        TableColumn column = tblTemas.getColumnModel().getColumn(0);
        column.setPreferredWidth(200); 
        column.setMaxWidth(600); 
        

        column = tblTemas.getColumnModel().getColumn(1);
        column.setPreferredWidth(50); 
        column.setMinWidth(50);
        column.setMaxWidth(100);
        
        column = tblTemas.getColumnModel().getColumn(2);
        column.setPreferredWidth(100); 
        column.setMaxWidth(100); 
        
        column = tblTemas.getColumnModel().getColumn(3);
        column.setPreferredWidth(200); 
        column.setMaxWidth(200);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cmbExtra;
    private javax.swing.JComboBox<String> cmbLista;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_imagen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblExtra;
    private javax.swing.JTable tblTemas;
    private javax.swing.JLabel txtEstado;
    // End of variables declaration//GEN-END:variables
}
