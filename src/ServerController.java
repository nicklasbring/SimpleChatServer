import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class ServerController {

    @FXML
    TextArea textArea_input, textArea_output;

    Server server;

    public void initialize() {
        server = new Server(textArea_input, textArea_output);
        Thread threadServer = new Thread(server);
        threadServer.start();
    }

    public void sendTekst(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            server.sendTekstTilKlient(textArea_output);
            textArea_output.clear();
        }
    }
}
