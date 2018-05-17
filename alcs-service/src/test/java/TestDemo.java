import org.junit.Test;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 21:57 2018-05-17
 */
public class TestDemo {
    @Test
    public void test(){
        String str =  "通工1416";
        String substring = str.substring(0, 2);
        System.out.println(substring);
        String substring2 = str.substring(2);
        System.out.println(substring2);
    }
}
