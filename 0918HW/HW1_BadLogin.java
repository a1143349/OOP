import javax.swing.*;
import java.awt.*;

public class  HW1_BadLogin extends JFrame {
    public HW1_BadLogin() {
        setTitle("登入");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel l1 = new JLabel("帳號:");
        JTextField t1 = new JTextField(10); 
        JLabel l2 = new JLabel("密碼:");
        JTextField t2 = new JTextField(10); 
        JButton btn = new JButton("登入");

        add(l1); add(t1); add(l2); add(t2); add(btn);

        btn.addActionListener(e -> {
            // 2. == 改成 .equals()
            if (t1.getText().equals("admin") && t2.getText().equals("1234")) {
                System.out.println("登入成功");
            }
        });
        setVisible(true); 
    }
    public static void main(String[] a) { new HW1_BadLogin(); }
}