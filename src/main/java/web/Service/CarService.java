package web.Service;

import web.model.Car;

import java.util.List;

public class CarService {
    public static List<Car> getCars(List<Car> list, int count){
        if(count == 0 | count >= 5){
            return list;
        }else {
            return list.subList(0,count);
        }
    }
}
