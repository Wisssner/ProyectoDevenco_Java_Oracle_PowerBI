package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import Modelo.ExternoProy_Partida;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ExternoProy_PartidaDAO implements CRUD<ExternoProy_Partida>{
    
    //ConectarOracle conexion=new ConectarOracle();
    ConectarOracle conexion=ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;

    @Override
    public List listar() {
        List<ExternoProy_Partida> lista = new ArrayList<>();
        String sql="SELECT * FROM PPROY_PARTIDA order by CODCIA";
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                ExternoProy_Partida pm = new ExternoProy_Partida();
                pm.setCodCia(rs.getInt(1));
                pm.setCodPyto(rs.getInt(2));
                pm.setNroVersion(rs.getInt(3));
                pm.setIngEgr(rs.getString(4));
                pm.setCodPartida(rs.getInt(5));
                pm.setCodPartidas(rs.getString(6));
                pm.setFlgCC(rs.getString(7));
                pm.setNivel(rs.getInt(8));
                pm.setuUniMed(rs.getString(9));
                pm.setTabEstado(rs.getString(10));
                pm.setCodEstado(rs.getString(11));
                pm.setVigente(rs.getString(12).charAt(0));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        System.out.println("terminando la lista");
        return lista;
    }

    @Override
    public int add(ExternoProy_Partida p) {
        System.out.println("{call INSERTAR_PPROY_PARTIDA(?,?,?,?,?,?,?,?,?)}");
        try{
           con=conexion.conectar();
           myCall = con.prepareCall("{call INSERTAR_PPROY_PARTIDA(?,?,?,?,?,?,?,?,?)}");
           myCall.setInt(1, p.getCodPyto());
           myCall.setInt(2, p.getNroVersion());
           myCall.setInt(3, p.getCodCia());
           myCall.setString(4, p.getIngEgr());
           myCall.setInt(5, p.getCodPartida());
           myCall.setString(6, p.getCodPartidas());
           myCall.setString(7, p.getTabEstado());
           myCall.setString(8, p.getCodEstado());
           myCall.setString(9, String.valueOf(p.getVigente()));
           myCall.execute();
           myCall.close();
           con.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+ex.toString());
            System.out.println(ex.toString());
            return 0;
        }
       return 1;
    }

    @Override
    public int actualizar(ExternoProy_Partida p) {
        String sql1= "update PPROY_PARTIDA set NROVERSION=? where CODCIA=? AND CODPYTO=? AND INGEGR=? and CODPARTIDA=?";
        System.out.println(sql1);
        try{
           con=conexion.conectar();
           pst=con.prepareStatement(sql1);
           myCall.setInt(1, p.getNroVersion());
           myCall.setInt(2, p.getCodCia());
           myCall.setInt(3, p.getCodPyto());
           myCall.setString(4, p.getIngEgr());
           myCall.setInt(5, p.getCodPartida());
           pst.execute();
           pst.close();
           con.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+ex.toString());
            System.out.println(ex.toString());
            return 0;
        }
        return 1;
    }

    @Override
    public void eliminar(int id) {
       return;
    }
    
    public void eliminarDatos(int cia,int cod,String tip,int pyto,int ver) {
       String sql1="DELETE from PPROY_PARTIDA where CODCIA="+cia+" AND CODPARTIDA="+cod+" AND INGEGR='"+tip+"' AND CODPYTO="+pyto+" AND NROVERSION="+ver;
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql1);
            rs=ps.executeQuery(sql1);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "PProy_Partida eliminado con exito.");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
    }
    
    public ExternoProy_Partida listarId(int cia,int pyto,int ver,int cod,String tip){
        ExternoProy_Partida pm = new ExternoProy_Partida();
        String sql="SELECT CODPYTO,NROVERSION,CODPARTIDA,CODESTADO,VIGENTE FROM PPROY_PARTIDA WHERE CODCIA="+cia+" AND"
                + " CODPYTO="+pyto+" AND NROVERSION="+ver+" AND CODPARTIDA="+cod+" AND INGEGR='"+tip+"'";
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                pm.setCodPyto(rs.getInt(1));
                pm.setNroVersion(rs.getInt(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodEstado(rs.getString(4));
                pm.setVigente(rs.getString(5).charAt(0));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return pm;
    }  
    

    public List listarPorCodCia(int id, String tip){
        List<ExternoProy_Partida> lista = new ArrayList<>();
        String sql="SELECT "
                    + "CodPyto,NroVersion,CodPartida,CodEstado,Vigente "
                + "FROM PPROY_PARTIDA WHERE CODCIA="+id+" AND INGEGR='"+tip+"' order by CODPYTO";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                ExternoProy_Partida pm = new ExternoProy_Partida();
                pm.setCodPyto(rs.getInt(1));
                pm.setNroVersion(rs.getInt(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodEstado(rs.getString(4));
                pm.setVigente(rs.getString(5).charAt(0));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return lista;
    }
    
    public List listarPorCodCiav2(int id, String tip,int pyto){
        List<ExternoProy_Partida> lista = new ArrayList<>();
        String sql="SELECT "
                    + "CodPyto,NroVersion,CodPartida,CodEstado,Vigente "
                + "FROM PPROY_PARTIDA WHERE CODCIA="+id+" AND INGEGR='"+tip+"' AND CODPYTO="+pyto+" order by CODPYTO";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                ExternoProy_Partida pm = new ExternoProy_Partida();
                pm.setCodPyto(rs.getInt(1));
                pm.setNroVersion(rs.getInt(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodEstado(rs.getString(4));
                pm.setVigente(rs.getString(5).charAt(0));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return lista;
    }
    
    public List listarPorCodCia(int id, String tip,int pyto){
        List<ExternoProy_Partida> lista = new ArrayList<>();
        String sql="SELECT "
                    + "CodPyto,NroVersion,CodPartida,CodEstado,Vigente "
                + "FROM PPROY_PARTIDA WHERE CODCIA="+id+" AND INGEGR='"+tip+"' AND CODPYTO="+pyto+" order by CODPYTO";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                ExternoProy_Partida pm = new ExternoProy_Partida();
                pm.setCodPyto(rs.getInt(1));
                pm.setNroVersion(rs.getInt(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodEstado(rs.getString(4));
                pm.setVigente(rs.getString(5).charAt(0));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return lista;
    }
    
    public List listarPorCodPyto(int id){
        List<ExternoProy_Partida> lista = new ArrayList<>();
        String sql="SELECT "
                    + "CodPartida "
                + "FROM PPROY_PARTIDA WHERE CODPYTO="+id+" order by CODPARTIDA";
        System.out.println(sql);
        try{
            con=conexion.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            while(rs.next()){
                ExternoProy_Partida pm = new ExternoProy_Partida();
                pm.setCodPartida(rs.getInt(1));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return lista;
    }
    
    public ExternoProy_Partida listarId(String id){
        ExternoProy_Partida pyPart = new ExternoProy_Partida();
        String sql="SELECT DESPARTIDA,CODPARTIDA FROM PPROY_PARTIDA WHERE CODPARTIDA="+id+" order by CODPARTIDA";
        try{
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                //pyPart.setDesPartida(rs.getString(1));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Excepcion.\n"+e.toString());
            System.out.println(e.toString());
        }
        return pyPart;
    } 
}