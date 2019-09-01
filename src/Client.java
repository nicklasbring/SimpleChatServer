import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {

    private Socket socket;
    private int port = 8000;
    private String serverIP = "localhost";
    private TextArea textArea_input;
    private TextArea textArea_output;
    private DataInputStream input;
    private DataOutputStream output;


    public Client(TextArea textArea, TextArea textArea_output) {
        this.textArea_input = textArea;
        this.textArea_output = textArea_output;
    }

    @Override
    public void run() {
        try {
            //Anmoder om adgang til serveren på denne pc på port 8000
            socket = new Socket(serverIP, port);
            System.out.println("Klient beder om adgang til server: '" + serverIP + "' På port: " + port);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            if (input != null){
                while (true) {
                    String tekstFraServer = input.readUTF();
                    textArea_input.appendText("server:  " + tekstFraServer);
                    System.out.println("tekst modtaget");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTekstTilServer(TextArea textArea_output) {
        //String tekstDerSkalSendes = textArea_output.getText();
        try {
            output.writeUTF(textArea_output.getText());
            System.out.println("Der blev skrevet tekst til serveren");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

