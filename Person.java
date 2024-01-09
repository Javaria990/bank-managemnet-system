package OKAY;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
public class Person implements Serializable
{
    String name ;
    String number;
    String cnic;
    String email;
    ArrayList<Person> kim = new ArrayList<>();
    public Person() {
    }
    public Person(String name, String number, String cnic,String email) {
        this.name = name;
        this.number = number;
        this.cnic = cnic;
        this.email = email;
    }
    public void add_person(Person ep) throws IOException, ClassNotFoundException {
        kim.add(ep);
        add_to_file();
        file_to_arraylist();
    }
    public Person search_person(String cn) {
        for (Person om : kim
        ) {
            if (om.cnic.equals(cn)) {
                return om;

            }
        }
        return null;
    }
    public void remove_person(Person pp ) throws IOException, ClassNotFoundException {
        kim.remove(pp);
        add_to_file();
        file_to_arraylist();
    }
    public void show_person() {
        StringBuilder personsDetails = new StringBuilder("List of all persons:\n");
        for (Person im : kim
        ) {
            personsDetails.append("Name: ").append(im.name).append("\n")
                    .append("Email: ").append(im.email).append("\n")
                    .append("CNIC: ").append(im.cnic).append("\n")
                    .append("Phone Number: ").append(im.number).append("\n\n");


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
        FileOutputStream fos=new FileOutputStream("C:\\Users\\hp\\IdeaProjects\\try\\src\\OKAY\\Person.txt");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(kim);
        oos.close();
        fos.close();
    }
    public void file_to_arraylist() throws IOException, ClassNotFoundException {
        FileInputStream fis=new FileInputStream("C:\\Users\\hp\\IdeaProjects\\try\\src\\OKAY\\Person.txt");
        ObjectInputStream ois=new ObjectInputStream(fis);
        kim=(ArrayList<Person>) ois.readObject();
        ois.close();
        fis.close();
    }
    @Override
    public String toString() {
        return "\u001B[33mName: "+name+"\nCNIC: "+cnic+"\nNumber: "+number+"\nE-Mail: "+email+"\u001B[0m";
    }
}
