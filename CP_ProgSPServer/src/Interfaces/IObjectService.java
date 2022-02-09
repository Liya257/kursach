package Interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IObjectService {
    ArrayList<?> showAll();
    void delete();
    int addInDatabase();
    void changeInDatabase();
}