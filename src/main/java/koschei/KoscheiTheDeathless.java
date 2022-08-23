package koschei;

import koschei.models.Ocean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class KoscheiTheDeathless {

    @Qualifier("ocean1")
    private Ocean1 ocean;

    public String getRulesByDeth() {
        return "На свете есть океан (World0) , " + ocean.toString();
    }

    @Autowired
    public void setOcean(Ocean1 ocean) {
        this.ocean = ocean;
    }
}
