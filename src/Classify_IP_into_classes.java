import org.jetbrains.annotations.Contract;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

/*
Java program to classify an IPv4 address into classes, mentioned in the subject of Data
communication and computer networks. The IP classification are specified as follows:

000-127: Class A
128-191: Class B
192-223: Class C
224:239: Class D
240-255: Class E
*/
public class Classify_IP_into_classes {

    private Map<Integer,String> classMap = new TreeMap<>();

    Classify_IP_into_classes()
    {
        classMap.put(191,"B");
        classMap.put(223,"C");
        classMap.put(239,"D");
        classMap.put(255,"E");
    }
    private boolean isLeast(int IPPart)
    {
        if(IPPart<=127){
            return true;
        }
        return false;
    }
    private String getClassHelper(int IPPart){
        String classPart="A";
        if(isLeast(IPPart))return classPart;
        else
        {
            for(Map.Entry<Integer,String> e:classMap.entrySet())
            {
                if(IPPart<=e.getKey()){
                    classPart=e.getValue();
                    break;
                }
            }
        }
        return classPart;
    }
    int[] breakIP(String IP)
    {
        int IPArray[] =new int[4];
        int i=0;
        int threeDots=0;
        String IPPart="";
        String IPCLassified="";
        while(i<IP.length())
        {
            if(IP.charAt(i)=='.'&&threeDots<3)
            {
                i++;
                IPArray[threeDots]=Integer.parseInt(IPPart);
                threeDots++;
                IPPart="";
            }
            IPPart+=IP.charAt(i);
            i++;
        }
        IPArray[3]=Integer.parseInt(IPPart);
        return IPArray;
    }
    String checkIP(String IPString)
    {
        boolean b=Pattern.matches("\\b(25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9])\\b",IPString);
        return b?"Valid IP":"Invalid Ip";
    }
    String getClass(String IP){
            String IPClassified="";
            int IPPart[] = breakIP(IP);
            for(int i:IPPart) {
                IPClassified += getClassHelper(i)+".";
            }
            return IPClassified.substring(0,IPClassified.length()-1);
    }
    public static void main(String[] args) {

        Classify_IP_into_classes classObj=new Classify_IP_into_classes();
        System.out.println("Enter IP");
        Scanner sc=new Scanner(System.in);
        String inputIP=sc.next();
        String checkedIP = classObj.checkIP(inputIP);
        System.out.println(checkedIP);


        if(!checkedIP.equals("Invalid IP")) {
            System.out.println(classObj.getClass(inputIP));
        }

    }
}
