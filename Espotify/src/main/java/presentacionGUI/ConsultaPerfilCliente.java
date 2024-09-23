/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacionGUI;

import controladores.Fabrica;
import controladores.iSistema;
import datatypes.DataAlbum;
import datatypes.DataLista;
import datatypes.DataTema;
import excepciones.UsuarioNoExisteException;
import logica.Cliente;
import datatypes.DataUsuario;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author dokgo
 */
public class ConsultaPerfilCliente extends javax.swing.JInternalFrame {

    iSistema sys;
    public String nickname;
    
    Cliente cl;
    

    public ConsultaPerfilCliente() {
        sys = new Fabrica().getSistema();
        
        initComponents();
        
        jLabel_imagen = new javax.swing.JLabel();
        getContentPane().add(jLabel_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(599, 5, 150, 100));
        //this.setSize(700,300);
        
        
        
        jComboBox_nickname.setSelectedItem(null);  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_correo = new javax.swing.JLabel();
        jComboBox_nickname = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_info = new javax.swing.JTable();
        jButton_salir = new javax.swing.JButton();
        jLabel_imagen = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_seguidores = new javax.swing.JList<>();
        jLabel_seguidores = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlist_artista_seguido = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jlist_cliente_seguido = new javax.swing.JList<>();
        jLabel_client = new javax.swing.JLabel();
        jLabel_artist = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jlist_listas = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList_listaFav = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList_albumFav = new javax.swing.JList<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        jList_temaFav = new javax.swing.JList<>();
        jLabel_listaFav = new javax.swing.JLabel();
        jLabel_albumFav = new javax.swing.JLabel();
        jLabel_temaFav = new javax.swing.JLabel();

        setTitle("Consulta Perfil Cliente");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_correo.setText("Ingrese nickname a consultar: ");
        getContentPane().add(jLabel_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        try {
            DataUsuario[] dataClientes = sys.getClientes();
            String[] nicksC = new String[dataClientes.length];
            for (int i = 0; i < dataClientes.length; i++) {
                nicksC[i] = dataClientes[i].getNick();
            }
            jComboBox_nickname.setModel(new javax.swing.DefaultComboBoxModel<>(nicksC));
        } catch (UsuarioNoExisteException e) {
            // Manejar la excepción
        }
        jComboBox_nickname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_nicknameActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_nickname, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 280, -1));

