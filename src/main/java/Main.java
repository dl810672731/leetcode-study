import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> keys = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        String first = sc.next();
        String second = sc.next();
        String[] source = first.split(",");
        for (String str : source) {
            String[] temp = str.split(":");
            map.put(Integer.valueOf(temp[0]), Integer.valueOf(temp[1]));
            keys.add(Integer.valueOf(temp[0]));
        }
        String[] cutList = second.split(",");
        List<Boolean> res = new ArrayList<>();
        for (String str : cutList) {
            List<Integer> temps = keys.stream().sorted(Integer::compareTo)
                    .filter(s -> s >= Integer.parseInt(str))
                    .limit(1)
                    .collect(Collectors.toList());
            System.out.println("str" + str + "temps" + temps);
            if (temps.isEmpty()) {
                System.out.println("temps isEmpty");
                res.add(false);
            } else {
                int tarSource = temps.get(0);
                if (map.get(tarSource) > 0) {
                    Integer t = map.get(tarSource);
                    map.put(tarSource, t - 1);
                    if (t - 1 == 0) {
                        keys.remove(tarSource);
                    }
                    res.add(true);
                } else {
                    res.add(false);
                }
            }
        }
        StringBuilder ln = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            Boolean b = res.get(i);
            if (i == res.size() - 1) {
                ln.append(b);
            } else {
                ln.append(b).append(",");
            }
        }
        System.out.println(ln.toString());
    }
}
