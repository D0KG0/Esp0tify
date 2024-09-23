/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacionGUI;

import controladores.Fabrica;
import controladores.iSistema;
import datatypes.DataAlbum;
import datatypes.DataLista;
import datatypes.DataTema;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Cliente;

/**
 *
 * @author Tonny2
 */
public class EliminarTemaListaAlbum extends javax.swing.JFrame {

    iSistema sys = new Fabrica().getSistema();
    DefaultTableModel model= new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
    DefaultTableModel modelTemas= new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
    DefaultTableModel modelAlbum= new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
    
    public EliminarTemaListaAlbum() {
        initComponents();
        this.setTitle("Eliminar Tema/Lista/Album");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbClientes = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTemas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableListas = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlbum = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Seleccionar Cliente:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        cmbClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClientesActionPerformed(evt);
            }
        });
        jPanel1.add(cmbClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 170, 40));

        jTableTemas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableTemas);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 700, 110));

        jTableListas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableListas);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 700, 120));

        jTableAlbum.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableAlbum);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 700, 100));

        jLabel1.setText("Temas");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));

        jLabel3.setText("Listas");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        jLabel4.setText("Album");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, -1, -1));

        jButton1.setText("Eliminar Tema");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 700, -1));

        jButton2.setText("Eliminar Lista");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 700, -1));

        jButton3.setText("Eliminar Album");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 700, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 3, 100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        List<String> listaClientes = sys.traerNickNamesClientes();
        
        for(String cliente: listaClientes){
            cmbClientes.addItem(cliente);
        }
    }//GEN-LAST:event_formWindowOpened
    
    public void cargarTablaListas(){
        model.setRowCount(0);
        String nickname= (String)cmbClientes.getSelectedItem();
        
       List<DataLista> listasFav = sys.traerListasFavoritasPorIdCliente(nickname);
       
        for (DataLista dtL: listasFav) {
            model.addRow(new Object[]{dtL.getId(),dtL.getNombre()});
        }
        
        jTableListas.setModel(model);
    }
    
    public void cargarTablaTemas(){
        modelTemas.setRowCount(0);
        String nickname= (String)cmbClientes.getSelectedItem();
        
        
       List<DataTema> temasFav = sys.traerTemasFavoritosPorIdCliente(nickname);
       
        for (DataTema dtT: temasFav) {
            modelTemas.addRow(new Object[]{dtT.getId(),dtT.getNombre()});
        }
        
        jTableTemas.setModel(modelTemas);
    }
    
    public void cargarTablaAlbumes(){
        modelAlbum.setRowCount(0);
        String nickname= (String)cmbClientes.getSelectedItem();
        
        
       List<DataAlbum> albumesFav = sys.traerAlbumesFavoritosPorIdCliente(nickname);
       
        for (DataAlbum dtA: albumesFav) {
            modelAlbum.addRow(new Object[]{dtA.getId(),dtA.getNombre()});
        }
        
        jTableAlbum.setModel(modelAlbum);
    }
    private void cmbClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClientesActionPerformed
        cargarTablaListas();
        cargarTablaTemas();
        cargarTablaAlbumes();
        
    }//GEN-LAST:event_cmbClientesActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int row = jTableListas.getSelectedRow();

       // Verificar si se ha seleccionado una lista
       if (row == -1) {
           JOptionPane.showMessageDialog(this, "Debe seleccionar una lista para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
           return;
       }

       // Obtener el ID de la lista y continuar con la eliminación
       int listaId = (int) model.getValueAt(row, 0);
       String nickname = (String) cmbClientes.getSelectedItem();

       // Confirmar la eliminación
       int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la lista?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
       if (confirm == JOptionPane.YES_OPTION) {
           sys.eliminarListaFavoritaCliente(nickname, listaId);
           cargarTablaListas();
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = jTableTemas.getSelectedRow();

        // Verificar si se ha seleccionado un tema
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tema para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el ID del tema y continuar con la eliminación
        Long idTema = (Long) modelTemas.getValueAt(row, 0);
        String nickname = (String) cmbClientes.getSelectedItem();

        // Confirmar la eliminación
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el tema?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            sys.eliminarTemasFavoritosCliente(nickname, idTema);
            cargarTablaTemas();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = jTableAlbum.getSelectedRow();

        // Verificar si se ha seleccionado un álbum
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un álbum para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el ID del álbum y continuar con la eliminación
        Long idAlbum = (Long) modelAlbum.getValueAt(row, 0);
        String nickname = (String) cmbClientes.getSelectedItem();

        // Confirmar la eliminación
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el álbum?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            sys.eliminarAlbumesFavoritosCliente(nickname, idAlbum);
            cargarTablaAlbumes();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cmbClientes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableAlbum;
    private javax.swing.JTable jTableListas;
    private javax.swing.JTable jTableTemas;
    // End of variables declaration//GEN-END:variables
}
