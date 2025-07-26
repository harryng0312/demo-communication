package org.harryng.demo.security.signalprotocol;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class SignalDemo {
    private static final Logger logger = LoggerFactory.getLogger(SignalDemo.class);

    @Test
    public void testSignalProtocol() throws Exception {
        // Khởi tạo server
        MockKeyServer keyServer = new MockKeyServer();

        // Khởi tạo Alice và Bob
        SignalClient alice = new SignalClient("alice", 1, keyServer);
        SignalClient bob = new SignalClient("bob", 1, keyServer);

        // Alice xây dựng phiên với Bob
        alice.buildSession("bob", 1);

        // Danh sách tin nhắn
        String[] aliceMessages = {"Hello Bob!", "How are you?", "This is a secure message!"};
        String[] bobMessages = {"Hi Alice!", "I'm good, thanks!", "Secure messaging is cool!"};

        // Alice gửi nhiều tin nhắn cho Bob
        logger.info("Alice sending messages to Bob:");
        for (String message : aliceMessages) {
            byte[] ciphertext = alice.encryptMessage(message, "bob", 1);
            String decrypted = bob.decryptMessage(ciphertext, "alice", 1);
            logger.info("Alice sent: {}, Bob received: {}", message, decrypted);
        }

        // Bob xây dựng phiên với Alice (thực tế không cần vì phiên đã được thiết lập qua PreKeySignalMessage)
        bob.buildSession("alice", 1);

        // Bob gửi nhiều tin nhắn cho Alice
        logger.info("Bob sending messages to Alice:");
        for (String message : bobMessages) {
            byte[] ciphertext = bob.encryptMessage(message, "alice", 1);
            String decrypted = alice.decryptMessage(ciphertext, "bob", 1);
            logger.info("Bob sent: {}, Alice received: {}", message, decrypted);
        }
    }
}
