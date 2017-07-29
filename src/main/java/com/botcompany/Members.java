package com.botcompany;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class Members {

    private static List<String> members = new ArrayList<>();
    private static TreeMap<String, Map<String, Boolean>> sumAttendance = new TreeMap<>();
    private static Map<String, Boolean> currentAttendance;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MMMM.yyyy");



    public static Map<String, Boolean> getAttendanceByDate(String date){
        Map<String, Boolean> result = new HashMap<>();
        if(sumAttendance.containsKey(date))
            result = sumAttendance.get(date);
        return result;
    }

    public static ArrayList< Map<String, Boolean>> getAttendanceByWeekCount(int number){
        ArrayList<Map<String, Boolean>> result = new ArrayList<>();
        if (sumAttendance.size() >= number) {
            ArrayList<String> dates = new ArrayList<>();
            dates.addAll(sumAttendance.keySet());
            Collections.sort(dates);
            for (int i = dates.size() - 1; i >= dates.size() - number ; i--) {
                result.add(sumAttendance.get(dates.get(i)));
            }
        }
        else {
            result.addAll(sumAttendance.values());
        }
        return result;
    }

    public static void addAttendanceResult(Map<String, Boolean> result){
        Date date = new Date(System.currentTimeMillis());
        String currentDate = sdf.format(date);
        sumAttendance.put(currentDate, result);
    }

    public static List<String> getMembers() {
        generateMembers();
        return members;
    }


    public static void setCurrentAttendance(Map<String, Boolean> Attendance) {
        currentAttendance = Attendance;
    }

    public static Map<String, Boolean> getCurrentAttendance() {
        return currentAttendance;
    }

    private static void generateMembers(){
        members.add("Avtushenko Irina starshaya");
        members.add("Avtushenko Sergey");
        members.add("Avtushenko Sasha");
        members.add("Avtushenko Inna");
        members.add("Pyateckaya (Avtushenko) Marina");
        members.add("Pyateckiy Ruslan");
        members.add("Pyateckiy Timophey");
        members.add("Avtushenko Liza");
        members.add("Avtushenko Erik");
        members.add("Avtushenko Rodion");
        members.add("Avtushenko Irina mladshaya");
        members.add("Avtushenko Zhora");
        members.add("Avtushenko Dana");
        members.add("Bevz Sergey");
        members.add("Bevz Nastya");
        members.add("Bevz Aaron");
        members.add("Bondarenko Nina");
        members.add("Bondarenko Dima");
        members.add("Bondarenko Kristina");
        members.add("Bondarenko Maria");
        members.add("Lukyanchuk Dima");
        members.add("Lukyanchuk Nadiya");
        members.add("Makarow Yura");
        members.add("Makarova Lera");
        members.add("Chupikov Mykola");
        members.add("Chupikova Daria");
        members.add("Chupikov Vlad");
        members.add("Sherbina Vitaliy");
        members.add("Sherbina Svetlana");
        members.add("Pidtykana Tatyana Vasilevna");
        members.add("Kren Tamila");
        members.add("Kren Valya");
        members.add("Maksimov Valentin");
        members.add("Muxina Lyuba");
        members.add("Zhenya Dremina");
        members.add("Illia Dremin");
        members.add("Danil Verba");
        members.add("Dovban Elena");
        members.add("Prince Mike");
        members.add("Hrapun sasha");
        members.add("Oleg sashy muzh");
    }

}
