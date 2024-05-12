import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HammingCode {
    String databit;
    boolean is_odd_verify_way=true;
    int CheckbitLength;
    ArrayList<Character> list;
    int checkbitLength =1;
    ArrayList<Integer> indexOfdatabit;
    int totalLength;

    HammingCode(){
        Scanner s =new Scanner(System.in);
        databit =s.nextLine();
        if(Authentic(databit)){

         //   System.out.println("the number of verify_digit:"+get_and_update_CheckDigit(x)+totalLength);

            init_and_padding_the_list();
            //
            fix_the_checkbit();
            //
        }else{
            System.out.println("Authenticed check failed");
        }

    }
    public boolean Authentic(String x){
        boolean legalChar = true;
        if(x != null){
            char[] array = x.toCharArray();
            for(char c:array)
            {
                if(c=='0' || c=='1'){

                }else{
                    legalChar=false;
                }
            }
        }else{
            return false;
        }
        return legalChar;
    }
    public void updateCheckbitLength(String x){
        int string_len = x.length();


        while(Math.pow(2, checkbitLength)-1<string_len+ checkbitLength){
            checkbitLength +=1;
        }
        CheckbitLength = checkbitLength;

    }
    public static String formatBinaryCount(int count,int resSize) // 传入整型数字，和格式化字符大小  ，左侧0填充的数字，当形参count的二进制数长度大于resSize指定大小时，报错返回空。
    {
String temp=Integer.toBinaryString(count);
        if(temp.length()>resSize)
        {
            System.out.println("形参count的二进制数长度大于resSize指定大小!");
            return  null;
        }else{
            String formatType="%"+0+resSize+"d";
            int resInt = Integer.parseInt(Integer.toBinaryString(count));
            String paddedNumber = String.format(formatType, resInt); // 前补0，总长度为8
        //   System.out.println(paddedNumber); // 输出：00000123
            return paddedNumber;
        }

    }
    public static  void main(String[]args)
    {
        HammingCode s= new HammingCode();
            System.out.println("11111");



    }
public void init_and_padding_the_list(){
    updateCheckbitLength(databit);
    System.out.println("the data you input:"+ databit);
    System.out.println("the number of verify_digit:"+ checkbitLength+totalLength);
    totalLength = CheckbitLength + databit.length() ;
    list=new ArrayList<Character>();
    indexOfdatabit = new ArrayList<Integer>();
    int string_count =0;
    int temp=1;
    for(int i = 0; i< databit.length()+ CheckbitLength; i++)
    {

        /*            byte a =i.*/
        if(temp-1==i){
            list.add('0');
            System.out.println("temp: "+temp);
            indexOfdatabit.add(temp-1);
            temp*=2;

        }else{
            list.add(databit.toCharArray()[databit.length()-string_count-1]);
            string_count+=1;
        }

    }
    Collections.reverse(list);

}

public void fix_the_checkbit(){
    for(int digit_temp = 0; digit_temp< CheckbitLength; digit_temp++)
    {
        boolean groupingXOR =false;
        boolean trueTemp =true;
        int passtheIndex ;
        for(int t=0;t<totalLength;t++){
            int indexOfcheckbit= t+1;
            String x= formatBinaryCount(t+1, CheckbitLength); //t+1 对应下标

            if(x.toCharArray()[CheckbitLength -digit_temp-1]  =='1'){
             //   System.out.println(x);
           //     System.out.println("对应下标："+indexOfcheckbit);
           //     System.out.println("list对象打印："+list.get(totalLength - indexOfcheckbit));
                if (list.get(totalLength - indexOfcheckbit)=='1'){
                    groupingXOR= groupingXOR^trueTemp;
                }
            }
        }
   //     System.out.println("异或结果："+groupingXOR);
        list.set(totalLength- indexOfdatabit.get(digit_temp)-1,(groupingXOR)?'1':'0');
        System.out.println("");
    }
}
}