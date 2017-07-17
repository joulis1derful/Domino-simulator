package repository;

import model.Bar;
import model.Chain;
import org.apache.log4j.Logger;
import service.ChainService;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChainRepository implements DbRepository {
    private static final Logger log = Logger.getLogger(ChainRepository.class);
    private Connection connection;
    private ChainService chainService;

    public ChainRepository() {
        connection = DbUtil.getConnection();
    }

    @Override
    public void saveToDb(List<Bar> chainBars) {
        try {
            chainService = new ChainService();
            String resultChain = chainService.convertChain(chainBars);
            PreparedStatement prepStatement = connection.prepareStatement
                    ("INSERT INTO results(result_trace) VALUES (?)");
            prepStatement.setString(1, resultChain);

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("SQL problems / syntax errors or check connection");
        }
    }

    @Override
    public List<Chain> getFromDb() {
        List<Chain> chains = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT * FROM results ORDER BY id DESC LIMIT 20 ");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String resultTrace = resultSet.getString(2);
                chains.add(new Chain(id, resultTrace));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("SQL problems / syntax errors. Check db connection");
        }
        return chains;
    }
}