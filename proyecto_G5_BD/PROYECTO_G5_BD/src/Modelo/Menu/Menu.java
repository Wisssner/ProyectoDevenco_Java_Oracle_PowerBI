package Modelo.Menu;

import Modelo.Design.ScrollBarCustom;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import static Vistas.V_Login.varNombreCiaGlobalDeLogin;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

public class Menu extends javax.swing.JPanel {

    public int getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(int selectedLocation) {
        this.selectedLocation = selectedLocation;
        repaint();
    }

    public void addEventMenu(EventMenu event) {
        this.events.add(event);
    }
    
    //agregado
    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    //private int selectedIndex = 0;
    private Animator animator;
    private TimingTarget target;
    private int selectedLocation = 151;
    private int targetLocation;
    private List<EventMenu> events = new ArrayList<>();

    public final MigLayout layout;
    public EventMenuSelected event;
    public EventShowPopupMenu eventShowPopup;
    public boolean enableMenu = true;
    public boolean showMenu = true;
    
    public Menu() {
        initComponents();
        jLabel1.setText(varNombreCiaGlobalDeLogin);
        jLabel2.setText("Cod. Compañia: "+varCodCiaGlobalDeLogin);
        setOpaque(false);
        setBackground(new Color(54,87,185)); /*CAMBIAR COLORES DEL Menu*/
        sp.getViewport().setOpaque(false);
        sp.setBorder(null);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout=new MigLayout("wrap, fillx, inset 0", "[fill]", "[]0[]");//"wrap, fillx, insets 0", "[fill]", "[]0[]"
        menu.setLayout(layout);
    }
    
    public void initMenuItem(){
        /*Anteriores Menu*/
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/1_s.png")),"Inicio", "Principal"));
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/6_s.png")), "Maestros", "Cliente","Empleado","Empresa Venta","Proveedor"));
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/8_s.png")), "Partidas", "Partidas","Configuración Partidas"));  
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/8_s.png")), "P.Partidas", "Partidas", "Configuracion Partidas"));/*NUEVO MENU P.PARTIDAS*/
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/9_s.png")), "Proyectos", "Consulta Proyectos","Partidas Proyectos","Config. Part. Proy."));
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/9_s.png")), "P.Proyectos", "Consulta Proyectos","Partidas Proyectos","Config. Part. Proy."));/*Nuevo Menu - P.PROYECTOS*/
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/5_s.png")), "Presupuesto", "Ingreso-Egreso"));
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/5_s.png")), "P.Presupuesto", "Ingreso-Egreso"));/*NUEVO MENU - P.PRESUPUESTO*/
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/3_s.png")), "Ventas", "Ingreso"));
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/11_s.png")), "Egresos", "Pagos"));
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/12_s.png")), "Flujo de Caja", "Flujo de caja"));
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/12_s.png")), "P.Flujo de Caja", "Flujo de caja"));/*NUEVO MENU -  P.FLUJO CAJA*/
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/4_s.png")), "Administrador", "Compañia"));
        addMenu(new ModeloMenu(new ImageIcon(getClass().getResource("/image/7_s.png")), "Configuración", "General"));
        
    
    }
    
    public void addMenu(ModeloMenu modmenu) {
        // Verifica si el menú es "Partidas Externo"
        if ("Partidas Externo".equals(modmenu.getMenuName())) {
            // Establece el color de fondo personalizado (en este caso, rojo)
            modmenu.setCustomBackgroundColor(Color.RED);
            System.out.println("Entró al if para Partidas Externo");
        }
        menu.add(new MenuItem(modmenu, getEventMenu(), event, menu.getComponentCount()), "h 40!");
    }
    //agregado
    public EventMenu getEventMenu(){
        return (Component com, boolean open) -> {
            if(enableMenu){
                if(showMenu){
                    if(open){
                        new MenuAnimacion(layout,com).openMenu();
                    }else{
                        new MenuAnimacion(layout,com).closeMenu();
                    }
                    return true;
                }else{
                    eventShowPopup.showPopup(com);
                }
            }
            return false;
        };
    }
    //agregado
    public void ocultarMenu(){
        for(Component com : menu.getComponents()){
            MenuItem item=(MenuItem) com;
            if(item.isOpen()){
                new MenuAnimacion(layout, com,500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //int y = selectedLocation;
        g2.setColor(new Color(54,87,185));
        //g2.fill(createShape(y));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        super.paintComponent(grphcs);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        imageAvatar1 = new Modelo.Design.ImageAvatar();
        sp = new javax.swing.JScrollPane();
        menu = new javax.swing.JPanel();

        jLabel1.setBackground(new java.awt.Color(118, 193, 67));
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(237, 237, 237));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TOTTUS");

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(200, 200, 200));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Admin");

        imageAvatar1.setBackground(new java.awt.Color(255, 0, 255));
        imageAvatar1.setForeground(new java.awt.Color(240, 240, 240));
        imageAvatar1.setGradientColor1(new java.awt.Color(235, 235, 235));
        imageAvatar1.setGradientColor2(new java.awt.Color(255, 255, 255));
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/tottus-mobile.png"))); // NOI18N

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menu.setOpaque(false);

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
        );

        sp.setViewportView(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .addComponent(imageAvatar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(sp))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Modelo.Design.ImageAvatar imageAvatar1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JPanel menu;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
