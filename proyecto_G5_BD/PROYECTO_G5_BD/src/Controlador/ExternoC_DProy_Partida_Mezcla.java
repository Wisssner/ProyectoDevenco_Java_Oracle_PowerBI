package Controlador;

import Vistas.V_DProy_Partida_Mezcla;
import Modelo.DAO.DProyPartidaMezclaDAO;
import Modelo.DAO.ExternoDProyPartidaMezclaDAO;
import Modelo.DAO.ExternoProy_Partida_MezclaDAO;
import Modelo.DAO.Proy_Partida_MezclaDAO;
import Modelo.DAO.ProyectoDAO;
import Modelo.DProyPartidaMezcla;
import Modelo.ExternoDProyPartidaMezcla;
import Modelo.ExternoProy_Partida_Mezcla;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Proy_Partida_Mezcla;
import Modelo.Proyecto;
import Vistas.V_ExternoDProy_Partida_Mezcla;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class ExternoC_DProy_Partida_Mezcla implements ActionListener,MouseListener,ItemListener{
    
    V_ExternoDProy_Partida_Mezcla vpm=new V_ExternoDProy_Partida_Mezcla();
    ExternoDProyPartidaMezclaDAO dpmDAO = new ExternoDProyPartidaMezclaDAO();
    DefaultTableModel modelPartidaMezclaI = new DefaultTableModel();
    DefaultTableModel modelPartidaMezclaE = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorterI;
    TableRowSorter<DefaultTableModel> sorterE;
    
    public ExternoC_DProy_Partida_Mezcla(V_ExternoDProy_Partida_Mezcla vpm){
        this.vpm=vpm;
        this.vpm.btt_Descargar.addActionListener(this);
        this.vpm.btt_Actualizar_I.addActionListener(this);
        this.vpm.btt_Registrar_I.addActionListener(this);
        this.vpm.btt_Eliminar_I.addActionListener(this);
        this.vpm.tablaDProy_Partida_I.addMouseListener(this);
        this.vpm.codPyto_I.addItemListener(this);
        
        this.vpm.btt_Actualizar_E.addActionListener(this);
        this.vpm.btt_Registrar_E.addActionListener(this);
        this.vpm.btt_Eliminar_E.addActionListener(this);
        this.vpm.tablaDProy_Partida_E.addMouseListener(this);
        this.vpm.codPyto_E.addItemListener(this);
        init();
    }
    
    public void init(){
        initTablaPartidaMezcla_I();
        initTablaPartidaMezcla_E();
        vpm.init();
        initListarProyectos_I();
        initListarProyectos_E();
    }
    
    public void initListarProyectos_I(){
        vpm.codPyto_I.removeAllItems();
        List<Proyecto> listaPI = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<listaPI.size();i++){
            System.out.println(listaPI.get(i).getCodPyto());
            vpm.codPyto_I.addItem(listaPI.get(i).getCodPyto());
        }
    }
    
    public void initListarProyectos_E(){
        vpm.codPyto_E.removeAllItems();
        List<Proyecto> listaPE = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        for(int i=0;i<listaPE.size();i++){
            vpm.codPyto_E.addItem(listaPE.get(i).getCodPyto());
        }
    }
    public void initListarPartidas(int codpyto){
        vpm.codPartida_I.removeAllItems();
        vpm.codPartida_E.removeAllItems();
        List<ExternoProy_Partida_Mezcla> listaParI = new ExternoProy_Partida_MezclaDAO().listarCodPartida(varCodCiaGlobalDeLogin,"I",codpyto);
        List<ExternoProy_Partida_Mezcla> listaParE = new ExternoProy_Partida_MezclaDAO().listarCodPartida(varCodCiaGlobalDeLogin,"E",codpyto);
        new ExternoProy_Partida_MezclaDAO().eliminarCodPartida(listaParI,varCodCiaGlobalDeLogin,"I",codpyto);
        new ExternoProy_Partida_MezclaDAO().eliminarCodPartida(listaParE,varCodCiaGlobalDeLogin,"E",codpyto);
        
        for(int i=0;i<listaParI.size();i++){
            vpm.codPartida_I.addItem(listaParI.get(i).getCodPartida());
        }
        
        for(int i=0;i<listaParE.size();i++){
            vpm.codPartida_E.addItem(listaParE.get(i).getCodPartida());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DENTRO DE ACTION PDPROY_PARTIDA_MEZCLA");
        if(e.getSource()==vpm.btt_Registrar_I){
            registrarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vpm.btt_Actualizar_I){
            actualizarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vpm.btt_Eliminar_I){
            eliminarDatos("I");
            actualizarTabla();
        }
        if(e.getSource()==vpm.btt_Registrar_E){
            registrarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vpm.btt_Actualizar_E){
            actualizarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vpm.btt_Eliminar_E){
            eliminarDatos("E");
            actualizarTabla();
        }
        if(e.getSource()==vpm.btt_Descargar){
            descargarDatos();
        }
    }
    
    public void actualizarTabla(){
        limpiarTabla(modelPartidaMezclaI);
        limpiarTabla(modelPartidaMezclaE);
        initTablaPartidaMezcla_I();
        initTablaPartidaMezcla_E();
        System.out.println("Refrescando tabla automaticamente.");
    }
    
    public void initTablaPartidaMezcla_I(){
        List<ExternoDProyPartidaMezcla> listaI = new ExternoDProyPartidaMezclaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"I");
        modelPartidaMezclaI = (DefaultTableModel)vpm.tablaDProy_Partida_I.getModel();
        Object[] o=new Object[10];
        sorterI = new TableRowSorter<>(modelPartidaMezclaI);
        vpm.tablaDProy_Partida_I.setRowSorter(sorterI);
        limpiarTabla(modelPartidaMezclaI);
        for(int i=0;i<listaI.size();i++){
            o[0]=listaI.get(i).getCodPyto();
            o[1]=listaI.get(i).getNroVersion();
            o[2]=listaI.get(i).getCodPartida();
            o[3]=(listaI.get(i).geteDesembolso().equals("1"))?"Adelanto":"Pago";
            o[4]=listaI.get(i).getNroPago();
            if(listaI.get(i).geteCompPago().equals("1")){
                o[5]="Factura";}
            else{
                o[5]=(listaI.get(i).geteCompPago().equals("2"))?"RxH":"Voucher";}
            o[6]=listaI.get(i).getImpDesemNeto();
            o[7]=listaI.get(i).getImpDesemIgv();
            o[8]=listaI.get(i).getImpDesemTotal();
            o[9]=listaI.get(i).getSemilla();
            modelPartidaMezclaI.addRow(o);
        }
        vpm.tablaDProy_Partida_I.setModel(modelPartidaMezclaI);
    }
    
    public void initTablaPartidaMezcla_E(){
        List<ExternoDProyPartidaMezcla> listaE = new ExternoDProyPartidaMezclaDAO().listarPorCodCia(varCodCiaGlobalDeLogin,"E");
        modelPartidaMezclaE = (DefaultTableModel)vpm.tablaDProy_Partida_E.getModel();
        Object[] o=new Object[10];
        sorterI = new TableRowSorter<>(modelPartidaMezclaE);
        vpm.tablaDProy_Partida_E.setRowSorter(sorterE);
        limpiarTabla(modelPartidaMezclaE);
        for(int i=0;i<listaE.size();i++){
            o[0]=listaE.get(i).getCodPyto();
            o[1]=listaE.get(i).getNroVersion();
            o[2]=listaE.get(i).getCodPartida();
            o[3]=(listaE.get(i).geteDesembolso().equals("1"))?"Adelanto":"Pago";
            o[4]=listaE.get(i).getNroPago();
            if(listaE.get(i).geteCompPago().equals("1")){
                o[5]="Factura";}
            else{
                o[5]=(listaE.get(i).geteCompPago().equals("2"))?"RxH":"Voucher";}
            o[6]=listaE.get(i).getImpDesemNeto();
            o[7]=listaE.get(i).getImpDesemIgv();
            o[8]=listaE.get(i).getImpDesemTotal();
            o[9]=listaE.get(i).getSemilla();
            modelPartidaMezclaE.addRow(o);
        }
        vpm.tablaDProy_Partida_E.setModel(modelPartidaMezclaE);
    }
    
    public void limpiarTabla(DefaultTableModel model){
        for(int i = 0; i < model.getRowCount();i++){
            model.removeRow(i);
            i=i-1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         int fila,semilla;
         String fecha;
         java.util.Date date = new java.util.Date();
         SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
         if(e.getSource()==vpm.tablaDProy_Partida_I){
            fila = vpm.tablaDProy_Partida_I.getSelectedRow();
            semilla = Integer.parseInt(vpm.tablaDProy_Partida_I.getValueAt(fila,9).toString());
            ExternoDProyPartidaMezcla pmI = new ExternoDProyPartidaMezclaDAO().listarId(semilla,"I");
            vpm.codPartida_I.setSelectedItem(pmI.getCodPartida());
            vpm.codPyto_I.setSelectedItem(pmI.getCodPyto());
            vpm.desembolso_I.setSelectedItem(((pmI.geteDesembolso().equals("1"))?"Adelanto":"Pago"));
            fecha = f.format(pmI.getFecDesembolso());
            vpm.fecCompra_I.setText(fecha);
            if(pmI.geteDesembolso().equals("1"))
                vpm.comprobante_I.setSelectedItem("Factura");
            else
                vpm.comprobante_I.setSelectedItem(((pmI.geteDesembolso().equals("2"))?"RxH":"Voucher"));
            vpm.importeNeto_I.setValue(pmI.getImpDesemNeto());
            vpm.igv_I.setValue(pmI.getImpDesemIgv());
        }
        
        if(e.getSource()==vpm.tablaDProy_Partida_E){
            fila = vpm.tablaDProy_Partida_E.getSelectedRow();
            semilla = Integer.parseInt(vpm.tablaDProy_Partida_E.getValueAt(fila,9).toString());
            ExternoDProyPartidaMezcla pmE = new ExternoDProyPartidaMezclaDAO().listarId(semilla,"E");
            vpm.codPartida_E.setSelectedItem(pmE.getCodPartida());
            vpm.codPyto_E.setSelectedItem(pmE.getCodPyto());
            vpm.desembolso_E.setSelectedItem(((pmE.geteDesembolso().equals("1"))?"Adelanto":"Pago"));
            fecha = f.format(pmE.getFecDesembolso());
            vpm.fecCompra_E.setText(fecha);
            if(pmE.geteDesembolso().equals("1"))
                vpm.comprobante_E.setSelectedItem("Factura");
            else
                vpm.comprobante_E.setSelectedItem(((pmE.geteDesembolso().equals("2"))?"RxH":"Voucher"));
            vpm.importeNeto_E.setValue(pmE.getImpDesemNeto());
            vpm.igv_E.setValue(pmE.getImpDesemIgv());
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
        ExternoDProyPartidaMezcla pm = new ExternoDProyPartidaMezcla();
        Date fecha = null;
        if(tip == "I"){
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setCodPyto(Integer.parseInt(vpm.codPyto_I.getSelectedItem().toString()));
            pm.setIngEgr(tip);
            pm.setCodPartida(Integer.parseInt(vpm.codPartida_I.getSelectedItem().toString()));
            ExternoProy_Partida_Mezcla pmI = new ExternoProy_Partida_MezclaDAO().listarId2(varCodCiaGlobalDeLogin,"I",pm.getCodPartida(),pm.getCodPyto());
            
            pm.setNroVersion(pmI.getNroVersion());
            pm.setCorr(pmI.getCorr());
            
            if(vpm.comprobante_I.getSelectedItem().equals("Factura"))
                pm.seteCompPago("1");
            else
                pm.seteCompPago((vpm.comprobante_I.getSelectedItem().equals("RxH"))?"2":"3");
            
            pm.seteDesembolso((vpm.comprobante_I.getSelectedItem().equals("Adelanto"))?"1":"2");
            
            try {
                fecha = new SimpleDateFormat("dd/MM/yyyy").parse(vpm.fecCompra_I.getText());
            } catch (ParseException ex) {
                Logger.getLogger(C_Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }
            pm.setFecDesembolso(new java.sql.Date(fecha.getTime()));
            
            pm.setImpDesemNeto(Integer.parseInt(vpm.importeNeto_I.getValue().toString()));
            pm.setImpDesemIgv(Integer.parseInt(vpm.igv_I.getValue().toString()));
            
            pm.setImpDesemTotal(pm.getImpDesemNeto()+pm.getImpDesemIgv());
            
            if(dpmDAO.buscarSemilla(varCodCiaGlobalDeLogin,pm.getCodPartida(),"I") != 0){
                pm.setSemilla(dpmDAO.buscarSemilla(varCodCiaGlobalDeLogin,pm.getCodPartida(),"I"));
                pm.setRep(1);
            }
            else{
                pm.setSemilla(0);
                pm.setRep(0);
            }
        }
        else{
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setCodPyto(Integer.parseInt(vpm.codPyto_E.getSelectedItem().toString()));
            pm.setIngEgr(tip);
            pm.setCodPartida(Integer.parseInt(vpm.codPartida_E.getSelectedItem().toString()));
            ExternoProy_Partida_Mezcla pmE = new ExternoProy_Partida_MezclaDAO().listarId2(varCodCiaGlobalDeLogin,"E",pm.getCodPartida(),pm.getCodPyto());
            
            pm.setNroVersion(pmE.getNroVersion());
            pm.setCorr(pmE.getCorr());
            
            if(vpm.comprobante_E.getSelectedItem().equals("Factura"))
                pm.seteCompPago("1");
            else
                pm.seteCompPago((vpm.comprobante_E.getSelectedItem().equals("RxH"))?"2":"3");
            
            pm.seteDesembolso((vpm.comprobante_E.getSelectedItem().equals("Adelanto"))?"1":"2");
            
            try {
                fecha = new SimpleDateFormat("dd/MM/yyyy").parse(vpm.fecCompra_E.getText());
            } catch (ParseException ex) {
                Logger.getLogger(C_Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }
            pm.setFecDesembolso(new java.sql.Date(fecha.getTime()));
            
            pm.setImpDesemNeto(Integer.parseInt(vpm.importeNeto_E.getValue().toString()));
            pm.setImpDesemIgv(Integer.parseInt(vpm.igv_E.getValue().toString()));
            
            pm.setImpDesemTotal(pm.getImpDesemNeto()+pm.getImpDesemIgv());
            
            if(dpmDAO.buscarSemilla(varCodCiaGlobalDeLogin,pm.getCodPartida(),"E") != 0){
                pm.setSemilla(dpmDAO.buscarSemilla(varCodCiaGlobalDeLogin,pm.getCodPartida(),"E"));
                pm.setRep(1);
            }
            else{
                pm.setSemilla(0);
                pm.setRep(0);
            }
        }
        if(dpmDAO.add(pm)==0){
            showMessage2("PDProy_Partida_Mezcla registrado correctamente");
            vaciarCampos();
        }else{
            showMessage1("Error al registrar el DProy_Partida_Mezcla");
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
    //FALTA
    }
    
    public void eliminarDatos(String tip){
      return;
}

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==vpm.codPyto_I){
            if(vpm.codPyto_I.getSelectedIndex()!=-1){
                initListarPartidas(Integer.parseInt(vpm.codPyto_I.getSelectedItem().toString()));
            } else{
                vpm.codPyto_I.setSelectedItem(-1);
            }
        }
        
         if(e.getSource()==vpm.codPyto_E){
            if(vpm.codPyto_E.getSelectedIndex()!=-1){
                initListarPartidas(Integer.parseInt(vpm.codPyto_E.getSelectedItem().toString()));
            } else{
                vpm.codPyto_E.setSelectedItem(-1);
            }
        } 
    }
    
     public void vaciarCampos(){
        vpm.importeNeto_I.setModel(new SpinnerNumberModel(0,0,999999999,1000));
        vpm.importeNeto_E.setModel(new SpinnerNumberModel(0,0,999999999,1000));
        vpm.igv_I.setModel(new SpinnerNumberModel(0,0,999999999,1000));
        vpm.igv_E.setModel(new SpinnerNumberModel(0,0,999999999,1000));
    }
   public void descargarDatos() {
    try {
        // Nombre del archivo PDF que se generará
        String nombreArchivo = "reporte_Exter_PresupuestoDescPartidas.pdf";

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
        Paragraph title = new Paragraph("Reporte de Partidas")
                .setFont(boldFont)
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(title);

        // Agregar tabla para la parte I
        document.add(new Paragraph("Tabla Partida I")
                .setFont(boldFont)
                .setFontSize(14)
                .setMarginBottom(10));
        Table tableI = new Table(UnitValue.createPercentArray(vpm.tablaDProy_Partida_I.getColumnCount()));
        addTableHeaders(tableI, vpm.tablaDProy_Partida_I, boldFont, headerBgColor);
        addTableData(tableI, vpm.tablaDProy_Partida_I, normalFont, cellBgColor);
        document.add(tableI);

        // Agregar tabla para la parte E
        document.add(new Paragraph("Tabla Partida E")
                .setFont(boldFont)
                .setFontSize(14)
                .setMarginTop(20)
                .setMarginBottom(10));
        Table tableE = new Table(UnitValue.createPercentArray(vpm.tablaDProy_Partida_E.getColumnCount()));
        addTableHeaders(tableE, vpm.tablaDProy_Partida_E, boldFont, headerBgColor);
        addTableData(tableE, vpm.tablaDProy_Partida_E, normalFont, cellBgColor);
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
private void addTableHeaders(Table table, javax.swing.JTable jTable, PdfFont font, Color bgColor) {
    for (int i = 0; i < jTable.getColumnCount(); i++) {
        table.addHeaderCell(new Cell()
                .add(new Paragraph(jTable.getColumnName(i))
                .setFont(font)
                .setFontSize(12)
                .setFontColor(com.itextpdf.kernel.colors.ColorConstants.WHITE))
                .setBackgroundColor(bgColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE));
    }
}

// Método para agregar datos a la tabla
private void addTableData(Table table, javax.swing.JTable jTable, PdfFont font, Color bgColor) {
    for (int row = 0; row < jTable.getRowCount(); row++) {
        for (int col = 0; col < jTable.getColumnCount(); col++) {
            table.addCell(new Cell()
                    .add(new Paragraph(jTable.getValueAt(row, col).toString())
                    .setFont(font)
                    .setFontSize(10))
                    .setBackgroundColor(bgColor)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE));
        }
    }
}
     
 }
