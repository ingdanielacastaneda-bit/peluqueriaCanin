package com.mycompany.peluqueriacanina.ui;

import com.mycompany.peluqueriacanina.service.MascotaService;
import com.mycompany.peluqueriacanina.model.Mascota;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;


public class VerDatos extends javax.swing.JFrame {
    
    private MascotaService mascotaService;

    public VerDatos() {
        // Inicializar servicio de forma segura
        try {
            this.mascotaService = new MascotaService();
        } catch (Exception e) {
            System.err.println("Error al inicializar MascotaService: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Inicializar componentes
        try {
            initComponents();
        } catch (Exception e) {
            System.err.println("Error al inicializar componentes: " + e.getMessage());
            e.printStackTrace();
            throw e; // Si no se pueden inicializar componentes, no continuar
        }
        
        // Cargar imágenes (no crítico)
        try {
            cargarImagenes();
        } catch (Exception e) {
            System.err.println("Error al cargar imágenes (no crítico): " + e.getMessage());
        }
        
        // Cargar tabla (puede fallar pero la ventana debe mostrarse)
        try {
            cargarTabla();
        } catch (Exception e) {
            System.err.println("Error al cargar tabla: " + e.getMessage());
            e.printStackTrace();
            // Crear tabla vacía en caso de error
            try {
                DefaultTableModel modeloTabla = new DefaultTableModel();
                String[] titulos = {"Num", "Nombre", "Color", "Raza", "Alergico", "At.Esp", "Dueño", "Cel", "Dirección"};
                modeloTabla.setColumnIdentifiers(titulos);
                if (tablaMascotas != null) {
                    tablaMascotas.setModel(modeloTabla);
                }
            } catch (Exception ex) {
                System.err.println("Error al crear tabla vacía: " + ex.getMessage());
            }
        }
    }
    
    private void cargarImagenes() {
        ImageIcon iconEditar = ImageLoader.loadImage("icono editar.png", 20, 20);
        if (iconEditar != null) {
            btnEditar.setIcon(iconEditar);
        }
        
        ImageIcon iconEliminar = ImageLoader.loadImage("icono eliminar.png", 20, 20);
        if (iconEliminar != null) {
            btnEliminar.setIcon(iconEliminar);
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMascotas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 1, 36)); // NOI18N
        jLabel2.setText("Visualizacion de datos");

        tablaMascotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaMascotas);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Datos de mascotas");

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(209, 209, 209))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        
        // Asegurar que la ventana tenga un tamaño mínimo visible
        if (getWidth() < 800) {
            setSize(800, 600);
        }
        if (getHeight() < 600) {
            setSize(Math.max(getWidth(), 800), 600);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tablaMascotas.getRowCount() == 0) {
            mostrarMensaje("No hay mascotas para editar en la tabla", "Error", "Error al editar");
            return;
        }
        
        if (tablaMascotas.getSelectedRow() < 0) {
            mostrarMensaje("No seleccionó ninguna mascota", "Error", "Error al editar");
            return;
        }
        
        try {
            int num_cliente = Integer.parseInt(String.valueOf(tablaMascotas.getValueAt(tablaMascotas.getSelectedRow(), 0)));
            
            ModificarDatos pantallaModif = new ModificarDatos(num_cliente);
            JDialog dialog = new JDialog(this, "Modificacion de datos", true);
            dialog.setContentPane(pantallaModif);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.pack();
            
            // Asegurar tamaño adecuado
            if (dialog.getWidth() < 800) {
                dialog.setSize(800, 600);
            }
            
            // Centrar en la pantalla
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            
            cargarTabla();
        } catch (NumberFormatException ex) {
            mostrarMensaje("Error al obtener el ID de la mascota", "Error", "Error al editar");
        } catch (Exception ex) {
            mostrarMensaje("Error al editar la mascota: " + ex.getMessage(), "Error", "Error al editar");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (tablaMascotas.getRowCount() == 0) {
            mostrarMensaje("No hay mascotas para eliminar en la tabla", "Error", "Error al eliminar");
            return;
        }
        
        if (tablaMascotas.getSelectedRow() < 0) {
            mostrarMensaje("No seleccionó ninguna mascota", "Error", "Error al eliminar");
            return;
        }
        
        try {
            int num_cliente = Integer.parseInt(String.valueOf(tablaMascotas.getValueAt(tablaMascotas.getSelectedRow(), 0)));
            
            int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar esta mascota?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                mascotaService.eliminarMascota(num_cliente);
                mostrarMensaje("Mascota eliminada correctamente", "Info", "Eliminación exitosa");
                cargarTabla();
            }
        } catch (NumberFormatException ex) {
            mostrarMensaje("Error al obtener el ID de la mascota", "Error", "Error al eliminar");
        } catch (Exception ex) {
            mostrarMensaje("Error al eliminar la mascota: " + ex.getMessage(), "Error", "Error al eliminar");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
 
    private void mostrarMensaje(String mensaje, String tipo, String titulo) {
        JOptionPane optionPane = new JOptionPane(mensaje);
        if (tipo.equals("Info")) {
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        } else if (tipo.equals("Error")) {
            optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
        }
        JDialog dialog = optionPane.createDialog(titulo);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        cargarTabla();
    }//GEN-LAST:event_formWindowOpened



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaMascotas;
    // End of variables declaration//GEN-END:variables

    public void cargarTabla() {
        try {
            DefaultTableModel modeloTabla = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            String[] titulos = {"Num", "Nombre", "Color", "Raza", "Alergico", "At.Esp", "Dueño", "Cel", "Dirección"};
            modeloTabla.setColumnIdentifiers(titulos);
            
            if (mascotaService == null) {
                tablaMascotas.setModel(modeloTabla);
                return;
            }
            
            List<Mascota> listaMascotas = mascotaService.obtenerTodasLasMascotas();
            
            if (listaMascotas != null && !listaMascotas.isEmpty()) {
                for (Mascota masco : listaMascotas) {
                    if (masco == null) {
                        continue;
                    }
                    
                    String nombreDuenio = "";
                    String celDuenio = "";
                    String direccion = "";
                    
                    if (masco.getUnDuenio() != null) {
                        nombreDuenio = masco.getUnDuenio().getNombre() != null ? masco.getUnDuenio().getNombre() : "";
                        celDuenio = masco.getUnDuenio().getCelDuenio() != null ? masco.getUnDuenio().getCelDuenio() : "";
                        direccion = masco.getUnDuenio().getDireccion() != null ? masco.getUnDuenio().getDireccion() : "";
                    }
                    
                    Object[] objeto = {
                        masco.getNum_cliente(),
                        masco.getNombre() != null ? masco.getNombre() : "",
                        masco.getColor() != null ? masco.getColor() : "",
                        masco.getRaza() != null ? masco.getRaza() : "",
                        masco.getAlergico() != null ? masco.getAlergico() : "",
                        masco.getAtencion_especial() != null ? masco.getAtencion_especial() : "",
                        nombreDuenio,
                        celDuenio,
                        direccion
                    };
                    
                    modeloTabla.addRow(objeto);
                }
            }
            
            tablaMascotas.setModel(modeloTabla);
        } catch (Exception e) {
            mostrarMensaje("Error al cargar los datos: " + e.getMessage(), "Error", "Error al cargar datos");
            DefaultTableModel modeloTabla = new DefaultTableModel();
            String[] titulos = {"Num", "Nombre", "Color", "Raza", "Alergico", "At.Esp", "Dueño", "Cel", "Dirección"};
            modeloTabla.setColumnIdentifiers(titulos);
            tablaMascotas.setModel(modeloTabla);
        }
    }
}

