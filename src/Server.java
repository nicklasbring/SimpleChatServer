import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;

public class Server implements Runnable{

    private ServerSocket server;
    private Socket socket;
    private int port = 8000;
    private DataInputStream input;
    private DataOutputStream output;
    private TextArea textArea_input;
    private TextArea textArea_output;


    public Server(TextArea textArea_input, TextArea textArea_output) {
        this.textArea_input = textArea_input;
        this.textArea_output = textArea_output;
    }

    @Override
    public void run() {
        try {
            //Opretter ny server som kan tilgås på port 8000
            server = new ServerSocket(port);
            System.out.println("Serveren er startet på port: " + port);
            Date date = new Date();
            System.out.println("\nServeren er startet: " + (new Timestamp(date.getTime())));

            //Venter på opkobling fra klient
            socket = server.accept();
            System.out.println("\nKlienten er tilsluttet serveren");

            //Data input og output for at skrive til klien og modtage data fra klienten
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());


            if (input != null){
            while (true) {
                String tekstFraKlient = input.readUTF();
                textArea_input.appendText("klient:  " + tekstFraKlient);
                System.out.println("tekst modtaget");
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTekstTilKlient(TextArea textArea_output) {
        //String tekstDerSkalSendes = textArea_output.getText();
        try {
            output.writeUTF(textArea_output.getText());
            System.out.println("Der blev skrevet tekst til Klienten");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
