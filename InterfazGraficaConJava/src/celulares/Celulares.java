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
                    + "celular(marca, modelo, anioLanzamiento, sistemaOperativo, operadora, observacion) "
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

    public boolean updateCelular(int id, String marca, String modelo, String anioLanzamiento, String sistemaOperativo,String operadora,String observacion) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("update celular "
                    + "set marca = ? ,"
                    + "modelo = ? ,"
                    + "anioLanzamiento = ? ,"
                    + "sistemaOperativo = ? ,"
                    + "operadora = ? ,"
                    + "observacion= ? " 
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
            PreparedStatement pstm = con.getConnection().prepareStatement("delete from celular where id = ?");
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
                String estMarca = res.getString("marca");
                String estModelo = res.getString("modelo");
                String estAnioLanzamiento = res.getString("anioLanzamiento");
                String estSistemaOperativo = res.getString("sistemaOperativo");
                String estOperadora = res.getString("operadora");
                String estObservacion = res.getString("observacion");
                celulares[i][0] = estCodigo;
                celulares[i][1] = estMarca;
                celulares[i][2] = estModelo;
                celulares[i][3] = estAnioLanzamiento;
                celulares[i][4] = estSistemaOperativo;
                celulares[i][5] = estOperadora;
                celulares[i][6] = estObservacion;
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return celulares;
    }
}
