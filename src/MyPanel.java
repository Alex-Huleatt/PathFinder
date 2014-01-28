
import PathFinding.GraphBuilder;
import PathFinding.Point;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alexhuleatt
 */
public class MyPanel extends javax.swing.JPanel {

    private Point[] path;
    private ArrayList<Point> obstacles;
    private Point[] waypoints;
    private int num_waypoints;
    private static final int RECT_SIZE = 10;
    int last_x;
    int last_y;

    /**
     * Creates new form MyPanel
     */
    public MyPanel() {
        obstacles = new ArrayList<>();
        last_x = -1;
        last_y = -1;
        initComponents();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.red);
        for (Point p : obstacles) {
            g.fillRect(p.x * RECT_SIZE, p.y * RECT_SIZE, RECT_SIZE, RECT_SIZE);
        }
        g.setColor(Color.blue);
        for (int i = 0; i < num_waypoints; i++) {
            Point p = waypoints[i];
            g.fillRect(p.x * RECT_SIZE + RECT_SIZE / 4, p.y * RECT_SIZE + RECT_SIZE / 4, RECT_SIZE / 2, RECT_SIZE / 2);
        }

        if (path != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(1));
            Point current = new Point(0, 0);
            g.setColor(Color.magenta);
            for (int i = 0; i < path.length; i++) {
                g.drawLine(current.x * RECT_SIZE + RECT_SIZE / 2, current.y * RECT_SIZE + RECT_SIZE / 2, path[i].x * RECT_SIZE + RECT_SIZE / 2, path[i].y * RECT_SIZE + RECT_SIZE / 2);
                g.setColor(Color.green);
                g.fillRect(current.x * RECT_SIZE, current.y * RECT_SIZE, RECT_SIZE, RECT_SIZE);
                g.setColor(Color.magenta);
                current = path[i];
            }
        }
        g.setColor(Color.cyan);
        g.fillRect(0,0,RECT_SIZE, RECT_SIZE);
        g.fillRect(getWidth() - RECT_SIZE, getHeight() - RECT_SIZE, RECT_SIZE, RECT_SIZE);
    }

    public void path() {
        GraphBuilder g = new GraphBuilder(getWidth() / RECT_SIZE, getHeight() / RECT_SIZE);
        for (Point p : obstacles) {
            g.addObstacle(p);
        }
        g.buildMatrix();
        path = g.getPath(new Point(0, 0), new Point(getWidth() / RECT_SIZE - 1, getHeight() / RECT_SIZE - 1));
        System.out.println(Arrays.toString(path));
        waypoints = g.getWayPoints();
        num_waypoints = g.num_waypoints();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        last_x = evt.getX();
        last_y = evt.getY();
        int x = ((int) (evt.getX() / RECT_SIZE + 0.5));
        int y = ((int) (evt.getY() / RECT_SIZE + 0.5));
        Point p = new Point(x, y);
        if (obstacles.contains(p)) {
            obstacles.remove(p);
        } else {
            obstacles.add(p);
        }
        path = null;
        num_waypoints = 0;
        repaint();
    }//GEN-LAST:event_formMouseClicked

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (Math.abs(evt.getX() - last_x) > RECT_SIZE || Math.abs(evt.getY() - last_y) > RECT_SIZE) {
            formMouseClicked(evt);
        }
    }//GEN-LAST:event_formMouseDragged

    public void reset() {
        path = null;
        num_waypoints = 0;
        obstacles = new ArrayList<>();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
