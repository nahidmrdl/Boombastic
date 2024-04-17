package entity.monster.monstertypes;

import java.awt.*;
import java.util.*;

import cell.normalCell.NormalCell;
import cell.wall.WallCell;
import entity.Entity;
import entity.monster.Monster;
import entity.player.Player;
import map.GameMap;
import util.Edge;
import util.Node;

import java.util.List;

//TODO NOT READY!! SHORTEST PATH IMPLEMENTATION.
public class SpeedyMonster extends Monster {
    private Image baseImage;
    private int direction;
    private Date lastMoveTime = new Date();

    private Node[][] nodes;
    private int speed;

    // Constructor
    public SpeedyMonster(int x, int y, GameMap gameMap, List<Player> players) {
        super(x, y, gameMap, players);
        findValidStartingPosition();
        direction = new Random().nextInt(4); // 0: left, 1: right, 2: up, 3: down
        this.speed = 700;
//        setupNodes();
    }

//    private void setupNodes() {
//        int width = gameMap.getMap().length;
//        int height = gameMap.getMap()[0].length;
//        this.nodes = new Node[width][height];  // Assuming 'nodes' is a class variable
//
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                 nodes[x][y] = new Node(x, y);
//
//                // Only proceed to connect nodes if the current cell is a NormalCell
//                if (gameMap.getCell(x, y) instanceof NormalCell) {
//                    if (x > 0 && gameMap.getCell(x - 1, y) instanceof NormalCell) {
//                        nodes[x][y].edges.add(new Edge(nodes[x - 1][y], 1));
//                    }
//                    if (y > 0 && gameMap.getCell(x, y - 1) instanceof NormalCell) {
//                        nodes[x][y].edges.add(new Edge(nodes[x][y - 1], 1));
//                    }
//                    if (x < width - 1 && gameMap.getCell(x + 1, y) instanceof NormalCell) {
//                        nodes[x][y].edges.add(new Edge(nodes[x + 1][y], 1));
//                    }
//                    if (y < height - 1 && gameMap.getCell(x, y + 1) instanceof NormalCell) {
//                        nodes[x][y].edges.add(new Edge(nodes[x][y + 1], 1));
//                    }
//                }
//            }
//        }
//    }

//    public void moveTowardNearestPlayer() {
//        Player nearestPlayer = findNearestPlayer();
//        if (nearestPlayer != null) {
//            Node start = nodes[this.getX()][this.getY()];
//            Node end = nodes[nearestPlayer.getX()][nearestPlayer.getY()];
//            List<Node> path = dijkstra(start, end);
//            if (!path.isEmpty() && path.size() > 1) {
//                // Move to the first node in the path that isn't the current node
//                Node nextStep = path.get(1);
//                this.setX(nextStep.x);
//                this.setY(nextStep.y);
//            }
//        }
//    }

//    private List<Node> dijkstra(Node start, Node end) {
//        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(n -> n.minDistance));
//        Map<Node, Double> bestDistances = new HashMap<>();  // This map will track the best known distances
//
//        start.minDistance = 0;
//        queue.add(start);
//        bestDistances.put(start, 0.0);
//
//        while (!queue.isEmpty()) {
//            Node u = queue.poll();
//            for (Edge e :u.edges){
//                System.out.println(e.target + " " + e.cost);
//
//            }
//
//
//            // If this distance is not the best known distance, skip processing for this node
//            if (u.minDistance > bestDistances.getOrDefault(u, Double.POSITIVE_INFINITY)) {
//                continue;
//            }
//
//            if (u.equals(end)) break;
//
//            for (Edge e : u.edges) {
//                Node v = e.target;
//                double distanceThroughU = u.minDistance + e.cost;
//
//                // Only update if the found distance is better
//                if (distanceThroughU < v.minDistance) {
//                    v.minDistance = distanceThroughU;
//                    v.predecessor = u;
//                    queue.add(v);
//                    bestDistances.put(v, distanceThroughU);  // Update the best known distance
//                }
//            }
//        }
//
//        return reconstructPath(end);
//    }
//
//
//    private List<Node> reconstructPath(Node target) {
//        List<Node> path = new ArrayList<>();
//        for (Node at = target; at != null; at = at.predecessor) {
//            path.add(at);
//        }
//        Collections.reverse(path);
//        return path;
//    }


    private void findValidStartingPosition() {
        int maxX = gameMap.getMap().length;
        int maxY = gameMap.getMap()[0].length;
        Random rand = new Random();
        do {
            this.x = rand.nextInt(maxY);
            this.y = rand.nextInt(maxX);
        } while (!(gameMap.getMap()[this.y][this.x] instanceof NormalCell));
        
    }





    public void moveRandomly() {
        if(lastMoveTime.getTime() + speed < new Date().getTime()) {
            lastMoveTime = new Date();
            Random rand = new Random();
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            int newX = this.x + dx[direction];
            int newY = this.y + dy[direction];

            if (newX >= 0 && newX < gameMap.getMap()[0].length &&
                    newY >= 0 && newY < gameMap.getMap().length &&
                    gameMap.getMap()[newY][newX] instanceof NormalCell) {
                this.x = newX;
                this.y = newY;
            } else {
                direction = rand.nextInt(4);
            }
        }
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    // Method to check if next to player
    public boolean isNextToPlayer(int px, int py) {
        return Math.abs(this.x - px) <= 1 && Math.abs(this.y - py) <= 1;
    }

    private Player findNearestPlayer() {
        Player nearestPlayer = null;
        double minDistance = Double.MAX_VALUE;  // Start with the largest possible value

        for (Player player : this.players) {  // Iterate through the list of players
            double distance = Math.sqrt(Math.pow(this.x - player.getX(), 2) + Math.pow(this.y - player.getY(), 2));
            if (distance < minDistance) {
                minDistance = distance;
                nearestPlayer = player;
            }
        }

        return nearestPlayer;  // Return the nearest player found, or null if no players are in the list
    }
}
