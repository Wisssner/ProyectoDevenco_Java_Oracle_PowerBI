package Controlador;

import Modelo.DAO.ExternoPartidaDAO1;
import Modelo.DAO.PartidaDAO;
import Modelo.DAO.ExternoProy_PartidaDAO;
import Modelo.DAO.ProyectoDAO;
import Modelo.ExternoPartida;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Partida;
import Modelo.ExternoProy_Partida;
import Modelo.Proyecto;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import Vistas.V_ExternoProy_Partida;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;


public class ExternoC_Proy_Partida implements ActionListener, KeyListener, MouseListener{
    
    ExternoProy_PartidaDAO ppDAO = new ExternoProy_PartidaDAO();
    V_ExternoProy_Partida vpp = new V_ExternoProy_Partida();
    DefaultTableModel modelProy_PartidaI = new DefaultTableModel();
    DefaultTableModel modelProy_PartidaE = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorterI;
    TableRowSorter<DefaultTableModel> sorterE;
    
    public ExternoC_Proy_Partida(V_ExternoProy_Partida vpp){
        this.vpp=vpp;
        this.vpp.btt_Descargar.addActionListener(this);
        this.vpp.btt_Registrar_I.addActionListener(this);
        this.vpp.btt_Eliminar_I.addActionListener(this);
        this.vpp.tablaProy_Partida_I.addMouseListener(this);
        this.vpp.btt_Actualizar_E.addActionListener(this);
        this.vpp.btt_Registrar_E.addActionListener(this);
        this.vpp.btt_Eliminar_E.addActionListener(this);
        this.vpp.tablaProy_Partida_E.addMouseListener(this);
        this.vpp.actualizaTabla.addActionListener(this);
        this.vpp.nuevo.addActionListener(this);
        init();
    }
    
    public void init(){
        initTablaProy_Partida_I();
        initTablaProy_Partida_E();
        vpp.init();
        initListarPartidas();
        initListarProyectos_I();
        initListarProyectos_E();
    }
    
