package life.majiang.community.controller;

import life.majiang.community.model.Inventory;

import java.util.Comparator;

public class CompareInventory implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Inventory inventory1 = (Inventory) o1;
        Inventory inventory2 = (Inventory) o2;
        int flag=inventory1.getTimeprotect().compareTo(inventory2.getTimeprotect());
        if (flag==0){
            return inventory1.getNum().compareTo(inventory2.getNum());
        }
        else{
            return flag;
        }
    }

//    public int compare(Object arg0, Object arg1) {
//        User user0=(User)arg0;
//        User user1=(User)arg1;
//
//        //首先比较年龄，如果年龄相同，则比较名字
//
//        int flag=user0.getAge().compareTo(user1.getAge());
//        if(flag==0){
//            return user0.getName().compareTo(user1.getName());
//        }else{
//            return flag;
//        }
//    }
}
