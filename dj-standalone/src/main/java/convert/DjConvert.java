package convert;

import lombok.Data;

/**
 * @author jiandong 2024-05-20 create
 *
 * 使用idea 的 ObjectHelper插件生成
 */
@Data
public class DjConvert {
    private String dj1;
    private String dj2;
    private String dj3;
    private String dj4;
    private String dj5;
    private DjConvert dj6;

    DjConvert convert(DjConvert dj) {
        if (dj == null) {
            return null;
        }
        DjConvert djConvert = new DjConvert();
        djConvert.setDj1(dj.getDj1());
        djConvert.setDj2(dj.getDj2());
        djConvert.setDj3(dj.getDj3());
        djConvert.setDj4(dj.getDj4());
        djConvert.setDj5(dj.getDj5());
        djConvert.setDj6(dj.getDj6());
        return djConvert;
    }

}
