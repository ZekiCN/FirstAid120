import java.util.UUID;

/**
 * Created by Administrator on 2017-5-31.
 */
public class Unit {
    public static void main(String [] args){
        for(int i  =0; i<30;i++){
            String id = UUID.randomUUID().toString().trim();
            System.out.println("-------------"+id);


        }

    }

}
