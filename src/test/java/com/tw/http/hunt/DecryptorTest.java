package com.tw.http.hunt;

import org.junit.Assert;
import org.junit.Test;

public class DecryptorTest {

    @Test
    public void testDecrypt() {
        Decryptor decryptor = new Decryptor("F KFRTZX JCUQTWJW TSHJ XFNI, YMFY YMJ JCYWFTWINSFWD NX NS BMFY BJ IT, STY BMT BJ FWJ. LT JCUQTWJ!",
                5l);
        String exp = "A FAMOUS EXPLORER ONCE SAID, THAT THE EXTRAORDINARY IS IN WHAT WE DO, NOT WHO WE ARE. GO EXPLORE!";
        Assert.assertEquals(exp, decryptor.decrypt());

        decryptor = new Decryptor("GOVV GO MKX NY SD WI GKI, YB GO MKX KVV MYWO LKMU SX DSWO PYB DRO XOHD KVSQXWOXD KXN IYE'BO GOVMYWO DY DBI KXN USVV WO DROX, SX YR, CKI, KXYDROB 5,000 IOKBC?",
                10l);
        System.out.println(decryptor.decrypt());
    }
}