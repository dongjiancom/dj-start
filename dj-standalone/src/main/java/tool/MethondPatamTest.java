package tool;

import common.DjBean;

/**
 * @author jiandong 2023-08-19 create
 */
public class MethondPatamTest {
    public static void main(String[] args) {
        DjBean dj = new DjBean().setAge(18).setName("dj");
        dj.djAgeChange(dj);
        System.out.println(dj);


//        DjBean dj = new DjBean().setAge(18).setName("dj");
//        dj.djAgeChange(19);
//        System.out.println(dj);
    }



}