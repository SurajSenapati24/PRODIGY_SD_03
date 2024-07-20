/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactmanagementsystem;

/**
 *
 * @author Suraj Senapati
 */
import java.io.*;
import java.util.ArrayList;

public class ContactManager {
    private ArrayList<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void editContact(int index, Contact contact) {
        contacts.set(index, contact);
    }

    public void deleteContact(int index) {
        contacts.remove(index);
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void saveContactsToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadContactsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            contacts = (ArrayList<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

