/*
 * DlgTurista.java
 * Creada el 12/04/2018
 */
package UI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import Entidades.Encargado;
//import javax.swing.DefaultComboBoxModel;

/**
 * Esta clase es una ventana de dialogo que muestra sus funciones segun la
 * opcion seleccionada.
 *
 * @author Jesús González
 */
public class DlgEncargado extends javax.swing.JDialog {

    /**
     * Ventana de dialogo para la operación seleccionada a realizar en un
     * turista
     *
     * @param parent Ventana sobre la que aparecerá el cuadro de diálogo
     * @param title Título del cuadro de diálogo
     * @param modal true si permite acceder fuera de los límites del cuadro de
     * diálogo, false en caso contrario
     * @param encargado encargado a agregar, editar o eliminar
     * @param operacion Operación a realizar en el cuadro de diálogo: AGREGAR =
     * 0, ACTUALIZAR = 1, ELIMINAR = 2, DESPLEGAR = 3;
     * @param respuesta Boton presionado al salir de los cuadros de * diálogos:
     * ACEPTAR = "Aceptar", CANCELAR = "Cancelar".
     */
    public DlgEncargado(java.awt.Frame parent, String title, boolean modal, Encargado encargado, int operacion, StringBuffer respuesta) {//Poner el combox en el parametro tambn
        super(parent, title, modal);
        initComponents();
        this.encargado = encargado;
        this.operacion = operacion;
        this.respuesta = respuesta;
        //this.listaTuristas = listaTuristas;

        if (operacion == ConstantesGUI.AGREGAR) {
            botonAceptar.setText("Guardar");
        } else if (operacion == ConstantesGUI.ACTUALIZAR) {
            botonAceptar.setText("Actualizar");
        } else if (operacion == ConstantesGUI.ELIMINAR) {
            botonAceptar.setText("Eliminar");
            botonRetaurar.setEnabled(false);
        } else if (operacion == ConstantesGUI.DESPLEGAR) {
            botonAceptar.setText("Continuar");
            botonRetaurar.setEnabled(false);
            botonCancelar.setEnabled(false);
        }

        tf_idencargado.setText(String.valueOf(encargado.getIDEncargado()));

        if (operacion == ConstantesGUI.ELIMINAR || operacion == ConstantesGUI.ACTUALIZAR
                || operacion == ConstantesGUI.DESPLEGAR) {
            tf_idencargado.setText(String.valueOf(encargado.getIDEncargado()));
            tf_nombre.setText(encargado.getNombre());
            cb_materia.setSelectedItem(encargado.getMateria());
            cb_nivel.setSelectedItem(encargado.getNivel());
        }

        if (operacion == ConstantesGUI.ELIMINAR || operacion == ConstantesGUI.DESPLEGAR) {
            tf_idencargado.setEditable(false);
            tf_nombre.setEditable(false);
            cb_materia.setEnabled(false);
            cb_nivel.setEnabled(false);
        }

        if (operacion == ConstantesGUI.AGREGAR) {
            tf_idencargado.setEditable(false);
        }

        respuesta.append(ConstantesGUI.CANCELAR);

        centraCuadroDialogo(parent);

        setVisible(true);
    }

    public void centraCuadroDialogo(java.awt.Frame parent) {
        Dimension frameSize = parent.getSize();
        Point loc = parent.getLocation();
        Dimension dlgSize = getPreferredSize();
        setLocation((frameSize.width - dlgSize.width) / 2 + loc.x, (frameSize.height - dlgSize.height) / 2 + loc.y);
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
        tf_idencargado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tf_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        botonAceptar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        botonRetaurar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cb_materia = new javax.swing.JComboBox<>();
        cb_nivel = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("ID:");

        tf_idencargado.setColumns(15);
        tf_idencargado.setToolTipText("Curp del cliente");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        tf_nombre.setColumns(20);
        tf_nombre.setToolTipText("Nombre del cliente");
        tf_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_nombreKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Materia:");

        botonAceptar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        botonAceptar.setText("Aceptar");
        botonAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        botonCancelar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonRetaurar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        botonRetaurar.setText("Restaurar");
        botonRetaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRetaurarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Nivel:");

        cb_materia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cb_nivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(botonAceptar)
                .addGap(33, 33, 33)
                .addComponent(botonRetaurar)
                .addGap(35, 35, 35)
                .addComponent(botonCancelar)
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tf_idencargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                        .addComponent(cb_materia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cb_nivel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(tf_idencargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cb_materia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_nivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonRetaurar)
                    .addComponent(botonCancelar)
                    .addComponent(botonAceptar))
                .addGap(58, 58, 58))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonRetaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRetaurarActionPerformed
        if (operacion == ConstantesGUI.AGREGAR) {
            //campoTextoCurp.setText("");
            tf_nombre.setText("");
            cb_materia.setSelectedIndex(0);
            cb_nivel.setSelectedIndex(0);
        }

        if (operacion == ConstantesGUI.ACTUALIZAR) {
            tf_idencargado.setText(String.valueOf(encargado.getIDEncargado()));
            tf_nombre.setText(encargado.getNombre());
            cb_materia.setSelectedItem(encargado.getMateria());
            cb_nivel.setSelectedItem(encargado.getNivel());
        }
    }//GEN-LAST:event_botonRetaurarActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        if ((operacion == ConstantesGUI.AGREGAR) || operacion == ConstantesGUI.ACTUALIZAR) {
            encargado.setIDEncargado(Integer.parseInt(tf_idencargado.getText()));
            encargado.setNombre(tf_nombre.getText());
            encargado.setMateria((String) cb_materia.getSelectedItem());
            encargado.setNivel((String) cb_nivel.getSelectedItem());
        }
        respuesta.delete(0, respuesta.length());
        respuesta.append(ConstantesGUI.ACEPTAR);
        this.dispose();
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void tf_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nombreKeyTyped

        char c = evt.getKeyChar();
        int k = (int) evt.getKeyChar();
        if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE) {
            evt.consume();
        }
//
//        if (Character.isDigit(c)) {
//            getToolkit().beep();
//            evt.consume();
//            JOptionPane.showMessageDialog(rootPane, "Ingresar solo letras ");
//        }        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nombreKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonRetaurar;
    private javax.swing.JComboBox<String> cb_materia;
    private javax.swing.JComboBox<String> cb_nivel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField tf_idencargado;
    private javax.swing.JTextField tf_nombre;
    // End of variables declaration//GEN-END:variables
    private Encargado encargado;
    private int operacion;
    private StringBuffer respuesta;

}
