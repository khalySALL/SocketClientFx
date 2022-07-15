package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController extends Thread implements Initializable {
	
	@FXML
	private TextField messagetxt;
	@FXML
	private Button envoyerbt;
	@FXML
	private TextArea receptiontxt;
	private Socket client;
    private BufferedReader fromServeur;
    private PrintStream toServeur;
    private Scanner scanner = new Scanner(System.in);
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 try{

             this.start();
         }catch (Exception ex) {
             ex.printStackTrace();
         }
		
	}
	
	 @Override
	    public void run() {
	    	try {
	            client = new Socket("127.0.0.1", 8889);
	            toServeur = new PrintStream(client.getOutputStream());
	            fromServeur = new BufferedReader(
	                    new InputStreamReader(client.getInputStream()));
	            
	            while (true){
	                System.out.println("Client, votre message :");
	                //toServeur.println(scanner.nextLine());
	                toServeur.println(messagetxt.getText());


	                String message = fromServeur.readLine();
	                receptiontxt.setText(message);
	                //System.out.println("Message  du serveur : " + message);
	            }
	    	}catch (Exception ex) {
	            ex.printStackTrace();
	        }finally {
	            try {
	                client.close();
	            }catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	
}
