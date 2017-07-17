package repository;

import model.Bar;
import org.apache.log4j.Logger;
import util.DbUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BarRepository implements DbRepository {
    private static final Logger log = Logger.getLogger(BarRepository.class);
    private Connection dbConnection;

    public BarRepository() {
        dbConnection = DbUtil.getConnection();
    }

    @Override
    public void saveToDb(List<Bar> userBars) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(userBars);
            oos.flush();
            byte[] data = bos.toByteArray();

            PreparedStatement prepStatement = dbConnection.prepareStatement
                    ("insert into userdominoes(userBars) values (?)");
            prepStatement.setObject(1, data);

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQL problems / syntax errors. Check db connection");
        } catch (IOException e) {
            log.error("Can't serialize object");
        } finally {
            close(bos);
            close(oos);
        }
    }

    @Override
    public List<Bar> getFromDb() {
        List<Bar> bars = new ArrayList<>();
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            PreparedStatement ps = dbConnection.prepareStatement
                    ("SELECT * FROM userdominoes");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                bais = new ByteArrayInputStream(resultSet.getBytes("userBars"));
                ois = new ObjectInputStream(bais);
                bars = (List<Bar>) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            log.error("Can't find or read serialized object");
        } catch (SQLException e) {
            log.error("SQL problems / syntax errors. Check db connection");
        } finally {
            close(bais);
            close(ois);
        }
        return bars;
    }

    private void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            log.error("Can't close file io stream");
        }
    }

}
