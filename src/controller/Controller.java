package controller;

import gui.FormEvent;
import model.Database;
import model.Person;
import java.util.List;
public class Controller {


    public void addPerson(FormEvent ev){

        String name = ev.getName();
        String occupation = ev.getOccupation();

        Person person = new Person(name, occupation);


    }
}
