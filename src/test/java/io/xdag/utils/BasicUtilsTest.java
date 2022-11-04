/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020-2030 The XdagJ Developers
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.xdag.utils;

import static io.xdag.utils.BasicUtils.address2Hash;
import static io.xdag.utils.BasicUtils.crc32Verify;
import static io.xdag.utils.BasicUtils.hash2Address;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.xdag.utils.exception.XdagOverFlowException;
import java.math.BigDecimal;
import org.apache.tuweni.bytes.Bytes32;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

public class BasicUtilsTest {

    @Test
    public void TestXdag2amount() {

        BigDecimal a = BigDecimal.valueOf(972.8);
        assertEquals(4178144185549L, BasicUtils.xdag2amount(a.doubleValue()));

        BigDecimal b = BigDecimal.valueOf(51.2);
        assertEquals(219902325556L, BasicUtils.xdag2amount(b.doubleValue()));

        BigDecimal c = BigDecimal.valueOf(100);
        assertEquals(429496729600L, BasicUtils.xdag2amount(c.doubleValue()));
    }

    @Test(expected = XdagOverFlowException.class)
    public void TestXdag2amountOverflow() {
        double d = -1.3;
        BasicUtils.xdag2amount(d);
    }

    @Test
    public void TestAmount2xdag() {
        long a = 4178144185548L;
        // 3CC CCCC CCCD?
        assertEquals(972.799999999814, BasicUtils.amount2xdag(a), 0.0);

        long b = 219902325556L;
        // 3333333334
        assertEquals(51.200000000186, BasicUtils.amount2xdag(b), 0.0);

//        long c = 4398046511104L;
        // 400 0000 0000?
        assertEquals(1.490000000224, BasicUtils.amount2xdag(6399501272L), 0.0);
        // Xfer:transferred   44796508898   10.430000000 XDAG to the address 0000002f28322e9d817fd94a1357e51a. 10.43
        assertEquals(10.430000000168, BasicUtils.amount2xdag(44796508898L), 0.0);

        // Xfer:transferred   42949672960   10.000000000 XDAG to the address 0000002f28322e9d817fd94a1357e51a. 10
        assertEquals(10.0, BasicUtils.amount2xdag(42949672960L), 0.0);

        // Xfer:transferred 4398046511104 1024.000000000 XDAG to the address 0000002f28322e9d817fd94a1357e51a. 1024
        assertEquals(1024.0, BasicUtils.amount2xdag(4398046511104L), 0.0);
    }

//    @Test(expected = XdagOverFlowException.class)
//    public void TestAmount2xdagOverflow() {
//        long a = -1;
//        BasicUtils.amount2xdag(a);
//    }

    @Test
    public void xdag_diff2logTest() {
//        double res = BasicUtils.xdag_diff2log(
//                BasicUtils.getDiffByHash(Bytes32.fromHexString("00000021c468294605ebcf8ce9462026caf42941ca82373e6ca5802d1fe339c8"));
//        System.out.println(res);

    }

    @Test
    public void xdag_hashrate() {
//        BigInteger diff = BasicUtils.getDiffByHash(
//                Hex.decode("00000021c468294605ebcf8ce9462026caf42941ca82373e6ca5802d1fe339c8"));
    }

    @Test
    public void xdag_log_difficulty2hashrateTest() {
//        double res = BasicUtils.xdag_log_difficulty2hashrate(42.79010346356279);
        //System.out.println("this is res :" + res);
    }

    @Test
    public void testHash2Address() {
        String news = "42cLWCMWZDKPZM8WJfpmI7Lbe3p83U2l";
        String originhash = "4aa1ab5742feb010a54ddd7c7a7bdbb22366fa2516cf648f32641623580b67e3";
        Bytes32 hash1 = Bytes32.fromHexString(originhash);
        assertEquals(hash2Address(hash1), news);
    }

    @Test
    public void testAddress2Hash() {
        String news = "42cLWCMWZDKPZM8WJfpmI7Lbe3p83U2l";
        String originhashlow = "0000000000000000a54ddd7c7a7bdbb22366fa2516cf648f32641623580b67e3";
        Bytes32 hashlow = address2Hash(news);
        assertEquals(hashlow.toUnprefixedHexString(), originhashlow);
    }

    @Test
    public void testCrc() {
        String reque = "8b050002a353147e3853050000000040ffffc63c7b0100000000000000000000"
                + "c44704e82cf458076643a426a6d35cdb6ff1c168866f00e40000000000000000"
                + "c44704e82cf458076643a426a6d35cdb6ff1c168866f00e40000000000000000"
                + "a31e6283a9f5da4a4f56c3503f3ab27c776fbb2f89f67e20cd875ac0523fe613"
                + "08e2eda6e9eb9b754d96128a5676b52e6cc9f2a2e102e9896fea58864c489c6b"
                + "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000097d1511ec1723d118fe19c06ab4ca825a982282a1733b508b6b56f73cc4adeb";

        byte[] uncryptData = Hex.decode(reque);
        int crc = BytesUtils.bytesToInt(uncryptData, 4, true);
        System.arraycopy(BytesUtils.longToBytes(0, true), 0, uncryptData, 4, 4);
        assertTrue(crc32Verify(uncryptData, crc));
    }
}
