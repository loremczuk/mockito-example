package pl.oremczuk.mockito;

public class Service {

    private Database database;

    public Service (Database database) {
        this.database = database;
    }

    public boolean query (String query) {
        return database.isConnected();
    }


}
