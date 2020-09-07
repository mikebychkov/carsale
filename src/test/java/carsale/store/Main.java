package carsale.store;

public class Main {

    public static void main(String[] args) {
        Integer i = 2;
        Object oi = (Object) i;
        Integer i2 = (Integer) oi;
        System.out.println(i2);

        Boolean b = true;
        Object ob = (Object) b;
        Boolean b2 = (Boolean) ob;
        System.out.println(b2);
    }
}
