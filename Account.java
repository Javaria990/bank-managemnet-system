package OKAY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
public class Account implements Serializable
{
    String number;
    String cl_id;
    static int ID ;
    String acc_Id;
    int sum_r ;
    float amount ;
    String nmm ;
    String na;
    Client n = new Client();
    ArrayList<Account> ki = new ArrayList<>();
    public Account(Client n,float amou) {
        this.number = n.num;
        this.n = n;
        this.amount = amou;
        DecimalFormat fmt = new DecimalFormat("00000");
        this.acc_Id = "acc_"+fmt.format(ID);
        this.cl_id = n.client_ID;
        this.nmm = n.c_ID;
        this.na = n.na;
        ID++;
    }
    public void add_account(Account jm) throws IOException, ClassNotFoundException {
        ki.add(jm);
        add_to_file();
        file_to_arraylist();
        System.out.println(ki);
        System.out.println(jm);
        System.out.println("\u001B[33m\u001B[1mAccount created with ID: "+jm.acc_Id+"\u001B[0m\u001B[0m");
    }
    public ArrayList<String>  account_det(String min){
        ArrayList<String> kil = new ArrayList<>();
        for (Account k: ki
        ) {
            if (k.nmm.equals(min)){
                kil.add(k.acc_Id);
            }
        }
        return kil;
    }
    public ArrayList<Account>  account_(String min){
        ArrayList<Account> kiloo = new ArrayList<>();
        for (Account k: ki
        ) {
            if (k.nmm.equals(min)){
                kiloo.add(k);
            }
        }
        return kiloo;
    }
    public ArrayList<Account>  account_cli_id(String cid){
        ArrayList<Account> km = new ArrayList<>();
        for (Account k: ki
        ) {
            if (k.cl_id.equals(cid)){
                km.add(k);
            }
        }
        return km;
    }
    public ArrayList<Float> amnt_det(String min){
        ArrayList<Float> kilo = new ArrayList<>();
        for (Account k: ki
        ) {
            if (k.cl_id.equals(min)){
                kilo.add(k.amount);
            }
        }
        return kilo;
    }
    public Account() {
    }
    public void show_account(){
        StringBuilder personsDetails = new StringBuilder("List of all persons:\n");
        for (Account imi: ki
        ) {
            personsDetails.append("Name: ").append(imi.na).append("\n")
                    .append("Client ID: ").append(imi.cl_id).append("\n")
                    .append("CNIC: ").append(imi.nmm).append("\n")
                    .append("Amount: ").append(imi.amount).append("\n")
                    .append("Account ID: ").append(imi.acc_Id).append("\n")
                    .append("Phone Number: ").append(imi.number).append("\n\n");

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
    public void withdraw_amount(String m, float amo) throws IOException, ClassNotFoundException {
        for (Account nml : ki) {
            if (nml.acc_Id.equals(m)) {
                if (nml.amount < amo) {
                    System.out.println("The amount exceeds your current balance.");
                } else {
                    nml.amount -= amo;
                    System.out.println("The current balance is " + nml.amount);
                    add_to_file();
                    file_to_arraylist();
                }
                break;
            }
        }
    }
    public void deposit_amount(String m, float amo) throws IOException, ClassNotFoundException {
        for (Account nml : ki) {
            if (nml.acc_Id.equals(m)) {
                nml.amount += amo;
                System.out.println("\u001B[35m\u001B[1mThe current amount is " + nml.amount+"\u001B[0m\u001B[0m");
                add_to_file();
                file_to_arraylist();
            }
        }
    }
    public Account search_account(String accid) {
        for (Account om : ki
        ) {
            if (om.acc_Id.equals(accid)) {
                return om;
            }
        }
        return null;
    }
    public void delete_account(Account md) throws IOException, ClassNotFoundException {
        ki.remove(md);
        add_to_file();
        file_to_arraylist();
        System.out.println("\u001B[35m\u001B[1mAccount is removed successfully\u001B[0m\u001B[0m");
    }
    public int totalAmount() {
        int sum_r = 0;
        for (Account imi : ki) {
            sum_r += (int) imi.amount;
        }
        return sum_r;
    }
    public void add_to_file() throws IOException
    {
        FileOutputStream fos=new FileOutputStream("C:\\Users\\hp\\IdeaProjects\\try\\src\\OKAY\\Account.txt");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(ki);
        oos.close();
        fos.close();
    }
    public void file_to_arraylist() throws IOException, ClassNotFoundException {
        FileInputStream fis=new FileInputStream("C:\\Users\\hp\\IdeaProjects\\try\\src\\OKAY\\Account.txt");
        ObjectInputStream ois=new ObjectInputStream(fis);
        ki= (ArrayList<Account>) ois.readObject();
        ois.close();
        fis.close();
    }
    @Override
    public String toString() {
        return n+"\u001B[31m\nAccount ID: "+acc_Id+"\t \tAmount: "+amount+"\u001B[0m";
    }
}
