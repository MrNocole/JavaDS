import java.util.LinkedList;
import java.util.List;

/*
 *  @author : zhanGTao
 *  @version: 1.0
 */
public class BigInt {
    /*
        Sign
        flag == true means positive
        flag == false means negative
     */
    boolean flag ;
    /*

     */
    List<Byte> num;
    BigInt(Integer input){
        this(input.toString());
    }
    BigInt(String input){
        int index = 0;
        num = new LinkedList<>();
        flag = true;
        if (!Character.isDigit(input.charAt(0))){
            flag = input.charAt(0) != '-';
            index++;
        }
        for (int i = 0 ; i < input.length() - index ; i ++){
            num.add((byte)((int)input.charAt(input.length()-i-1)-'0'));
        }
    }
    BigInt(){
        flag = true;
        num = new LinkedList<>();
    }
    /*
           @param two BigInt
           it guaranteed that two BigInt r and o are both positive (except : both r and o are negative
           in this case we and think they positive and return a negative sum) and always r > o
           @return BigInt
           sum of r + o
     */
    static BigInt Add(BigInt r,BigInt o){
        boolean flag = true;
        if (r.flag && !o.flag){
            BigInt t = o.clone();
            t.flag = true;
            return div(r.clone(),t);
        } else if (!r.flag && !o.flag){
            flag = false;
        } else if (!r.flag){
            BigInt t = r.clone();
            t.flag = true;
            return div(o.clone(), t);
        }
        if (r.num.size() < o.num.size())return Add(o,r);
        BigInt ret = new BigInt();
        ret.flag = flag;
        boolean plus = false;
        for (int i = 0 ; i < r.num.size() ; i ++){
            int t = r.num.get(i);
            if (i < o.num.size())t += o.num.get(i);
            if (plus){
                t ++ ;
                plus = false;
            }
            if (t >= 10){
                plus = true;
            }
            ret.num.add((byte) (t%10));
        }
        return ret;
    }
    /*
           @param two BigInt
           it guaranteed that two BigInt r and o are both positive and always r > o
           @return BigInt
           sum of r - o
     */
    static BigInt div(BigInt r,BigInt o){
        if (r.flag && !o.flag){
            BigInt t = o.clone();
            t.flag = true;
            return Add(r,t);
        } else if (!r.flag && o.flag){
            BigInt t = o.clone();
            t.flag = false;
            return Add(r,t);
        } else if (!r.flag){
            BigInt t1 = r.clone();
            t1.flag = true;
            BigInt t2 = o.clone();
            t2.flag = true;
            return div(t2,t1);
        }
        boolean flag = false ;
        if (cmp(r,o) == 1){
            BigInt ret = new BigInt();
            for (int i = 0 ; i < r.num.size() ; i ++){
                if (flag){
                    r.num.set(i, (byte) (r.num.get(i)-1));
                    flag = false;
                }
                int t = r.num.get(i);
                if (i < o.num.size()) t -= o.num.get(i);
                if (t<0){
                    flag = true;
                    t += 10;
                }
                ret.num.add((byte) t);
            }
            return ret;
        }else if (cmp(r,o) == 0){
            BigInt ret = new BigInt();
            ret.flag = true;
            ret.num.add((byte) 0);
            return ret;
        } else {
            BigInt ret = div(o, r);
            ret.flag = false;
            return ret;
        }
    }

    /*
        @param two BigInt
        @return case 1  : r  > o
                case 0  : r == o
                case -1 : r  < o
     */
    static int cmp(BigInt r,BigInt o){
        if (r.flag != o.flag){
            return r.flag?1:-1;
        } else if (r.flag) {
            if (r.num.size() != o.num.size())return (r.num.size()>o.num.size())?1:-1;
            for (int i = r.num.size()-1 ; i >= 0 ; i --){
                if (!r.num.get(i).equals(o.num.get(i))){
                    return (r.num.get(i) > o.num.get(i))?1:-1;
                }
            }
            return 0;
        } else {
            if (r.num.size() != o.num.size())return (r.num.size()<o.num.size())?1:-1;
            for (int i = r.num.size()-1 ; i >= 0 ; i --){
                if (!r.num.get(i).equals(o.num.get(i))){
                    return (r.num.get(i) < o.num.get(i))?1:-1;
                }
            }
            return 0;
        }
    }
/*    public String getString(){
        StringBuilder sb = new StringBuilder();
        if (!this.flag){
            sb.append('-');
        }
        boolean flag = false;
        for (int i = 0 ; i < num.size() ; i ++){
            if (!flag && num.get(num.size() - 1 - i) == 0){
                continue;
            }
            flag = true;
            sb.append(num.get(num.size()-1-i));
        }
        if (sb.length() == 0)sb.append(0);
        return sb.toString();
    }*/
    public String toString(){
        int len = num.size() , cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < len ; i ++){
            sb.append(num.get(i));
            cnt ++;
            if (cnt%3==0){
                sb.append(',');
            }
        }
        len = sb.length()-1;
        while (sb.charAt(len)==',' || sb.charAt(len)=='0'){
            sb.deleteCharAt(len);
            len--;
        }
        if (!flag){
            sb.append('-');
        }
//        System.out.println(sb.toString());
        return sb.reverse().toString();
    }
    public BigInt clone(){
        BigInt ret = new BigInt();
        ret.flag = this.flag;
        ret.num.addAll(this.num);
        return ret;
    }
}
