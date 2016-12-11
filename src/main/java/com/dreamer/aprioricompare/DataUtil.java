package com.dreamer.aprioricompare;


import com.dreamer.aprioricompare.doamin.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linpeng123l on 16/9/8.
 */
public class DataUtil {

    public final static String IP = "121.251.19.130";
    public final static String USERNAME = "root";
    public final static String PASSWORD = "615615";
    public final static String DBNAME = "article_search2";

   /* static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
*/

    public static List<Transaction> getTransactions() {
        try {
            List<Transaction> transactions = new ArrayList<>();
            System.out.println(DataUtil.class.getClassLoader().getResource("pumsb_star.dat"));
            BufferedReader br = new BufferedReader(new FileReader(DataUtil.class.getClassLoader().getResource("pumsb_start.dat").getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] inputchars = line.split(" ");
                ArrayList<String> arr = new ArrayList<String>();
                for (int i = 0; i < inputchars.length; i++) {
                    arr.add(inputchars[i]);
                }
                Transaction trans = new Transaction(arr);
                transactions.add(trans);
            }

            int count = 0;
            for(int i = 0;i<transactions.size();i++){
                count = count + transactions.get(i).getTrans().size();
            }
            System.out.println(count/transactions.size());

            return transactions;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("出错");
        }
    }

    /*public static List<Transaction> getTransactions() {
        try {
            List<Transaction> transactions = new ArrayList<>();
            List<List<String>> datas = new ArrayList<>();
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + IP + "/" + DBNAME + "?user=" + USERNAME + "&password=" + PASSWORD);
            PreparedStatement ps = connection.prepareStatement("select * from thesis_detail");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String temp = rs.getString("key_words_all");
                String[] inputchars = temp.split(",");
                ArrayList<String> arr = new ArrayList<String>();
                for (int i = 0; i < inputchars.length; i++) {
                    String[] inputchars2 = inputchars[i].split(" ");
                    for (int j = 0; j < inputchars2.length; j++) {
                        arr.add(inputchars2[j].trim().toLowerCase());
                    }
                }
                Transaction trans = new Transaction(arr);
                transactions.add(trans);
            }
            return transactions;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("出错");
        }
    }
*/

}
