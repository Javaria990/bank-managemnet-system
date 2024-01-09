package OKAY;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
public class Client implements Serializable
{
    static int ID;

    public void setC_ID(String c_ID) {
        this.c_ID = c_ID;
    }

    String client_ID;
    String c_ID;
    String na;
    Person per =new Person();
    String num;
    static ArrayList<Client> ht = new ArrayList<>();
    public Client(Person per) {
        this.client_ID = "CLI"+ID;
        this.per = per;
        this.num= per.number;
        this.c_ID = per.cnic;
        this.na = per.name;
        ID++;
    }
    public Client(){
    }
    public void add_client(Client pl){
        ht.add(pl);
        System.out.println("Client added successfully with Client_ID : "+pl.client_ID);
    }
    public Client search_client(String id) {
        for (Client om : ht
        ) {
            if (om.client_ID.equals(id)) {
                return om;
            }
        }
        return null;
    }
    public Client search_clients(String k){
        for (Client mn: ht
        ) {
            if (mn.c_ID.equals(k))
            {
                return mn;
            }
        }
        return null;
    }
    public void show_clients(){
        StringBuilder personsDetails = new StringBuilder("List of all persons:\n");
        for (Client ym: ht) {
            personsDetails.append("Name: ").append(ym.na).append("\n")
                    .append("Email: ").append(ym.per.email).append("\n")
                    .append("CNIC: ").append(ym.c_ID).append("\n")
                    .append("Phone Number: ").append(ym.num).append("\n\n");

        }
        JTextArea textArea = new JTextArea(personsDetails.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JFrame detailsFrame = new JFrame("Client Details");
        detailsFrame.getContentPane().removeAll(); // Clear previous content
        detailsFrame.setTitle("All Persons");
        detailsFrame.setSize(400, 300);
        detailsFrame.setLayout(new BorderLayout());
        detailsFrame.add(scrollPane, BorderLayout.CENTER);
        detailsFrame.setVisible(true);

    }
    public void add_to_file() throws IOException
    {
        FileOutputStream fos=new FileOutputStream("C:\\Users\\hp\\IdeaProjects\\try\\src\\OKAY\\Client_data.txt");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(ht);
        oos.close();
        fos.close();
    }

    public void file_to_arraylist() throws IOException, ClassNotFoundException {
        FileInputStream fis=new FileInputStream("C:\\Users\\hp\\IdeaProjects\\try\\src\\OKAY\\Client_data.txt");
        ObjectInputStream ois=new ObjectInputStream(fis);
        ht=(ArrayList<Client>) ois.readObject();
        ois.close();
        fis.close();
    }
    @Override
    public String toString() {
        return per+"\n\u001B[35mClient ID: "+client_ID+"\u001B[0m";
    }
    public void delete_client(Client pk) throws IOException, ClassNotFoundException {
        ht.remove(pk);
        add_to_file();
        file_to_arraylist();
    }
}
