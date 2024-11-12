package com.masturbate;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.time.Period;

public class GUI {
    public static void main(String[] args) {
        InfoManager IM = new InfoManager();

        JFrame frame = new JFrame("NNN assistant");
        frame.setSize(350, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        if (IM.records.isEmpty()) {
            JLabel label = new JLabel("尚未有记录");
            frame.add(label);
        } else {
            JLabel label = new JLabel(
                    "<html><body><p>你近7天鹿了" + IM.getWeekCount() + "次<br/><br/>" +
                            "近30天鹿了" + IM.getMonthCount() + "次<br/><br/>" +
                            "近365天鹿了" + IM.getYearCount() + "次<br/><br/>" +
                            "总共鹿了" + IM.getSum() + "次<br/><br/><br/>" +
                            "上次鹿（" + IM.getLastTime().toString() + ")距今已有" +
                            IM.getSinceLastTime().getDays() + "天" +
                            "<p><br/><br/></body></html>");
            label.setFont(new Font("幼圆", Font.PLAIN, 20));
            frame.add(label);
        }

        JButton btn_masturbate = new JButton("<html><body>鹿</body></html>");
        btn_masturbate.addActionListener(e -> {
            JFrame f = new JFrame();
            f.setSize(350, 300);
            f.setLocationRelativeTo(null);
            f.setLayout(new FlowLayout());
            f.setVisible(true);

            JLabel label = new JLabel("鹿后感");
            f.add(label);

            StringBuilder reflection = new StringBuilder("");
            // 创建一个多行的文本输入区域
            JTextArea reflectionField = new JTextArea(7, 30); 
            reflectionField.setLineWrap(true); // 设置自动换行
            reflectionField.setWrapStyleWord(true); // 设置在单词边界处换行

            // 将文本区域添加到滚动面板中
            JScrollPane scrollPane = new JScrollPane(reflectionField);
            f.add(scrollPane);

            JButton submit = new JButton("提交");
            submit.addActionListener(i -> {
                IM.masturbate(reflectionField.getText().replace("\n"," ").replace(",", "，"));
                System.out.println("masturbate");
                f.dispose();
                frame.dispose();
                IM.saveData();
                main(args);
            });
            f.add(submit);

        });
        frame.add(btn_masturbate);

    }
}