package com.mycompany.peluqueriacanina.ui;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import com.mycompany.peluqueriacanina.ui.ImageLoader;

public class Principal extends javax.swing.JFrame {
    
    private static javax.swing.JFrame ventanaCargarDatos = null;

    public Principal() {
        initComponents();
        cargarImagenes();
    }
    
    private void cargarImagenes() {
        // Intentar múltiples nombres posibles para la imagen principal
        ImageIcon icon = ImageLoader.loadImage("golden_retriever_peluqueria.png");
        if (icon == null) {
            icon = ImageLoader.loadImage("LOGO_PELUQUERIA-removebg-preview.png");
        }
        if (icon == null) {
            // Buscar cualquier imagen que contenga "LOGO" o "PELUQUERIA"
            icon = ImageLoader.loadImage("LOGO_PELUQUERIA-removebg-preview.png");
        }
        if (icon != null) {
            jLabel3.setIcon(icon);
            jLabel3.setText(""); // Limpiar cualquier texto
            jLabel3.setVisible(true);
        } else {
            // Si no hay imagen, ocultar el label
            jLabel3.setIcon(null);
            jLabel3.setText("");
            jLabel3.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnCargaDatos = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 36)); // NOI18N
        jLabel1.setText("PELUQUERIA CANINA");

        jButton1.setText("VER DATOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnCargaDatos.setText("CARGAR DATOS");
        btnCargaDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargaDatosActionPerformed(evt);
            }
        });

        jButton3.setText("SALIR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addComponent(btnCargaDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(43, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(btnCargaDatos)
                    .addContainerGap(118, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel1)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargaDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargaDatosActionPerformed
        // Verificar si ya hay una ventana de CargarDatos abierta
        if (ventanaCargarDatos != null && ventanaCargarDatos.isDisplayable()) {
            ventanaCargarDatos.toFront(); // Traer al frente si ya está abierta
            return;
        }
        
        // Crear nueva ventana solo si no hay una abierta
        JFrame frame = new JFrame("Cargar Datos");
        CargarDatos pantalla = new CargarDatos(frame); // Pasar referencia del frame
        frame.setContentPane(pantalla);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Guardar referencia para controlar instancia única
        ventanaCargarDatos = frame;
        
        // Limpiar referencia cuando se cierre la ventana
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent evt) {
                ventanaCargarDatos = null;
            }
        });
    }//GEN-LAST:event_btnCargaDatosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        VerDatos verDatosFrame = null;
        
        try {
            verDatosFrame = new VerDatos();
        } catch (Throwable e) {
            System.err.println("ERROR CRÍTICO al crear VerDatos:");
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error al crear la ventana Ver Datos:\n\n" + 
                e.getClass().getSimpleName() + ": " + e.getMessage() + 
                "\n\nRevisa la consola para más detalles.",
                "Error Crítico",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        try {
            verDatosFrame.setLocationRelativeTo(null);
            verDatosFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            
            // Asegurar que la ventana tenga un tamaño adecuado
            if (verDatosFrame.getWidth() < 900) {
                verDatosFrame.setSize(900, 600);
            }
            
            verDatosFrame.setVisible(true);
            verDatosFrame.toFront();
            verDatosFrame.requestFocus();
        } catch (Exception e) {
            System.err.println("ERROR al mostrar VerDatos:");
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error al mostrar la ventana Ver Datos:\n\n" + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargaDatos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
