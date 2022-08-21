package app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AnimalsCage {

    @Autowired
    @Qualifier("cat")
    private Animal animal1;
    @Autowired
    @Qualifier("dog")
    private Animal animal2;

    @Autowired
    private Timer time;


    public void whatAnimalSay() {
        System.out.println("Animals say:");
        System.out.println(animal1.toString() + " and " + animal2.toString());
        System.out.println("At:");
        System.out.println(time.getTime());
        System.out.println("________________________");
    }

    public Timer getTimer() {
        return time;
    }
}
