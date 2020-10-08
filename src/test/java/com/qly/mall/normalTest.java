package com.qly.mall;

import java.util.Random;

public class normalTest {
    public static void main(String[] args) {
        Random random = new Random();
        Integer id = null;
        StringBuffer stringBuffer = null;
        while (stringBuffer == null) {
            stringBuffer = new StringBuffer();
            for (int i = 0; i < 8; i++) {
                int num = random.nextInt(10);
                if (i == 0) {
                    while (num == 0) num = random.nextInt(10);
                }
                stringBuffer.append(num);
            }
            id = Integer.parseInt(new String(stringBuffer));
        }
        System.out.println(id);
    }
}
