
package presentacionGUI;

import controladores.Fabrica;
import controladores.iSistema;
import datatypes.DataLista;
import datatypes.DataTema;
import datatypes.DataUsuario;
import excepciones.UsuarioNoExisteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.ListaPorDefecto;
import persistencia.ControladorPersistencia;


public class QuitarTemaLista extends javax.swing.JInternalFrame {
    
    ControladorPersistencia cpu;
    
    iSistema sys = new Fabrica().getSistema(cpu);
    DefaultTableModel model2 = new DefaultTableModel();
    
    public QuitarTemaLista() {
        initComponents();
        cbCliente.setVisible(false);
        lCliente.setVisible(false);
        cbLista.setVisible(false);
        lLista.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lTipo = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTemas = new javax.swing.JTable();
        txtTema = new javax.swing.JTextField();
        lTema = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        lLista = new javax.swing.JLabel();
        cbLista = new javax.swing.JComboBox<>();
        cbCliente = new javax.swing.JComboBox<>();
        lCliente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Quitar Tema Lista");

        lTipo.setText("Seleccione tipo:");

        cbTipo.addItem("Por Defecto");
        cbTipo.addItem("Particular");
        cbTipo.setSelectedItem(null);
        cbTipo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbTipoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });

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
        tblTemas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTemasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTemas);

        lTema.setText("Tema:");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        lLista.setText("Seleccione lista:");

        cbLista.setSelectedItem(null);
        cbLista.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbListaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbListaActionPerformed(evt);
            }
        });

        try {
            DataUsuario[] dataClientes = sys.getClientes();
            String[] nicksC = new String[dataClientes.length];
            for (int i = 0; i < dataClientes.length; i++) {
                nicksC[i] = dataClientes[i].getNick();
            }
            cbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(nicksC));
        } catch (UsuarioNoExisteException e) {
            // Manejar la excepción
        }
        cbCliente.setSelectedItem(null);
        cbCliente.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbClientePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClienteActionPerformed(evt);
            }
        });

        lCliente.setText("Seleccione cliente:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(428, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169))
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lLista)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lTipo)
                    .addComponent(cbLista, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lCliente))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(lTema)
                            .addGap(53, 53, 53))))
                .addGap(121, 121, 121))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(127, 127, 127)
                    .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(470, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lLista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLista, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lTema)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTema, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(467, Short.MAX_VALUE)
                    .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(46, 46, 46)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
    int row = tblTemas.getSelectedRow();
    
    if(cbTipo.getSelectedItem().toString().equals("Particular")){
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tema para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Long idTema = (Long) model2.getValueAt(row, 0);
        String nickname = (String) cbCliente.getSelectedItem();

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el tema?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            sys.eliminarTemasListaPart(nickname, cbLista.getSelectedItem().toString(), idTema);
            cargarTablaPartTemas();
        }
        
    }else if(cbTipo.getSelectedItem().toString().equals("Por Defecto")){
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tema para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Long idTema = (Long) model2.getValueAt(row, 0);
        String nickname = (String) cbLista.getSelectedItem();

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el tema?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            sys.eliminarTemaListaDef(nickname, idTema);
            cargarTablaDefTemas();
        }
        
    }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void cbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClienteActionPerformed

    }//GEN-LAST:event_cbClienteActionPerformed

    private void cbListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListaActionPerformed

    }//GEN-LAST:event_cbListaActionPerformed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed

    }//GEN-LAST:event_cbTipoActionPerformed

    private void cbClientePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbClientePopupMenuWillBecomeInvisible
        cbLista.removeAllItems();
        model2.setRowCount(0);
        if(cbTipo.getSelectedItem() != null){
            if(cbTipo.getSelectedItem().toString().equals("Particular") || cbCliente.getSelectedItem() != null){
                List<DataLista> dtLista = sys.getListasParticulares(cbCliente.getSelectedItem().toString());
                if (!dtLista.isEmpty()){
                    for(DataLista dtL : dtLista){
                        if(dtL != null){
                            cbLista.addItem(dtL.getNombre());
                        }else{}
                    }
                }
                cbLista.setVisible(true);
                lLista.setVisible(true);
            }
        }else{}
    }//GEN-LAST:event_cbClientePopupMenuWillBecomeInvisible

    private void cbTipoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbTipoPopupMenuWillBecomeInvisible
        cbLista.removeAllItems();
        model2.setRowCount(0);
        if(cbTipo.getSelectedItem() != null){
            if(cbTipo.getSelectedItem().toString().equals("Por Defecto")){
                List<DataLista> dtLista = sys.traerListasPorDefecto();
                if (!dtLista.isEmpty()){
                    for(DataLista dtL : dtLista){
                        if(dtL != null){
                            cbLista.addItem(dtL.getNombre());
                        }
                    }
                }
                cbLista.setVisible(true);
                lLista.setVisible(true);
                cbCliente.setVisible(false);
                lCliente.setVisible(false);
                cbCliente.setSelectedItem(null);
                cbLista.setSelectedItem(null);
                
            }else if(cbTipo.getSelectedItem().toString().equals("Particular")){
                cbCliente.setVisible(true);
                lCliente.setVisible(true);
                cbLista.setVisible(false);
                lLista.setVisible(false);
                cbLista.setSelectedItem(null);
                
            }
        }else{}
    }//GEN-LAST:event_cbTipoPopupMenuWillBecomeInvisible

    private void cbListaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbListaPopupMenuWillBecomeInvisible
        if (cbLista.getSelectedItem() != null){
            if(cbTipo.getSelectedItem().toString().equals("Particular")){
                cargarTablaPartTemas();
            }
            else if (cbTipo.getSelectedItem().toString().equals("Por Defecto")){
                cargarTablaDefTemas();
            }
        }
    }//GEN-LAST:event_cbListaPopupMenuWillBecomeInvisible

    private void tblTemasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTemasMouseClicked
        int row = tblTemas.getSelectedRow();
        if (row != -1){
            txtTema.setText((String) model2.getValueAt(row, 1));
        }
    }//GEN-LAST:event_tblTemasMouseClicked

    public void cargarTablaPartTemas(){
        model2.setRowCount(0);
        model2.setColumnIdentifiers(new Object[]{"Id", "Nombre"});

        DataLista dtLista = sys.traerListaPartClienteNombre(cbCliente.getSelectedItem().toString(), cbLista.getSelectedItem().toString());
        Collection<DataTema> temas = dtLista.getTemas();


        Iterator<DataTema> it = temas.iterator();
        while(it.hasNext()){
            DataTema dtTema = it.next();
            model2.addRow(new Object[]{dtTema.getId(),dtTema.getNombre()});
        }
        tblTemas.setModel(model2);
    }

    public void cargarTablaDefTemas(){
        model2.setRowCount(0);
        model2.setColumnIdentifiers(new Object[]{"Id", "Nombre"});

        DataLista dtLista = sys.getListaDefecto(cbLista.getSelectedItem().toString());
        Collection<DataTema> temas = dtLista.getTemas();


        Iterator<DataTema> it = temas.iterator();
        while(it.hasNext()){
            DataTema dtTema = it.next();
            model2.addRow(new Object[]{dtTema.getId(),dtTema.getNombre()});
        }
        tblTemas.setModel(model2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbCliente;
    private javax.swing.JComboBox<String> cbLista;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lLista;
    private javax.swing.JLabel lTema;
    private javax.swing.JLabel lTipo;
    private javax.swing.JTable tblTemas;
    private javax.swing.JTextField txtTema;
    // End of variables declaration//GEN-END:variables
}