        jTable_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Nickname", "Nombre", "Apellido", "Correo", "Fecha Nac"
            }
        ));
        jTable_info.setEnabled(false);
        jScrollPane1.setViewportView(jTable_info);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 740, 50));

        jButton_salir.setText("Salir");
        jButton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_salirActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 670, 190, -1));
        getContentPane().add(jLabel_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 150, 80));

        jScrollPane2.setViewportView(list_seguidores);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 140, 140));

        jLabel_seguidores.setText("Seguidores: ");
        getContentPane().add(jLabel_seguidores, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel1.setText("Seguidos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, -1, -1));

        jScrollPane3.setViewportView(jlist_artista_seguido);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 140, 140));

        jScrollPane4.setViewportView(jlist_cliente_seguido);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 140, 140));

        jLabel_client.setText("Clientes");
        getContentPane().add(jLabel_client, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, -1, -1));

        jLabel_artist.setText("Artistas");
        getContentPane().add(jLabel_artist, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, -1, -1));

        jLabel2.setText("Listas de Reproduccion: ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jScrollPane5.setViewportView(jlist_listas);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 140, 140));

        jLabel3.setText("Preferencias");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, -1, -1));

        jScrollPane6.setViewportView(jList_listaFav);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 520, 140, 140));

        jScrollPane7.setViewportView(jList_albumFav);

        getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, 140, 140));

        jScrollPane8.setViewportView(jList_temaFav);

        getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, 140, 140));

        jLabel_listaFav.setText("Lista");
        getContentPane().add(jLabel_listaFav, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 500, -1, -1));

        jLabel_albumFav.setText("Album");
        getContentPane().add(jLabel_albumFav, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, -1, -1));

        jLabel_temaFav.setText("Tema");
        getContentPane().add(jLabel_temaFav, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 500, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_nicknameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_nicknameActionPerformed
        if (jComboBox_nickname.getSelectedItem() == null) {
        return;
        }

        String nickname = jComboBox_nickname.getSelectedItem().toString();

        try {
            DataUsuario[] clientes = sys.getClientes();

            // busca al cliente
            for (DataUsuario dataUsuario : clientes) {
                if (dataUsuario.getNick().equals(nickname)) {

                    //crea la tabla
                    DefaultTableModel model = (DefaultTableModel) jTable_info.getModel();
                    model.setRowCount(0);
                    model.setColumnIdentifiers(new Object[]{"Nickname", "Nombre", "Apellido", "Correo", "FechaNac"});

                    // mete los datos del cliente en la tabla
                    model.addRow(new Object[]{
                        dataUsuario.getNick(),
                        dataUsuario.getNombre(),
                        dataUsuario.getApellido(),
                        dataUsuario.getCorreo(),
                        dataUsuario.getFechaNac().toString()
                    });
                    jTable_info.repaint();
                    
                    String imagePath = dataUsuario.getImagen();
                    if (imagePath != null && !imagePath.isEmpty()) {
                        java.net.URL imageURL = getClass().getResource("/images/" + imagePath);
                        if (imageURL != null) {
                            ImageIcon imageIcon = new ImageIcon(imageURL);
                            java.awt.Image scaledImage = imageIcon.getImage().getScaledInstance(jLabel_imagen.getWidth(), jLabel_imagen.getHeight(), java.awt.Image.SCALE_SMOOTH);
                            jLabel_imagen.setIcon(new ImageIcon(scaledImage));
                        } else {
                            System.out.println("No se pudo encontrar la imagen en la ruta: " + "/images/" + imagePath);
                            jLabel_imagen.setIcon(null);
                        }
                    } else {
                        jLabel_imagen.setIcon(null); //
                    }
                    
//                    String imagePath = "https://"+dataUsuario.getImagen();
//                    if (imagePath != null && !imagePath.isEmpty()) {
//                    try {
//                    URL url = new URL(imagePath);
//                            ImageIcon imageIcon = new ImageIcon(url);
//                            // Escalar la imagen para que quepa en el JLabel
//                            java.awt.Image scaledImage = imageIcon.getImage().getScaledInstance(jLabel_imagen.getWidth(), jLabel_imagen.getHeight(), java.awt.Image.SCALE_SMOOTH);
//                            jLabel_imagen.setIcon(new ImageIcon(scaledImage));
//                        } catch (MalformedURLException e) {
//                            // Manejar error en la URL
//                            e.printStackTrace();
//                            jLabel_imagen.setIcon(null); // Si la URL es inválida
//                        }
//                    } else {
//                        jLabel_imagen.setIcon(null); // En caso de que no haya imagen
//                    }
                    
                    //segudiores
                    List<Cliente> seguidores = sys.getSeguidores(nickname);
                    
                    DefaultListModel<String> modeloSeguidores = new DefaultListModel<>();
                    for (Cliente seguidor : seguidores) {
                        modeloSeguidores.addElement(seguidor.getNickname());
                        }
                        list_seguidores.setModel(modeloSeguidores);
                        
                    //seguidos
                    
                    Map<String, ArrayList<String>> usuariosSeguidos = sys.UsuarioSeguidos(nickname);
                    DefaultListModel<String> modeloClientesSeguidos = new DefaultListModel<>();
                    ArrayList<String> clientesSeguidos = usuariosSeguidos.get("clientes");
                    
                    for (String cliente : clientesSeguidos) {
                        modeloClientesSeguidos.addElement(cliente);
                    }
                    jlist_cliente_seguido.setModel(modeloClientesSeguidos);
                    
                    DefaultListModel<String> modeloArtistasSeguidos = new DefaultListModel<>();
                    ArrayList<String> artistasSeguidos = usuariosSeguidos.get("artistas");
                    
                    for (String artista : artistasSeguidos) {
                        modeloArtistasSeguidos.addElement(artista);
                    }
                    jlist_artista_seguido.setModel(modeloArtistasSeguidos);
                    
                    //LIstas de rproduccion
                    List<DataLista> listasParticulares = sys.traerListasParticularesPorClientes(nickname);
                    DefaultListModel<String> modeloListasReproduccion = new DefaultListModel<>();
                    for (DataLista lista : listasParticulares) {
                        modeloListasReproduccion.addElement(lista.getNombre()); 
                    }
                    jlist_listas.setModel(modeloListasReproduccion);

                    
                    //preferencias del cliente
                    
                    // listas fav
                    List<DataLista> listasFavoritas = sys.traerListasFavoritasPorIdCliente(nickname);
                    DefaultListModel<String> modeloListasFav = new DefaultListModel<>();
                    for (DataLista lista : listasFavoritas) {
                        modeloListasFav.addElement(lista.getNombre());
                    }
                    jList_listaFav.setModel(modeloListasFav);

                    // albumes fav
                    List<DataAlbum> albumesFavoritos = sys.traerAlbumesFavoritosPorIdCliente(nickname);
                    //System.out.println("Álbumes favoritos del cliente: " + albumesFavoritos);
                    DefaultListModel<String> modeloAlbumFav = new DefaultListModel<>();
                    for (DataAlbum album : albumesFavoritos) {
                        modeloAlbumFav.addElement(album.getNombre());
                    }
                    jList_albumFav.setModel(modeloAlbumFav);

                    // temas fav
                    List<DataTema> temasFavoritos = sys.traerTemasFavoritosPorIdCliente(nickname);
                    //System.out.println("temasFavoritos del cliente: " + temasFavoritos);
                    DefaultListModel<String> modeloTemaFav = new DefaultListModel<>();
                    for (DataTema tema : temasFavoritos) {
                        modeloTemaFav.addElement(tema.getNombre());
                    }
                    jList_temaFav.setModel(modeloTemaFav);
                    
                    break;
                }
            }
        } catch (UsuarioNoExisteException e) {
            JOptionPane.showMessageDialog(null, "No se encontraron clientes registrados", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jComboBox_nicknameActionPerformed

    private void jButton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_salirActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable_info.getModel();
        model.setRowCount(0);
        
        jComboBox_nickname.setSelectedItem(null);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_salirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_salir;
    private javax.swing.JComboBox<String> jComboBox_nickname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_albumFav;
    private javax.swing.JLabel jLabel_artist;
    private javax.swing.JLabel jLabel_client;
    private javax.swing.JLabel jLabel_correo;
    private javax.swing.JLabel jLabel_imagen;
    private javax.swing.JLabel jLabel_listaFav;
    private javax.swing.JLabel jLabel_seguidores;
    private javax.swing.JLabel jLabel_temaFav;
    private javax.swing.JList<String> jList_albumFav;
    private javax.swing.JList<String> jList_listaFav;
    private javax.swing.JList<String> jList_temaFav;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable_info;
    private javax.swing.JList<String> jlist_artista_seguido;
    private javax.swing.JList<String> jlist_cliente_seguido;
    private javax.swing.JList<String> jlist_listas;
    private javax.swing.JList<String> list_seguidores;
    // End of variables declaration//GEN-END:variables
}
