package celulares;

import java.sql.*;

public class Celulares {

    Conexion con;

    public Celulares() {
        con = new Conexion();
    }

    /*Añade un nuevo registro*/
    public boolean NuevoCelular(String marca, String modelo, String anioLanzamiento, String sistemaOperativo, String operadora, String observacion) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("insert into "
                    + "persona(marca, modelo, anioLanzamiento, sistemaOperativo, operadora, observacion) "
                    + " values(?,?,?,?,?,?)");
            pstm.setString(1, marca);
            pstm.setString(2, modelo);
            pstm.setString(3, anioLanzamiento);
            pstm.setString(4, sistemaOperativo);
            pstm.setString(5, operadora);
            pstm.setString(6, observacion);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
           return false;
        }
    }

    public boolean updatePersona(int id, String marca, String modelo, String anioLanzamiento, String sistemaOperativo,String operadora,String observacion) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("update persona "
                    + "set nombres = ? ,"
                    + "appPaterno = ? ,"
                    + "appMaterno = ? ,"
                    + "mail = ? ,"
                    + "edad = ? ,"
                    + "celular= ? " 
                    + "where id = ? ");
            pstm.setString(1, marca);
            pstm.setString(2, modelo);
            pstm.setString(3, anioLanzamiento);
            pstm.setString(4, sistemaOperativo);
            pstm.setString(5, operadora); 
            pstm.setString(6, observacion);
            pstm.setInt(7, id);//modifice
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteCelular(String cod) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("delete from persona where id = ?");
            pstm.setString(1, cod);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /*obtenemos todos los datos de la tabla*/
    public Object[][] obtenerCelular() {
        int numcel;
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(*) as total FROM celular ");
            ResultSet res = pstm.executeQuery();
            res.next();
            numcel = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            return null;
        }

        Object[][] celulares = new Object[numcel][7];
        //realizamos la consulta sql y llenamos los datos en "Object"
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT "
                    + "*"
                    + " FROM celular"
                    + " ORDER BY id ");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                String estCodigo = res.getString("id");
                String estNombre = res.getString("marca");
                String estpaterno = res.getString("modelo");
                String estmaterno = res.getString("anioLanzamiento");
                String estmail = res.getString("sistemaOperativo");
                String edad = res.getString("operadora");
                String observacion = res.getString("observacion");
                celulares[i][0] = estCodigo;
                celulares[i][1] = estNombre;
                celulares[i][2] = estpaterno;
                celulares[i][3] = estmaterno;
                celulares[i][4] = estmail;
                celulares[i][5] = edad;
                celulares[i][6] = observacion;
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return celulares;
    }
}
