import javax.swing.*;
import java.awt.*;

public class HW1 extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. 天空與草地：畫面上半部天藍色、下半部綠色 (用 fillRect)
        // 考量到視覺比例，草地設定在畫面下方的 80 像素
        g.setColor(new Color(135, 206, 235)); // 天藍色
        g.fillRect(0, 0, 500, 320); // 天空
        
        g.setColor(new Color(60, 179, 113)); // 綠色
        g.fillRect(0, 320, 500, 80); // 草地

        // 2. 雲朵：至少 3 個互相重疊的白色 fillOval
        g.setColor(Color.WHITE);
        g.fillOval(70, 70, 70, 45);   // 左邊的圓
        g.fillOval(100, 50, 90, 60);  // 中間偏上的大圓
        g.fillOval(150, 70, 70, 45);  // 右邊的圓

        // 3. 太陽：黃色正圓 + 至少 8 條光芒 (使用 for 迴圈與三角函數)
        g.setColor(Color.YELLOW);
        int sunX = 380, sunY = 40, sunRadius = 40; 
        g.fillOval(sunX, sunY, sunRadius * 2, sunRadius * 2); // 畫直徑 80 的正圓

        int cx = sunX + sunRadius; // 取得太陽圓心 X
        int cy = sunY + sunRadius; // 取得太陽圓心 Y
        for (int i = 0; i < 8; i++) {
            double angle = i * (Math.PI / 4); // 45度角遞增 (2π / 8)
            int r1 = sunRadius + 15; // 光芒起點距離圓心的半徑
            int r2 = sunRadius + 35; // 光芒終點距離圓心的半徑
            
            // 計算光芒線段的起點與終點座標
            int x1 = (int) (cx + r1 * Math.cos(angle));
            int y1 = (int) (cy + r1 * Math.sin(angle));
            int x2 = (int) (cx + r2 * Math.cos(angle));
            int y2 = (int) (cy + r2 * Math.sin(angle));
            g.drawLine(x1, y1, x2, y2);
        }

        // 4. 房屋主體：用 fillRect 填色 + drawRect 畫黑框
        int houseX = 150, houseY = 190, houseW = 200, houseH = 130;
        g.setColor(new Color(255, 235, 190)); // 房屋底色 (米黃色)
        g.fillRect(houseX, houseY, houseW, houseH);
        g.setColor(Color.BLACK);
        g.drawRect(houseX, houseY, houseW, houseH);

        // 5. 屋頂：只能用 drawLine 畫出三角形 (三條紅色的線段)
        g.setColor(Color.RED);
        // 三個頂點座標：左下(130, 190), 右下(370, 190), 頂端(250, 100)
        g.drawLine(130, houseY, 370, houseY); // 底邊
        g.drawLine(130, houseY, 250, 100);    // 左斜邊
        g.drawLine(370, houseY, 250, 100);    // 右斜邊

        // 6. 門：用 fillRoundRect 畫門，用小的 fillOval 畫門把
        int doorW = 50, doorH = 70;
        int doorX = 250 - (doorW / 2);       // 讓門在房屋中間置中
        int doorY = houseY + houseH - doorH; // 貼齊房屋底部
        g.setColor(new Color(139, 69, 19));  // 咖啡色
        g.fillRoundRect(doorX, doorY, doorW, doorH, 15, 15); // 圓角矩形
        
        g.setColor(Color.YELLOW);
        g.fillOval(doorX + 35, doorY + 35, 8, 8); // 門把

        // 7. 窗戶：至少 2 扇，用 fillRect + drawRect，並用 drawLine 畫十字窗框
        int winSize = 40;
        int winY = 220; // 兩扇窗戶的高度相同
        
        // 左窗戶
        int leftWinX = 170;
        g.setColor(Color.WHITE);
        g.fillRect(leftWinX, winY, winSize, winSize);
        g.setColor(Color.BLACK);
        g.drawRect(leftWinX, winY, winSize, winSize); // 窗框外框
        g.drawLine(leftWinX + winSize / 2, winY, leftWinX + winSize / 2, winY + winSize); // 十字直線
        g.drawLine(leftWinX, winY + winSize / 2, leftWinX + winSize, winY + winSize / 2); // 十字橫線

        // 右窗戶
        int rightWinX = 290;
        g.setColor(Color.WHITE);
        g.fillRect(rightWinX, winY, winSize, winSize);
        g.setColor(Color.BLACK);
        g.drawRect(rightWinX, winY, winSize, winSize); // 窗框外框
        g.drawLine(rightWinX + winSize / 2, winY, rightWinX + winSize / 2, winY + winSize); // 十字直線
        g.drawLine(rightWinX, winY + winSize / 2, rightWinX + winSize, winY + winSize / 2); // 十字橫線

        // 8. 簽名：在畫面右下角
        g.setColor(Color.BLACK);
        g.setFont(new Font("標楷體", Font.BOLD, 14));
        g.drawString("a1143349 王俞証", 380, 380);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("小房子風景畫");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        HW1 panel = new HW1();
        // 設定畫布尺寸為 500x400
        panel.setPreferredSize(new Dimension(500, 400));
        
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); // 讓視窗在螢幕正中央開啟
        frame.setVisible(true);
    }
}