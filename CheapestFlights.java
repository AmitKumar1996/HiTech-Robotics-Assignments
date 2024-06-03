import java.util.*;

class CheapestFlights {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        
        HashMap<Integer, List<int[]>> adjMap = new HashMap<>();

        
        for (int[] flight : flights) {
            if (!adjMap.containsKey(flight[0])) {
                adjMap.put(flight[0], new ArrayList<>());
            }
            adjMap.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        int[] priceArray = new int[n];
        Arrays.fill(priceArray, Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});
        int stops = 0;

        while (!queue.isEmpty() && stops <= k) {
            int len = queue.size(); 
            while (len > 0) {
                int[] temp = queue.poll();
                int node = temp[0];
                int price = temp[1];
                if (!adjMap.containsKey(node)) {
                    len--;
                    continue;
                }
                for (int[] neighbours : adjMap.get(node)) {
                    int toNode = neighbours[0];
                    int toNodePrice = neighbours[1];
                    if (price + toNodePrice >= priceArray[toNode]) {
                        continue;
                    }
                    priceArray[toNode] = price + toNodePrice;
                    queue.offer(new int[]{toNode, priceArray[toNode]});
                }
                len--;
            }
            stops++; 
        }
        return (priceArray[dst] == Integer.MAX_VALUE ? -1 : priceArray[dst]);
    }

    
public static void main(String[] args) {
    CheapestFlights cheapestflights = new CheapestFlights();
        int n = 3;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0;
        int dst = 2;
        int k = 1;
        int result = cheapestflights.findCheapestPrice(n, flights, src, dst, k);
        System.out.println("The cheapest price is: " + result); // Output should be 200

     
    }
}
