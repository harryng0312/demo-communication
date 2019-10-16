package org.harryng.demo.user.service;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.provider.asymmetric.dh.BCDHPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.harryng.demo.main.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.*;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.*;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Import(Application.class)
public class TestCryptoService {

    static Logger logger = LoggerFactory.getLogger(TestCryptoService.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void init() {
        Provider provider = new BouncyCastleProvider();
        Security.insertProviderAt(provider, 1);
    }

    @Test
    public void testGcm() throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, NoSuchProviderException {
        final int GCM_TAG_LENGTH = 16;
        Cipher cipherGcm = Cipher.getInstance("AES/GCM/NoPadding", "BC");
        Cipher cipherCtr = Cipher.getInstance("AES/CTR/PKCS5Padding", "BC");
        String plainText = "abcdefghijklmnopqrstuvwxyz0123456789";
        byte[] plainTextBytes = plainText.getBytes("UTF-8");
        byte[] keyBin = {(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
                (byte) 0xBB, (byte) 0xBB, (byte) 0xBB, (byte) 0xBB,
                (byte) 0xCC, (byte) 0xCC, (byte) 0xCC, (byte) 0xCC,
                (byte) 0xDD, (byte) 0xDD, (byte) 0xDD, (byte) 0xDD,
                (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
                (byte) 0xBB, (byte) 0xBB, (byte) 0xBB, (byte) 0xBB,
                (byte) 0xCC, (byte) 0xCC, (byte) 0xCC, (byte) 0xCC,
                (byte) 0xDD, (byte) 0xDD, (byte) 0xDD, (byte) 0xDD};
        byte[] ivBin = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
        byte[] ivBin2 = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
        SecretKey key = new SecretKeySpec(keyBin, "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, ivBin);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBin2);

        cipherGcm.init(Cipher.ENCRYPT_MODE, key, gcmParameterSpec);
        cipherCtr.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        byte[] gcmCipherTextBin = cipherGcm.doFinal(plainTextBytes);
        byte[] ctrCipherTextBin = cipherCtr.doFinal(plainTextBytes);
        logger.info("GCM Cipher text:" + Hex.toHexString(gcmCipherTextBin));
        logger.info("CBC Cipher text:" + Hex.toHexString(ctrCipherTextBin));

        cipherGcm.init(Cipher.DECRYPT_MODE, key, gcmParameterSpec);
        cipherCtr.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        byte[] gcmPlainTextBin = cipherGcm.doFinal(gcmCipherTextBin);
        byte[] ctrPlainTextBin = cipherCtr.doFinal(ctrCipherTextBin);
        logger.info("GCM Plain text:" + new String(gcmPlainTextBin));
        logger.info("CBC Plain text:Ø" + new String(ctrPlainTextBin));

    }

    @Test
    public void testECDH() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
        final String engineName = "secp256r1";
        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec(engineName);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDH");
        keyPairGenerator.initialize(ecGenParameterSpec);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        BigInteger primeBigInt = new BigInteger("FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A087"
                + "98E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7"
                + "EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655"
                + "D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E"
                + "86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AAAC42DAD3"
                + "3170D04507A33A85521ABDF1CBA64ECFB850458DBEF0A8AEA71575D060C7DB3970F85A6E1E4C7ABF5AE8CDB0933D71E8C94E04A"
                + "25619DCEE3D2261AD2EE6BF12FFA06D98A0864D87602733EC86A64521F2B18177B200CBBE117577A615D6C770988C0BAD946E20"
                + "8E24FA074E5AB3143DB5BFCE0FD108E4B82D120A93AD2CAFFFFFFFFFFFFFFFF", 16);
        BigInteger g = new BigInteger("2760744755528813491569280443344664518062867499678957699428814759790585782109" +
                "289545837580282363042598314513648511309505283400816264036217499004560890000");
        // client privBigInteger : 21932742400161643848870396084214787957878170707663034274635769975374424529122
        // client pubBigInteger: 4837230114149807833018714943765137483387371962092490598255245109769217115924100337821004649786792798975556709524629345393190190155273150989813797716750821211957496270933919081185935364607584562111026935240390982832778142931361015361483016772063636871615024803886602054544076882181674316313513263412884689790763936592974323274465048117969215141247596875677194454238644335629186278112275680843323440437759868536319729551374433057157731273601596659081104779247326643551101677878751651102828336437873423531114550959993238479246022534143168304519737770509260539204637493401543870581244722468757149148234641182009383547198649414914792994316360564816008649810311007407095244471317106370100453083537096616587947019524320265523202171944788041146816261964531172088615554454285330546154652871383779497399286643232461811395713568594549186645952250390642007163611075889367753828930350453973291058664471774175266899497193033554634953102529
        BigInteger oPubKeyBigInt = new BigInteger("4837230114149807833018714943765137483387371962092490598255245109769217115924100337821004649786792798975556709524629345393190190155273150989813797716750821211957496270933919081185935364607584562111026935240390982832778142931361015361483016772063636871615024803886602054544076882181674316313513263412884689790763936592974323274465048117969215141247596875677194454238644335629186278112275680843323440437759868536319729551374433057157731273601596659081104779247326643551101677878751651102828336437873423531114550959993238479246022534143168304519737770509260539204637493401543870581244722468757149148234641182009383547198649414914792994316360564816008649810311007407095244471317106370100453083537096616587947019524320265523202171944788041146816261964531172088615554454285330546154652871383779497399286643232461811395713568594549186645952250390642007163611075889367753828930350453973291058664471774175266899497193033554634953102529");
        KeyPairGenerator keyPairGeneratorDh = KeyPairGenerator.getInstance("DH");
        DHParameterSpec dhParameterSpec = new DHParameterSpec (primeBigInt, g);
        keyPairGeneratorDh.initialize(dhParameterSpec);
        KeyPair kp = keyPairGeneratorDh.generateKeyPair();
        logger.info("Priv Key:" + ((DHPrivateKey)kp.getPrivate()).getX());
        logger.info("Pub Key:" + ((DHPublicKey)kp.getPublic()).getY());
        //common: 3988192159103559091805965450678475727678952378055996908836163778222493643498198261800818623052993842570648537376458178160391755165511190315218392290896114309218477006277894092252471298729446516682022450443938440637050486211719205079778098551862110162558128851518965398937684479482345901071290328605834665393250149589741779296702252031011213364216318979197247350145295277068488219852379775068221371371189536310859380401567593075354271146311926320806026713690062600280636345262085610708604844895008182140841087431095042845612110533044277575094856063840051419234351616314506119345256398287010799080184237357717531536490270515677752949121659131025634864268091408619166287671391837580727481288612227234703390812105830567766428289433714604728318829979756782726185446597762539768275843219700936757369059949561562852892955246445256132773890765320176260580221083069026223404542474593894589215482728580964141321806082870715417750960072
        SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance("DH");
        DHPublicKey oDhPubKey = new BCDHPublicKey(subjectPublicKeyInfo);
        KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
        keyAgreement.init(kp.getPrivate());
        keyAgreement.doPhase(oDhPubKey, true);
    }
}
