import javafx.application.Application;
import javafx.stage.Stage;
import lk.ijse.hms.util.FactoryConfiguration;
import org.hibernate.Session;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            Session session = FactoryConfiguration.getInstance().getSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
