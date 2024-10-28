/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacionGUI;
import javax.swing.table.DefaultTableModel;
import controladores.iSistema;
import controladores.Fabrica;
import datatypes.DataAlbum;
import datatypes.DataGenero;
import datatypes.DataTema;
import datatypes.DataUsuario;
import excepciones.AlbumNoExisteException;
import excepciones.GeneroNoExisteException;
import excepciones.UsuarioNoExisteException;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.table.TableColumn;
import persistencia.ControladorPersistencia;


public class ConsultaAlbum extends javax.swing.JInternalFrame {

    ControladorPersistencia cpu;
    
    iSistema sys = new Fabrica().getSistema(cpu);
   
    public ConsultaAlbum() {
        initComponents();
        buttonGroup1.add(rbtn_gen);
        buttonGroup1.add(rbtn_art);
        
        jLabel_artista.setVisible(false);
        jLabel_albumartista.setVisible(false);
        jLabel_genero.setVisible(false);
        jLabel_albumGenero.setVisible(false);
        
        cb_genero.setVisible(false);
        cb_albumgen.setVisible(false);
        cb_artista.setVisible(false);
        cb_albumar.setVisible(false);
   
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        rbtn_gen = new javax.swing.JRadioButton();
        rbtn_art = new javax.swing.JRadioButton();
        btn_salir = new javax.swing.JButton();
        cb_albumgen = new javax.swing.JComboBox<>();
        cb_albumar = new javax.swing.JComboBox<>();
        cb_artista = new javax.swing.JComboBox<>();
        cb_genero = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_album = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_temas = new javax.swing.JTable();
        jLabel_genero = new javax.swing.JLabel();
        jLabel_artista = new javax.swing.JLabel();
        jLabel_albumartista = new javax.swing.JLabel();
        jLabel_albumGenero = new javax.swing.JLabel();
        jLabel_imagen = new javax.swing.JLabel();

        setTitle("Consulta Album");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Filtar por:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        rbtn_gen.setText("Generos");
        rbtn_gen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn_genActionPerformed(evt);
            }
        });
        getContentPane().add(rbtn_gen, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        rbtn_art.setText("Artistas");
        rbtn_art.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn_artActionPerformed(evt);
            }
        });
        getContentPane().add(rbtn_art, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, -1));

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 450, 98, -1));

        cb_albumgen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_albumgenActionPerformed(evt);
            }
        });
        getContentPane().add(cb_albumgen, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 180, -1));

        cb_albumar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_albumarActionPerformed(evt);
            }
        });
        getContentPane().add(cb_albumar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 180, -1));

        cb_artista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_artistaActionPerformed(evt);
            }
        });
        getContentPane().add(cb_artista, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 180, -1));

        cb_genero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_generoActionPerformed(evt);
            }
        });
        getContentPane().add(cb_genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 180, -1));

        jTable_album.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Año", "Generos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_album.setEnabled(false);
        jScrollPane1.setViewportView(jTable_album);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 540, 130));

        jTable_temas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Posicion", "Nombre", "Duración", "Archivo", "Direccion Web"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable_temas);
        if (jTable_temas.getColumnModel().getColumnCount() > 0) {
            jTable_temas.getColumnModel().getColumn(0).setResizable(false);
            jTable_temas.getColumnModel().getColumn(0).setPreferredWidth(1);
            jTable_temas.getColumnModel().getColumn(1).setPreferredWidth(165);
            jTable_temas.getColumnModel().getColumn(2).setPreferredWidth(1);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 780, 150));

        jLabel_genero.setText("Seleccionar Genero");
        getContentPane().add(jLabel_genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jLabel_artista.setText("Seleccionar Artista");
        getContentPane().add(jLabel_artista, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        jLabel_albumartista.setText("Seleccionar Album");
        getContentPane().add(jLabel_albumartista, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, -1));

        jLabel_albumGenero.setText("Seleccionar Album");
        getContentPane().add(jLabel_albumGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, -1));
        getContentPane().add(jLabel_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 160, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtn_genActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn_genActionPerformed
        jLabel_genero.setVisible(true);
        cb_genero.setVisible(true);
        jLabel_albumGenero.setVisible(true);
        cb_albumgen.setVisible(true);

        jLabel_artista.setVisible(false);
        cb_artista.setVisible(false);
        jLabel_albumartista.setVisible(false);
        cb_albumar.setVisible(false);
        
        try {
            
            
        DataGenero[] generos = sys.getGeneros2(); 
            for (DataGenero genero : generos) {
                cb_genero.addItem(genero.getNombre());
            }
        } catch (GeneroNoExisteException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron generos.");
        }
    }//GEN-LAST:event_rbtn_genActionPerformed

    private void rbtn_artActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn_artActionPerformed
        jLabel_artista.setVisible(true);
        cb_artista.setVisible(true);
        jLabel_albumartista.setVisible(true);
        cb_albumar.setVisible(true);

        jLabel_genero.setVisible(false);
        cb_genero.setVisible(false);
        jLabel_albumGenero.setVisible(false);
        cb_albumgen.setVisible(false);
        
        cb_artista.removeAllItems();
        
        try {
        // Suponiendo que tienes un método en el sistema que retorna los artistas
        DataUsuario[] artistas = sys.getArtistas(); 
        for (DataUsuario artista : artistas) {
            cb_artista.addItem(artista.getNick());
            }
        } catch (UsuarioNoExisteException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron artistas.");
        }
        
    }//GEN-LAST:event_rbtn_artActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_salirActionPerformed

    private void cb_generoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_generoActionPerformed

        cb_albumgen.removeAllItems();
        jLabel_albumGenero.setVisible(true);
        cb_albumgen.setVisible(true);
        String generoSeleccionado = cb_genero.getSelectedItem().toString();      
        if (generoSeleccionado != null) {
            try {
                DataAlbum[] albums = sys.getAlbumsByGenero(generoSeleccionado);
                for (DataAlbum album : albums) {
                    cb_albumgen.addItem(album.getNombre());
                }
            } catch (AlbumNoExisteException ex) {
                
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron albumes para el genero seleccionado.");
            }
        }
    }//GEN-LAST:event_cb_generoActionPerformed

    private void cb_artistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_artistaActionPerformed
        cb_albumar.removeAllItems();
        jLabel_albumartista.setVisible(true);
        cb_albumar.setVisible(true);
        
        Object artistaSeleccionadoObj = cb_artista.getSelectedItem();
    if (artistaSeleccionadoObj != null) {
        String artistaSeleccionado = artistaSeleccionadoObj.toString();
        try {
            DataAlbum[] albums = sys.getAlbumsByArtista(artistaSeleccionado);
            for (DataAlbum album : albums) {
                cb_albumar.addItem(album.getNombre());
            }
        } catch (AlbumNoExisteException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron albumes para el artista seleccionado.");
        }
    }
    }//GEN-LAST:event_cb_artistaActionPerformed

    private void cb_albumgenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_albumgenActionPerformed
        Object albumSeleccionadoObj = cb_albumgen.getSelectedItem();
        Object generoSeleccionadoObj = cb_genero.getSelectedItem();

        if (albumSeleccionadoObj != null && generoSeleccionadoObj != null) {
            String albumSeleccionado = albumSeleccionadoObj.toString();
            String generoSeleccionado = generoSeleccionadoObj.toString();

            try {
                DataAlbum album = sys.getAlbumEspecificoGen2(albumSeleccionado, generoSeleccionado);

                DefaultTableModel model = (DefaultTableModel) jTable_album.getModel();
                model.setRowCount(0);

                // agregar el album a la tabla
                model.addRow(new Object[]{
                album.getNombre(),
                album.getFechaCreacion(),
                String.join(", ", album.getGeneros()) // Asumiendo que getGeneros() devuelve una lista de Strings
            });

                // agregar temas del album al otro jtable
                DefaultTableModel temasModel = (DefaultTableModel) jTable_temas.getModel();
                temasModel.setRowCount(0);

                for (DataTema tema : album.getTemas()) {
                    temasModel.addRow(new Object[]{
                        tema.getPosicion(),
                        tema.getNombre(),
                        tema.getDuracion(),
                        tema.getArchivo(),
                        tema.getDireccionWeb()
                    });
                }
                
                String imagePath = album.getImagenAlbum();
                    if (imagePath != null && !imagePath.isEmpty()) {
                        java.net.URL imageURL = getClass().getResource("/imagesA/" + imagePath);
                        if (imageURL != null) {
                            ImageIcon imageIcon = new ImageIcon(imageURL);
                            java.awt.Image scaledImage = imageIcon.getImage().getScaledInstance(jLabel_imagen.getWidth(), jLabel_imagen.getHeight(), java.awt.Image.SCALE_SMOOTH);
                            jLabel_imagen.setIcon(new ImageIcon(scaledImage));
                        } else {
                            System.out.println("No se pudo encontrar la imagen en la ruta: " + "/imagesA/" + imagePath);
                            jLabel_imagen.setIcon(null);
                        }
                    } else {
                        jLabel_imagen.setIcon(null); //
                    }

            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron detalles para el album seleccionado.");
            }
        }
    }//GEN-LAST:event_cb_albumgenActionPerformed

    private void cb_albumarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_albumarActionPerformed
        
    Object albumSeleccionadoObj = cb_albumar.getSelectedItem();
    Object artistaSeleccionadoObj = cb_artista.getSelectedItem();
    
    if (albumSeleccionadoObj != null && artistaSeleccionadoObj != null) {
        String albumSeleccionado = albumSeleccionadoObj.toString();
        String artistaSeleccionado = artistaSeleccionadoObj.toString();
        
        try {
            DataAlbum album = sys.getAlbumEspecificoArt2(albumSeleccionado, artistaSeleccionado);
            
            DefaultTableModel model = (DefaultTableModel) jTable_album.getModel();
            model.setRowCount(0);

            // agregar el album a la tabla
                model.addRow(new Object[]{
                album.getNombre(),
                album.getFechaCreacion(),
                String.join(", ", album.getGeneros()) // Asumiendo que getGeneros() devuelve una lista de Strings
            });

            // agregar temas del album al otro jtable
            DefaultTableModel temasModel = (DefaultTableModel) jTable_temas.getModel();
            temasModel.setRowCount(0);
            
            List<DataTema> temasOrdenados = new ArrayList<>(album.getTemas()); // Convertir a ArrayList
            temasOrdenados.sort(Comparator.comparingInt(DataTema::getPosicion)); // Ordenar por posición

            for (DataTema tema : album.getTemas()) {
                temasModel.addRow(new Object[]{
                    tema.getPosicion(),
                    tema.getNombre(),
                    tema.getDuracion(),
                    tema.getArchivo(),
                    tema.getDireccionWeb()
                });
            }
            
            String imagePath = album.getImagenAlbum();
                    if (imagePath != null && !imagePath.isEmpty()) {
                        java.net.URL imageURL = getClass().getResource("/imagesA/" + imagePath);
                        if (imageURL != null) {
                            ImageIcon imageIcon = new ImageIcon(imageURL);
                            java.awt.Image scaledImage = imageIcon.getImage().getScaledInstance(jLabel_imagen.getWidth(), jLabel_imagen.getHeight(), java.awt.Image.SCALE_SMOOTH);
                            jLabel_imagen.setIcon(new ImageIcon(scaledImage));
                        } else {
                            System.out.println("No se pudo encontrar la imagen en la ruta: " + "/imagesA/" + imagePath);
                            jLabel_imagen.setIcon(null);
                        }
                    } else {
                        jLabel_imagen.setIcon(null); //
                    }

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron detalles para el album seleccionado.");
        }
        }
    }//GEN-LAST:event_cb_albumarActionPerformed

   

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_salir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cb_albumar;
    private javax.swing.JComboBox<String> cb_albumgen;
    private javax.swing.JComboBox<String> cb_artista;
    private javax.swing.JComboBox<String> cb_genero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_albumGenero;
    private javax.swing.JLabel jLabel_albumartista;
    private javax.swing.JLabel jLabel_artista;
    private javax.swing.JLabel jLabel_genero;
    private javax.swing.JLabel jLabel_imagen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_album;
    private javax.swing.JTable jTable_temas;
    private javax.swing.JRadioButton rbtn_art;
    private javax.swing.JRadioButton rbtn_gen;
    // End of variables declaration//GEN-END:variables
}
