package repository;

import model.Bar;
import model.DominoModel;

import java.util.List;

public interface DbRepository {
    void saveToDb(List<Bar> list);

    List<? extends DominoModel> getFromDb();
}