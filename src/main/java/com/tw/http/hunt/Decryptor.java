package com.tw.http.hunt;

import java.util.HashMap;
import java.util.Map;

public class Decryptor {

    private String encryptedMessage;

    private Long key;

    public Decryptor(String encryptedMessage, Long key) {
        this.encryptedMessage = encryptedMessage;
        this.key = key;
    }

    public String decrypt() {
        Map<Integer, Character> charMap = new HashMap<Integer, Character>();
        Map<Character, Integer> numMap = new HashMap<Character, Integer>();

        for (int i = 0; i < 26; i++) {
            charMap.put(i, (char) ('A' + i));
            numMap.put((char) ('A' + i), i);
        }
        char[] chars = encryptedMessage.toCharArray();
        char[] dec = new char[chars.length];

        int key = this.key.intValue();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (numMap.get(aChar) == null) {
                dec[i] = aChar;
                continue;
            }

            int num = numMap.get(aChar) - key;
            if (num < 0)
                num += 26;
            dec[i] = charMap.get(num);
        }
        return new String(dec);
    }
}