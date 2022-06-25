import javafx.application.Application;
import javafx.stage.Stage;
import lk.ijse.hms.util.FactoryConfiguration;
import lk.ijse.hms.util.Navigations;
import org.hibernate.Session;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Navigations.getInstance().setNewStage("Login-Form");
    }
}
