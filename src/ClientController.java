import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ClientController {

    @FXML
    TextArea textArea_output, textArea_input;

    Client client;

    public void initialize() {
        client = new Client(textArea_input, textArea_output);
        Thread threadClient = new Thread(client);
        threadClient.start();
    }

    public void sendTekst(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            client.sendTekstTilServer(textArea_output);
            textArea_output.clear();
        }
    }
}