    public void initListarProyectos_I(){
        vpp.codPyto_I.removeAllItems();
        List<Proyecto> lista = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<lista.size();i++){
            vpp.codPyto_I.addItem(lista.get(i).getCodPyto());
        }
    }
    
    public void initListarProyectos_E(){
        vpp.codPyto_E.removeAllItems();
        List<Proyecto> lista = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<lista.size();i++){
            vpp.codPyto_E.addItem(lista.get(i).getCodPyto());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DENTRO DE ACTION PARTIDA");
        if(e.getSource()==vpp.btt_Registrar_I){
            registrarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vpp.btt_Descargar){
            descargarDatos();
        }
        if(e.getSource()==vpp.btt_Eliminar_I){
            eliminarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vpp.btt_Registrar_E){
            registrarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vpp.btt_Actualizar_E){
            actualizarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vpp.btt_Eliminar_E){
            eliminarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vpp.actualizaTabla){
            actualizarTabla();
        }
        if(e.getSource()==vpp.nuevo){
            vaciarCampos();
            actualizarTabla();
        }
    }
    
    public void initListarPartidas(){
        System.out.println("Partidas");
        vpp.codPartida_I.removeAllItems();
        vpp.codPartida_E.removeAllItems();
        List<ExternoPartida> listaParI = new ExternoPartidaDAO1().listarPorCodCia(varCodCiaGlobalDeLogin,"I");
        List<ExternoPartida> listaParE = new ExternoPartidaDAO1().listarPorCodCia(varCodCiaGlobalDeLogin,"E");
        for(int i=0;i<listaParI.size();i++){
            vpp.codPartida_I.addItem(listaParI.get(i).getCodPartida());
        }
        for(int i=0;i<listaParE.size();i++){
            vpp.codPartida_E.addItem(listaParE.get(i).getCodPartida());
        }
    }
    
    public void actualizarTabla(){
        limpiarTabla(modelProy_PartidaI);
        limpiarTabla(modelProy_PartidaE);
        initTablaProy_Partida_I();
        initTablaProy_Partida_E();
        System.out.println("Refrescando tabla automaticamente.");
    }
    
    public void initTablaProy_Partida_I(){
        List<ExternoProy_Partida> listaI = new ExternoProy_PartidaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"I");
        modelProy_PartidaI = (DefaultTableModel)vpp.tablaProy_Partida_I.getModel();
        Object[] o=new Object[5];
        sorterI = new TableRowSorter<>(modelProy_PartidaI);
        vpp.tablaProy_Partida_I.setRowSorter(sorterI);
        limpiarTabla(modelProy_PartidaI);
        for(int i=0;i<listaI.size();i++){
            o[0]=listaI.get(i).getCodPyto();
            o[1]=listaI.get(i).getNroVersion();
            o[2]=listaI.get(i).getCodPartida();
            if(listaI.get(i).getCodEstado().equals("1")){
                o[3]="Disponible";}
            else{
                o[3]=(listaI.get(i).getCodEstado().equals("2"))?"No Disp.":"Reservado";}
            o[4]=(listaI.get(i).getVigente())=='1'?"Si":"No";
            modelProy_PartidaI.addRow(o);
        }
        vpp.tablaProy_Partida_I.setModel(modelProy_PartidaI);
    }
    
    public void initTablaProy_Partida_E(){
        List<ExternoProy_Partida> listaE = new ExternoProy_PartidaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"E");
        modelProy_PartidaE = (DefaultTableModel)vpp.tablaProy_Partida_E.getModel();
        Object[] o=new Object[7];
        sorterE = new TableRowSorter<>(modelProy_PartidaE);
        vpp.tablaProy_Partida_E.setRowSorter(sorterE);
        limpiarTabla(modelProy_PartidaE);
        for(int i=0;i<listaE.size();i++){
            o[0]=listaE.get(i).getCodPyto();
            o[1]=listaE.get(i).getNroVersion();
            o[2]=listaE.get(i).getCodPartida();
             if(listaE.get(i).getCodEstado().equals("1")){
                o[3]="Disponible";}
            else{
                o[3]=(listaE.get(i).getCodEstado().equals("2"))?"No Disp.":"Reservado";}
            o[4]=(listaE.get(i).getVigente())=='1'?"Si":"No";
            modelProy_PartidaE.addRow(o);
        }
        vpp.tablaProy_Partida_E.setModel(modelProy_PartidaE);
    }
    
    public void limpiarTabla(DefaultTableModel model){
        for(int i = 0; i < model.getRowCount();i++){
            model.removeRow(i);
            i=i-1;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila,pyto,ver,cod;
        if(e.getSource()==vpp.tablaProy_Partida_I){
            fila = vpp.tablaProy_Partida_I.getSelectedRow();
            pyto = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,0).toString());
            ver = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,1).toString());
            cod = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,2).toString());
            System.out.println("PartidaMezcla = "+cod);
            ExternoProy_Partida pI = new ExternoProy_PartidaDAO().listarId(varCodCiaGlobalDeLogin,pyto,ver,cod,"I");
            vpp.codPyto_I.setSelectedItem(String.valueOf(pI.getCodPyto()));
            vpp.codPartida_I.setSelectedItem(String.valueOf(pI.getCodPartida()));  
            vpp.nroVersion_I.setValue(pI.getNroVersion());
        }
        
        if(e.getSource()==vpp.tablaProy_Partida_E){
            fila = vpp.tablaProy_Partida_E.getSelectedRow();
            pyto = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,0).toString());
            ver = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,1).toString());
            cod = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,2).toString());
            System.out.println("PartidaMezcla = "+cod);
            ExternoProy_Partida pE = new ExternoProy_PartidaDAO().listarId(varCodCiaGlobalDeLogin,pyto,ver,cod,"E");
            vpp.codPyto_E.setSelectedItem(pE.getCodPyto());
            vpp.codPartida_E.setSelectedItem(pE.getCodPartida());
            vpp.nroVersion_E.setValue(pE.getNroVersion());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
         return;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
       return;
    }
    
    public void registrarDatos(String tip){
        ExternoProy_Partida pm = new ExternoProy_Partida();
        if(tip == "I"){
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);
            pm.setCodPyto(Integer.parseInt(vpp.codPyto_I.getSelectedItem().toString()));
            pm.setCodPartida(Integer.parseInt(vpp.codPartida_I.getSelectedItem().toString()));
            pm.setNroVersion(Integer.parseInt(vpp.nroVersion_I.getValue().toString()));
            pm.setTabEstado("-1");
            pm.setCodEstado("1");
            ExternoPartida p = new ExternoPartidaDAO1().listarId(varCodCiaGlobalDeLogin,pm.getCodPartida(),"I");
            pm.setCodPartidas(p.getCodPartidas());
            pm.setVigente(p.getVigente());
        }
        else{
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);
            pm.setCodPyto(Integer.parseInt(vpp.codPyto_E.getSelectedItem().toString()));
            pm.setCodPartida(Integer.parseInt(vpp.codPartida_E.getSelectedItem().toString()));
            pm.setNroVersion(Integer.parseInt(vpp.nroVersion_E.getValue().toString()));
            pm.setTabEstado("-1");
            pm.setCodEstado("1");
            ExternoPartida p = new ExternoPartidaDAO1().listarId(varCodCiaGlobalDeLogin,pm.getCodPartida(),"E");
            pm.setCodPartidas(p.getCodPartidas());
            pm.setVigente(p.getVigente());
        }
        if(ppDAO.add(pm)==1){
            showMessage2("Proy_Partida registrado correctamente");
            vaciarCampos();
        }else{
            showMessage1("Error al registrar Proy_Partida");
        }
    }
    
    private boolean showMessage1(String message){
        Mensaje1 obj = new Mensaje1(Frame.getFrames()[1],true);
        obj.showMessage(message);
        return obj.isAceptar();
    }
    
    private boolean showMessage2(String message) {
        Mensaje2 obj = new Mensaje2(Frame.getFrames()[1], true);
        obj.showMessage(message);
        return obj.isAceptar();
    }
    
    public void actualizarDatos(String tip){
        int fila,pyto,cod;
        char vig;
        ExternoProy_Partida pm = new ExternoProy_Partida();
        if(tip == "I"){
            fila = vpp.tablaProy_Partida_I.getSelectedRow();
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,0).toString());
                cod = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,2).toString());
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setCodPyto(pyto);
                pm.setNroVersion(Integer.parseInt(vpp.nroVersion_I.getValue().toString()));
                if(ppDAO.actualizar(pm)==1){
                    showMessage2("Proy_Partida registrado correctamente");
                    vaciarCampos();
                }else{
                    showMessage1("Error al registrar Proy_Partida");
                }
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
        else{
            fila = vpp.tablaProy_Partida_E.getSelectedRow();
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,0).toString());
                cod = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,2).toString());
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setCodPyto(pyto);
                pm.setNroVersion(Integer.parseInt(vpp.nroVersion_E.getValue().toString()));
                if(ppDAO.actualizar(pm)==1){
                    showMessage2("Proy_Partida registrado correctamente");
                    vaciarCampos();
                }else{
                    showMessage1("Error al registrar Proy_Partida");
                }
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
    }
    
    public void eliminarDatos(String tip){
        int fila,cod,pyto,ver;
        if(tip=="I"){
            fila = vpp.tablaProy_Partida_I.getSelectedRow();
            System.out.println("La fila es"+fila);
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,0).toString());
                ver = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,1).toString());
                cod = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila,2).toString());
                ppDAO.eliminarDatos(varCodCiaGlobalDeLogin,cod,tip,pyto,ver);
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
        else{
            fila = vpp.tablaProy_Partida_E.getSelectedRow();
            System.out.println("La fila es"+fila);
            if(fila!=-1){
                System.out.println("Hay filas seleccionadas.");
                pyto = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,0).toString());
                ver = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,1).toString());
                cod = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila,2).toString());
                ppDAO.eliminarDatos(varCodCiaGlobalDeLogin,cod,tip,pyto,ver);
            }else{
                showMessage1("Debe seleccionar una fila");
            }
        }
    }
    
    public void vaciarCampos(){
        vpp.nroVersion_E.setValue(1);
        vpp.nroVersion_I.setValue(1);
        vpp.nroVersion_I.setModel(new SpinnerNumberModel(1,1,9,1));
        
        vpp.nroVersion_E.setModel(new SpinnerNumberModel(1,1,9,1));
    }
    
            
    public void descargarDatos() {
    try {
        // Ruta y nombre del archivo PDF que deseas generar
        String nombreArchivo = "reporte_ExternoProyectos_partidas.pdf";

        // Inicialización del PdfWriter
        PdfWriter writer = new PdfWriter(nombreArchivo);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Fuentes y colores
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont normalFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        Color headerBgColor = new DeviceRgb(63, 169, 219); // Color de fondo de la cabecera
        Color cellBgColor = new DeviceRgb(220, 230, 241); // Color de fondo de las celdas

        // Agregar título al documento
        Paragraph title = new Paragraph("Reporte de Proyectos y Partidas Externo")
                .setFont(boldFont)
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(title);

        // Agregar tabla para Proy_Partida_I
        document.add(new Paragraph("Tabla de Proyectos y Partidas I")
                .setFont(boldFont)
                .setFontSize(14)
                .setMarginBottom(10));
        Table tableI = new Table(UnitValue.createPercentArray(modelProy_PartidaI.getColumnCount()));
        addTableHeaders(tableI, modelProy_PartidaI, boldFont, headerBgColor);
        addTableData(tableI, modelProy_PartidaI, normalFont, cellBgColor);
        document.add(tableI);

        // Agregar tabla para Proy_Partida_E
        document.add(new Paragraph("Tabla de Proyectos y Partidas E")
                .setFont(boldFont)
                .setFontSize(14)
                .setMarginTop(20)
                .setMarginBottom(10));
        Table tableE = new Table(UnitValue.createPercentArray(modelProy_PartidaE.getColumnCount()));
        addTableHeaders(tableE, modelProy_PartidaE, boldFont, headerBgColor);
        addTableData(tableE, modelProy_PartidaE, normalFont, cellBgColor);
        document.add(tableE);

        // Cerrar el documento
        document.close();

        // Mensaje de éxito
        showMessage2("Descarga de PDF exitosa. Se ha generado el archivo '" + nombreArchivo + "'.");
    } catch (IOException ex) {
        showMessage1("Error al generar el PDF: " + ex.getMessage());
    }
}

// Método para agregar encabezados de la tabla
private void addTableHeaders(Table table, DefaultTableModel model, PdfFont font, Color bgColor) {
    for (int i = 0; i < model.getColumnCount(); i++) {
        table.addHeaderCell(new Cell()
                .add(new Paragraph(model.getColumnName(i))
                .setFont(font)
                .setFontSize(12)
                .setFontColor(com.itextpdf.kernel.colors.ColorConstants.WHITE))
                .setBackgroundColor(bgColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE));
    }
}

// Método para agregar datos a la tabla
private void addTableData(Table table, DefaultTableModel model, PdfFont font, Color bgColor) {
    for (int row = 0; row < model.getRowCount(); row++) {
        for (int col = 0; col < model.getColumnCount(); col++) {
            table.addCell(new Cell()
                    .add(new Paragraph(model.getValueAt(row, col).toString())
                    .setFont(font)
                    .setFontSize(10))
                    .setBackgroundColor(bgColor)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE));
        }
    }
}        
        
}