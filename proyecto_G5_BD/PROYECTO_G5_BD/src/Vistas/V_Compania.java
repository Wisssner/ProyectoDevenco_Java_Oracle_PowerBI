
package Vistas;

import Modelo.Interface.SearchOptinEvent;
import Modelo.SearchOption;
import javax.swing.ImageIcon;


public class V_Compania extends javax.swing.JPanel {
    public int opt=0;
    public V_Compania() {
        initComponents();
        setOpaque(false);
    }
    
    public void init(){
        tablaCia.fixTable(jScrollPane1);
        txtBusqueda.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                opt=index;
                txtBusqueda.setHint("Buscar " + option.getName() + "...");
            }
        });
        txtBusqueda.addOption(new SearchOption("todo", new ImageIcon(getClass().getResource("/image/loadall.png"))));
        txtBusqueda.addOption(new SearchOption("por descripción", new ImageIcon(getClass().getResource("/image/document.png"))));
        txtBusqueda.addOption(new SearchOption("por desc. Corta", new ImageIcon(getClass().getResource("/image/ruc.png"))));
    }
    public void inhabilitarCampos(){
        desCia.setEnabled(false);
        desCorta.setEnabled(false);
        vigente.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        pictureBox1 = new Modelo.Design.PictureBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelShadow2 = new Modelo.Design.PanelShadow();
        textAreaScroll1 = new Modelo.Design.TextAreaScroll();
        desCia = new Modelo.Design.TextArea();
        textAreaScroll5 = new Modelo.Design.TextAreaScroll();
        desCorta = new Modelo.Design.TextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCia = new Modelo.DesignTable.Tabla();
        btt_Registrar = new Modelo.Design.Button();
        btt_Eliminar = new Modelo.Design.Button();
        btt_Actualizar = new Modelo.Design.Button();
        vigente = new Modelo.Design.Combobox();
        txtBusqueda = new Modelo.Design.TextFieldSearchOption();

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/compania.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("¡Registre la compañía!");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 179, 255));
        jLabel4.setText("Complete la información de la compañía");

        textAreaScroll1.setLabelText("Descripción Compania:");

        desCia.setBorder(null);
        desCia.setColumns(20);
        desCia.setForeground(new java.awt.Color(40, 40, 40));
        desCia.setRows(5);
        textAreaScroll1.setViewportView(desCia);

        textAreaScroll5.setLabelText("Descripción Corta:");

        desCorta.setBorder(null);
        desCorta.setColumns(20);
        desCorta.setForeground(new java.awt.Color(40, 40, 40));
        desCorta.setRows(5);
        textAreaScroll5.setViewportView(desCorta);

        tablaCia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodCia", "Descripción", "DescripciónCorta", "Vigente"
            }
        ));
        jScrollPane1.setViewportView(tablaCia);

        btt_Registrar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Registrar.setText("Registrar");

        btt_Eliminar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Eliminar.setText("Eliminar");

        btt_Actualizar.setBackground(new java.awt.Color(240, 240, 240));
        btt_Actualizar.setText("Actualizar");

        vigente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vigente", "No Vigente" }));
        vigente.setSelectedIndex(-1);
        vigente.setLabeText("Estado:");

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vigente, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(textAreaScroll5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btt_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(btt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(btt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btt_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelShadow2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(textAreaScroll5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(vigente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panelShadow2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel4)))
                .addGap(32, 32, 32)
                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Modelo.Design.Button btt_Actualizar;
    public Modelo.Design.Button btt_Eliminar;
    public Modelo.Design.Button btt_Registrar;
    private com.raven.datechooser.DateChooser dateChooser1;
    public Modelo.Design.TextArea desCia;
    public Modelo.Design.TextArea desCorta;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JScrollPane jScrollPane1;
    private Modelo.Design.PanelShadow panelShadow2;
    private Modelo.Design.PictureBox pictureBox1;
    public Modelo.DesignTable.Tabla tablaCia;
    private Modelo.Design.TextAreaScroll textAreaScroll1;
    private Modelo.Design.TextAreaScroll textAreaScroll5;
    public Modelo.Design.TextFieldSearchOption txtBusqueda;
    public Modelo.Design.Combobox vigente;
    // End of variables declaration//GEN-END:variables
}
